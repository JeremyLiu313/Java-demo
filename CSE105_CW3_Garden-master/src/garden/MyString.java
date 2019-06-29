/*
 * This java class is for the arraylist to save to file
 */
package garden;

/**
 *
 * @author Yuanzhe Liu
 */
public class MyString extends ImageDisplay {
    private final int myPattern;
    private final String myPath1;
    private final String myPath2;
    private final String myPath3;
    private final String myPath4;
    private final int flowerbed;

    public MyString (int myPattern, String imagePath1, String imagePath2, 
            String imagePath3, String imagePath4, int myX,int myY, 
            int myWidth, int myHeight, int flowerbed){
        super(imagePath1, myX, myY, myWidth, myHeight);
        this.myPath1 = imagePath1;
        this.myPath2 = imagePath2;
        this.myPath3 = imagePath3;
        this.myPath4 = imagePath4;
        this.myPattern = myPattern;
        this.flowerbed = flowerbed;
    }

    public int getFlowerbed() {
        return flowerbed;
    }

    public int getMyPattern() {
        return myPattern;
    }

    public String getMyPath1() {
        return myPath1;
    }

    public String getMyPath2() {
        return myPath2;
    }

    public String getMyPath3() {
        return myPath3;
    }

    public String getMyPath4() {
        return myPath4;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }

    @Override
    public String toString() {
        return "ImageDisplay" + ", Pattern = " + myPattern +
                                ", Path1 = " + myPath1 +
                                ", Path2 = " + myPath2 +
                                ", Path3 = " + myPath3 +
                                ", Path4 = " + myPath4 +
                                ", myX = " + myX +
                                ", myY = " + myY +
                                ", myWidth = " + myWidth +
                                ", myHeight = " + myHeight +
                                ", flowerbed = " + flowerbed;
    }
}
