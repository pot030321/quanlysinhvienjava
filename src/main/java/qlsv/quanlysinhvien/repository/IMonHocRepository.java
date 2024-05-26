package qlsv.quanlysinhvien.repository;
import qlsv.quanlysinhvien.entity.MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IMonHocRepository extends JpaRepository<MonHoc, String> {
}
