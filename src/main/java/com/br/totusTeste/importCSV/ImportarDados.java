package com.br.totusTeste.importCSV;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;

import java.io.*;
import java.util.*;
import static io.restassured.RestAssured.given;

public class ImportarDados {

    String path = ".\\src\\test\\resources\\arquivoCSV\\arquivoCSVTotusTeste.csv";
    public void getDataFileCsv() {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            HashMap<String, Object> elements = null;
            List<Map<String, Object>> lista = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String info[] = line.trim().split(",");

                elements = new HashMap<String, Object>();
                elements.put("data_vencimento", info[1]);
                elements.put("data_pagamento", info[2]);
                elements.put("valor", Integer.valueOf(info[3]));
                elements.put("descricao", info[4]);
                elements.put("situacao", info[5]);
                lista.add(elements);
            }

            int count =0;
            for(int x =0; x < lista.size();x++){
                count++;
            }

            for(int i = 0; i < count; i++){
                Thread.sleep(2000);
                Response response = given()
                        .contentType(ContentType.JSON)
                        .body(lista.get(i))
                        .post("/conta");
                System.out.println("Retorno Response " + response.asString());
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERRO ==>> " + e.getMessage());
        }
    }
}
