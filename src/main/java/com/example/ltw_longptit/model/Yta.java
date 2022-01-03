package com.example.ltw_longptit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table

public class Yta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_yta")
    private int id;
    @Column
    private int ytaThamNien;
    @Column
    private String ytaCMT;
    @Column
    private String ytaTen;
    @Column
    private String ytaSDT;
    @Column
    private String ytaDiaChi;
    @Column
    private String ytaNgaySinh;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "yta", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<HoTro> listHoTro = new ArrayList<>();
}
