package com.example.ltw_longptit.controller;

import com.example.ltw_longptit.model.HoTro;
import com.example.ltw_longptit.model.Kham;
import com.example.ltw_longptit.model.LuongYTa;
import com.example.ltw_longptit.model.Yta;
import com.example.ltw_longptit.repo.HoTroRepository;
import com.example.ltw_longptit.repo.YtaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/hotro", produces = "application/json")
@CrossOrigin(origins = "*")
public class HoTroController {
    @Autowired
    private HoTroRepository hoTroRepository;

    @Autowired
    private YtaRepository ytaRepo;

    @GetMapping()
    public List<HoTro> getAllHoTro() {
        return hoTroRepository.findAll();
    }

    @GetMapping("/{id}")
    public HoTro getHoTroById(@PathVariable("id") int id) {
        Optional<HoTro> optionalHoTro = hoTroRepository.findById(id);
        if (optionalHoTro.isPresent()) {
            return optionalHoTro.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public HoTro postHoTro(@RequestBody HoTro hoTro) {
        return hoTroRepository.save(hoTro);
    }

    @PutMapping("/{id}")
    public HoTro putHoTro(@RequestBody HoTro hoTro) {
        return hoTroRepository.save(hoTro);
    }

    @DeleteMapping("/{id}")
    public void deleteHoTro(@PathVariable("id") Integer id) {
        try {
            hoTroRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @GetMapping("dsYtaFromKhamId/{id}")
    public List<HoTro> getYtaFromKhamId(@PathVariable("id") String id) {
        return hoTroRepository.getYTaTheoIdKham(id);
    }

    @GetMapping("tk/yta/{keyword}/{id}")
    public List<HoTro> getHoTroByIdYta(@PathVariable("keyword") String keyword,
                                       @PathVariable("id") String id) {
        return hoTroRepository.getHoTroTheoIdYTa(keyword, id);
    }


    public LuongYTa getLuongYTa(String keyword, String id){

        Yta yta = ytaRepo.getById(Integer.parseInt(id));
        LuongYTa luongYTa = new LuongYTa();

        int soLan = hoTroRepository.getSoLanHoTro(keyword, id);
        int luongCoBan = 5000000;
        int luongCongThem = soLan* 200000;
        int luongTong = luongCoBan + luongCongThem;

        luongYTa.setSoLan(soLan);
        luongYTa.setLuongCoBan(luongCoBan);
        luongYTa.setLuongCongThem(luongCongThem);
        luongYTa.setLuongTong(luongTong);
        luongYTa.setYtaTen(yta.getYtaTen());
        luongYTa.setYtaCMT(yta.getYtaCMT());
        luongYTa.setYtaDiaChi(yta.getYtaDiaChi());
        luongYTa.setYtaNgaySinh(yta.getYtaNgaySinh());
        luongYTa.setYtaThamNien(yta.getYtaThamNien());
        luongYTa.setId(yta.getId());
        luongYTa.setYtaSDT(yta.getYtaSDT());

        return luongYTa;

    }

    @GetMapping("/tl/yta/{keyword}")
    public List<LuongYTa> getListLuong( @PathVariable("keyword") String keyword ){

        List<LuongYTa> luongYTaList = new ArrayList<>();
        List<Yta> ytaList = ytaRepo.findAll();

        for(int i=0; i<ytaList.size(); i++){

            LuongYTa lyt = getLuongYTa(keyword, String.valueOf(ytaList.get(i).getId()));
            luongYTaList.add(lyt);

        }
        return luongYTaList;

    }


//    //2021-12
//    public LuongYTa getLuongYTa(String keyword, String id) {
//        int soLan = hoTroRepository.getSoLanHoTro(keyword, id);
//        Yta yta = ytaRepo.getById(Integer.parseInt(id));
//        LuongYTa luongYTa = new LuongYTa();
//        luongYTa.setLuongCoBan(5000000);
//        int luongCongThem = soLan * 200000;
//        //set lại thằng luong y tá ke thua tu YTa
//        int luongTong = luongCongThem + 5000000;
//        luongYTa.setLuongCongThem(luongCongThem);
//        luongYTa.setLuongTong(luongTong);
//        luongYTa.setSoLan(soLan);
//        luongYTa.setId(yta.getId());
//        luongYTa.setYtaCMT(yta.getYtaCMT());
//        luongYTa.setYtaDiaChi(yta.getYtaDiaChi());
//        luongYTa.setYtaThamNien(yta.getYtaThamNien());
//        luongYTa.setYtaSDT(yta.getYtaSDT());
//        luongYTa.setYtaTen(yta.getYtaTen());
//        luongYTa.setYtaNgaySinh(yta.getYtaNgaySinh());
//
//        return luongYTa;
//    }
//
//    @GetMapping("tl/yta/{keyword}")
//    public List<LuongYTa> getListLuong(@PathVariable("keyword") String keyword) {
//        List<Yta> ytaFull = ytaRepo.findAll();
//
//        ArrayList<LuongYTa> listLuongYTa = new ArrayList<>();
//
//        for (int i = 0; i < ytaFull.size(); i++) {
//            listLuongYTa.add(getLuongYTa(keyword, String.valueOf(ytaFull.get(i).getId())));
//        }
//
//        return listLuongYTa;
//    }
//

    @DeleteMapping("/delete/hotro-theo-id-kham/{id}")
    public void deleteHoTroTheoIdKham(@PathVariable("id") String id) {
        hoTroRepository.deleteHoTroTheoIdKham(id);
    }


}
