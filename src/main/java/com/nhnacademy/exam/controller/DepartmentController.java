package com.nhnacademy.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {
    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<String> findDepartment(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {

        if (accountService.exists(id)) {
            try {
                accountService.deleteAccount(id);
            } catch (RuntimeException e) {
                return ResponseEntity.internalServerError().build();
            }
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();

    }
}
