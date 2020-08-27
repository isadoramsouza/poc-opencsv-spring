package com.isadora.poc.service;

import com.isadora.poc.dto.FornecedorDTO;
import com.isadora.poc.model.Fornecedor;
import com.isadora.poc.repository.FornecedorRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FornecedorService {

    FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<FornecedorDTO>  importarArquivoCSV(MultipartFile arquivoCSV) throws IOException {
       List<FornecedorDTO> listaFornecedorDTO = new ArrayList<>();

       File outputFile = new File("arquivoCSV");

        try (FileOutputStream outputStream = new FileOutputStream(outputFile);) {
            outputStream.write(arquivoCSV.getBytes());
        }

        try (
                Reader reader = Files.newBufferedReader(Paths.get(outputFile.getPath()));
        ) {
            CsvToBean<FornecedorDTO> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(FornecedorDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<FornecedorDTO> csvFornecedorIterator = csvToBean.iterator();

            while (csvFornecedorIterator.hasNext()) {
                FornecedorDTO fornecedorDTO = (FornecedorDTO) csvFornecedorIterator.next();
                Fornecedor fornecedor = salvarFornecedorFromCSV(fornecedorDTO);
                listaFornecedorDTO.add(fornecedorDTO.toDTO(fornecedor));
            }
        }
        return listaFornecedorDTO;
    }

    private Fornecedor salvarFornecedorFromCSV(FornecedorDTO fornecedorDTO){
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findByNuCpfCnpj(fornecedorDTO.getNuCpfCnpj());
        Fornecedor fornecedor = null;

        if(fornecedorOptional.isPresent()){

            fornecedor = fornecedorOptional.get();

            fornecedor.setDeRazaoSocial(fornecedorDTO.getDeRazaoSocial());
            fornecedor.setDeNomeFantasia(fornecedorDTO.getDeNomeFantasia());
            fornecedor.setDeEmail(fornecedorDTO.getDeEmail());
            fornecedor.setNuCpfCnpj(fornecedorDTO.getNuCpfCnpj());
            fornecedor.setTpSujeito(fornecedorDTO.getTpSujeito());

        }else{
            fornecedor = fornecedorDTO.toModel();
        }
        return fornecedorRepository.saveAndFlush(fornecedor);
    }
}
