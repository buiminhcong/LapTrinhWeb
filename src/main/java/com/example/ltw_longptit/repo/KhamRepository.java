package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.BenhNhan;
import com.example.ltw_longptit.model.Kham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KhamRepository extends JpaRepository<Kham, Integer> {

    @Query(value = "select * from kham where datein like %:keyword% and status = 'mắc bệnh'",nativeQuery = true)
    public List<Kham> getAllKhamByThangMacBenh(String keyword);

    @Query(value = "select * from kham where datein like %:keyword%",nativeQuery = true)
    public List<Kham> getAllKhamByThang(String keyword);

    @Query(value = "select * from kham where datein like %:keyword% and id_benh = :id",nativeQuery = true)
    public List<Kham> getAllKhamByBenh(String keyword, String id );

    @Query(value = "select distinct(id_benh) from kham where datein like %:keyword% ",nativeQuery = true)
    public List<Integer> getBenhTrongThang(String keyword);

    @Query(value = "select distinct(id_bn) from kham where datein like %:keyword% and id_bs = :id  and status = 'mắc bệnh'",nativeQuery = true)
    public List<Integer> getAllKhamByBacSy(String keyword, String id);

    @Query(value = "select * from kham where datein like %:keyword% and id_bn = :id",nativeQuery = true)
    public List<Kham> getAllKhamByBenhNhan( String keyword , String id);

    @Query(value = "select * from kham where id_bs = :id_bs and id_bn = :id_bn limit 1",nativeQuery = true)
    public Kham getKham( String id_bn, String id_bs);

    @Query(value = "select * from kham where id_bn =:id",nativeQuery = true)
    public List<Kham> getKhamTheoIdBenhNhan(String id);

    


}
