package com.isadora.poc.controller;

import com.isadora.poc.dto.FornecedorDTO;
import com.isadora.poc.service.FornecedorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/fornecedor")
public class FornecedorController {

    FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @ApiOperation(value = "Importa fornecedores a partir do CSV", tags = "Fornecedor")
    @PostMapping(value = "/importar-fornecedor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação bem sucedida."),
            @ApiResponse(code = 400, message = "Parâmetros inválidos."),
            @ApiResponse(code = 401, message = "Sem autorização."),
            @ApiResponse(code = 403, message = "Sem autorização."),
            @ApiResponse(code = 404, message = "Endpoint não encontrado."),
            @ApiResponse(code = 500, message = "Erro durante a operação.")
    })
    public ResponseEntity<List<FornecedorDTO>> importaFornecedoresDoCSV(@RequestParam("file") MultipartFile arquivoCSV) throws IOException {
        return new ResponseEntity<>(fornecedorService.importarArquivoCSV(arquivoCSV), HttpStatus.OK);
    }

}
