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
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "razon_social", nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private String rfc;

    private String sitioWeb;

    private String descripcion;

    @OneToMany
    @JoinTable(
            name = "empresa_vacantes",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "vacante_id")
    )
    private List<Vacante> vacantes;

    private boolean activa;


    @PrePersist
    public void prePersist(){
        this.activa = false;
    }


}
