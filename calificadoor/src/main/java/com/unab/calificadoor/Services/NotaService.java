package com.unab.calificadoor.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unab.calificadoor.Dao.NotaDao;
import com.unab.calificadoor.Models.Nota;


@Service
public class NotaService {
    
    @Autowired
    private NotaDao notaDao;

    @Transactional(readOnly=false)
    public Nota save(Nota nota) {
        return notaDao.save(nota);
    }

    @Transactional(readOnly = false)
    public void delete (Integer id) {
        notaDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Nota findById(Integer id){
        return notaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Nota> findAll(){
        return (List<Nota>) notaDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Nota> consulta_nota(String idcal){
        return (List<Nota>) notaDao.consulta_nota(idcal);
    }
}

