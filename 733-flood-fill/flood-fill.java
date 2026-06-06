class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int start = image[sr][sc];
        if (start != color) dfs(image, sr, sc, start, color);
        return image;
    }
    private void dfs(int[][] img, int r, int c, int start, int color){
        if (r<0||r>=img.length||c<0||c>=img[0].length||img[r][c]!=start) return;
        img[r][c]=color;
        dfs(img,r+1,c,start,color);
        dfs(img,r-1,c,start,color);
        dfs(img,r,c+1,start,color);
        dfs(img,r,c-1,start,color);
    }
}