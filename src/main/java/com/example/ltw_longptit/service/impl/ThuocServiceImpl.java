package com.example.ltw_longptit.service.impl;

import com.example.ltw_longptit.model.Thuoc;
import com.example.ltw_longptit.repo.ThuocRepository;
import com.example.ltw_longptit.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuocServiceImpl implements ThuocService {

    @Autowired
    ThuocRepository thuocRepository;

    //get
    @Override
    public List<Thuoc> getAllThuoc() {
        return thuocRepository.findAll();
    }

    @Override
    public Optional<Thuoc> getThuocByID(long id) {
        Optional<Thuoc> opt = thuocRepository.findById(id);
        return opt;
    }

    //post
    @Override
    public Thuoc addThuoc(Thuoc thuoc) {
        return thuocRepository.save(thuoc);
    }

    //update
    @Override
    public Thuoc updateThuoc(long id, Thuoc thuoc) {
        Thuoc thuoc1 = thuocRepository.getById(id);

        thuoc1.setId(id);
        thuoc1.setTen(thuoc.getTen());
        thuoc1.setGia(thuoc.getGia());
        thuoc1.setLoaiThuoc(thuoc.getLoaiThuoc());
        thuoc1.setCongDung(thuoc.getCongDung());

        return thuocRepository.save(thuoc1);
    }

    //delete
    @Override
    public void deleteThuoc(long id) {
        Thuoc t = thuocRepository.getById(id);
        if (t != null) {
            thuocRepository.delete(t);
        } else {
            throw new RuntimeException("id not exist");
        }

    }


    @Override
    public List<Thuoc> searchListThuoc(String key) {
        List<Thuoc> list = thuocRepository.timKiemThuocByKey(key);
        return list;
    }
}
