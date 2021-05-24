package com.example.kim_s_cafe.model.reservation;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Table(name="reservation")
@Entity
public class reservationvo {
    
    @Id
    @Column(name="rid",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)///테이블은 여기서 만들고 mysql에서 오토인크리먼트하면된다 자동으로 들어기도하네 
    private int rid;

    @Column(name="persons", nullable=false)
    private int persons;

    @Column(name="rname",nullable = false)
    private String rname;

    @Column(name="remail",nullable = false)
    private String remail;

    @Column(name = "created",nullable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name="reservationday",nullable = false)
    private Timestamp reservationday;

    @Column(name="seat",nullable = false)
    private String seat;
}
