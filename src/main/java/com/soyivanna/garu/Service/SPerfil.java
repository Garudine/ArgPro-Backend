package com.soyivanna.garu.Service;

import com.soyivanna.garu.Entity.Perfil;
import com.soyivanna.garu.Repository.RPerfil;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SPerfil {

    @Autowired
    RPerfil rPerfil;

    public List<Perfil> list() {
        return rPerfil.findAll();
    }

    public Optional<Perfil> getOne(int id) {
        return rPerfil.findById(id);
    }

    public Optional<Perfil> getByImgPerfil(String imgperfil) {
        return rPerfil.findByImgPerfil(imgperfil);
    }

    public void save(Perfil perfil) {
        rPerfil.save(perfil);
    }

    public void delete(int id) {
        rPerfil.deleteById(id);
    }

    public boolean existsById(int id) {
        return rPerfil.existsById(id);
    }

    public boolean existsByImgPerfil(String imgperfil) {
        return rPerfil.existsByImgPerfil(imgperfil);
    }

}
