package com.unab.calificadoor.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="calificador")
public class Calificador implements Serializable {

    @Id
    @Column(name="id_calificador")
    private String id_calificador;
    @Column(name="calificacion")
    private double calificacion;
    @ManyToOne
    @JoinColumn(name="id_estudiante")
    private Estudiante estudiante;

    @Override
    public String toString() {
        return "Calificador [id_calificador=" + id_calificador + ", calificacion=" + calificacion + ", estudiante="
                + estudiante + "]";
    }

    
}
