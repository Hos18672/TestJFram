import java.util.LinkedList;
import java.util.Queue;

public class Bridge {

    public int shortestBridge(Integer[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int level = 0;
        boolean found = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(A, i, j, m, n, queue);
                    found = true;
                    break;
                }
            }
            if (found == true) {
                break;
            }
        }
        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = cur[0] + dir[j][0];
                    int y = cur[1] + dir[j][1];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }
                    if (A[x][y] == 1) {
                        return level;
                    } else if (A[x][y] == 2) {
                        continue;
                    } else if (A[x][y] == 0) {
                        A[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
            level++;
        }
        return -1;
    }

    private void dfs(Integer[][] A, int i, int j, int m, int n, Queue<int[]> queue) {
        queue.add(new int[]{i, j});
        A[i][j] = 2;
        if (i - 1 >= 0 && A[i - 1][j] == 1) {
            dfs(A, i - 1, j, m, n, queue);
        }
        if (i + 1 < m && A[i + 1][j] == 1) {
            dfs(A, i + 1, j, m, n, queue);
        }
        if (j - 1 >= 0 && A[i][j - 1] == 1) {
            dfs(A, i, j - 1, m, n, queue);
        }
        if (j + 1 < n && A[i][j + 1] == 1) {
            dfs(A, i, j + 1, m, n, queue);
        }
    }
}
