import java.util.*;

public class EscalonamentoIntervalos {

    // classe auxiliar para armazenar intervalos com índice original
    private static class Intervalo {
        int inicio, termino, indice;
        Intervalo(int s, int f, int i) {
            this.inicio = s;
            this.termino = f;
            this.indice = i;
        }
    }

    // resultado contendo a lista de índices selecionados e o número de iterações efetuadas
    public static class Resultado {
        public final List<Integer> selecionados;
        public final int iteracoes;
        public Resultado(List<Integer> sel, int it) {
            this.selecionados = sel;
            this.iteracoes = it;
        }
    }

    /**
     * Encontra uma Subcoleção Disjunta Máxima (SDM) por método guloso.
     * @param s array de tempos de início (s1, s2, …, sn)
     * @param f array de tempos de término (f1, f2, …, fn)
     * @return Resultado contendo:
     *   – lista de índices (1…n) dos intervalos selecionados
     *   – número de iterações do laço interno (testes de sobreposição)
     */
    public static Resultado sdmGuloso(int[] s, int[] f) {
        int n = s.length;
        List<Intervalo> lista = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            lista.add(new Intervalo(s[i], f[i], i + 1));
        }
        // Ordena por término crescente
        lista.sort(Comparator.comparingInt(iv -> iv.termino));

        List<Integer> X = new ArrayList<>();
        int iteracoes = 0;
        int i = 0;
        // percorre intervalos ordenados, "pulando" os que se sobrepõem
        while (i < n) {
            Intervalo atual = lista.get(i);
            X.add(atual.indice);
            int fimAtual = atual.termino;
            int k = i + 1;
            // pula enquanto o próximo começar antes do término do selecionado
            while (k < n && lista.get(k).inicio < fimAtual) {
                iteracoes++;
                k++;
            }
            i = k;
        }
        return new Resultado(X, iteracoes);
    }

    public static void main(String[] args) {
        // Caso de teste 1: exemplo dado no enunciado (não ordenado originalmente)
        int[] s1 = { 4, 6, 13, 4, 2, 6, 7, 9, 1, 3, 9 };
        int[] f1 = { 8, 7, 14, 5, 4, 9, 10, 11, 6, 13, 12 };
        Resultado r1 = sdmGuloso(s1, f1);
        System.out.println("Caso 1:");
        System.out.println("  Intervalos selecionados (índices originais): " + r1.selecionados);
        System.out.println("  Iterações (testes de sobreposição): " + r1.iteracoes);

        // Caso de teste 2: exemplo clássico
        // Intervalos: (1,2), (3,4), (0,6), (5,7), (8,9), (5,9)
        int[] s2 = { 1, 3, 0, 5, 8, 5 };
        int[] f2 = { 2, 4, 6, 7, 9, 9 };
        Resultado r2 = sdmGuloso(s2, f2);
        System.out.println("\nCaso 2:");
        System.out.println("  Intervalos selecionados (índices originais): " + r2.selecionados);
        System.out.println("  Iterações (testes de sobreposição): " + r2.iteracoes);
    }
}
