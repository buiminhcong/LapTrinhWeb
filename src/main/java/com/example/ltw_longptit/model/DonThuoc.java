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
public class DonThuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donthuoc")
    private int id;


    @OneToOne
    @JoinColumn(name = "id_kham") //name="tên cột khóa ngoại"
    @OnDelete(action = OnDeleteAction.CASCADE)
    Kham kham;

    @Column
    private int tongTien;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donThuoc", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<ThuocSuDung> listThuocsd = new ArrayList<>();
}
