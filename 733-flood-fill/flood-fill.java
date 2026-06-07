class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Helper(image, sr, sc, color, image[sr][sc]);
        return image;
    }

    private void Helper(int[][] image, int i, int j, int Newcolor, int OrgColor){

        if(i < 0 || j < 0 || i >= image.length || j>= image[0].length || image[i][j] == Newcolor || image[i][j] != OrgColor){
            return;
        }
        image[i][j] = Newcolor;
        Helper(image, i + 1, j , Newcolor, OrgColor);
        Helper(image, i - 1, j , Newcolor, OrgColor);
        Helper(image, i , j + 1, Newcolor, OrgColor);
        Helper(image, i , j - 1, Newcolor, OrgColor);
    }
}