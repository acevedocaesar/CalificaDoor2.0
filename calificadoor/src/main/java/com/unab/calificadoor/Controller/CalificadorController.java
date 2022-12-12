package com.unab.calificadoor.Controller;

import com.unab.calificadoor.Models.Calificador;
import com.unab.calificadoor.Dao.CalificadorDao;
import com.unab.calificadoor.Services.CalificadorService;


import java.util.List;


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
@RequestMapping("/calificador")
public class CalificadorController {
    
    @Autowired
    private CalificadorDao calificadorDao;
    @Autowired
    private CalificadorService calificadorService;


    @PostMapping(value="/")
    public ResponseEntity<Calificador> agregar(@RequestBody Calificador calificador){   
            Calificador obj = calificadorService.save(calificador);
            return new ResponseEntity<>(obj, HttpStatus.OK);

    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Calificador> eliminar(@PathVariable String id){ 
        Calificador obj = calificadorService.findById(id); 
        if(obj!=null) 
            calificadorService.delete(id);
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @PutMapping(value="/") 
    public ResponseEntity<Calificador> editar(@RequestBody Calificador calificador){ 
        Calificador obj = calificadorService.findById(calificador.getId_calificador()); 
        if(obj!=null) {
           obj.setCalificacion(calificador.getCalificacion());
           calificadorService.save(obj);
        } 
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @GetMapping("/list")
    public List<Calificador> consultarTodo(){
        return calificadorService.findAll();
    } 

    @GetMapping("/list/{id}")
    public Calificador consultaPorId(@PathVariable String id){
        return calificadorService.findById(id);

    }

    @GetMapping("/consulta_calificador")
    public List<Calificador> consulta_calificador(@RequestParam("idc")String idc){
        return calificadorService.consulta_calificador(idc);

    }

}

