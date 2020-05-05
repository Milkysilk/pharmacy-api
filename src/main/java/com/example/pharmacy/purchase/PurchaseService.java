package com.example.pharmacy.purchase;

import com.example.pharmacy.store.IoRecord;
import com.example.pharmacy.store.IoRecordMapper;
import com.example.pharmacy.store.Storage;
import com.example.pharmacy.store.StorageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PurchaseService {

    private final PurchaseMapper purchaseMapper;
    private final IoRecordMapper ioRecordMapper;
    private final StorageMapper storageMapper;

    @Autowired
    public PurchaseService(PurchaseMapper purchaseMapper, IoRecordMapper ioRecordMapper, StorageMapper storageMapper) {
        this.purchaseMapper = purchaseMapper;
        this.ioRecordMapper = ioRecordMapper;
        this.storageMapper = storageMapper;
    }

    @Secured("ROLE_PURCHASE")
    public Purchase create(Purchase purchase) {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(purchase.getMaker().getUsername())) {
            return null;
        }
        purchase.setStatus(-1);
        purchase.setPlanDate(new Timestamp(System.currentTimeMillis()));
        purchase.setMedicineId(purchase.getMedicine().getId());
        purchase.setMakerId(purchase.getMaker().getId());
        if (purchaseMapper.create(purchase) == 1) {
            return purchase;
        } else  {
            return null;
        }
    }

    @Secured({"ROLE_PURCHASE", "ROLE_STORAGE"})
    public List<Purchase> getAll() {
        return purchaseMapper.readAll();
    }

    @Secured("ROLE_PURCHASE")
    public Purchase update(Purchase purchase) {
        purchase.setExecutorId(purchase.getExecutor().getId());
        if (purchase.getStatus() == 0) {
            purchase.setStartDate(new Date(System.currentTimeMillis()));
        } else if (purchase.getStatus() == 1) {
            purchase.setCompleteDate(new Date(System.currentTimeMillis()));
            purchase.setTotalPrice(purchase.getMedicine().getPrice() * purchase.getActualQuantity());
            IoRecord ioRecord = IoRecord.builder()
                    .medicineId(purchase.getMedicine().getId())
                    .repoId(purchase.getRepo().getId())
                    .recorderId(purchase.getExecutorId())
                    .type(1)
                    .quantity(purchase.getActualQuantity())
                    .build();
            Storage storage = storageMapper.readByMedicineIdAndRepositoryId(ioRecord.getMedicineId(), ioRecord.getRepoId());
            int result = storage.getInventory() + ioRecord.getQuantity();
            if (result > storage.getLimit()) {
                return null;
            } else {
                storage.setInventory(result);
                storageMapper.update(storage);
                ioRecordMapper.create(ioRecord);
            }
        }

        if (purchaseMapper.update(purchase) > 0) {
            return purchase;
        } else {
            return null;
        }
    }

    @Secured("ROLE_PURCHASE")
    public Boolean delete(Integer id) {
        return purchaseMapper.delete(id) == 1;
    }
}
