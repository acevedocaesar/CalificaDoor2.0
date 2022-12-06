package com.unab.Calificadoor.Dao;
import com.unab.Calificadoor.Models.Estudiante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EstudianteDao extends CrudRepository< Estudiante, String>  {
    //Operación de Autentiiicación (SELECT)
    @Transactional(readOnly=true)//No afecta integridad base de datos
    @Query(value="SELECT * FROM estudiante WHERE id_estudiante= :usuario AND clave_estudiante= :clave", nativeQuery=true)
    public Estudiante login(@Param("usuario") String usuario, @Param("clave") String clave); 

}
