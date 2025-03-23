package com.sebaorrego.foringa.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sebaorrego.foringa.model.Rol;
import com.sebaorrego.foringa.model.Usuarios;

public interface RolRepository extends JpaRepository<Rol, Long> {

}
