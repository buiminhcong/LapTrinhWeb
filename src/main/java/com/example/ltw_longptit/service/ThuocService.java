package com.example.ltw_longptit.service;

import com.example.ltw_longptit.model.Thuoc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ThuocService {

    //getAllThuoc
    List<Thuoc> getAllThuoc();

    //get Thuoc by id
    Optional<Thuoc> getThuocByID(long id);

    //post Thuoc
    Thuoc addThuoc(Thuoc thuoc);

    // update
    Thuoc updateThuoc(long id, Thuoc thuoc);

    //delete
    void deleteThuoc(long id);

    //search list thuoc
    List<Thuoc> searchListThuoc(String key);


}