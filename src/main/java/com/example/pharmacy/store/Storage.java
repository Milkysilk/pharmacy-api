package com.example.pharmacy.store;

import com.example.pharmacy.basic.Medicine;
import com.example.pharmacy.basic.Repo;
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
public class Storage {
    private Integer id;
    @JsonIgnore
    private Timestamp createTime;
    @JsonIgnore
    private Timestamp updateTime;
    private Integer medicineId;
    private Integer repoId;
    private int limit;
    private int inventory;

    private Repo repo;
    private Medicine medicine;
}
