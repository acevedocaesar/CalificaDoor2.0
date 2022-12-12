package com.unab.calificadoor.Controller;

import com.unab.calificadoor.Models.Nota;
import com.unab.calificadoor.Dao.NotaDao;
import com.unab.calificadoor.Services.NotaService;
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
@RequestMapping("/nota")
public class NotaController {
    @Autowired
    private NotaDao NotaDao;
    @Autowired
    private NotaService notaService;

    @PostMapping(value="/")
    public ResponseEntity<Nota> agregar(@RequestBody Nota nota){        
        Nota obj = notaService.save(nota);
        return new ResponseEntity<>(obj, HttpStatus.OK);     
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Nota> eliminar(@PathVariable Integer id){ 
        Nota obj = notaService.findById(id); 
        if(obj!=null) 
            notaService.delete(id); 
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @PutMapping(value="/") 
    public ResponseEntity<Nota> editar(@RequestBody Nota nota){ 
        Nota obj = notaService.findById(nota.getId_nota()); 
        if(obj!=null) { 
            obj.setFecha_entrega(nota.getFecha_entrega());
            obj.setNota(nota.getNota());
            notaService.save(obj); 
        } 
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }

    @GetMapping("/list") 
    public List<Nota> consultarTodo(){
        return notaService.findAll();
    }
    
    @GetMapping("/list/{id}") 
    public Nota consultaPorId(@PathVariable Integer id){ 
        return notaService.findById(id); 
    }

    @GetMapping("/consulta_nota")
    public List<Nota> consulta_nota(@RequestParam("idcal")String idcal){
        return notaService.consulta_nota(idcal);
    }
}