package com.unab.calificadoor.Service;
import com.unab.calificadoor.Models.Cliente;
import com.unab.calificadoor.Dao.ClienteDao;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteDao estudianteDao;

    @Transactional(readOnly=false)
    public Estudiante save(Estudiante estudiante) {
        return estudianteDao.save(estudiante);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        estudianteDao.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Estudiante findById(String id) {
        return estudianteDao.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Estudiante> findAll() {
        return (List<Estudiante>) estudianteDao.findAll();
    }

    @Transactional(readOnly=true)
    public Estudiante login(String usuario, String clave) {
        return estudianteDao.login(usuario, clave);
    }

}
