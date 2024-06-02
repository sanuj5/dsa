package algorithm.dp;

public class NumberOfEnclaves {
    boolean[][] visited;
    boolean enclave = true;
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        visited = new boolean[n][m];
        int enclaves = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && grid[i][j] == 1) {
                    enclave = true;
                    int cells = dfs(i, j, grid);
                    if(enclave)
                        enclaves += cells;
                }
            }
        }
        return enclaves;
    }

    private int dfs(int n, int m, int[][] grid){
        if (n < 0 || n >= grid.length || m < 0 || m >= grid[0].length || grid[n][m] == 0 || visited[n][m])
            return 0;
        visited[n][m] = true;
        if(n == 0 || m ==0 || n == grid.length-1 || m == grid[0].length-1) {
            enclave = false;
        }
        return dfs(n-1, m , grid) + dfs(n+1, m , grid) + dfs(n, m-1 , grid) + dfs(n, m+1 , grid) + 1;
    }

    public static void main(String[] args) {
        NumberOfEnclaves ii = new NumberOfEnclaves();
        int[][] grid = new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0,1, 1, 0}, {0, 0, 0, 0}};
        System.out.println(ii.numEnclaves(grid));
    }
}
