package com.unab.Calificadoor.Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="calificadoor")
public class Calificadoor implements Serializable {
    @Id
    @NotEmpty(message = "El campo identificador calificadoor no debe ser vacío")
    @Column(name="id_calificadoor")
    private String id_calificadoor;
    @Column(name="fecha_entrega")
    private Date fecha_entrega;
    @Column(name="estado_calificacion")
    private double estado_calificacion;
    @ManyToOne
    @JoinColumn(name="id_estudiante")
    private Estudiante estudiante;
    
    @Override
    public String toString() {
        return "Calificadoor [id_calificadoor=" + id_calificadoor + ", fecha_entrega=" + fecha_entrega + ", estado_calificacion="
                + estado_calificacion + ", estudiante=" + estudiante + "]";
    }

    

}
