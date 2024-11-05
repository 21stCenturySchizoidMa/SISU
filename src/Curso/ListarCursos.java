package Curso;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ListarCursos {
    private static final String AGRESTE_FILE = "info-agreste.txt";
    private static final String RECIFE_FILE = "info-recife.txt";
    private static final String VITORIA_FILE = "info-vitoria.txt";

    public void listarCursosPorCampus() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEscolha o campus (1 - Agreste, 2 - Recife, 3 - Vitória): ");
        String escolhaCampus = sc.next();

        String arquivoSelecionado;
        switch (escolhaCampus) {
            case "1":
                arquivoSelecionado = AGRESTE_FILE;
                break;
            case "2":
                arquivoSelecionado = RECIFE_FILE;
                break;
            case "3":
                arquivoSelecionado = VITORIA_FILE;
                break;
            default:
                System.out.println("Opção de campus inválida!");
                return;
        }

        exibirCursos(arquivoSelecionado);
    }

    private void exibirCursos(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            System.out.println("\nCursos disponíveis no campus selecionado:\n");
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
