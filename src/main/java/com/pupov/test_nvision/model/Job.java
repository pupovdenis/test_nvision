package com.pupov.test_nvision.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "job")
@JsonIgnoreProperties(ignoreUnknown = false)
public class Job {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "user")
    private String user;

    @Column(name = "device")
    private String device;

    @Column(name = "amount")
    private int amount;

    @Column(name = "time_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time = new Date();
}
