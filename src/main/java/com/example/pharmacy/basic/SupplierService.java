package com.example.pharmacy.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Secured("ROLE_BASIC")
public class SupplierService {
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierService(SupplierMapper supplierMapper) {
        this.supplierMapper = supplierMapper;
    }

    public Supplier create(Supplier supplier) {
        if (supplierMapper.create(supplier) == 1) {
            return supplier;
        } else {
            return null;
        }
    }

    public List<Supplier> getAll() {
        return supplierMapper.readAll();
    }

    public Boolean update(Supplier supplier) {
        return supplierMapper.update(supplier) > 0;
    }
}
