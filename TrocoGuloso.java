import java.util.*;

public class TrocoGuloso {

    public static class Result {
        public final Map<Integer, Integer> troco;
        public final int iteracoes;
        public Result(Map<Integer, Integer> troco, int iteracoes) {
            this.troco = troco;
            this.iteracoes = iteracoes;
        }
    }

    public static Result trocoGuloso(List<Integer> moedas, int valor) {
        // Ordena moedas em ordem decrescente
        Collections.sort(moedas, Collections.reverseOrder());
        // Inicializa contagem de troco
        Map<Integer, Integer> troco = new LinkedHashMap<>();
        for (int m : moedas) {
            troco.put(m, 0);
        }
        int iteracoes = 0;
        int restante = valor;
        // Algoritmo guloso
        for (int m : moedas) {
            while (restante >= m) {
                restante -= m;
                troco.put(m, troco.get(m) + 1);
                iteracoes++;
            }
        }
        return new Result(troco, iteracoes);
    }

    public static void main(String[] args) {
        List<Integer> moedas1 = Arrays.asList(100, 25, 10, 5, 1);
        Result res1 = trocoGuloso(moedas1, 289);
        System.out.println("Troco 289: " + res1.troco + ", Iterações: " + res1.iteracoes);

        List<Integer> moedas2 = Arrays.asList(50, 20, 10, 1);
        Result res2 = trocoGuloso(moedas2, 93);
        System.out.println("Troco 93: " + res2.troco + ", Iterações: " + res2.iteracoes);
    }
}
