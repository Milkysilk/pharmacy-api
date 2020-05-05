package com.example.pharmacy.store;

import com.example.pharmacy.basic.Medicine;
import com.example.pharmacy.basic.Repo;
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
public class IoRecord {
    private Integer id;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private Integer medicineId;
    private Integer repoId;
    private Integer recorderId;
    private int type;
    private int quantity;
    private Timestamp date;

    private Repo repo;
    private Medicine medicine;
    private User recorder;
}
