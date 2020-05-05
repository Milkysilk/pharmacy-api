package com.example.pharmacy.purchase;

import com.example.pharmacy.basic.Medicine;
import com.example.pharmacy.basic.Repo;
import com.example.pharmacy.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {
    private Integer id;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private Integer medicineId;
    private Integer status;
    private Integer makerId;
    private Timestamp planDate;
    private Integer executorId;
    private Integer plannedQuantity;
    private Integer actualQuantity;
    private Double totalPrice;
    private Date startDate;
    private Date completeDate;

    private Medicine medicine;
    private User maker;
    private User executor;
    private Repo repo;
}
