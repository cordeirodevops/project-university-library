import java.util.Scanner;

public class Main {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

    // Variáveis de estado do sistema (Procedural - Sem Orientação a Objetos ainda)
    boolean sistemaAtivo = true;
    
    // Simulação de um membro e um exemplar para nossa lógica inicial
    int limiteEmprestimos = 3;
    int emprestimosAtivos = 0;
    boolean exemplarDisponivel = true;
    boolean exemplarDanificado = false;
    
    System.out.println("===========================================");
    System.out.println(" SISTEMA DE GESTÃO DE BIBLIOTECA (CLI V1) ");
    System.out.println("===========================================");
    
    while (sistemaAtivo) {
        System.out.println("\nEscolha uma operação:");
        System.out.println("1 - Realizar Empréstimo");
        System.out.println("2 - Realizar Devolução");
        System.out.println("3 - Sair do Sistema");
        System.out.print("Opção: ");
        
        int opcao = scanner.nextInt();
        
        if (opcao == 1) {
            // Lógica procedural do Caso de Uso 01
            System.out.println("\n--- Operação de Empréstimo ---");
            if (emprestimosAtivos >= limiteEmprestimos) {
                System.out.println("ERRO: O membro atingiu o limite máximo de " + limiteEmprestimos + " empréstimos simultâneos.");
            } else if (!exemplarDisponivel) {
                System.out.println("ERRO: O exemplar não está disponível no momento.");
            } else if (exemplarDanificado) {
                System.out.println("ERRO: O exemplar está danificado e bloqueado para empréstimo.");
            } else {
                emprestimosAtivos++;
                exemplarDisponivel = false;
                System.out.println("SUCESSO: Empréstimo realizado. Prazo de devolução: 14 dias corridos.");
                System.out.println("Empréstimos ativos do membro: " + emprestimosAtivos);
            }
            
        } else if (opcao == 2) {
            // Lógica procedural do Caso de Uso 02
            System.out.println("\n--- Operação de Devolução ---");
            if (exemplarDisponivel) {
                System.out.println("AVISO: Este exemplar já consta como disponível na estante.");
            } else {
                System.out.print("Quantos dias corridos se passaram desde a retirada? ");
                int diasPassados = scanner.nextInt();
                
                if (diasPassados > 14) {
                    int diasAtraso = diasPassados - 14;
                    double valorMultaPorDia = 2.00; // Suposição de R$2,00 por dia
                    double multaTotal = diasAtraso * valorMultaPorDia;
                    System.out.println("AVISO: Devolução fora do prazo! Atraso de " + diasAtraso + " dias.");
                    System.out.println("MULTA GERADA: R$ " + multaTotal);
                } else {
                    System.out.println("SUCESSO: Devolução realizada dentro do prazo.");
                }
                
                exemplarDisponivel = true;
                emprestimosAtivos--;
                System.out.println("O exemplar voltou a ficar disponível.");
            }
            
        } else if (opcao == 3) {
            System.out.println("\nEncerrando o sistema...");
            sistemaAtivo = false;
        } else {
            System.out.println("\nERRO: Opção inválida. Tente novamente.");
        }
    }
    
    scanner.close();
}

}
