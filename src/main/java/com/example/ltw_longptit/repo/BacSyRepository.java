package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.BacSi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BacSyRepository extends JpaRepository<BacSi, Integer> {
    @Query(value = "SELECT * from bac_si where LOWER(bacsy_ten) like %:keyword%" , nativeQuery = true)
    public List<BacSi> searchBacSy(String keyword);

    @Transactional
    @Modifying
    @Query(value = "delete from kham where id_bs=:id", nativeQuery = true)
    public void deleteThuocSuDungTheoDonThuoc(String id);

}
