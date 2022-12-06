package com.unab.Calificadoor.Controller;
import com.unab.Calificadoor.Models.Calificadoor;
import com.unab.Calificadoor.Security.Hash;
import com.unab.Calificadoor.Dao.CalificadoorDao;
import com.unab.Calificadoor.Models.Estudiante;
import com.unab.Calificadoor.Dao.EstudianteDao;
import com.unab.Calificadoor.Models.Administrador;
import com.unab.Calificadoor.Dao.AdministradorDao;
import com.unab.Calificadoor.Service.EstudianteService;
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
@RequestMapping("/Calificadoor")
public class CalificadoorController {
    
    @Autowired
    private CalificadoorDao CalificadoorDao;

    @Autowired
    private AdministradorDao administradorDao;

    @Autowired
    private EstudianteDao EstudianteDao;

    @Autowired
    private CalificadoorService CalificadoorService;
    
    
    
    @PostMapping(value="/")
    @ResponseBody
    public ResponseEntity<Calificadoor> agregar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario, @Valid @RequestBody Calificadoor calificadoor){   
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(calificadoorService.save(calificadoor), HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); 
        }
            
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Calificadoor> eliminar(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
       if (admon!=null) {
            Calificadoor obj = calificadoorService.findById(id); 
            if(obj!=null) 
                calificadoorService.delete(id);
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
      
       } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
       
        
    }


    @PutMapping(value="/") 
    @ResponseBody
    public ResponseEntity<Calificadoor> editar(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario,@Valid @RequestBody Calificadoor calificadoor){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            Calificadoor obj = calificadoorService.findById(calificadoor.getId_calificadoor()); 
            if(obj!=null) { 
                obj.setFecha_entrega(calificadoor.getFecha_entrega());
                obj.setNota_calificadoor(calificadoor.getNota_calificadoor());
                obj.setEstudiante(calificadoor.getEstudiante());
                calificadoorService.save(calificadoor); 
            } 
            else 
                return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
            return new ResponseEntity<>(obj, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    }

    @PutMapping(value="/entrega") 
    public void entrega(@RequestParam ("idEst") String idEst,@RequestParam ("tarea") String tarea,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Estudiante estudiante1=new Estudiante();
        estudiante1=estudianteDao.login(usuario, Hash.sha1(clave));
        if (estudiante1!=null) {
           estudianteService.tarea(idEst, tarea); 
        }
          
    }

    @GetMapping("/notas") 
    @ResponseBody
    public ResponseEntity<List<Calificadoor>> consultarTodo(@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(calificadoorService.findByAll(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }  
          
    }

    @GetMapping("/notas/{id}") 
    @ResponseBody
    public ResponseEntity<Calificadoor> consultaPorId(@PathVariable String id,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Administrador admon=new Administrador();
        admon=administradorDao.login(usuario, Hash.sha1(clave));
        if (admon!=null) {
            return new ResponseEntity<>(calificadoorService.findById(id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }   
    }


    @GetMapping("/consulta_calificadoor")
    @ResponseBody
    public ResponseEntity<List<Calificadoor>> consulta_calificadoor(@RequestParam ("idc") String idc,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Estudiante estudiante=new Estudiante();
        estudiante=estudianteDao.login(usuario, Hash.sha1(clave));
        if (estudiante!=null) {
            return new ResponseEntity<>(estudianteService.consulta_calificadoor(idc),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        
    }
}
