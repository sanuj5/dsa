package algorithm.dp;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int minutes = -1;
        int n = grid.length;
        int m = grid[0].length;
        int totalFresh = 0;
        int totalRotten = 0;
        Queue<Integer[]> queue = new LinkedList<>();
        int[][] visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = -1;
                if (grid[i][j] == 2) {
                    queue.offer(new Integer[]{i, j});
                    visited[i][j] = 0;
                    totalRotten++;
                }
                if (grid[i][j] == 1) {
                    totalFresh++;
                }
            }
        }
        if(totalFresh == 0){
            return 0;
        }
        if(totalRotten == 0){
            return -1;
        }

        while(!queue.isEmpty()){
            Integer[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int currentMinute = visited[x][y] + 1;
            if(x-1 >= 0 && grid[x-1][y] == 1){
                grid[x-1][y] = 2;
                queue.offer(new Integer[]{x-1, y});
                visited[x-1][y] = currentMinute;
            }
            if(y-1 >= 0 && grid[x][y-1] == 1){
                grid[x][y-1] = 2;
                queue.offer(new Integer[]{x, y-1});
                visited[x][y-1] = currentMinute;
            }
            if(x+1 < n && grid[x+1][y] == 1){
                grid[x+1][y] = 2;
                queue.offer(new Integer[]{x+1, y});
                visited[x+1][y] = currentMinute;
            }
            if(y+1 < m && grid[x][y+1] == 1){
                grid[x][y+1] = 2;
                queue.offer(new Integer[]{x, y+1});
                visited[x][y+1] = currentMinute;
            }
        }
        int maxMinute = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(grid[i][j] == 1){
                    return -1;
                }
                if(visited[i][j] > maxMinute){
                    maxMinute = visited[i][j];
                }
            }
        }
        return maxMinute;
    }

    public static void main(String[] args) {
        RottenOranges ro = new RottenOranges();
        int[][] grid = new int[][]{{0,2}};
        System.out.println(ro.orangesRotting(grid));
    }
}
