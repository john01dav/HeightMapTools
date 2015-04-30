package src.john01dav.heightmaptools;

import src.john01dav.heightmaptools.heightmap.HeightMap;

import javax.swing.*;
import java.awt.*;

public class HeightMapFrame extends JComponent {
    private HeightMap heightMap;

    public HeightMapFrame(HeightMap heightMap){
        this.heightMap = heightMap;
    }

    public void paint(Graphics g){
        int value;

        for(int cx=0;cx<heightMap.getSizeX();cx++){
            for(int cy=0;cy<heightMap.getSizeY();cy++){
                value = ((int) heightMap.getIntAt(cx, cy));
                g.setColor(new Color(value, value, value));
                g.fillRect(cx, cy, 1, 1);
            }
        }
    }

}
