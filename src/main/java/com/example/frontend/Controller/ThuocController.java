package com.example.frontend.Controller;

import com.example.frontend.enity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/thuoc")
public class ThuocController {

    private RestTemplate rest = new RestTemplate();
    String url = "http://localhost:8080/thuoc";

    @GetMapping("/current")
    public String homeThuoc(Model model){
        List<Thuoc> list = Arrays.asList(rest.getForObject(url + "/list-thuoc", Thuoc[].class));
        model.addAttribute("listThuoc", list);
        return "thuoc/dsThuoc";
    }

    @GetMapping("/create")
    public String newThuoc(Model model){
        Thuoc thuoc = new Thuoc();
        model.addAttribute("thuoc", thuoc);
        return "thuoc/addThuoc";
    }

    @PostMapping("/save")
    public String save(@Valid Thuoc thuoc, Errors errors) {
        System.out.println(errors.toString());
            if (errors.hasErrors()) {
                return "thuoc/addThuoc";
            }
            rest.postForObject(url + "/add-thuoc",thuoc, Thuoc.class);
            return "redirect:/thuoc/current";
    }

    @GetMapping("/update")
    public String edit(@RequestParam("id") long id, Model model) {
        System.out.println("ID : ");
        Thuoc thuoc = rest.getForObject(url + "/get-thuoc/{id}", Thuoc.class, id);
        model.addAttribute("thuoc", thuoc);

        return "/thuoc/editThuoc";

    }

    @PostMapping("/saveEdit")
    public String update(Thuoc thuoc) {
        System.out.println(thuoc.getId());
        rest.put(url+"/update-thuoc/{id}", thuoc, thuoc.getId());
        return "redirect:/thuoc/current";
    }

    @GetMapping("/delete")
    public String deleteThuoc(@RequestParam("id") long id){
        System.out.println("Thanh Cong");
        //cập nhật lại đơn thuốc
        //gọi tất cả các đơn thuốc mà chứa thuốc đó, sửa lại

        rest.delete(url + "/delete-thuoc/{id}",id);
        return "redirect:/thuoc/current";
    }

    @GetMapping("/search")
    public String searchThuoc( @RequestParam("key") String key, Model model){
        key = key.toLowerCase();
        if (key.equals("")) return "redirect:/thuoc/current";
        List<Thuoc> list = Arrays.asList(rest.getForObject(url + "/search-list/{key}",Thuoc[].class,key));
        model.addAttribute("listThuoc", list);
        return "thuoc/dsThuoc";
    }
}
