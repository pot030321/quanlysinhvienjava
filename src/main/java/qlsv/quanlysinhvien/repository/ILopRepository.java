package qlsv.quanlysinhvien.repository;
import qlsv.quanlysinhvien.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ILopRepository extends JpaRepository<Lop, Long> {
}
