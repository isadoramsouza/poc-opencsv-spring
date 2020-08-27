package com.isadora.poc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "FORNECEDOR")
@NoArgsConstructor
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdFornecedor;
    private String deRazaoSocial;
    private String deNomeFantasia;
    private String deEmail;
    private String nuCpfCnpj;
    private String tpSujeito;

}
