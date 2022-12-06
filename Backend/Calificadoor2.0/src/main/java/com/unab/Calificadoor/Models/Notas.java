package com.unab.Calificadoor.Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name="Notas")
public class Notas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_notas")
    private int id_notas;
    //@NotEmpty(message = "EL campo Fecha transacción no debe ser vacío")
    @Column(name="fecha_notas")
    private Date fecha_notas;
    //@NotEmpty(message = "EL campo valor transacción no debe ser vacío")
    @Column(name="valor_notas")
    private double valor_notas;
    //@NotEmpty(message = "EL campo Tipo transacción no debe ser vacío")
    @Column(name="tipo_notas")
    private String tipo_noas;
    //@NotEmpty(message = "EL campo identificador cuenta que realizó la transacción no debe ser vacío")
    @ManyToOne
    @JoinColumn(name="id_calificadoor")
    private Calificadoor calificadoor;

    @Override
    public String toString() {
        return "Notas [id_notas=" + id_notas + ", fecha_notas=" + fecha_notas
                + ", valor_notas=" + valor_notas + ", tipo_notas=" + tipo_notas + ", calificadoor="
                + calificadoor + "]";
    }
}
