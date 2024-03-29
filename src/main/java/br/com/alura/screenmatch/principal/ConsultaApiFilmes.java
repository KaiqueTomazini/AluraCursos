package main.java.br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import main.java.br.com.alura.screenmatch.excecoes.ErroDeConversaoDeAnoException;
import main.java.br.com.alura.screenmatch.modelos.Titulo;
import main.java.br.com.alura.screenmatch.modelos.TituloOmdb;

public class ConsultaApiFilmes {
    private String url = "https://www.omdbapi.com/?t=";
    private String apiKey = "&apikey=2960c35a";

    public Titulo consultaFilme(String movieName) {
        Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                        .setPrettyPrinting()
                        .create();
        try {
            
            URI uri = URI.create(this.url +
                                URLEncoder.encode(movieName, StandardCharsets.UTF_8.toString()).toString() +
                                this.apiKey
                                );
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                                            .uri(uri)
                                            .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            TituloOmdb myTitleOmdb = gson.fromJson(response.body(), TituloOmdb.class);
            
            return new Titulo(myTitleOmdb);

        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("", e.getCause());
        } catch (ErroDeConversaoDeAnoException e) {
            throw new ErroDeConversaoDeAnoException(e.getMessage());
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to encode URI as requested: Cause = " + e.getCause());
        }catch(Exception e){
            throw new RuntimeException("Unable to obtain movie with that name: Cause = " + e.getCause());
        }
    }

}
