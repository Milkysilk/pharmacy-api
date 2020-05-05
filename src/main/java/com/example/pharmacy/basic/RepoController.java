package com.example.pharmacy.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repos")
public class RepoController {

    private final RepoService repoService;

    @Autowired
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }

    @PostMapping
    public ResponseEntity<?> newRepo(@RequestBody Repo repo) {
        Repo repo1 = repoService.create(repo);
        if (repo1 == null) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(repo1, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getRepos() {
        return new ResponseEntity<>(repoService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRepo(@RequestBody Repo repo) {
        if (!repoService.update(repo)) {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(repo, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (repoService.delete(Integer.parseInt(id))) {
            return new ResponseEntity<>("删除成功", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("发生错误", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
