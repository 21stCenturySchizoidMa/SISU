package Model.Notas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CalcularNotas {
    private double media;
    private final Map<String, float[]> cursosPesos = new HashMap<>();

    public List<String> CarregarDados(String campus) {
        String arquivo = switch (campus.toLowerCase()) {
            case "recife" -> "cursos-recife.txt";
            case "agreste" -> "cursos-agreste.txt";
            case "vitoria" -> "cursos-vitoria.txt";
            default -> null;
        };


        List<String> cursos = new ArrayList<>();

        if (arquivo != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split(" ");

                    int indiceInicioPesos = 0;
                    while (indiceInicioPesos < partes.length && !partes[indiceInicioPesos].matches("\\d+(\\.\\d+)?")) {
                        indiceInicioPesos++;
                    }

                    StringBuilder cursoBuilder = new StringBuilder();
                    for (int i = 0; i < indiceInicioPesos; i++) {
                        if (i > 0) cursoBuilder.append(" ");
                        cursoBuilder.append(partes[i]);
                    }
                    String curso = cursoBuilder.toString();

                    float[] pesos = new float[5];
                    for (int i = 0; i < 5; i++) {
                        pesos[i] = Float.parseFloat(partes[indiceInicioPesos + i]);
                    }
                    cursosPesos.put(curso, pesos);
                    cursos.add(curso);
                }
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados: " + e.getMessage());
            }
        } else {
            System.out.println("Campus inválido.");
        }

        return cursos;
    }

    public double calcularNotas(double nota1, double nota2, double nota3, double nota4, double nota5, String curso) {
        float[] pesos = cursosPesos.get(curso);

        if (pesos == null) {
            System.out.println("Model.Model.Model.Curso não encontrado.");
            return 0;
        }

        media = (nota1 * pesos[0] + nota2 * pesos[1] + nota3 * pesos[2] + nota4 * pesos[3] + nota5 * pesos[4]) /
                (pesos[0] + pesos[1] + pesos[2] + pesos[3] + pesos[4]);
        return media;
    }

    public void solicitarNotas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite sua nota de matemática:");
        double nota1 = sc.nextDouble();

        System.out.println("Digite sua nota de ciências da natureza:");
        double nota2 = sc.nextDouble();

        System.out.println("Digite sua nota de linguagens:");
        double nota3 = sc.nextDouble();

        System.out.println("Digite sua nota de humanas:");
        double nota4 = sc.nextDouble();

        System.out.println("Digite sua nota de redação:");
        double nota5 = sc.nextDouble();

        System.out.println("Digite o campus (recife, agreste, vitoria):");
        String campus = sc.next();

        CarregarDados(campus);

        System.out.println("Digite o curso:");
        sc.nextLine();
        String curso = sc.nextLine();

        double mediaPonderada = calcularNotas(nota1, nota2, nota3, nota4, nota5, curso);
        if (mediaPonderada > 0) {
            System.out.println("A média ponderada é: " + mediaPonderada);
        }
    }

    public double getMedia() {
        return media;
    }


}