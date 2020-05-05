package com.example.pharmacy.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StorageService {

    private final StorageMapper storageMapper;

    @Autowired
    public StorageService(StorageMapper storageMapper) {
        this.storageMapper = storageMapper;
    }

    @Secured("ROLE_STORAGE")
    public Storage create(Storage storage) {
        storage.setRepoId(storage.getRepo().getId());
        storage.setMedicineId(storage.getMedicine().getId());
        if (storageMapper.create(storage) == 1) {
            return storage;
        } else  {
            return null;
        }
    }

    @Secured("ROLE_STORAGE")
    public List<Storage> getAll() {
        return storageMapper.readAll();
    }

    @Secured("ROLE_STORAGE")
    public Storage update(Storage storage) {
        if (storage.getInventory() > storage.getLimit()) {
            return null;
        }
        if (storageMapper.update(storage) > 0) {
            return storage;
        } else {
            return null;
        }
    }

    @Secured("ROLE_STORAGE")
    public Boolean delete(Integer id) {
        return storageMapper.delete(id) == 1;
    }
}
