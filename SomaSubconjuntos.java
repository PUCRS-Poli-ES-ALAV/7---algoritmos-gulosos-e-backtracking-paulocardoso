import java.util.*;

public class SomaSubconjuntos {

    public static List<Integer> encontrarSubconjuntoZero(int[] nums) {
        List<Integer> resultado = new ArrayList<>();
        if (backtrack(nums, 0, new ArrayList<>(), 0, resultado)) {
            return resultado;
        }
        return Collections.emptyList();
    }

    private static boolean backtrack(int[] nums, int index, List<Integer> atual, int soma, List<Integer> resultado) {
        if (!atual.isEmpty() && soma == 0) {
            resultado.addAll(atual);
            return true;
        }
        if (index >= nums.length) {
            return false;
        }
        // Incluir o elemento atual
        atual.add(nums[index]);
        if (backtrack(nums, index + 1, atual, soma + nums[index], resultado)) {
            return true;
        }
        atual.remove(atual.size() - 1);
        // Não incluir o elemento atual
        if (backtrack(nums, index + 1, atual, soma, resultado)) {
            return true;
        }
        return false;
    }

    // Exemplo de uso
    public static void main(String[] args) {
        int[] conjunto = {-7, -3, -2, 5, 8};
        List<Integer> subconjunto = encontrarSubconjuntoZero(conjunto);
        if (!subconjunto.isEmpty()) {
            System.out.println("Subconjunto encontrado: " + subconjunto);
        } else {
            System.out.println("Nenhum subconjunto com soma zero encontrado.");
        }
    }
}