package com.example.ngogiaphuong2280602523.controller;


import com.example.ngogiaphuong2280602523.model.SinhVien;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SinhVienController {

    // LÆ°u trá»¯ danh sĂ¡ch sinh viĂªn trong memory
    private static List<SinhVien> danhSachSinhVien = new ArrayList<>();
    private static Long nextId = 1L;

    @GetMapping("/sinhvien")
    public String showForm(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("danhSachSinhVien", danhSachSinhVien);
        model.addAttribute("isEdit", false);
        return "form-sinhvien";
    }

    @PostMapping("/sinhvien")
    public String submitForm(@Valid SinhVien sinhVien, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("danhSachSinhVien", danhSachSinhVien);
            model.addAttribute("isEdit", false);
            return "form-sinhvien";
        }
        
        // ThĂªm sinh viĂªn má»›i
        sinhVien.setId(nextId++);
        danhSachSinhVien.add(sinhVien);
        
        model.addAttribute("message", "Sinh viĂªn Ä‘Ă£ Ä‘Æ°á»£c thĂªm thĂ nh cĂ´ng!");
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("danhSachSinhVien", danhSachSinhVien);
        model.addAttribute("isEdit", false);
        return "form-sinhvien";
    }

    @GetMapping("/sinhvien/edit")
    public String editForm(@RequestParam("id") Long id, Model model) {
        SinhVien sinhVien = findById(id);
        if (sinhVien != null) {
            model.addAttribute("sinhVien", sinhVien);
            model.addAttribute("isEdit", true);
        } else {
            model.addAttribute("sinhVien", new SinhVien());
            model.addAttribute("isEdit", false);
            model.addAttribute("error", "KhĂ´ng tĂ¬m tháº¥y sinh viĂªn!");
        }
        model.addAttribute("danhSachSinhVien", danhSachSinhVien);
        return "form-sinhvien";
    }

    @PostMapping("/sinhvien/update")
    public String updateForm(@Valid SinhVien sinhVien, BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("danhSachSinhVien", danhSachSinhVien);
            model.addAttribute("isEdit", true);
            return "form-sinhvien";
        }
        
        // TĂ¬m vĂ  cáº­p nháº­t sinh viĂªn
        SinhVien existing = findById(sinhVien.getId());
        if (existing != null) {
            existing.setTen(sinhVien.getTen());
            existing.setTuoi(sinhVien.getTuoi());
            existing.setKhoa(sinhVien.getKhoa());
            model.addAttribute("message", "ÄĂ£ cáº­p nháº­t sinh viĂªn thĂ nh cĂ´ng!");
        } else {
            model.addAttribute("error", "KhĂ´ng tĂ¬m tháº¥y sinh viĂªn Ä‘á»ƒ cáº­p nháº­t!");
        }
        
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("danhSachSinhVien", danhSachSinhVien);
        model.addAttribute("isEdit", false);
        return "form-sinhvien";
    }

    @GetMapping("/sinhvien/delete")
    public String deleteStudent(@RequestParam("id") Long id, Model model) {
        SinhVien sinhVien = findById(id);
        if (sinhVien != null) {
            danhSachSinhVien.remove(sinhVien);
            model.addAttribute("message", "ÄĂ£ xĂ³a sinh viĂªn thĂ nh cĂ´ng!");
        } else {
            model.addAttribute("error", "KhĂ´ng tĂ¬m tháº¥y sinh viĂªn Ä‘á»ƒ xĂ³a!");
        }
        
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("danhSachSinhVien", danhSachSinhVien);
        model.addAttribute("isEdit", false);
        return "form-sinhvien";
    }

    private SinhVien findById(Long id) {
        return danhSachSinhVien.stream()
                .filter(sv -> sv.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
