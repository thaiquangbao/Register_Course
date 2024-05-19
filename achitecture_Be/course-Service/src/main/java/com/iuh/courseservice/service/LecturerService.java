package com.iuh.courseservice.service;

import com.iuh.courseservice.dto.LecturerRp;
import com.iuh.courseservice.dto.LecturerRq;
import com.iuh.courseservice.model.Lecturer;
import com.iuh.courseservice.repository.LecturerRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    public LecturerRepo lecturerRepo;
    public LecturerRp mapEachInList(Lecturer lecturer){
        return LecturerRp.builder()
                .giangVienid(lecturer.getGiangVienid())
                .tenGiangVien(lecturer.getTenGiangVien())
                .chuyenNghanh(lecturer.getChuyenNghanh())
                .build();
    }

    public List<LecturerRp> getAllLecturer(){
        return lecturerRepo.findAll().stream().map(this::mapEachInList).toList();
    }
    public LecturerRq addNewLectrer(LecturerRq lecturerRq){
        Lecturer lecture = Lecturer.builder()
                .tenGiangVien(lecturerRq.getTenGiangVien())
                .chuyenNghanh(lecturerRq.getChuyenNghanh())
                .build();
        lecturerRepo.save(lecture);
        return  lecturerRq;
    }

    public LecturerRp getOneById(int id){
        Optional<Lecturer> optionalLecturer = lecturerRepo.findById(id);
        if (optionalLecturer.isPresent()){
            Lecturer lecturer = optionalLecturer.get();
            LecturerRp lecture =  LecturerRp.builder()
                    .chuyenNghanh(lecturer.getChuyenNghanh())
                    .tenGiangVien(lecturer.getTenGiangVien())
                    .build();
            return lecture;
        }
        return null;
    }
}
