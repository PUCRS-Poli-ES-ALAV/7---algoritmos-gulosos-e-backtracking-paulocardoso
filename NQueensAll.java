import java.util.*;

public class NQueensAll {
    private int n;
    private int[] queens;             // queens[row] = column of the queen in that row
    private boolean[] cols;           // columns occupied
    private boolean[] diag1;          // major diagonals (row + col)
    private boolean[] diag2;          // minor diagonals (row - col + n - 1)
    private List<int[]> solutions;    // lista de todas as soluções

    public NQueensAll(int n) {
        this.n = n;
        this.queens = new int[n];
        this.cols   = new boolean[n];
        this.diag1  = new boolean[2 * n - 1];
        this.diag2  = new boolean[2 * n - 1];
        this.solutions = new ArrayList<>();
    }

    // Backtracking para encontrar todas as soluções
    private void backtrack(int row) {
        if (row == n) {
            // armazena cópia da solução atual
            solutions.add(queens.clone());
            return;
        }
        for (int col = 0; col < n; col++) {
            int d1 = row + col;
            int d2 = row - col + (n - 1);
            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                queens[row] = col;
                cols[col]   = true;
                diag1[d1]   = true;
                diag2[d2]   = true;

                backtrack(row + 1);

                // desfaz para tentar próxima coluna
                cols[col]   = false;
                diag1[d1]   = false;
                diag2[d2]   = false;
            }
        }
    }

    // Invoca o backtracking e retorna as soluções
    public List<int[]> solveAll() {
        backtrack(0);
        return solutions;
    }

    // Imprime uma solução no formato de tabuleiro
    private void printSolution(int[] sol) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(sol[r] == c ? "Q " : ". ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entre o valor de N (>=2): ");
        int n = sc.nextInt();
        if (n < 2) {
            System.out.println("N deve ser >= 2.");
            return;
        }

        NQueensAll solver = new NQueensAll(n);
        List<int[]> sols = solver.solveAll();
        System.out.println("Total de soluções encontradas: " + sols.size());

        for (int i = 0; i < sols.size(); i++) {
            System.out.println("\nSolução " + (i + 1) + ":");
            solver.printSolution(sols.get(i));
        }
    }
}
