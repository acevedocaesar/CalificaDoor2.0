package com.unab.Calificadoor.Dao;
import com.unab.Calificadoor.Models.Calificadoor;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CalificadoorDao  extends CrudRepository<Calificadoor, String> {
    //Operación para seleccionar cuentas de un cliente en particular (SELECT)
    @Transactional(readOnly=true)//No afecta integridad base de datos
    @Query(value="SELECT * FROM calificadoor WHERE id_estudiante= :idc", nativeQuery=true)
    public List<Calificadoor> consulta_calificadoor(@Param("idc") String idc); 
    //Cálculo promedio
    @Transactional(readOnly=false)
    @Modifying
    @Query(value="UPDATE calificadoor SET estado_calificadoor=estado_calificadoor + :valor_entrega WHERE id_cuenta like :idEstu", nativeQuery=true)
    public void deposito(@Param("idcta") String idEstu,@Param("valor_deposito") Double valor_deposito); 
    
}
