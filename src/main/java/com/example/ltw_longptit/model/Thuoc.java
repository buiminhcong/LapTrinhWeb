package com.example.ltw_longptit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table
public class Thuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_thuoc")
    private long id;

    @Column
    @Min(value = 0, message = "Giá phải lớn hơn 0")
    private int gia;

    @Column
    @NotBlank(message = "Thiếu Tên Thuốc")
    private String ten;

    @Column
    @NotBlank(message = "Thiếu Tên Loại Thuốc")
    private String loaiThuoc;
    private String congDung;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thuoc", fetch = FetchType.LAZY)
//    @JsonBackReference
//    private List<ThuocSuDung> listThuocSd = new ArrayList<>();
}
