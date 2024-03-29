package main.java.br.com.alura.screenmatch.principal;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.br.com.alura.screenmatch.modelos.Titulo;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String search = "";
        List<Titulo> title = new ArrayList<>();
        System.out.println("Caso queira parar de buscar filmes, basta escrever sair para finalizar a busca!");
        System.out.println("If you want to stop searching for films, just type exit to end the search!");

        while (!search.equalsIgnoreCase("sair") || !search.equalsIgnoreCase("exit")) {

            System.out.println("Digite um filme para buscar(Enter a movie name to search): ");
            search = scanner.nextLine();

            if (search.equalsIgnoreCase("sair") || search.equalsIgnoreCase("exit")) {
                scanner.close();
                break;
            }

            ConsultaApiFilmes consultaApiFilmes = new ConsultaApiFilmes();
            title.add(consultaApiFilmes.consultaFilme(search));

        }
        GeraArquivo geraArquivo = new GeraArquivo();
        geraArquivo.criaJson(title);
    }
}
