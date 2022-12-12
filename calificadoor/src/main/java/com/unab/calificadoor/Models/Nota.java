package com.unab.calificadoor.Models;

import java.sql.Date;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="nota")
public class Nota implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_nota")
    private int id_nota;
    @Column(name="fecha_entrega")
    private Date fecha_entrega;
    @Column(name="nota")
    private double nota;
    @ManyToOne
    @JoinColumn(name="id_calificador")
    private Calificador calificador;
    @Override
    public String toString() {
        return "Nota [id_nota=" + id_nota + ", fecha_entrega=" + fecha_entrega + ", nota=" + nota + ", calificador="
                + calificador + "]";
    }

    
    
}
