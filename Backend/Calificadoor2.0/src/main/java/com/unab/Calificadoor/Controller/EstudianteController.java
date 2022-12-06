package com.unab.Calificadoor.Controller;
import com.unab.Calificadoor.Models.Estudiante;
import com.unab.Calificadoor.Models.Administrador;
import com.unab.Calificadoor.Security.Hash;
import com.unab.Calificadoor.Dao.EstudianteDao;
import com.unab.Calificadoor.Dao.AdministradorDao;
import com.unab.Calificadoor.Service.EstudianteService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/estudiante")
public class EstudianteController {
    @Autowired
    private EstudianteDao estudianteDao; 
    @Autowired
    private AdministradorDao administradorDao;
    @Autowired
    private EstudianteService estudianteService;
    
    @PostMapping(value="/")
    @ResponseBody
    public ResponseEntity<Estudiante> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Estudiante estudiante){   
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            estudiante.setClave_estudiante(Hash.sha1(estudiante.getClave_estudiante()));
            return new ResponseEntity<>(estudianteService.save(estudiante), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }
            
    }
   
    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Estudiante> eliminar(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            Estudiante obj = estudianteService.findById(id); 
            if(obj!=null) 
                estudianteService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
      
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
       
        
    }
    
    @PutMapping(value="/") 
    @ResponseBody
    public ResponseEntity<Estudiante> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody Estudiante estudiante){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            estudiante.setClave_estudiante(Hash.sha1(estudiante.getClave_estudiante()));
            Estudiante obj = estudianteService.findById(estudiante.getId_estudiante()); 
            if(obj!=null) { 
                obj.setNombre_estudiante(estudiante.getNombre_estudiante());
                obj.setClave_estudiante(estudiante.getClave_estudiante());
                estudianteService.save(estudiante); 
            } 
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    }
   
    @GetMapping("/list") 
    @ResponseBody
    public ResponseEntity<List<Estudiante>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(estudianteService.findAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }  
          
    }
    
    @GetMapping("/list/{id}") 
    @ResponseBody
    public ResponseEntity<Estudiante> consultaPorId(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(estudianteService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }
    
    @GetMapping("/login")
    @ResponseBody
    public Estudiante ingresar(@RequestParam ("usuario") String usuario,@RequestParam ("clave") String clave) {
        clave=Hash.sha1(clave);
        return estudianteService.login(usuario, clave); 
    }
}
