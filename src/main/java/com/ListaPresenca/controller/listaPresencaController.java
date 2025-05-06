package com.ListaPresenca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ListaPresenca.Entidade.listaPresenca;
import com.ListaPresenca.Service.listaPresencaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Módulo Lista de Presença", description = "API de controle de presença")
@RestController
@RequestMapping("/presenca")
public class listaPresencaController {
	private final listaPresencaService listaPresencaService;

    @Autowired
    public listaPresencaController(listaPresencaService listaPresencaService) {
        this.listaPresencaService = listaPresencaService;
    }

@Operation(summary = "Localizar presença por ID")
    @GetMapping("/{id}")
    public ResponseEntity<listaPresenca> getPresencaById(@PathVariable Long id) {
        listaPresenca presenca = listaPresencaService.getPresencaById(id);
        if (presenca != null) {
            return ResponseEntity.ok(presenca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<listaPresenca>> getAllPresencas() {
        List<listaPresenca> presencas = listaPresencaService.getAllPresencas();
        return ResponseEntity.ok(presencas);
    }

@Operation(summary = "Localizar presença por nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<listaPresenca>> buscarPorNome(@PathVariable String nome) {
        List<listaPresenca> presencas = listaPresencaService.buscarPorNome(nome);
        return ResponseEntity.ok(presencas);
    }

@Operation(summary = "Localizar presença por cargo")
    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<listaPresenca>> buscarPorCargo(@PathVariable String cargo) {
        List<listaPresenca> presencas = listaPresencaService.buscarPorCargo(cargo);
        return ResponseEntity.ok(presencas);
    }

@Operation(summary = "Localizar presença por empresa")
    @GetMapping("/empresa/{empresa}")
    public ResponseEntity<List<listaPresenca>> buscarPorEmpresa(@PathVariable String empresa) {
        List<listaPresenca> presencas = listaPresencaService.buscarPorEmpresa(empresa);
        return ResponseEntity.ok(presencas);
    }

    @PostMapping("/")
@Operation(summary = "Registrar nova presença")
    public ResponseEntity<listaPresenca> criarPresenca(@RequestBody @Valid listaPresenca presenca) {
        listaPresenca novaPresenca = listaPresencaService.salvarPresenca(presenca);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPresenca);
    }

    @PutMapping("/{id}")
@Operation(summary = "Atualizar presença por ID")
    public ResponseEntity<listaPresenca> atualizarPresenca(@PathVariable Long id, @RequestBody @Valid listaPresenca presenca) {
        listaPresenca atualizada = listaPresencaService.updatePresenca(id, presenca);
        if (atualizada != null) {
            return ResponseEntity.ok(atualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
@Operation(summary = "Deletar presença por ID")
    public ResponseEntity<Void> deletarPresenca(@PathVariable Long id) {
        boolean deletada = listaPresencaService.deletePresenca(id);
        if (deletada) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}