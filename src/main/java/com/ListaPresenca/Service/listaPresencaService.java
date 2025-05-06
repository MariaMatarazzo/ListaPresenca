package com.ListaPresenca.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ListaPresenca.Entidade.listaPresenca;
import com.ListaPresenca.Repository.listaPresencaRepository;

@Service
public class listaPresencaService {
	private final listaPresencaRepository listaPresencaRepository;

    @Autowired
    public listaPresencaService(listaPresencaRepository listaPresencaRepository) {
        this.listaPresencaRepository = listaPresencaRepository;
    }

    public List<listaPresenca> getAllPresencas() {
        return listaPresencaRepository.findAll();
    }

    public listaPresenca getPresencaById(Long id) {
        Optional<listaPresenca> presenca = listaPresencaRepository.findById(id);
        return presenca.orElse(null);
    }

    public List<listaPresenca> buscarPorNome(String nome) {
        return listaPresencaRepository.findByNome(nome);
    }

    public List<listaPresenca> buscarPorCargo(String cargo) {
        return listaPresencaRepository.findByCargo(cargo);
    }

    public List<listaPresenca> buscarPorEmpresa(String empresa) {
        return listaPresencaRepository.findByEmpresa(empresa);
    }

    public listaPresenca salvarPresenca(listaPresenca presenca) {
        return listaPresencaRepository.save(presenca);
    }

    public listaPresenca updatePresenca(Long id, listaPresenca updatedPresenca) {
        Optional<listaPresenca> existing = listaPresencaRepository.findById(id);
        if (existing.isPresent()) {
            updatedPresenca.setId(id);
            return listaPresencaRepository.save(updatedPresenca);
        }
        return null;
    }

    public boolean deletePresenca(Long id) {
        Optional<listaPresenca> existing = listaPresencaRepository.findById(id);
        if (existing.isPresent()) {
            listaPresencaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
