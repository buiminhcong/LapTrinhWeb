package com.example.ltw_longptit.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table
public class ThuocSuDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thuoc_sd")
    private int id;


    @Column
    private int soLuong;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_thuoc")//name="tên cột khóa ngoại"
    Thuoc thuoc;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_donthuoc")//name="tên cột khóa ngoại"
    DonThuoc donThuoc;


}
