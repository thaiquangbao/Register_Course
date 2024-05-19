package com.iuh.registerservice.service;

import com.iuh.registerservice.dto.RegisterRqRp;
import com.iuh.registerservice.model.Register;
import com.iuh.registerservice.repository.RegisterRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RegisterService {
    @Autowired
    public RegisterRepo registerRepo;

    @Autowired
    public EntityManager entityManager;
    public RegisterRqRp mapOneToOneRegister(Register register){
        return RegisterRqRp.builder()
                .id(register.getId())
                .userName(register.getUserName())
                .couserId(register.getCouserId())
                .hocKyDangKy(register.getHocKyDangKy())
                .trinhTrangMonHoc(register.isTrinhTrangMonHoc())
                .classCouserDetailId(register.getClassCouserDetailId())
                .build();
    }
    public RegisterRqRp addNewRegister(RegisterRqRp registerRqRp){
        Register register = Register.builder()
                .id(registerRqRp.getId())
                .userName(registerRqRp.getUserName())
                .couserId(registerRqRp.getCouserId())
                .hocKyDangKy(registerRqRp.getHocKyDangKy())
                .trinhTrangMonHoc(registerRqRp.isTrinhTrangMonHoc())
                .classCouserDetailId(registerRqRp.getClassCouserDetailId())
                .build();
        registerRepo.save(register);
        return registerRqRp;
    }

    @Transactional
    public List<RegisterRqRp> getListRegisterByUserName(String username){
        List<Register> listRs = registerRepo.findAllByUserName(username);
        return  listRs.stream().map(this::mapOneToOneRegister).toList();

    }
    public List<RegisterRqRp> getListRegisterByHocKyDangKy(int hocky){
        List<Register> listRs = registerRepo.findAllByHocKyDangKy(hocky);
        return  listRs.stream().map(this::mapOneToOneRegister).toList();
    }
    public List<RegisterRqRp> getListRegisterByUserNameAndHocKyDangKy(String username,int hocky){
        List<Register> listRs = registerRepo.findAllByUserNameAndHocKyDangKy(username,hocky);
        return  listRs.stream().map(this::mapOneToOneRegister).toList();
    }
//
    @Transactional
    public RegisterRqRp removeRegister(RegisterRqRp registerRqRp){
        registerRepo.deleteRegisterByUserNameAndClassCouserDetailId(registerRqRp.getUserName(),registerRqRp.getClassCouserDetailId());
        return registerRqRp;
    }
}
