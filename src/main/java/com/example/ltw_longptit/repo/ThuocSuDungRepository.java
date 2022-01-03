package com.example.ltw_longptit.repo;

import com.example.ltw_longptit.model.ThuocSuDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ThuocSuDungRepository extends JpaRepository<ThuocSuDung, Integer> {
    @Query(value="SELECT * FROM thuoc_su_dung WHERE id_donthuoc=:id ;",nativeQuery = true)
    public List<ThuocSuDung> statisticMedicineUsedbyPrescriptionId(@Param("id") String id);


    //khai báo anotation để có thể xóa được
    @Transactional//bắt đầu một giao dịch chỉ đọc hoàn toàn mới khi phương thức này được gọi, tạm dừng bất kỳ giao dịch hiện có nào
    @Modifying//để nâng cao chú thích @Query,có thể thực thi CHÈN, CẬP NHẬT, XÓA,
    @Query(value = "delete from thuoc_su_dung where id_donthuoc=:id", nativeQuery = true)
    public void deleteThuocSuDungTheoDonThuoc(String id);

//
//    @Query(value = "select * from thuoc_su_dung where id_thuoc=:id",nativeQuery = true)
//    public List<ThuocSuDung> getThuocSuDungTheoIdThuoc(String id);




}
