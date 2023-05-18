package com.soyivanna.garu.Controller;

import com.soyivanna.garu.Dto.dtoPersonas;
import com.soyivanna.garu.Entity.Persona;
import com.soyivanna.garu.Security.Controller.Mensaje;
import com.soyivanna.garu.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://soyivanna.web.app","http://localhost:4200"})
public class PersonaController {

    @Autowired
    ImpPersonaService impPersonaServ;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = impPersonaServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
        @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!impPersonaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = impPersonaServ.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!impPersonaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        impPersonaServ.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersonas dtopersonas) {
        if (StringUtils.isBlank(dtopersonas.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (impPersonaServ.existsByNombre(dtopersonas.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopersonas.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido  es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopersonas.getPuesto())) {
            return new ResponseEntity(new Mensaje("El puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
       if (StringUtils.isBlank(dtopersonas.getImgBanner())) {
            return new ResponseEntity(new Mensaje("La imagen banner es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        
        Persona persona = new Persona(
                dtopersonas.getNombre(),dtopersonas.getApellido(), dtopersonas.getPuesto(),dtopersonas.getImgBanner()
        );
        impPersonaServ.save(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);

    }
     
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersonas dtopersonas) {
        if (!impPersonaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if (impPersonaServ.existsByNombre(dtopersonas.getNombre()) && impPersonaServ.getByNombre(dtopersonas.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersonas.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopersonas.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersonas.getPuesto())) {
            return new ResponseEntity(new Mensaje("El puesto no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersonas.getImgBanner())) {
            return new ResponseEntity(new Mensaje("La imagen banner inicio no puede estar vacia"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = impPersonaServ.getOne(id).get();

        persona.setNombre(dtopersonas.getNombre());
        persona.setApellido(dtopersonas.getApellido());
        persona.setPuesto(dtopersonas.getPuesto());
        persona.setImgBanner(dtopersonas.getImgBanner());

        impPersonaServ.save(persona);

        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
    
}
