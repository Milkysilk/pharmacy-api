package com.example.pharmacy.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Medicine medicine) {
        Medicine medicine1 = medicineService.create(medicine);
        if (medicine1 == null) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(medicine1, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(medicineService.getAll(), HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody Medicine medicine) {
//        if (!medicineService.update(medicine)) {
//            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity<>(medicine, HttpStatus.OK);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (medicineService.delete(Integer.parseInt(id))) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
