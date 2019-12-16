package com.netcracker.hotelbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "ApartmentClass",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApartmentClass implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_class")
    private String nameClass;

    @Column(name = "number_of_rooms")
    @Min(value = 1, message = "Number of rooms cant be less then 1")
    private int numberOfRooms;

    @Column(name = "number_of_couchette")
    @Min(value = 1, message = "Number of couchette cant be less then 1")
    private int numberOfCouchette;

    @JsonIgnore
//    @ToString.Exclude
    @OneToMany(mappedBy = "apartmentClass", fetch = FetchType.LAZY)
    private List<Apartment> apartments;

    @JsonIgnore
//    @ToString.Exclude
    @OneToMany(mappedBy = "apartmentClass", fetch = FetchType.LAZY)
    private List<ApartmentPrice> apartmentPrices;

    @JsonIgnore
//    @ToString.Exclude
    @OneToMany(mappedBy = "apartmentClass", fetch = FetchType.LAZY)
    private List<Booking> bookings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentClass that = (ApartmentClass) o;
        return numberOfRooms == that.numberOfRooms &&
                numberOfCouchette == that.numberOfCouchette &&
                Objects.equals(nameClass, that.nameClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameClass, numberOfRooms, numberOfCouchette);
    }
}
