package com.unab.calificadoor.Dao;

import com.unab.calificadoor.Models.Calificador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface CalificadorDao extends CrudRepository <Calificador, String>{

    @Transactional(readOnly = true)
    @Query(value="SELECT * from calificador WHERE id_estudiante= :idc", nativeQuery = true)
    public List<Calificador> consulta_calificador(@Param("idc")String idc);
    
}
