package com.soyivanna.garu.Controller;

import com.soyivanna.garu.Dto.dtoPerfil;
import com.soyivanna.garu.Entity.Perfil;
import com.soyivanna.garu.Security.Controller.Mensaje;
import com.soyivanna.garu.Service.SPerfil;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfil")
@CrossOrigin(origins = {"https://argentinaprograma-ivanna.web.app","http://localhost:4200"})
public class CPerfil {
    @Autowired
    SPerfil sPerfil;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Perfil>> list() {
        List<Perfil> list = sPerfil.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Perfil> getById(@PathVariable("id") int id) {
        if (!sPerfil.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Perfil perfil = sPerfil.getOne(id).get();
        return new ResponseEntity(perfil, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPerfil dtoperfil) {
        if (StringUtils.isBlank(dtoperfil.getImgPerfil())) {
            return new ResponseEntity(new Mensaje("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sPerfil.existsByImgPerfil(dtoperfil.getImgPerfil())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Perfil proyectos = new Perfil(
                dtoperfil.getImgPerfil()
        );
        sPerfil.save(proyectos);
        return new ResponseEntity(new Mensaje("Perfil creado"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPerfil dtoperfil) {
        if (!sPerfil.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (sPerfil.existsByImgPerfil(dtoperfil.getImgPerfil()) && sPerfil.getByImgPerfil(dtoperfil.getImgPerfil()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese perfil ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoperfil.getImgPerfil())) {
            return new ResponseEntity(new Mensaje("La imagen no puede estar vacia"), HttpStatus.BAD_REQUEST);
        }

        Perfil perfil = sPerfil.getOne(id).get();

        perfil.setImgPerfil(dtoperfil.getImgPerfil());

        sPerfil.save(perfil);

        return new ResponseEntity(new Mensaje("Perfil actualizado"), HttpStatus.OK);
    }
    
    
    
}
