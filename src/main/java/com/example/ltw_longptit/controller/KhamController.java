package com.example.ltw_longptit.controller;


import com.example.ltw_longptit.model.*;
import com.example.ltw_longptit.repo.BacSyRepository;
import com.example.ltw_longptit.repo.KhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/kham", produces = "application/json")
@CrossOrigin(origins = "*")
public class KhamController {
    @Autowired
    private KhamRepository khamRepository;
    @Autowired
    private BacSyRepository bacSyRepo;

    @GetMapping
    public Iterable<Kham> getAllKham() {
        return khamRepository.findAll();
    }

    @GetMapping("/{id}")
    public Kham getKhamById(@PathVariable("id") int id) {
        Optional<Kham> optKham = khamRepository.findById(id);
        if (optKham.isPresent()) {
            return optKham.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    public Kham postKham(@RequestBody Kham test) {
        return khamRepository.save(test);
    }

    @PutMapping("/{id}")
    public Kham putKham(@RequestBody Kham test) {
        return khamRepository.save(test);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteKham(@PathVariable("id") Integer id) {
        try {
            khamRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @GetMapping("/tk/bacsy/{keyword}/{id}")
    public Iterable<Kham> getKhamByBacsy(@PathVariable("keyword") String keyword , @PathVariable("id") String id) {
        List<Integer> listInt =  khamRepository.getAllKhamByBacSy(keyword, id);
        List<Kham> listKham = new ArrayList<>();
        System.out.println("size" + listKham.size());
        // 1 list kham theo benh nhan
        for(int i=0 ; i<listInt.size() ; i++) {
            int idBn = listInt.get(i);
            listKham.add(khamRepository.getKham(String.valueOf(idBn), id));
        }
        ArrayList<Kham> result = new ArrayList<>();
        for (int i=0 ; i<listKham.size() ; i++) {
            String idBenhNhan = String.valueOf(listKham.get(i).getBenhNhan().getId());
            //tìm tất cả hồ sơ khám của bệnh nhân đó trong tháng, và trừ đi những thằng trùng liên tiếp
            List<Kham> listKham1 = getKhamByBenhNhan(keyword, idBenhNhan);
            for (int j=0 ; j<listKham1.size() ; j++) {
                int idBs = listKham1.get(j).getBacSy().getId();
                // benh nhan co the duoc kham boi bac si khac
                if (idBs != Integer.valueOf(id)) {
                    //nếu mà khác bác sĩ, thì phải trừ đi,cho cút
                    listKham1.remove(j);
                    j--;
                } else {
                    result.add(listKham1.get(j));
                }
            }
        }
        return result;

    }
    @GetMapping("/tl/bacsi/{keyword}")
    public List<LuongBacSi> getListLuong(@PathVariable("keyword") String keyword) {
        List<BacSi> listBs = bacSyRepo.findAll();
        List<LuongBacSi> listLuong = new ArrayList<>();
        for(int i=0  ; i < listBs.size() ; i++) {
            listLuong.add(getLuongBacSi(keyword, String.valueOf(listBs.get(i).getId())));
        }

        return listLuong;
    }

    public LuongBacSi getLuongBacSi(String keyword ,String id) {
        List<Integer> listInt =  khamRepository.getAllKhamByBacSy(keyword, id);
        List<Kham> listKham = new ArrayList<>();
        // 1 list kham theo benh nhan
        for(int i=0 ; i<listInt.size() ; i++) {
            int idBn = listInt.get(i);
            listKham.add(khamRepository.getKham(String.valueOf(idBn), id));
        }
        ArrayList<Kham> result = new ArrayList<>();
        int count =0;
        for (int i=0 ; i<listKham.size() ; i++) {
            List<Kham> test = getKhamByBenhNhan(keyword,String.valueOf(listKham.get(i).getBenhNhan().getId()));
            //
            ArrayList<Kham> listKham1 = new ArrayList<>(test);
            for (int j=0 ; j<listKham1.size() ; j++) {
                int idBs = listKham1.get(j).getBacSy().getId();
                // benh nhan co the duoc kham boi bac si khac
                if (idBs != Integer.valueOf(id)) {
                    listKham1.remove(j);
                    j--;
                } else {
                    result.add(listKham1.get(j));
                }
            }
            System.out.println("size moi lan" + listKham1.size());
            count += listKham1.size();

        }
        BacSi bacSi = bacSyRepo.getById(Integer.valueOf(id));
        int luongCung = 7000000;
        int soLan = count;
        int luongCongThem = count*1000000;
        int luongTong = luongCung + luongCongThem;

        LuongBacSi luongBacSi = new LuongBacSi();
        luongBacSi.setBacsyTen(bacSi.getBacsyTen());
        luongBacSi.setId(bacSi.getId());
        luongBacSi.setBacsyBacNghe(bacSi.getBacsyBacNghe());
        luongBacSi.setBacsyChuyenMon(bacSi.getBacsyChuyenMon());
        luongBacSi.setBacsyCMT(bacSi.getBacsyCMT());
        luongBacSi.setBacsyNgaySinh(bacSi.getBacsyNgaySinh());
        luongBacSi.setLuongCoBan(luongCung);
        luongBacSi.setSoLan(soLan);
        luongBacSi.setLuongCongThem(luongCongThem);
        luongBacSi.setLuongTong(luongTong);
        luongBacSi.setBacsyDiaChi(bacSi.getBacsyDiaChi());
        luongBacSi.setBacsyTrinhDoDaoTao(bacSi.getBacsyTrinhDoDaoTao());
        luongBacSi.setBacsySDT(bacSi.getBacsySDT());

        return luongBacSi;
    }


    @GetMapping("/tk/benh/all/{keyword}")
    public Iterable<Kham> getKhamByDate(@PathVariable("keyword") String keyword) {
        return khamRepository.getAllKhamByThang(keyword);
    }

    //@GetMapping("/tk/benhnhan/{keyword}/{id}")
    public List<Kham> getKhamByBenhNhan(String keyword ,String id) {
        ArrayList<Kham> listKham = (ArrayList<Kham>) khamRepository.getAllKhamByBenhNhan(keyword, id);
        // lấy ra ds khám trong tháng theo id bệnh nhân
        for(int i=0 ; i<listKham.size()-1; i++) {
                int maBenh =(int) listKham.get(i).getBenh().getId();
                int maBenh1 =(int) listKham.get(i+1).getBenh().getId();
            String status =  listKham.get(i).getStatus();
            String status1 =  listKham.get(i+1).getStatus();

//            if( listKham.get(i).getBacSy().getId() != listKham.get(i+1).getBacSy().getId() ) {
//                continue;
//            }
            //lọc các bênh giống nhaui liên tiếp
            if(status.equals("bình thường") && status1.equals("mắc bệnh") && maBenh == maBenh1 ) {
                listKham.remove(i);
                i--;
                continue;
            }
            if(status1.equals("bình thường") && maBenh != maBenh1 ) {
                listKham.remove(i+1);
                continue;
            }
            if (maBenh == maBenh1) {
                    listKham.remove(i+1);
                    i--;
                }
            }
        return listKham;
    }


    @GetMapping("/tk/benh/{keyword}/{id}")
    public Iterable<Kham> getKhamTrongThangTheoId(@PathVariable("keyword") String keyword,@PathVariable("id") String id) {
        return khamRepository.getAllKhamByBenh(keyword, id);
    }


    @GetMapping("/tk/benh/solan/{keyword}")
    public List<SoLanBenh> thongKeBenhTheo(@PathVariable("keyword") String keyword) {
               // ArrayList<Kham> listKham = (ArrayList<Kham>) khamRepository.getAllKhamByThangMacBenh(keyword);
        List<Integer> dsBenh = khamRepository.getBenhTrongThang(keyword);
       // HashMap<Integer ,List<Kham>> map = new HashMap<Integer ,List<Kham>>();
        List<SoLanBenh> soLanBenhList = new ArrayList<>();

        for(int i=0 ; i < dsBenh.size(); i++) {
            int count = 0;
            List<Kham> list = khamRepository.getAllKhamByBenh(keyword, String.valueOf(dsBenh.get(i)));
            for(int j=0 ; j<list.size() ; j++) {
                 if(list.get(j).getStatus().equals("mắc bệnh")) {
                    count++;
                }
            }
            SoLanBenh soLanBenh = new SoLanBenh();
            soLanBenh.setSoLan(list.size());
            soLanBenh.setId(list.get(0).getBenh().getId());
            soLanBenh.setTen(list.get(0).getBenh().getTen());
            soLanBenh.setSoLanMac(count);
            soLanBenhList.add(soLanBenh);
        }
        return soLanBenhList;
    }

    @GetMapping("/bn/{id}")
    public Iterable<Kham> getAllKhamTheoBenhNhan(@PathVariable("id") String id) {
        return khamRepository.getKhamTheoIdBenhNhan(id);
    }

}
