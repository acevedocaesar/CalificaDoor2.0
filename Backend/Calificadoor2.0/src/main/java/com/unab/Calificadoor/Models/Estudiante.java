package com.unab.calificadoor.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="estudiante")
public class Estudiante  implements Serializable{
    @Id
    @NotEmpty(message = "El campo identificador del estudiante no debe ser vacío")
    @Column(name="id_estudiante")
    private String id_estudiante;
    @NotEmpty(message = "El campo nombre estudiante no debe ser vacío")
    @Size(min = 5, max = 80,message = "El campo nombre Estudiante debe tener mínimo 5 caracteres y máximo 80")
    @Column(name="nombre_estudiante")
    private String nombre_estudiante;
    @NotEmpty(message = "El campo clave estudiante no debe ser vacío")
    @Size(min = 5, max = 80,message = "El campo clave de Estudiante debe tener mínimo 5 caracteres y máximo 50")
    @Column(name="clave_estudiante")
    private String clave_estudiante;

    @Override
    public String toString() {
        return "Cliente [id_estudiante=" + id_estudiante + ", nombre_estudiante=" + nombre_estudiante + ", clave_estudiante="
                + clave_estudiante + "]";
    }
}
