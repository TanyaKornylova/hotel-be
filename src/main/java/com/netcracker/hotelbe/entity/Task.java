package com.netcracker.hotelbe.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.netcracker.hotelbe.entity.enums.TaskStatus;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp start;

    @Column(name = "end_date")
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp end;

    @Column(name = "accept_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp accept;

    @Column(name = "complete_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp complete;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "task_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private TaskStatus status = TaskStatus.OPEN;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Staff creator;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    @NotNull
    private Staff executor;



}
