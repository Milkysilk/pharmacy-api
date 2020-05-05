package com.example.pharmacy.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Secured("ROLE_BASIC")
public class RepoService {

    private final RepoMapper repoMapper;

    @Autowired
    public RepoService(RepoMapper repoMapper) {
        this.repoMapper = repoMapper;
    }

    @Secured("ROLE_REPO")
    public Repo create(Repo repo) {
        if (repoMapper.create(repo) == 1) {
            return repo;
        } else  {
            return null;
        }
    }

    @Secured({"ROLE_REPO", "ROLE_STORAGE"})
    public List<Repo> getAll() {
        return repoMapper.readAll();
    }

    @Secured("ROLE_REPO")
    public Boolean update(Repo repo) {
        return repoMapper.update(repo) > 0;
    }

    @Secured("ROLE_REPO")
    public Boolean delete(Integer id) {
        return repoMapper.delete(id) == 1;
    }
}
