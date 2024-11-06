package View.Menus;

import java.util.Scanner;

public class MenuSecundario {
    private static Scanner choose;

    public MenuSecundario() {
        choose = new Scanner(System.in);
    }

    public String Exibirtela() {
        System.out.println("\nQual das opções abaixo deseja utilizar");
        System.out.println("\n1.Listar Cursos");
        System.out.println("2.Calcular Média");
        System.out.println("3.Informacoes do usuario");
        System.out.println("4.Atualizar informações");
        System.out.println("5.Sair");
        System.out.print("\nEscolha uma das opções acima: ");
        return choose.nextLine();
    }

    public Scanner getScanner(){
        return choose;
    }
}
