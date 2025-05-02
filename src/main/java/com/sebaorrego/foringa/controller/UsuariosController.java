package com.sebaorrego.foringa.controller;
import org.springframework.web.bind.annotation.RestController;

import com.sebaorrego.foringa.model.Rol;
import com.sebaorrego.foringa.model.Usuarios;
import com.sebaorrego.foringa.service.UsuariosService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sebaorrego.foringa.dto.ResponseDTO;
import com.sebaorrego.foringa.dto.RolDTO;
import com.sebaorrego.foringa.dto.UsuarioDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    @Autowired
    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/lista")
    public List<Usuarios> listaUsuarios() {
        return usuariosService.getAllUsuarios();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody Usuarios usuario) {
        //System.out.println(usuariosService.findByNombreUsuarioContrasena(usuario.getNombreUsuario(), usuario.getContraseña()));
        Optional<Usuarios> usuarioResultado = usuariosService.findByNombreUsuarioContrasena(usuario.getNombreUsuario(), usuario.getContraseña());
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuarioResultado.get().getId());
        usuarioDTO.setNombre(usuarioResultado.get().getNombre());
        usuarioDTO.setNombreUsuario(usuarioResultado.get().getNombreUsuario());
        usuarioDTO.setEmail(usuarioResultado.get().getEmail());
        usuarioDTO.setEstado(usuarioResultado.get().getEstado());
        usuarioDTO.setFechaNacimiento(usuarioResultado.get().getFechaNacimiento().toString());
        
        Rol rol = usuarioResultado.get().getRol();
        RolDTO rolDTO = new RolDTO();
        rolDTO.setNombreRol(rol.getNombreRol());
        usuarioDTO.setId_rol(rol.getId());
        usuarioDTO.setRol(rolDTO);

        Object data = null;
       if (usuarioResultado.isPresent()) {
            ResponseDTO response = new ResponseDTO(true, "Usuario logeado", usuarioDTO);
            return ResponseEntity.ok(response);
        } else {
            ResponseDTO response = new ResponseDTO(false, "Contraseña o usuario invalido", data);
            return ResponseEntity.status(401).body(response);
        }
       
    }

    @PostMapping("/crear")
    public ResponseEntity<ResponseDTO> crearUsuario(@RequestBody Usuarios usuario) {
        Usuarios nuevoUsuario = new Usuarios();
        //System.out.println("Rol recibido: " + usuario.getRol().getId());
        nuevoUsuario.setNombre(usuario.getNombre());
        nuevoUsuario.setNombreUsuario(usuario.getNombreUsuario());
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
        nuevoUsuario.setContraseña(usuario.getContraseña());
        nuevoUsuario.setEstado(usuario.getEstado());
        ResponseDTO response;

        try {
            if (usuario.getRol().getId() != null) {
                nuevoUsuario.setRol(usuario.getRol());
            } 
            Optional usuarioExistente =  usuariosService.findByNombreUsuarioEmail(usuario.getNombreUsuario(), usuario.getEmail());
            if (usuarioExistente.isPresent()) {
                response = new ResponseDTO(false, "Usuario ya existe", null);
                return ResponseEntity.status(400).body(response);
            }
            Usuarios usuarioGuardado = usuariosService.crearUsuario(nuevoUsuario);
            response = new ResponseDTO(true, "Usuario creado", usuarioGuardado);
        } catch (Exception e) {
            response = new ResponseDTO(false, "Error al crear usuario", e.getMessage());
        }
        
        return ResponseEntity.ok(response);
        
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ResponseDTO> actualizarUsuario(@RequestBody Usuarios usuario) {
        Optional usuarioExistente = usuariosService.findById(usuario.getId());
        
        ResponseDTO response;
        if (usuarioExistente.isPresent()) {
            try {
                int update = usuariosService.updateUsuarios(usuario);
                if (update == 1) {
                    response = new ResponseDTO(true, "Usuario actualizado", usuario);
                } else {
                    response = new ResponseDTO(false, "Error al actualizar usuario", null);
                }
            } catch (Exception e) {
                response = new ResponseDTO(false, "Error al actualizar usuario", null);
            }
        }else{
            response = new ResponseDTO(false, "Usuario no existe", null);
        }
        return ResponseEntity.status(200).body(response);

    }

    @PostMapping("/estado")
    public ResponseEntity<ResponseDTO> cambiarEstado(@RequestBody Usuarios usuario) {
        Optional<Usuarios> usuarioExistente = usuariosService.findById(usuario.getId());
        
        ResponseDTO response;
        if (usuarioExistente.isPresent()) {
            Optional<Usuarios> usuarioActualizado = usuariosService.updateEstado(usuario.getId(), usuario.getEstado());
            response = new ResponseDTO(true, "Estado usuario actualizado", usuarioActualizado);
        }else{
            response = new ResponseDTO(false, "Usuario no existe", null);
        }
        return ResponseEntity.status(200).body(response);

    }

    @PostMapping("/borrar")
    public ResponseEntity<ResponseDTO> borrarUsuario(@RequestBody Usuarios usuario) {
        Optional<Usuarios> usuarioExistente = usuariosService.findById(usuario.getId());
        
        ResponseDTO response;
        if (usuarioExistente.isPresent()) {
            Optional<Usuarios> usuarioBorrado = usuariosService.deleteUsuario(usuario.getId());
            response = new ResponseDTO(true, "Usuario borrado", usuarioBorrado);
        }else{
            response = new ResponseDTO(false, "Usuario no existe", null);
        }
        return ResponseEntity.status(200).body(response);

    }

           

}
