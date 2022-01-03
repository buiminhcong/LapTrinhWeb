package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.Yta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface YtaRepository extends JpaRepository<Yta, Integer> {

    @Query(value = "SELECT yta from Yta yta where LOWER(yta.ytaTen) like %:keyword%")
    public List<Yta> searchYta( String keyword);
}
