package src.john01dav.heightmaptools.heightmap.fractalnoise;

import src.john01dav.heightmaptools.heightmap.HeightMap;

import java.io.Serializable;
import java.util.ArrayList;

public class FractalNoiseHeightMap extends HeightMap implements Serializable{
    private transient ArrayList<FractalLayer> fractalLayerArrayList;

    public FractalNoiseHeightMap(int sizeX, int sizeY) {
        super(sizeX, sizeY);
    }

    @Override
    public void generate(){
        fractalLayerArrayList = new ArrayList<FractalLayer>();

        for(int cx=1;cx<getSizeX();cx++){
            for(int cy=1;cy<getSizeY();cy++){
                if(getSizeX() % cx == 0 && getSizeY() % cy == 0){
                    fractalLayerArrayList.add(new FractalLayer(this, cx, cy));
                }
                setAt(cx, cy, 0);
            }
        }

        int current = 0;

        for(FractalLayer fractalLayer : fractalLayerArrayList){
            System.out.println(current + " / " + fractalLayerArrayList.size());
            current++;
            fractalLayer.generate();
            for(int cx=0;cx<getSizeX();cx++){
                for(int cy=0;cy<getSizeY();cy++){
                    setAt(cx, cy, fractalLayer.getValueAt(cx, cy) + getAt(cx, cy));
                }
            }
        }

        changeRange(0, 255);
    }

}
