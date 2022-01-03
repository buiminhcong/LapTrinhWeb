package com.example.ltw_longptit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class BenhNhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bn")
    private int id;
    @Column
    private String ten;
    private String ngaySinh;
    private String diaChi;
    private String sdt;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benhNhan", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<Kham> listKham = new ArrayList<>();

}