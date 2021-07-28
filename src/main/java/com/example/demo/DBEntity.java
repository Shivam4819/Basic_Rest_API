package com.example.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Slf4j
@Data
public class DBEntity {
    @Id
    private Integer shortkey;
    private String urls;
    private int count;
}
