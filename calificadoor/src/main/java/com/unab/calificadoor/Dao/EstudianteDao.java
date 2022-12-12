package com.unab.calificadoor.Dao;

import com.unab.calificadoor.Models.Estudiante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;


public interface EstudianteDao extends CrudRepository <Estudiante, String> {
    //Operación para autentificación de Estudiante

    @Transactional(readOnly = true)
    @Query(value="SELECT * from estudiante WHERE id_estudiante= :usuario AND clave_estudiante= :clave", nativeQuery = true)
    public Estudiante login (@Param("usuario")String usuario, @Param("clave")String clave);


}
