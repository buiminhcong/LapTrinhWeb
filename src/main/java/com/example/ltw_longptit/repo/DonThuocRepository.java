package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.DonThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DonThuocRepository extends JpaRepository<DonThuoc, Integer> {

    @Query(value = "select * from don_thuoc where id_kham =:id",nativeQuery = true)
    public DonThuoc getDonThuocTheoIdKham(String id);

}
