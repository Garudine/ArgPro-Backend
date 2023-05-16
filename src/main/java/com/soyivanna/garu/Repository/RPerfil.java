package com.soyivanna.garu.Repository;

import com.soyivanna.garu.Entity.Perfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPerfil extends JpaRepository<Perfil, Integer>{
    public Optional<Perfil> findByImgPerfil(String imgperfil);
    public boolean existsByImgPerfil(String imgperfil);
}
