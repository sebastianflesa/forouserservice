package com.sebaorrego.foringa.repository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sebaorrego.foringa.model.Usuarios;
import jakarta.transaction.Transactional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
    List<Usuarios> findAll();
    @Query("SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario AND u.contraseña = :contraseña")
    Optional<Usuarios> findByNombreUsuarioContrasena(@Param("nombreUsuario") String nombreUsuario, @Param("contraseña") String contraseña);
    @Query("SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario AND u.email = :email")
    Optional<Usuarios> findByNombreUsuarioEmail(@Param("nombreUsuario") String nombreUsuario, @Param("email") String email);
    Usuarios save(Usuarios usuario);
    Optional<Usuarios> findById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Usuarios u SET u.estado = :estado WHERE u.id = :id")
    int updateEstado(@Param("estado") Integer estado, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Usuarios u WHERE u.id = :id")
    int deleteUsuario(@Param("id") Long id);

    //update usuarios
    @Modifying
    @Transactional
    @Query("UPDATE Usuarios u SET u.nombre = :nombre, u.nombreUsuario = :nombreUsuario, u.email = :email, u.fechaNacimiento = :fechaNacimiento, u.contraseña = :contraseña WHERE u.id = :id")
    int updateUsuarios(@Param("nombre") String nombre, @Param("nombreUsuario") String nombreUsuario, @Param("email") String email, @Param("fechaNacimiento") Date fechaNacimiento, @Param("contraseña") String contraseña, @Param("id") Long id);


}
