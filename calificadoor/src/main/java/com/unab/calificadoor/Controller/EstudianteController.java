package com.unab.calificadoor.Controller;

import com.unab.calificadoor.Models.Estudiante;
import com.unab.calificadoor.Dao.EstudianteDao;
import com.unab.calificadoor.Services.EstudianteService;


import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteDao estudianteDao;
    @Autowired
    private EstudianteService estudianteService;


    @PostMapping(value="/")
    public ResponseEntity<Estudiante> agregar (@Valid @RequestBody Estudiante estudiante){   
            Estudiante obj = estudianteService.save(estudiante);
            return new ResponseEntity<>(obj, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Estudiante> eliminar(@PathVariable String id){ 
        Estudiante obj = estudianteService.findById(id); 
        if(obj!=null) 
            estudianteService.delete(id);
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @PutMapping(value="/") 
    public ResponseEntity<Estudiante> editar(@Valid @RequestBody Estudiante estudiante){ 
        Estudiante obj = estudianteService.findById(estudiante.getId_estudiante()); 
        if(obj!=null) {
           obj.setNombre_estudiante(estudiante.getNombre_estudiante());
           obj.setClave_estudiante(estudiante.getClave_estudiante());
           estudianteService.save(obj);
        } 
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @GetMapping("/list")
    public List<Estudiante> consultarTodo(){
        return estudianteService.findAll();
    } 

    @GetMapping("/list/{id}")
    public Estudiante consultaPorId(@PathVariable String id){
        return estudianteService.findById(id);

    }

    @GetMapping("/login")
    public Estudiante login(@RequestParam("usuario")String usuario, @RequestParam("clave")String clave){
        return estudianteService.login(usuario, clave);

    }

}
