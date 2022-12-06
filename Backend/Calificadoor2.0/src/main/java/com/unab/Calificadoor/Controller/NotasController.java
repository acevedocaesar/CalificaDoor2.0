package com.unab.Calificadoor.Controller;
import com.unab.Calificadoor.Models.Notas;
import com.unab.Calificadoor.Security.Hash;
import com.unab.Calificadoor.Models.EstudianteDao;
import com.unab.Calificadoor.Dao.NotasDao;
import com.unab.Calificadoor.Dao.EstudianteDao;
import com.unab.Calificadoor.Service.NotasService;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/notas")
public class NotasController {

    @Autowired
    private NotasDao notasDao;
    @Autowired
    private EstudianteDao estudianteDao;
    @Autowired
    private NotasService notasService;
    
    @PostMapping(value="/")
    @ResponseBody
    public ResponseEntity<Notas> agregar(@RequestBody Notas notas){   
        Notas obj = notasService.save(notas);
        return new ResponseEntity<>(obj, HttpStatus.OK);     
    }

    @PostMapping(value="/crear_calificacion") 
    public void crear_calificacion(@RequestParam ("idEstu") String idEstu,@RequestParam ("valor_calificacion") Double valor_calificacion,@RequestParam ("tipo") String tipo,@RequestHeader("clave")String clave,@RequestHeader("usuario")String usuario){ 
        Estudiante estudiante1=new Estudiante();
        estudiante1=estudianteDao.login(usuario, Hash.sha1(clave));
        if (estudiante1!=null) {
           notasService.crear_calificacion(idEstu, valor_calificacion, tipo);
        }
          
    }

    @DeleteMapping(value="/{id}") 
    public ResponseEntity<Calificacion> eliminar(@PathVariable Integer id){ 
        Calificacion obj = notasService.findById(id); 
        if(obj!=null) 
            notasService.delete(id);
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }
    @PutMapping(value="/") 
    public ResponseEntity<Calificacion> editar(@RequestBody Calificacion calificacion){ 
        Calificacion obj = notasService.findById(calificacion.getId_calificacion()); 
        if(obj!=null) {
           obj.setValor_calificacion(calificacion.getValor_califiacion());
           obj.setFecha_califiacion(calificacion.getFecha_calificacion());
           notasService.save(obj);
        } 
        else 
            return new ResponseEntity<>(obj, HttpStatus.INTERNAL_SERVER_ERROR); 
        return new ResponseEntity<>(obj, HttpStatus.OK); 
    }
    @GetMapping("/list") 
    public List<Calificacion> consultarTodo(){
        return notasService.findByAll(); 
    }
    @GetMapping("/list/{id}") 
    public Calificacion consultaPorId(@PathVariable Integer id){ 
        return notasService.findById(id); 
    }

    @GetMapping("/consulta_notas")
    @ResponseBody
    public ResponseEntity<List<Calificacion>> consulta_calificacion(@RequestParam ("idcta") String idcta,@RequestHeader ("usuario") String usuario,@RequestHeader ("clave") String clave) { 
        Estudiante estudiante=new Estudiante();
        estudiante=estudianteDao.login(usuario, Hash.sha1(clave));
        if (estudiante!=null) {
            return new ResponseEntity<>(notasService.consulta_calificacion(idEstu),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
 
    }
}
