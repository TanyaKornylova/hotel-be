package com.netcracker.hotelbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "BookingAddServices",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class BookingAddServices implements Serializable {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "service_name")
    private String serviceName;

    @NotNull
    @Column(name = "price")
    private int price;

    @JsonIgnore
    @OneToMany(mappedBy = "bookingAddServices", fetch = FetchType.LAZY)
    private List<BookingAddServicesShip> bookingAddServicesShips;
}
