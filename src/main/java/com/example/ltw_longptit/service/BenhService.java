package com.example.ltw_longptit.service;

import com.example.ltw_longptit.model.Benh;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BenhService {

    //getAllBenh
    List<Benh> getAllBenh();

    //get Benh by id
    Optional<Benh> getBenhByID(long id);

    //post Thuoc
    Benh addBenh(Benh benh);

    // update
    Benh updateBenh(long id, Benh benh);

    //delete
    void deleteBenh(long id);


    //search list Benh
    List<Benh> searchListBenh(String key);


}
