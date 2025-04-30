import java.util.Scanner;

public class NQueens {
    private int n;
    private int[] queens;        // queens[row] = column of the queen in that row
    private boolean[] cols;      // columns occupied
    private boolean[] diag1;     // major diagonals (row + col)
    private boolean[] diag2;     // minor diagonals (row - col + n - 1)
    private boolean solved = false;

    public NQueens(int n) {
        this.n = n;
        queens = new int[n];
        cols   = new boolean[n];
        diag1  = new boolean[2 * n - 1];
        diag2  = new boolean[2 * n - 1];
    }

    public boolean solve(int row) {
        if (row == n) {
            // all queens placed
            return true;
        }
        for (int col = 0; col < n; col++) {
            int d1 = row + col;
            int d2 = row - col + (n - 1);
            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                // place queen
                queens[row] = col;
                cols[col]   = true;
                diag1[d1]   = true;
                diag2[d2]   = true;
                // recurse
                if (solve(row + 1)) {
                    return true;
                }
                // backtrack
                cols[col]   = false;
                diag1[d1]   = false;
                diag2[d2]   = false;
            }
        }
        return false;  // no valid position in this row
    }

    public void printSolution() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(queens[r] == c ? "Q " : ". ");
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
        NQueens solver = new NQueens(n);
        if (solver.solve(0)) {
            System.out.println("Solução encontrada para " + n + "-rainhas:");
            solver.printSolution();
        } else {
            System.out.println("Não há solução para " + n + "-rainhas.");
        }
    }
}
