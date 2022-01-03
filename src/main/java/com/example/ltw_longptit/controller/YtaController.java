package com.example.ltw_longptit.controller;


import com.example.ltw_longptit.model.Yta;
import com.example.ltw_longptit.repo.YtaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/yta", produces = "application/json")
@CrossOrigin(origins = "*")
public class YtaController {
    @Autowired
    private YtaRepository ytaRepository;
    @Autowired
    public YtaController(YtaRepository ytaRepository) {
        this.ytaRepository = ytaRepository;
    }

    @GetMapping
    public Iterable<Yta> getAllYta() {
        return ytaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Yta getYtaById(@PathVariable("id") int id) {
        Optional<Yta> yTa = ytaRepository.findById(id);
        if (yTa.isPresent()) {
            return yTa.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public Yta createYta(@RequestBody Yta yta) {
        System.out.println(yta.getYtaTen());
        return ytaRepository.save(yta);
    }
    //what the fuck
    @PutMapping("/{id}")
    public Yta updateYta(@RequestBody Yta yta,@PathVariable("id") int id) {
        Yta yta1 = ytaRepository.getById(id);
        yta1.setYtaTen(yta.getYtaTen());
        yta1.setYtaDiaChi(yta.getYtaDiaChi());
        yta1.setYtaSDT(yta.getYtaSDT());
        yta1.setYtaThamNien(yta.getYtaThamNien());
        yta1.setId(id);
        yta1.setYtaNgaySinh(yta.getYtaNgaySinh());
        yta1.setYtaCMT(yta.getYtaCMT());
        return ytaRepository.save(yta1);
    }

    @DeleteMapping("/{id}")
    public void deleteYta(@PathVariable("id") int id) {
           try {
               ytaRepository.deleteById(id);
           }catch (Exception e) {
               System.out.println(e);
           };
    }

    @GetMapping("/search/{keyword}")
    public Iterable<Yta> searchNurse(@PathVariable("keyword") String keyword) {
        return ytaRepository.searchYta(keyword);
    }






}
