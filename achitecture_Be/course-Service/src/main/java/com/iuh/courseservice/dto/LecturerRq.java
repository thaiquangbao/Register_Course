package com.iuh.courseservice.dto;

import lombok.*;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LecturerRq {
    public int giangVienid;
    public String tenGiangVien;
    public String chuyenNghanh;
}
