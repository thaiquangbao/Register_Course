package com.iuh.courseservice.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LecturerRp implements Serializable {
    private static final long serialVersionUID = 297063413780202219L;
    public int giangVienid;
    public String tenGiangVien;
    public String chuyenNghanh;
}
