
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegistroTransacoesComStream {
     /**
     * <h1>Orientação a Objetos na Prática com Java</h1>
     * Desafio de código 2 / 5  da DIO - Registro de Transações Bancárias com Stream API
     * <p>
     * <b>Note:</b> Desenvolvido na linguagem Java.
     *
     * @author  Victor Portela
     * @version 1.0
     * @since   06/06/2024
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double saldo = scanner.nextDouble();
        int quantidadeTransacoes = scanner.nextInt();

        List<Transacao> transacoes = new ArrayList<>();
        
        for (int i = 1; i <= quantidadeTransacoes; i++) {
            char tipoTransacao = scanner.next().charAt(0);

            double valorTransacao = scanner.nextDouble();

            Transacao transacao = new Transacao(tipoTransacao, valorTransacao);
            transacoes.add(transacao);

            if (transacao.getTipo()== 'D' || transacao.getTipo()=='d') {
                saldo += valorTransacao;
            } else if (transacao.getTipo() == 'S' || transacao.getTipo()=='s') {
                saldo -= valorTransacao;
            }
        }

        System.out.println("Saldo : " + saldo);
        System.out.println("Transacoes:");
        transacoes.stream()
                .filter(transacao -> transacao.getTipo()=='D'||transacao.getTipo()=='d'|| transacao.getTipo()== 'S' || transacao.getTipo()=='s')
                .map(t -> t.getTipo() + " de "+ t.getValor())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // Fechar o scanner para evitar vazamentos de recursos
        scanner.close();
    }
}

class Transacao {
    private char tipo;
    private double valor;

    public Transacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }
}
