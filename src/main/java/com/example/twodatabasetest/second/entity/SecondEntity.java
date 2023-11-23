package com.example.twodatabasetest.second.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "second")
public class SecondEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    protected Long id;

    private String data;
    @Column(unique = true)
    private Integer count;

    public SecondEntity(String data, Integer count) {
        this.data = data;
        this.count = count;
    }
}
