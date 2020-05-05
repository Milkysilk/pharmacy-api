package com.example.pharmacy.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class IoRecordService {

    private final IoRecordMapper ioRecordMapper;
    private final StorageMapper storageMapper;

    @Autowired
    public IoRecordService(IoRecordMapper ioRecordMapper, StorageMapper storageMapper) {
        this.ioRecordMapper = ioRecordMapper;
        this.storageMapper = storageMapper;
    }

    @Secured("ROLE_STORAGE")
    public IoRecord create(IoRecord ioRecord) {
        ioRecord.setRepoId(ioRecord.getRepo().getId());
        ioRecord.setMedicineId(ioRecord.getMedicine().getId());
        ioRecord.setRecorderId(ioRecord.getRecorder().getId());
        if (ioRecordMapper.create(ioRecord) == 1) {
            Storage storage = storageMapper.readByMedicineIdAndRepositoryId(ioRecord.getMedicineId(), ioRecord.getRepoId());
//            入库
            if (ioRecord.getType() == 1) {
                int result = storage.getInventory() + ioRecord.getQuantity();
                if (result > storage.getLimit()) {
                    return null;
                } else {
                    storage.setInventory(result);
                    storageMapper.update(storage);
                    return ioRecord;
                }
//                出库
            } else {
                int result = storage.getInventory() - ioRecord.getQuantity();
                if (result < 0) {
                    return null;
                } else {
                    storage.setInventory(result);
                    storageMapper.update(storage);
                    return ioRecord;
                }
            }

        } else  {
            return null;
        }
    }

    @Secured("ROLE_STORAGE")
    public List<IoRecord> getAll() {
        return ioRecordMapper.readAll();
    }

    @Secured("ROLE_STORAGE")
    public Boolean delete(Integer id) {
        return ioRecordMapper.delete(id) == 1;
    }
}
