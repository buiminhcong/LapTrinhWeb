package com.example.ltw_longptit.controller;

import com.example.ltw_longptit.model.Thuoc;
import com.example.ltw_longptit.service.ThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/thuoc", produces = "application/json")
public class ThuocController {

    @Autowired
    ThuocService thuocService;

    //get all
    @GetMapping("/list-thuoc")
    public List<Thuoc> getAllThuoc(){
        return thuocService.getAllThuoc();
    }

    //get By ID
    @GetMapping("/get-thuoc/{id}")
    public Optional<Thuoc> getThuocByID(@PathVariable("id") long id){
        return thuocService.getThuocByID(id);
    }

    //add thuoc

    @PostMapping("/add-thuoc")
    public Thuoc addThuoc(@RequestBody @Valid Thuoc thuoc){
        return thuocService.addThuoc(thuoc);
    }

    //update
    @PutMapping("/update-thuoc/{id}")
    public Thuoc updateThuoc(@PathVariable long id,@Valid @RequestBody Thuoc thuoc){
        return thuocService.updateThuoc(id, thuoc);
    }

    //delete
    @DeleteMapping("/delete-thuoc/{id}")
    public void deleteThuoc( @PathVariable("id") long id){
        this.thuocService.deleteThuoc(id);
    }


    //search list thuoc
    @GetMapping("/search-list/{key}")
    public List<Thuoc> searchListThuoc( @PathVariable("key") String key){
        return thuocService.searchListThuoc(key);
    }

}
