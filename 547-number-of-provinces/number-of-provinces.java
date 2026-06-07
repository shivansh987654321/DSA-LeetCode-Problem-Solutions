class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] visited = new boolean[n];
        for(int node = 0; node < n; node++){
            if(!visited[node]){
                count++;
                Helper_DFS(isConnected , visited, node);
            }
        }
        return count;
    }

    private void Helper_DFS(int[][] isConnected, boolean[] visited, int node){
        visited[node] = true;
        for(int j = 0 ; j < isConnected.length; j++){
            if(isConnected[node][j] == 1 && !visited[j]){
                Helper_DFS(isConnected , visited , j);
            }
        }
    }
}