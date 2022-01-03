package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.BacSi;
import com.example.ltw_longptit.model.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BenhNhanRepository extends JpaRepository<BenhNhan, Integer> {
    @Query(value = "SELECT * from benh_nhan where LOWER(ten) like %:keyword%" , nativeQuery = true)
    public List<BenhNhan> searchBenhNhan(String keyword);
}
