package com.example.diagnosticoJona.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Debes ingresar un nombre")
    @Pattern(
            regexp = "^[\\p{L} .'-]+$",
            message = "El nombre solo puede contener letras y espacios. No ingreses numeros "
    )
    private String nombre;

    @NotBlank(message = "Debes ingresar un apellido")
    @Pattern(
            regexp = "^[\\p{L} .'-]+$",
            message = "Los apellidos solo pueden contener letras y espacios. No ingreses numeros"
    )
    private String apellidos;
    @Column(unique = true)
    private String curp;
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellidos, String curp, Date fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
