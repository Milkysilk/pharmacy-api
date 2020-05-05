package com.example.pharmacy.basic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    private Integer id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String name;
    private String address;
    private String postcode;
    private String telephone;
    private String email;

    private List<Medicine> medicines;
}
