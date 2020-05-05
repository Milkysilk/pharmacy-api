package com.example.pharmacy.sale;

import com.example.pharmacy.basic.Medicine;
import com.example.pharmacy.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    private Integer id;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private Integer medicineId;
    private Integer recorderId;
    private int type;
    private int count;
    private double totalPrice;
    private Timestamp date;

    private Medicine medicine;
    private User recorder;
}
