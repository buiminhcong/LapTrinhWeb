package com.example.ltw_longptit.controller;


import com.example.ltw_longptit.model.BenhNhan;
import com.example.ltw_longptit.repo.BenhNhanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/benhnhan", produces = "application/json")
@CrossOrigin(origins = "*")
public class BenhNhanController {
    private BenhNhanRepository benhNhanRepo;

    public BenhNhanController(BenhNhanRepository benhNhanRepo) {
        this.benhNhanRepo = benhNhanRepo;
    }

    @GetMapping
    public Iterable<BenhNhan> getAllBenhNhan() {
        return benhNhanRepo.findAll();
    }

    @GetMapping("/{id}")
    public BenhNhan getBacSyById(@PathVariable("id") int id) {
        Optional<BenhNhan> benhNhan = benhNhanRepo.findById(id);
        if (benhNhan.isPresent()) {
            return benhNhan.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public BenhNhan createBacSy(@RequestBody BenhNhan benhNhan) {
        return benhNhanRepo.save(benhNhan);
    }

    @PutMapping("/{id}")
    public BenhNhan updateBacSy(@RequestBody BenhNhan benhNhan, @PathVariable("id") int id) {
        return benhNhanRepo.save(benhNhan);
    }

    @DeleteMapping("/{id}")
    public void deleteBacSy(@PathVariable("id") int id) {
        try {
            benhNhanRepo.deleteById(id);
        }catch (Exception e) {
            System.out.println(e);
        };
    }

    @GetMapping("/search/{keyword}")
    public Iterable<BenhNhan> searchBacSy(@PathVariable("keyword") String keyword) {
        return benhNhanRepo.searchBenhNhan(keyword);
    }

}
