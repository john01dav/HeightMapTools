package src.john01dav.heightmaptools.heightmap.fractalnoise;

import src.john01dav.heightmaptools.MathUtil;

import java.util.Random;

public class FractalLayer {
    private FractalNoiseHeightMap heightMap;
    private Random random;
    private int sectionSizeX;
    private int sectionSizeY;
    private int sectionCountX;
    private int sectionCountY;
    private float[][] smallHeightMap;
    private float[][] largeHeightMap;

    protected FractalLayer(FractalNoiseHeightMap heightMap, int sectionSizeX, int sectionSizeY){
        random = new Random(System.currentTimeMillis());

        this.heightMap = heightMap;
        this.sectionSizeX = sectionSizeX;
        this.sectionSizeY = sectionSizeY;
        this.sectionCountX = heightMap.getSizeX() / sectionSizeX;
        this.sectionCountY = heightMap.getSizeY() / sectionSizeY;
        this.smallHeightMap = new float[sectionCountX + 1][sectionCountY + 1];
        this.largeHeightMap = new float[heightMap.getSizeX()][heightMap.getSizeY()];
    }

    protected void generate(){
        int currentSmallMapX;
        int currentSmallMapY;

        for(int cx=0;cx<sectionCountX;cx++){
            for(int cy=0;cy<sectionCountY;cy++){
                smallHeightMap[cx][cy] = ((float) (random.nextInt(255) + random.nextDouble()));
            }
        }

        MathUtil.interpopulateHeightMap(smallHeightMap, MathUtil.greater(sectionCountX, sectionCountY));

        for(int cx=0;cx<heightMap.getSizeX();cx++){
            for(int cy=0;cy<heightMap.getSizeY();cy++){
                currentSmallMapX = (int) Math.abs(Math.floor(((float) cx - 1) / ((float) sectionSizeX)));
                currentSmallMapY = (int) Math.abs(Math.floor(((float) cy - 1) / ((float) sectionSizeY)));

                largeHeightMap[cx][cy] = smallHeightMap[currentSmallMapX][currentSmallMapY];
            }
        }
    }

    protected float getValueAt(int x, int y){
        return largeHeightMap[x][y];
    }

}
