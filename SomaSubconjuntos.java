import java.util.*;

/*
Complexidade das soluções:

1. Solução original (encontrar apenas um subconjunto):
   - Complexidade de tempo: O(2^n), pois cada elemento pode ou não estar no subconjunto.
   - Complexidade de espaço: O(n), devido à pilha de recursão e armazenamento do subconjunto.

2. Solução modificada (encontrar todos os subconjuntos):
   - Complexidade de tempo: O(2^n * n), pois pode haver até 2^n subconjuntos e cada subconjunto pode ter até n elementos.
   - Complexidade de espaço: O(2^n * n), para armazenar todos os subconjuntos encontrados.
*/

public class SomaSubconjuntos {

    public static List<List<Integer>> encontrarTodosSubconjuntosZero(int[] nums) {
        List<List<Integer>> resultados = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), 0, resultados);
        return resultados;
    }

    private static void backtrack(int[] nums, int index, List<Integer> atual, int soma, List<List<Integer>> resultados) {
        if (!atual.isEmpty() && soma == 0) {
            resultados.add(new ArrayList<>(atual));
            // Não retorna, pois queremos todos os subconjuntos
        }
        if (index >= nums.length) {
            return;
        }
        // Incluir o elemento atual
        atual.add(nums[index]);
        backtrack(nums, index + 1, atual, soma + nums[index], resultados);
        atual.remove(atual.size() - 1);
        // Não incluir o elemento atual
        backtrack(nums, index + 1, atual, soma, resultados);
    }

    // Exemplo de uso
    public static void main(String[] args) {
        int[] conjunto = {-7, -3, -2, 5, 8};
        List<List<Integer>> subconjuntos = encontrarTodosSubconjuntosZero(conjunto);
        if (!subconjuntos.isEmpty()) {
            System.out.println("Subconjuntos encontrados com soma zero:");
            for (List<Integer> s : subconjuntos) {
                System.out.println(s);
            }
        } else {
            System.out.println("Nenhum subconjunto com soma zero encontrado.");
        }
    }
}