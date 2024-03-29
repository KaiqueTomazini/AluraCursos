package main.java.br.com.alura.screenmatch.principal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.java.br.com.alura.screenmatch.modelos.Titulo;

public class GeraArquivo {

    public void criaJson(List<Titulo> lista) throws IOException{
        Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
        FileWriter fileWriter = new FileWriter("filmes.json");
        fileWriter.write(gson.toJson(lista));
        fileWriter.close();
    }

}
