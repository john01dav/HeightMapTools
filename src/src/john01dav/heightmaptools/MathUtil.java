package src.john01dav.heightmaptools;

public class MathUtil {

    public static float[][] interpopulateHeightMap(float[][] heightMap, int iterations){
        for(int currentIteration=0;currentIteration<iterations;currentIteration++){
            heightMap = interpopulateHeightMap(heightMap);
        }
        return heightMap;
    }

    public static float[][] interpopulateHeightMap(float[][] input){
        int sizeX = input.length;
        int sizeY = input[0].length;
        float[][] output = new float[sizeX][sizeY];
        float total;
        int count;

        for(int cx=0;cx<sizeX;cx++){
            for(int cy=0;cy<sizeY;cy++){
                total = 0f;
                count = 0;

                try{
                    total += input[cx][cy];
                    count++;
                }catch(ArrayIndexOutOfBoundsException e){}

                try{
                    total += input[cx + 1][cy];
                    count++;
                }catch(ArrayIndexOutOfBoundsException e){}

                try{
                    total += input[cx - 1][cy];
                    count++;
                }catch(ArrayIndexOutOfBoundsException e){}

                try{
                    total += input[cx][cy + 1];
                    count++;
                }catch(ArrayIndexOutOfBoundsException e){}

                try{
                    total += input[cx][cy - 1];
                    count++;
                }catch(ArrayIndexOutOfBoundsException e){}

                output[cx][cy] = total / ((float) count);
            }
        }

        return output;
    }

    public static float[][] duplicateHeightMap(float[][] input){
        int sizeX = input.length;
        int sizeY = input[0].length;
        float[][] output = new float[sizeX][sizeY];

        for(int cx=0;cx<sizeX;cx++){
            for(int cy=0;cy<sizeY;cy++){
                output[cx][cy] = input[cx][cy];
            }
        }

        return output;
    }

    public static int greater(int a, int b){
        if(a > b){
            return a;
        }else{
            return b;
        }
    }

}
