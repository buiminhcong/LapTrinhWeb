package com.example.ltw_longptit.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Kham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kham")
    private int id;

    @Column
    private String datein;

    @Column
    private String dateout;

    private String status;

    private String type;

    private int tongTien;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_bs")
    BacSi bacSy;

    @ManyToOne()
    @JoinColumn(name = "id_bn")
    @OnDelete(action = OnDeleteAction.CASCADE)
    BenhNhan benhNhan;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_benh")
    Benh benh;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kham", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<HoTro> listHoTro = new ArrayList<>();
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kham", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private DonThuoc donthuoc = new DonThuoc();



}
