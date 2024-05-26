package qlsv.quanlysinhvien.controller;

import jakarta.validation.Valid;
import qlsv.quanlysinhvien.entity.MonHoc;
import qlsv.quanlysinhvien.services.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;
    @GetMapping
    public String showAllSinhVien(Model model) {
        List<MonHoc> dsMonHoc = monHocService.getAllMonHoc();
        model.addAttribute("dsMonHoc", dsMonHoc);
        return "monhoc/list";
    }
    @GetMapping("/add")
    public String showAddMonHoc(Model model) {
        model.addAttribute("monHoc", new MonHoc());
        return "monhoc/add";
    }
    @PostMapping("/add")
    public String addMonHoc(@Valid @ModelAttribute("monHoc") MonHoc monHoc, BindingResult result) {
        if(result.hasErrors()){
            return "monhoc/add";
        }
        monHocService.addMonHoc(monHoc);
        return "redirect:/monhoc";
    }
    @GetMapping("/update/{maMonHoc}")
    public String showUpdateMonHoc(@PathVariable("maMonHoc") String maMonHoc, Model model) {
        MonHoc monHoc = monHocService.getMonHocById(maMonHoc);
        if(monHoc == null) {
            return "redirect:/monhoc/add";
        }
        model.addAttribute("monHoc", monHoc);
        return "monhoc/update";
    }
    @PostMapping("/update/{maMonHoc}")
    public String updateMonHoc(@PathVariable("maMonHoc") String maMonHoc, @Valid @ModelAttribute("monHoc") MonHoc monHoc, BindingResult result) {
        if(result.hasErrors()){
            return "monhoc/update";
        }
        MonHoc monHoc1 = monHocService.getMonHocById(maMonHoc);
        if(monHoc1 == null) {
            return "redirect:/monhoc/add";
        }
        monHocService.updateMonHoc(monHoc);
        return "redirect:/monhoc";
    }
    @GetMapping("/delete/{maMonHoc}")
    public String deleteMonHoc(@PathVariable("maMonHoc") String maMonHoc) {
        MonHoc monHoc = monHocService.getMonHocById(maMonHoc);
        if(monHoc == null) {
            return "redirect:/monhoc/add";
        }
        monHocService.deleteMonHoc(maMonHoc);
        return "redirect:/monhoc";
    }
}
