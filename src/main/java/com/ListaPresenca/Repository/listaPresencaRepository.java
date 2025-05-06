package com.ListaPresenca.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ListaPresenca.Entidade.listaPresenca;

public interface listaPresencaRepository extends JpaRepository<listaPresenca, Long> {
    
    List<listaPresenca> findByNome(String nome);
    List<listaPresenca> findByCargo(String cargo);
    List<listaPresenca> findByEmpresa(String empresa);

}
