package com.example.pharmacy.sale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class SaleService {

    private final SaleMapper saleMapper;

    @Autowired
    public SaleService(SaleMapper saleMapper) {
        this.saleMapper = saleMapper;
    }

    @Secured("ROLE_SALE")
    public Sale create(Sale sale) {
        sale.setMedicineId(sale.getMedicine().getId());
        sale.setRecorderId(sale.getRecorder().getId());
        sale.setTotalPrice(sale.getMedicine().getPrice() * sale.getCount());
        sale.setDate(new Timestamp(System.currentTimeMillis()));
        if (saleMapper.create(sale) == 1) {
            return sale;
        } else  {
            return null;
        }
    }

    @Secured("ROLE_SALE")
    public List<Sale> getAll() {
        return saleMapper.readAll();
    }

    @Secured("ROLE_SALE")
    public Boolean delete(Integer id) {
        return saleMapper.delete(id) == 1;
    }
}
