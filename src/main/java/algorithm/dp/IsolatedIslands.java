package algorithm.dp;

/**
 * https://leetcode.com/problems/number-of-islands/description/
 * Need to visit each node and for each node visit neighbours recursively (DFS), Keep track of node visited.
 * Recursion exits when node is already visited or '0'
 * Increase the count when DFS for one node completes. Do DFS for all nodes.
 */
public class IsolatedIslands {
    int n,m;

    public int numIslands(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    dfs(i,j,visited,grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, boolean[][] visited, char[][] grid){
        if( i < 0 || j < 0 || i >= n || j>=m || visited[i][j] || grid[i][j] == '0')
            return;
        visited[i][j] = true;
        dfs(i,j-1,visited,grid);
        dfs(i,j+1,visited,grid);
        dfs(i-1,j,visited,grid);
        dfs(i+1,j,visited,grid);
    }

    public static void main(String[] args) {
        IsolatedIslands ii = new IsolatedIslands();
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println(ii.numIslands(grid));
    }
}
