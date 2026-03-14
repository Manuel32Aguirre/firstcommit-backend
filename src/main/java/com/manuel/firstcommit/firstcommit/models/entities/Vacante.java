package com.manuel.firstcommit.firstcommit.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "vacante_tecnologias",
            joinColumns = @JoinColumn(name = "vacante_id"),
            inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private List<Tecnologia> tecnologiasRequeridas;

    private Double sueldo;

    private boolean activa;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="modalidad_id")
    private Modalidad modalidad;

    @PrePersist
    public void prePersist(){
        this.activa = false;
    }


}
