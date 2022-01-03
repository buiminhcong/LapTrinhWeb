package com.example.frontend.Controller;

import com.example.frontend.enity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tinhluong/bacsy")
public class TinhLuongBacSiController {
    RestTemplate rest = new RestTemplate();
    String url = "http://localhost:8080";

    @GetMapping
    public String getDsBacSy(Model model) {
        List<LuongBacSi> luongBacSiList = new ArrayList<>();
        model.addAttribute("luongBacSiList", luongBacSiList);
        model.addAttribute("keyword", "");
        return "bacsi/tlBacSy";
    }

    @GetMapping("/result")
    public String getFormResult(@RequestParam("keyword") String keyword, Model model) {
        List<LuongBacSi> luongBacSiList = Arrays.asList(rest.getForObject(url+"/kham/tl/bacsi/{keyword}", LuongBacSi[].class, keyword));
        model.addAttribute("luongBacSiList", luongBacSiList);
        model.addAttribute("keyword", keyword);
        return "bacsi/tlBacSy";
    }

    @GetMapping("/detail")
    public String showDetail(Model model, @RequestParam("keyword") String keyword,@RequestParam("id") String id) {
        List<Kham> khamList = Arrays.asList(rest.getForObject(url+"/kham/tk/bacsy/{keyword}/{id}", Kham[].class, keyword, id));
        model.addAttribute("listKham", khamList);
        return "bacsi/tlBacSyResult";
    }



}
