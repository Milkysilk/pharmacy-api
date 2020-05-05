package com.example.pharmacy.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medicine {
    private Integer id;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private String code;
    private String name;
    private Integer supplierId;
    private Date manufactureDate;
    private Double buyingPrice;
    private Double price;
    private String shelfLife;
    private String specifications;
    private String manufacturer;

    private List<Repo> repos;
    private Supplier supplier;
}
