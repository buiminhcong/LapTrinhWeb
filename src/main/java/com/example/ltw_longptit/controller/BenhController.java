package com.example.ltw_longptit.controller;

import com.example.ltw_longptit.model.Benh;
import com.example.ltw_longptit.service.BenhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/benh", produces = "application/json")
@CrossOrigin(origins = "*")
public class BenhController {

    @Autowired
    BenhService benhService;

    //get all
    @GetMapping("/list-benh")
    public List<Benh> getAllBenh(){
        return benhService.getAllBenh();
    }

    //get By ID
    @GetMapping("/get-benh/{id}")
    public Optional<Benh> getBenhByID(@PathVariable("id") long id){
        return benhService.getBenhByID(id);
    }

    //add benh

    @PostMapping("/add-benh")
    public Benh addThuoc(@RequestBody @Valid Benh benh){
        return benhService.addBenh(benh);
    }

    //update
    @PutMapping("/update-benh/{id}")
    public Benh updateThuoc(@PathVariable long id, @Valid @RequestBody Benh benh){
        return benhService.updateBenh(id, benh);
    }

    //delete
    @DeleteMapping("/delete-benh/{id}")
    public void deleteBenh( @PathVariable("id") long id){
        this.benhService.deleteBenh(id);
    }


    //search list benh
    @GetMapping("/search-list/{key}")
    public List<Benh> searchListBenh( @PathVariable("key") String key){
        return benhService.searchListBenh(key);
    }


}
