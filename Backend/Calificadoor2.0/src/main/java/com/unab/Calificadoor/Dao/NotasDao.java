package com.unab.calificadoor.Dao;
import com.unab.calificadoor.Models.Notas;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotasDao extends CrudRepository< Calificacion, Integer> {
    //Operación para seleccionar transacciones de una cuenta en particular (SELECT)
    @Transactional(readOnly=true)//No afecta integridad base de datos
    @Query(value="SELECT * FROM Notas WHERE id_calificadoor= :idEstu", nativeQuery=true)
    public List<Calificacion> consulta_califiacion(@Param("idEstu") String idEstu); 
    //Operación Crear transacción por depósito o retiro
    @Transactional(readOnly=false)
    @Modifying
    @Query(value="INSERT INTO calificacion(fecha_calificacion,valor_calificacion,id_calificadoor) VALUES (current_date(), :valor_calificacion, :idcta)", nativeQuery=true)
    public void crear_calificacion(@Param("idEstu") String idEstu,@Param("valor_calificacion") Double valor_calificacion);
}
