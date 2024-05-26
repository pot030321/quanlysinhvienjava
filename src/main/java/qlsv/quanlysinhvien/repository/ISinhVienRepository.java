package qlsv.quanlysinhvien.repository;
import qlsv.quanlysinhvien.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ISinhVienRepository extends JpaRepository<SinhVien, String> {
}
