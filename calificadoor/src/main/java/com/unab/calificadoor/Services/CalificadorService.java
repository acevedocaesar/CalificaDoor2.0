package com.unab.calificadoor.Services;

import com.unab.calificadoor.Models.Calificador;
import com.unab.calificadoor.Dao.CalificadorDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificadorService {

    @Autowired
    private CalificadorDao calificadorDao;

    @Transactional(readOnly=false)
    public Calificador save(Calificador calificador) {
        return calificadorDao.save(calificador);
    }

    @Transactional(readOnly=false)
    public void delete(String id) {
        calificadorDao.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Calificador findById(String id) {
        return calificadorDao.findById(id).orElse(null);
    }

    @Transactional(readOnly=true)
    public List<Calificador> findAll() {
        return (List<Calificador>) calificadorDao.findAll();
    }

    @Transactional(readOnly=true)
    public List<Calificador> consulta_calificador(String idc) {
        return (List<Calificador>) calificadorDao.consulta_calificador(idc);
    }

    }

