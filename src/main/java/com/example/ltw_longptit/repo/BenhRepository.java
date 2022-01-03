package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.Benh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BenhRepository extends JpaRepository<Benh, Long> {
    //select list thuoc by keyword
    @Query(value = "SELECT * FROM benh where LOWER(ten)  like %:key%", nativeQuery = true)
    List<Benh> timKiemBenhByKey(String key);
}
