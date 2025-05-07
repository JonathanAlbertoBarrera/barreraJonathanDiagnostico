package com.example.diagnosticoJona.cliente;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
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
}
