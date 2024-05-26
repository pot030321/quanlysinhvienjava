package qlsv.quanlysinhvien.services;

import qlsv.quanlysinhvien.entity.MonHoc;
import qlsv.quanlysinhvien.repository.IMonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRepository monHocRepository;

    public List<MonHoc> getAllMonHoc() {
        return monHocRepository.findAll();
    }

    public MonHoc getMonHocById(String id) {
        return monHocRepository.findById(id).orElse(null);
    }

    public void addMonHoc(MonHoc monHoc) {
        monHocRepository.save(monHoc);
    }
    public void deleteMonHoc(String id) {
        monHocRepository.deleteById(id);
    }
    public void updateMonHoc(MonHoc monHoc) {
        monHocRepository.save(monHoc);
    }
}
