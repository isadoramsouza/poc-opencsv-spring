package com.isadora.poc.dto;

import com.isadora.poc.model.Fornecedor;
import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

public class FornecedorDTO {

    @CsvBindByName(column = "Razao Social")
    private String deRazaoSocial;

    @CsvBindByName(column = "Nome Fantasia")
    private String deNomeFantasia;

    @CsvBindByName(column = "Email")
    private String deEmail;

    @CsvBindByName(column = "CPF/CNPJ")
    private String nuCpfCnpj;

    @CsvBindByName(column = "Tipo Fornecedor")
    private String tpSujeito;

    public Fornecedor toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, Fornecedor.class);
    }

    public FornecedorDTO toDTO(Fornecedor fornecedor){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(fornecedor, FornecedorDTO.class);
    }

}
