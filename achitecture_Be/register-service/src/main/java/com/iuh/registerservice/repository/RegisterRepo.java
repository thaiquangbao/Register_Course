package com.iuh.registerservice.repository;

import com.iuh.registerservice.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepo extends JpaRepository<Register,Long> {

    public List<Register> findAllByUserName(String username);
    public  void deleteRegisterByUserNameAndClassCouserDetailId(String usename, int classCouserDetailId);
    public List<Register> findAllByHocKyDangKy(int hocKyDangKy);
    public List<Register> findAllByUserNameAndHocKyDangKy(String username,int hocKyDangKy);

}
