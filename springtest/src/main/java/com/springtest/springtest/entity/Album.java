package com.springtest.springtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String title;
    private Long count;
}
