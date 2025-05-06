package com.example.diagnosticoJona.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ClienteDTO {
    @NotBlank(message = "Debes ingresar un nombre bro")
    private String nombre;
    @NotBlank(message = "Debes ingresar un apellido bro")
    private String apellidos;
    @Pattern(regexp = "^[A-Z]{4}\\d{6}[A-Z]{6}\\d{2}$", message = "La CURP debe tener el formato correcto")
    private String curp;
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String apellidos, String curp, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
