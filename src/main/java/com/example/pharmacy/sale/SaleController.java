package com.example.pharmacy.sale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Sale sale) {
        Sale sale1 = saleService.create(sale);
        if (sale1 == null) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(sale1, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        log.debug(id);
        if (saleService.delete(Integer.parseInt(id))) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
