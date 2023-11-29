package com.example.twodatabasetest.first.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "first_table")
public class FirstEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String data;

//    @Column(unique = true)
    private Integer count;

    public FirstEntity(String data, Integer count) {
        this.data = data;
        this.count = count;
    }
}
