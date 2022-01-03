package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.Thuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuocRepository extends JpaRepository<Thuoc, Long> {


    //select list thuoc by keyword
    @Query(value = "SELECT * FROM thuoc where LOWER(ten) like %:key%", nativeQuery = true)
    List<Thuoc> timKiemThuocByKey(String key);

}
