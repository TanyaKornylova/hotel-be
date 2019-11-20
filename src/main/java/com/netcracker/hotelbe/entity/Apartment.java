package com.netcracker.hotelbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.hotelbe.entity.enums.ApartmentStatus;
import com.netcracker.hotelbe.utils.PostgreSQLEnumType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Apartments",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Apartment implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private List<ApartmentPrice> apartmentPrices;

    @JsonIgnore
    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY)
    private List<UnavailableApartment> unavailableApartments;

    public Apartment() {
    }

    public Apartment(int roomNumber, String photo, String description, ApartmentStatus status,
                     ApartmentClass apartmentClass, List<ApartmentPrice> apartmentPrices,
                     List<UnavailableApartment> unavailableApartments) {
        this.roomNumber = roomNumber;
        this.photo = photo;
        this.description = description;
        this.status = status;
        this.apartmentClass = apartmentClass;
        this.apartmentPrices = apartmentPrices;
        this.unavailableApartments = unavailableApartments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApartmentStatus getStatus() {
        return status;
    }

    public void setStatus(ApartmentStatus status) {
        this.status = status;
    }

    public ApartmentClass getApartmentClass() {
        return apartmentClass;
    }

    public void setApartmentClass(ApartmentClass apartmentClass) {
        this.apartmentClass = apartmentClass;
    }

    public List<ApartmentPrice> getApartmentPrices() {
        return apartmentPrices;
    }

    public void setApartmentPrices(List<ApartmentPrice> apartmentPrices) {
        this.apartmentPrices = apartmentPrices;
    }

    public List<UnavailableApartment> getUnavailableApartments() {
        return unavailableApartments;
    }

    public void setUnavailableApartments(List<UnavailableApartment> unavailableApartments) {
        this.unavailableApartments = unavailableApartments;
    }
}
