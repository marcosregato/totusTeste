package com.br.totusTeste;

import com.br.totusTeste.importCSV.ImportarDados;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;



public class TesteImportDataCSV {


    @Test
    public void testeGetDataFileCsv() throws IOException {
        ImportarDados importa = new ImportarDados();
        importa.getDataFileCsv();



    }
}
