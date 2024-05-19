package com.iuh.registerservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "register")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private int classCouserDetailId;
    private int couserId;
    private int hocKyDangKy;
    private boolean trinhTrangMonHoc;
}
