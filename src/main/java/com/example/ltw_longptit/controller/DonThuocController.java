package com.example.ltw_longptit.controller;


import com.example.ltw_longptit.model.DonThuoc;
import com.example.ltw_longptit.repo.DonThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/donthuoc", produces = "application/json")
@CrossOrigin(origins = "*")
public class DonThuocController {

    @Autowired
    private DonThuocRepository donThuocRepository;
    @Autowired
    public DonThuocController(DonThuocRepository donThuocRepository) {
        this.donThuocRepository = donThuocRepository;
    }

    @GetMapping()
    public Iterable<DonThuoc> getAllHoaDonThuoc() {
        return donThuocRepository.findAll();
    }

    @GetMapping("/{id}")
    public DonThuoc getHoaDonById(@PathVariable("id") int id) {
        Optional<DonThuoc> optionalHoaDon = donThuocRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            return optionalHoaDon.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public DonThuoc postHoaDon(@RequestBody DonThuoc donThuoc) {
        return donThuocRepository.save(donThuoc);
    }

    @PutMapping("/{id}")
    public DonThuoc putHoaDon(@RequestBody DonThuoc donThuoc) {
        return donThuocRepository.save(donThuoc);
    }

    @DeleteMapping("/{id}")
    public void deleteHoaDon(@PathVariable("id") Integer id) {
        try {
            donThuocRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @GetMapping("/get-donthuoc-id-kham/{id}")
    public DonThuoc getDocThuocByIdKham(@PathVariable("id") String id) {
        return donThuocRepository.getDonThuocTheoIdKham(id);
    }

    @DeleteMapping("/delete-donthuoc-id-kham/{id}")
    public void deleteThuocTheoIdKham(@PathVariable("id") String id) {
        DonThuoc donThuoc = donThuocRepository.getDonThuocTheoIdKham(id);
        donThuocRepository.deleteById(donThuoc.getId());
    }
}
