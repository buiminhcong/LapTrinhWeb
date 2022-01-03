package com.example.ltw_longptit.controller;

import com.example.ltw_longptit.model.BacSi;
import com.example.ltw_longptit.repo.BacSyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/bacsi", produces = "application/json")
@CrossOrigin(origins = "*")
public class BacSyController {
    private BacSyRepository bacsyRepository;

    @Autowired
    public BacSyController(BacSyRepository bacsyRepository) {
        this.bacsyRepository = bacsyRepository;
    }

    @GetMapping
    public Iterable<BacSi> getAllBacSy() {
        return bacsyRepository.findAll();
    }

    @GetMapping("/{id}")
    public BacSi getBacSyById(@PathVariable("id") int id) {
        Optional<BacSi> bacSy = bacsyRepository.findById(id);
        if (bacSy.isPresent()) {
            return bacSy.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public BacSi createBacSy(@RequestBody BacSi bacsy) {
        return bacsyRepository.save(bacsy);
    }

    @PutMapping("/{id}")
    public BacSi updateBacSy(@RequestBody BacSi bacsy, @PathVariable("id") int id) {
        BacSi bacsi1 = bacsyRepository.getById(id);
        bacsi1.setBacsyTen(bacsy.getBacsyTen());
        bacsi1.setBacsyDiaChi(bacsy.getBacsyDiaChi());
        bacsi1.setBacsySDT(bacsy.getBacsySDT());
        bacsi1.setBacsyThamNien(bacsy.getBacsyThamNien());
        bacsi1.setId(id);
        bacsi1.setBacsyNgaySinh(bacsy.getBacsyNgaySinh());
        bacsi1.setBacsyCMT(bacsy.getBacsyCMT());
        bacsi1.setBacsyBacNghe(bacsy.getBacsyBacNghe());
        bacsi1.setBacsyChuyenMon(bacsy.getBacsyChuyenMon());
        bacsi1.setBacsyTrinhDoDaoTao(bacsy.getBacsyTrinhDoDaoTao());
        return bacsyRepository.save(bacsi1);
    }

    @DeleteMapping("/{id}")
    public void deleteBacSy(@PathVariable("id") int id) {
        try {
            bacsyRepository.deleteById(id);
        }catch (Exception e) {
            System.out.println(e);
        };
    }

    @GetMapping("/search/{keyword123}")
    public Iterable<BacSi> searchBacSy(@PathVariable("keyword123") String keyword) {
        return bacsyRepository.searchBacSy(keyword);
    }


}
