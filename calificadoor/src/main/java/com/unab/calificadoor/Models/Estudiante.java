package com.unab.calificadoor.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="estudiante")
public class Estudiante implements Serializable{
    @Id
    @NotEmpty(message = "El id del Estudiante no puede estar vacio")
    @Column(name="id_estudiante")
    private String id_estudiante;
    @NotEmpty(message = "El nombre del Estudiante no puede estar vacio")
    @Column(name="nombre_estudiante")
    private String nombre_estudiante;
    @NotEmpty(message = "La clave del Estudiante no puede estar vacio")
    @Column(name="clave_estudiante")
    private String clave_estudiante;
    @Override
    public String toString() {
        return "estudiante [id_estudiante=" + id_estudiante + ", nombre_estudiante=" + nombre_estudiante
                + ", clave_estudiante=" + clave_estudiante + "]";
    }

    
}
