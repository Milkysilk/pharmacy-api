package com.example.pharmacy.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Secured("ROLE_BASIC")
public class MedicineService {

    private final MedicineMapper medicineMapper;

    @Autowired
    public MedicineService(MedicineMapper medicineMapper) {
        this.medicineMapper = medicineMapper;
    }

    public Medicine create(Medicine medicine) {
        medicine.setSupplierId(medicine.getSupplier().getId());
        if (medicineMapper.create(medicine) == 1) {
            return medicine;
        } else  {
            return null;
        }
    }

    public List<Medicine> getAll() {
        return medicineMapper.readAll();
    }

//    public Boolean update(Medicine medicine) {
//        return medicineMapper.update(medicine) > 0;
//    }

    public Boolean delete(Integer id) {
        return medicineMapper.delete(id) == 1;
    }
}
