package com.netcracker.hotelbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.hotelbe.entity.enums.ApartmentStatus;
import com.netcracker.hotelbe.utils.PostgreSQLEnumType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Apartments",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "room_number"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Apartment implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Type( type = "pgsql_enum" )
    private ApartmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="class_room")
    private ApartmentClass apartmentClass;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY)
    private List<UnavailableApartment> unavailableApartments;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY)
    private List<Booking> bookings;

}
