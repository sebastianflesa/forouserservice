package com.sebaorrego.foringa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String fechaNacimiento;
    private Long id_rol;
    private RolDTO rol;
    private Integer estado;
}
