package sn.edu.isepdiamniadio.dbe.WorkingExpress.controller;

import org.springframework.web.bind.annotation.*;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Role;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.Services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Role one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Role create(@RequestBody Role e) {
        return service.save(e);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role e) {
        e.setId(id);
        return service.save(e);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}