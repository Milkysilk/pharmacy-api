package com.example.pharmacy.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/iorecord")
public class IoRecordController {

    private final IoRecordService ioRecordService;

    @Autowired
    public IoRecordController(IoRecordService ioRecordService) {
        this.ioRecordService = ioRecordService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody IoRecord ioRecord) {
        IoRecord ioRecord1 = ioRecordService.create(ioRecord);
        if (ioRecord1 == null) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(ioRecord1, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(ioRecordService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (ioRecordService.delete(Integer.parseInt(id))) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
