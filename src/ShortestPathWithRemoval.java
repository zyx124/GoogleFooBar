package interview.com;

import java.util.LinkedList;
import java.util.Queue;

// Prepare the Bunnies' Escape
// Given a map with 0 and 1s, 1 represents wall and 0 represents valid walk, at most 1 wall can be removed.
// Find the shortest path from top left to bottom right corner.

public class ShortestPathWithRemoval {

    public static void main(String[] args) {
        int[][] map = {{0, 1, 1, 1}, {0, 1, 0, 0}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        System.out.println(shortestPath(map));
    }

    public static int shortestPath(int[][] map) {
        int m = map.length;
        int n = map[0].length;

        int[][] reverse = bfs(m - 1, n - 1, map);
        int[][] normal = bfs(0, 0, map);

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reverse[i][j] != 0 && normal[i][j] != 0) {
                    int cur = normal[i][j] + reverse[i][j] - 1;
                    result = Math.min(cur, result);
                }

            }
        }
        return result;
    }

    private static int[][] bfs(int i, int j, int[][] map) {

        int[] directionX = {1, -1, 0, 0};
        int[] directionY = {0, 0, 1, -1};

        int m = map.length;
        int n = map[0].length;
        int[][] board = new int[m][n];


        board[i][j] = 1;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(i, j));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            int x = poll.x;
            int y = poll.y;
            for (int k = 0; k < 4; k++) {
                int newX = x + directionX[k];
                int newY = y + directionY[k];
                if (newX < m && newX >= 0 && newY >= 0 && newY < n) {
                    if (board[newX][newY] == 0) {
                        board[newX][newY] = board[x][y] + 1;
                        if (map[newX][newY] == 1) {
                            continue;
                        }
                        queue.offer(new Pair(newX, newY));
                    }
                }
            }
        }

        return board;
    }
}

class Pair {

    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
