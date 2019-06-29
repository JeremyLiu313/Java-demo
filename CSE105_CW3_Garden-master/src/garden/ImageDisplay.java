/*
 * This java class is for the arraylist to display image
 * extends JPanel
 */
package garden;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Yuanzhe Liu
 */
public class ImageDisplay extends JPanel {
    protected final int myX, myY;
    protected final int myWidth, myHeight;
    private final String myPath;
    private final BufferedImage myImage;

    public ImageDisplay (String imagePath,
                        int myX,
                        int myY, 
                        int myWidth, 
                        int myHeight){
        this.myX = myX;
        this.myY = myY;
        this.myWidth = myWidth;
        this.myHeight = myHeight;
        this.myPath= imagePath;
        myImage =FileUtils.loadImage(imagePath);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(myImage, 0, 0, myWidth, myHeight, this);
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public int getWidth() {
        return myWidth;
    }

    @Override
    public int getHeight() {
        return myHeight;
    }
}
