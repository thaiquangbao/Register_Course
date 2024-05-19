package com.iuh.registerservice.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRqRp implements Serializable {
    private int id;
    private String userName;
    private int classCouserDetailId;
    private int hocKyDangKy;
    private int couserId;
    private boolean trinhTrangMonHoc;
}
