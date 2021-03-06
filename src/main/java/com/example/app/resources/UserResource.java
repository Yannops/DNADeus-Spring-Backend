package com.example.app.resources;

import com.example.app.domain.User;
import com.example.app.services.UserService;

import com.example.app.services.MinistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ministries")
@CrossOrigin(origins = "http://localhost:3000")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private MinistryService ministryService;

    @GetMapping(value = "/{id}/users")
    public ResponseEntity<List<User>> findAll(@PathVariable Integer id){
        List<User> ministerioList = userService.findAll(id);
        System.out.println("");
        return ResponseEntity.ok().body(ministerioList);
    }

    @GetMapping(value = "/{idMin}/users/{idMem}")
    public ResponseEntity<User> findById(@PathVariable Integer idMin, @PathVariable Integer idMem){
        User objMem = userService.findById(idMin, idMem);
        System.out.println("");
        return ResponseEntity.ok().body(objMem);
    }

    @PostMapping(path = "/{id}/users")
    public ResponseEntity<Void> insert(@PathVariable Integer id, @RequestBody User obj){
        userService.insert(id, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        System.out.println("");
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{idMin}/users/{idMem}")
    public ResponseEntity<Void> delete(@PathVariable Integer idMin, @PathVariable Integer idMem){
        userService.delete(idMin, idMem);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping(value = "/{id}")
    public ResponseEntity<Membro> update(@PathVariable Integer id, @RequestBody Membro obj){
        obj = membroService.update(id, obj);
        return ResponseEntity.noContent().build();
    }*/
}
