package com.sebaorrego.foringa.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sebaorrego.foringa.model.Usuarios;

@Service
public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Optional<Usuarios> findByNombreUsuarioContrasena(String nombreUsuario, String contrasena);
    Optional<Usuarios> findByNombreUsuarioEmail(String nombreUsuario, String email);
    Usuarios crearUsuario(Usuarios usuario);
    int updateUsuarios(Usuarios usuario);
    Optional<Usuarios> findById(Long id);
    Optional<Usuarios> updateEstado(Long id, Integer estado);
    Optional<Usuarios> deleteUsuario(Long id);


}
