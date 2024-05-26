package qlsv.quanlysinhvien.controller;

import jakarta.validation.Valid;
import qlsv.quanlysinhvien.entity.Lop;
import qlsv.quanlysinhvien.entity.SinhVien;
import qlsv.quanlysinhvien.services.LopService;
import qlsv.quanlysinhvien.services.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sinhVien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;
    @Autowired
    private LopService lopService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping
    public String showAllSinhVien(Model model) {
        List<SinhVien> dsSinhVien = sinhVienService.getAllSinhVien();
        model.addAttribute("dsSinhVien", dsSinhVien);
        return "sinhvien/list";
    }

    @GetMapping("/add")
    public String showAddSinhVien(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        return "sinhvien/add";
    }

    @PostMapping("/add")
    public String addSinhVien(@Valid @ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result, Model model) {
        Lop lop = lopService.getLopById(sinhVien.getLop().getMaLop());
        sinhVien.setLop(lop);
        if(lop==null){
            return "lop/add";
        }
        if (result.hasErrors()) {
            List<Lop> dsLop = lopService.getAllLop();
            model.addAttribute("dsLop", dsLop);
            return "sinhvien/add";
        }

        sinhVienService.addSinhVien(sinhVien);
        return "redirect:/sinhVien";
    }

    @GetMapping("/update/{mssv}")
    public String showUpdateSinhVien(@PathVariable("mssv") String mssv, Model model) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(mssv);
        if (sinhVien == null) {
            return "redirect:/sinhVien/add";
        }
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        model.addAttribute("sinhVien", sinhVien);
        return "sinhvien/update";
    }

    @PostMapping("/update/{mssv}")
    public String updateSinhVien(@PathVariable("mssv") String mssv, @Valid @ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result,Model model) {
        Lop lop = lopService.getLopById(sinhVien.getLop().getMaLop());
        sinhVien.setLop(lop);
        if(lop==null){
            return "lop/add";
        }
        if (result.hasErrors()) {
            List<Lop> dsLop = lopService.getAllLop();
            model.addAttribute("dsLop", dsLop);
            return "sinhvien/update";
        }
        SinhVien sinhVien1 = sinhVienService.getSinhVienById(mssv);
        if (sinhVien1 == null) {
            return "redirect:/sinhVien/add";
        }
        sinhVienService.updateSinhVien(sinhVien);
        return "redirect:/sinhVien";
    }

    @GetMapping("/delete/{mssv}")
    public String deleteSinhVien(@PathVariable("mssv") String mssv) {
        SinhVien sinhVien = sinhVienService.getSinhVienById(mssv);
        if (sinhVien == null) {
            return "redirect:/sinhVien/add";
        }
        sinhVienService.deleteSinhVien(mssv);
        return "redirect:/sinhVien";
    }
}
