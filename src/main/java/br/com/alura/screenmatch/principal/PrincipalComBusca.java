package main.java.br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.*;

import main.java.br.com.alura.screenmatch.modelos.Titulo;
import main.java.br.com.alura.screenmatch.modelos.TituloOmdb;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um filme para buscar: ");
        String busca = scanner.nextLine();
        String uri = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=2960c35a";
        scanner.close();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            String json = response.body();

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            Titulo meuTitulo = new Titulo(meuTituloOmdb);

            System.out.println(meuTituloOmdb);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
