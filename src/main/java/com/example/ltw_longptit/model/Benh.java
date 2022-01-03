package com.example.ltw_longptit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table
public class Benh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_benh")
    private long id;

    @Column
    @NotBlank(message = "Tên bệnh không được để trống!")
    private String ten;

    @Column
    private String moTa;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benh", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<Kham> listKham = new ArrayList<>();
}
