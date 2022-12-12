package com.unab.calificadoor.Dao;

import com.unab.calificadoor.Models.Nota;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

public interface NotaDao extends CrudRepository<Nota, Integer> {

    @Transactional(readOnly = true)
    @Query(value="SELECT * from nota WHERE id_calificador= :idcal", nativeQuery = true)
    public List<Nota> consulta_nota (@Param("idcal")String idcal);
    
}
