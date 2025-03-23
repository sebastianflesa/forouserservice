package com.sebaorrego.foringa.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebaorrego.foringa.model.Usuarios;
import com.sebaorrego.foringa.model.Rol;
import com.sebaorrego.foringa.repository.UsuariosRepository;
import com.sebaorrego.foringa.repository.RolRepository;
import com.sebaorrego.foringa.dto.UsuarioDTO;
import com.sebaorrego.foringa.dto.RolDTO;

@Service
public class UsuariosServiceImpl implements UsuariosService {
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private RolRepository rolRepository;

    @Override
    public List getAllUsuarios() {  
        List<Usuarios> usuarios = usuariosRepository.findAll();
        return usuarios.stream().map(usuario -> {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setEstado(usuario.getEstado());
            
            String fechaNacimientoStr = usuario.getFechaNacimiento().toString();
            usuarioDTO.setFechaNacimiento(fechaNacimientoStr);
            Rol rol = usuario.getRol();
            RolDTO rolDTO = new RolDTO();
            rolDTO.setNombreRol(rol.getNombreRol());
            usuarioDTO.setId_rol(rol.getId());
            usuarioDTO.setRol(rolDTO);
            return usuarioDTO;
        }).collect(Collectors.toList());
    
    
    }

    public Optional<Usuarios> findByNombreUsuarioContrasena(String nombreUsuario, String contrasena) {
        return usuariosRepository.findByNombreUsuarioContrasena(nombreUsuario, contrasena);
    }

    public Usuarios crearUsuario(Usuarios usuario) {
        return usuariosRepository.save(usuario);
        
    }

    @Override
    public Optional<Usuarios> findByNombreUsuarioEmail(String nombreUsuario, String email) {
        return usuariosRepository.findByNombreUsuarioEmail(nombreUsuario, email);
        
    }

    @Override
    public int updateUsuarios(Usuarios usuario) {
        return usuariosRepository.updateUsuarios(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getFechaNacimiento(), usuario.getContraseña(), usuario.getId());
        
    }

    @Override
    public Optional<Usuarios> findById(Long id) {
        return usuariosRepository.findById(id);
        
    }

    @Override
    public Optional updateEstado(Long id, Integer estado) {
        int updatedRows = usuariosRepository.updateEstado(estado, id);
        if (updatedRows == 0) {
            throw new RuntimeException("No se encontró el usuario con ID: " + id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuarios> deleteUsuario(Long id) {
        int deletedRows = usuariosRepository.deleteUsuario(id);
        if (deletedRows == 0) {
            throw new RuntimeException("No se encontró el usuario con ID: " + id);
        }
        return Optional.empty();
    }
        
    


}
