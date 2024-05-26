package qlsv.quanlysinhvien.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;
@Data
@Entity(name="SinhVien")
@Table(name="SinhVien")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SinhVien {
    @Id
    @Column(name="MSSV",length=10)
    @Size(min =10,max=10,message = "MSSV phải có 10 chữ số")
    private String mssv;
    @Size(max=50,message="Họ tên chỉ tối đa 50 ký tự")
    @NotNull(message="Họ và tên không được để trống")
    @Column(name="HoTen",length = 50)
    private String hoTen;
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "NgaySinh")
    private Date ngaySinh;
    @Email(message = "Email phải hợp lệ")
    @NotNull(message = "Email không được để trống")
    @Column(name = "Email")
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLop",referencedColumnName = "MaLop",foreignKey = @ForeignKey(name = "FK_SINHVIEN_LOP"))
    private Lop lop;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SinhVien_MonHoc",joinColumns = {@JoinColumn(name ="MSSV")},inverseJoinColumns = {@JoinColumn(name = "MaMon")})
    private Set<MonHoc> monHocs;
}
