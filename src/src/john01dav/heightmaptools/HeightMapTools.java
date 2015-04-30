package src.john01dav.heightmaptools;

import src.john01dav.heightmaptools.heightmap.HeightMap;
import src.john01dav.heightmaptools.heightmap.fractalnoise.FractalNoiseHeightMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HeightMapTools extends JFrame {
    private HeightMap heightMap;

    private JToolBar toolBar;
    private JButton generateNewMap;
    private JButton saveImage;
    private HeightMapFrame heightMapFrame;

    public static void main(String[] args){
        new HeightMapTools().start();
    }

    private void start(){
        setLayout(new BorderLayout());

        heightMap = new FractalNoiseHeightMap(512, 512);

        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);

        generateNewMap = new JButton("New HeightMap");
        toolBar.add(generateNewMap);

        saveImage = new JButton("Save Image");
        saveImage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser jFileChooser = new JFileChooser();
                int returnVal = jFileChooser.showSaveDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION){
                    BufferedImage image = new BufferedImage(heightMap.getSizeX(), heightMap.getSizeY(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
                    heightMapFrame.paint(image.getGraphics());
                    try{
                        ImageIO.write(image, "png", jFileChooser.getSelectedFile());
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        toolBar.add(saveImage);

        heightMapFrame = new HeightMapFrame(heightMap);
        add(heightMapFrame, BorderLayout.CENTER);

        setTitle("HeightMapTools");
        setSize(512, 512);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
