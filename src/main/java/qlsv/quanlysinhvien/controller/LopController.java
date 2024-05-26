package qlsv.quanlysinhvien.controller;

import jakarta.validation.Valid;
import qlsv.quanlysinhvien.entity.Lop;
import qlsv.quanlysinhvien.services.LopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;
    @GetMapping
    public String showAllLop(Model model) {
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        return "lop/list";
    }
    @GetMapping("/add")
    public String showAddLop(Model model) {
    model.addAttribute("lop", new Lop());
    return "lop/add";
    }
    @PostMapping("/add")
    public String addLop(@Valid @ModelAttribute("lop") Lop lop, BindingResult result) {
        if(result.hasErrors()){
            return "lop/add";
        }
        lopService.addLop(lop);
        return "redirect:/lop";
    }
    @GetMapping("/update/{maLop}")
    public String showUpdateLop(@PathVariable("maLop") long maLop, Model model) {
        Lop lop = lopService.getLopById(maLop);
        if(lop == null) {
            return "redirect:/lop/add";
        }
        model.addAttribute("lop", lop);
        return "lop/update";
    }
    @PostMapping("/update/{maLop}")
    public String updateLop(@PathVariable("maLop") long maLop, @Valid @ModelAttribute("lop") Lop lop, BindingResult result) {
        if(result.hasErrors()){
            return "lop/update";
        }
        Lop Lop = lopService.getLopById(maLop);
        if(Lop == null) {
            return "redirect:/lop/add";
        }
        lopService.updateLop(lop);
        return "redirect:/lop";
    }
    @GetMapping("/delete/{maLop}")
    public String deleteLop(@PathVariable("maLop") long maLop) {
        Lop lop = lopService.getLopById(maLop);
        if(lop == null) {
            return "redirect:/lop/add";
        }
        lopService.deleteLop(maLop);
        return "redirect:/lop";
    }
}
