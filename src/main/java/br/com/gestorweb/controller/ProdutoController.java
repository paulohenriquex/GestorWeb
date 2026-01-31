package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.dto.ProdutoDTO;
import br.com.gestorweb.service.ProdutoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:5173")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> save(@RequestBody ProdutoDTO dto, @PathVariable Long usuarioId) {
        ProdutoDTO produtoSalvo = produtoService.save(dto, usuarioId);
        return ResponseEntity.ok(produtoSalvo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ProdutoDTO>> findAll(@PathVariable Long usuarioId) {
        List<ProdutoDTO> produtos = produtoService.findAll(usuarioId);
        return ResponseEntity.ok(produtos);
    }

}
