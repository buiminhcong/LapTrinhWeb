package com.example.ltw_longptit.service.impl;

import com.example.ltw_longptit.model.Benh;
import com.example.ltw_longptit.repo.BenhRepository;
import com.example.ltw_longptit.service.BenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenhServiceImpl implements BenhService {

    @Autowired
    BenhRepository benhRepository;

    //get
    @Override
    public List<Benh> getAllBenh() {
        return benhRepository.findAll();
    }

    @Override
    public Optional<Benh> getBenhByID(long id) {
        Optional<Benh> opt  = benhRepository.findById(id);
        return opt;
    }

    //post
    @Override
    public Benh addBenh(Benh benh) {
        return benhRepository.save(benh);
    }

    //update
    @Override
    public Benh updateBenh(long id, Benh benh) {

        Benh b = benhRepository.getById(id);

        b.setId(id);
        b.setTen(benh.getTen());
        b.setMoTa(benh.getMoTa());

        return benhRepository.save(b);
    }

    //delete
    @Override
    public void deleteBenh(long id) {
        Benh t = benhRepository.getById(id);
        if(t != null){
            benhRepository.delete(t);
        }else {
            throw new RuntimeException("id not exist");
        }

    }


    @Override
    public List<Benh> searchListBenh(String key) {
        List<Benh> list = benhRepository.timKiemBenhByKey(key);
        return  list;
    }

}
