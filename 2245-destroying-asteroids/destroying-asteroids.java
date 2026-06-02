class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {

        Arrays.sort(asteroids);
        long mass1= mass;
        for(int i = 0; i < asteroids.length; i++){
            if(asteroids[i] <= mass1){
                mass1 = mass1 + asteroids[i];
            }else{
                return false;
            }
        }
        return true;
    }
}