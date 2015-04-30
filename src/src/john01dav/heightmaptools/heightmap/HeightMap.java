package src.john01dav.heightmaptools.heightmap;

import java.io.Serializable;

public abstract class HeightMap implements Serializable{
    private int sizeX;
    private int sizeY;
    private float[][] heightMap;

    public HeightMap(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        heightMap = new float[sizeX][sizeY];
        generate();
    }

    public void changeRange(float newMin, float newMax){
        float leastValue = Float.MAX_VALUE;
        float greatestValue = Float.MIN_VALUE;
        float value, subtractBy, multiplyBy;

        for(int cx=0;cx<getSizeX();cx++){
            for(int cy=0;cy<getSizeY();cy++){
                value = getAt(cx, cy);

                if(value < leastValue) leastValue = value;
                if(value > greatestValue) greatestValue = value;
            }
        }

        //GV * x = NM

        subtractBy = leastValue - newMin;
        multiplyBy = newMax / (greatestValue - subtractBy);

        for(int cx=0;cx<getSizeX();cx++){
            for(int cy=0;cy<getSizeY();cy++){
                value = getAt(cx, cy);

                value -= subtractBy;
                value *= multiplyBy;

                setAt(cx, cy, value);
            }
        }
    }

    public float getAt(int x, int y){
        return heightMap[x][y];
    }

    public void setAt(int x, int y, float value){
        heightMap[x][y] = value;
    }

    public int getIntAt(int x, int y){
        return ((int) Math.round(heightMap[x][y]));
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public abstract void generate();

}
