package com.example.pharmacy.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<?> newSupplier(@RequestBody Supplier supplier) {
        Supplier supplier1 = supplierService.create(supplier);
        if (supplier1 == null) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(supplier1, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getSuppliers() {
        return new ResponseEntity<>(supplierService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Supplier supplier) {
        if (!supplierService.update(supplier)) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        }
    }
}
