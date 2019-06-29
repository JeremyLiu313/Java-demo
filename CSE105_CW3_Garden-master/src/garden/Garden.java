/*
 * This is the main class of the garden
 * This class is for controlling the JFrame window
 */
package garden;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFrame;

/**
 *
 * @author Yuanzhe Liu
 */
public class Garden {
    private static JFrame gardenJFrame;
    private static ArrayList<ImageDisplay> myGarden = new ArrayList<>();        // the arraylist for imageDisplay
    public static ArrayList<String> myString = new ArrayList();                 // create an arraylist for storing the flower
    // The main method of the program
    public static void main(String[] args) {
        System.out.println("Loading from file...");
        newJFrame();
        FileUtils.readGardenFromFile();                                         // read from text to load the flowerbed
        addBackground();
        System.out.println();
        System.out.println("Loading sucessfully!");
        UserInterface.showMenu();
    }
    // This method is for creating a new JFrame window
    private static void newJFrame() {
        gardenJFrame = new JFrame();
        gardenJFrame.setVisible(true);
        gardenJFrame.setSize(800,600);
        gardenJFrame.getContentPane().setBackground(new Color(77,57,24));
        gardenJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // This method is to dispose the window and reopen it
    public static void clearWindow() {
        closeWindow();
        newJFrame();
    }
    // This method is to close the JFrame Window
    public static void closeWindow() {
        gardenJFrame.dispose();
    }
    // This method is to clear the myString ArrayList and myGarden ArrayList
    public static void clearArrayList() {
        myString.clear();
        myGarden.clear();
    }
    // This method is to remove one from the myString ArrayList
    public static void removeGarden(int input) {
        myString.remove(myString.get(input - 1));
    }
    // This method is to add a background for the JFrame
    protected static void addBackground() {
        ImageDisplay myImage;
        myImage = new ImageDisplay("res/mud.jpg", 0, 0, 800, 600);
        gardenJFrame.add(myImage);
        gardenJFrame.repaint();
    }
    // This is the method for adding Flowerbed
    protected static void addFlower(int myPattern, String imagePath1, 
            String imagePath2, String imagePath3,String imagePath4, int x, 
            int y, int width, int height, int flowerbed){
        String[] arrImagePath = new String[4];                                  // create a new string array for getting the random flower
        arrImagePath[0] = imagePath1;
        arrImagePath[1] = imagePath2;
        arrImagePath[2] = imagePath3;
        arrImagePath[3] = imagePath4;
        ImageDisplay myImage;
        if (myPattern == 1) {                                                   // If the pattern is square
            for (int i = x; i < x + width;i += 20){                             // the for loop for row
                for (int j = y; j < y + height; j += 20){                       // the for loop for column
                    int m = y + height - j;
                    int n = x + width - i;
                    if (m >= 20 && n >= 20){                                    // to check if it can add flower on the rest space
                        myImage = new ImageDisplay(imagePath1, i, j, 20, 20);
                        myImage.setOpaque(false);
                        gardenJFrame.add(myImage);
                        myGarden.add(myImage);
                        gardenJFrame.repaint();
                    }
                }
            }
        }
        if (myPattern == 21) {                                                  // If the pattern is row stripe
            for (int j = y; j < y + height ; j += 20) {                         // the for loop for column
                imagePath1 = arrImagePath[(int)(Math.random()*3)];              // get the path of random flower
                for (int i = x; i < x + width; i += 20) {                       // the for loop for row
                    int m = y + height - j;
                    int n = x + width - i;
                    if (m >= 20 && n >= 20){                                    // to check if it can add flower on the rest space
                        myImage = new ImageDisplay(imagePath1, i, j, 20, 20);
                        myImage.setOpaque(false);
                        gardenJFrame.add(myImage);
                        myGarden.add(myImage);
                        gardenJFrame.repaint();
                    }
                }
            }
        }
        if (myPattern == 22) {                                                  // If the pattern is column stripe
            for (int i = x; i < x + width ; i += 20) {                          // the for loop for row
                imagePath1 = arrImagePath[(int)(Math.random()*3)];              // get the path of random flower
                for (int j = y; j < y + height; j += 20) {                      // the for loop for column
                    int m = y + height - j;
                    int n = x + width - i;
                    if (m >= 20 && n >= 20){                                    // to check if it can add flower on the rest space
                        myImage = new ImageDisplay(imagePath1, i, j, 20, 20);
                        myImage.setOpaque(false);
                        gardenJFrame.add(myImage);
                        myGarden.add(myImage);
                        gardenJFrame.repaint();
                    }
                }
            }
        }
        if (myPattern == 3) {                                                   // If the pattern is random
            for (int i = x; i < x + width ; i += 20) {                          // the for loop for row
                for (int j = y; j < y + height; j += 20) {                      // the for loop for column
                    int m = y + height - j;
                    int n = x + width - i;
                    if (m >= 20 && n >= 20){                                    // to check if it can add flower on the rest space
                        imagePath1 = arrImagePath[(int)(Math.random()*3)];      // get the path of random flower
                        myImage = new ImageDisplay(imagePath1, i, j, 20, 20);
                        myImage.setOpaque(false);
                        gardenJFrame.add(myImage);
                        myGarden.add(myImage);
                        gardenJFrame.repaint();
                    }
                }
            }
        }
        if (flowerbed == 1) {                                                   // if the user need a folwerbed, add one
            myImage = new ImageDisplay("res/grass.jpg", x, y, width, height);
            gardenJFrame.add(myImage);
            gardenJFrame.repaint();
        }
    }
    // The method for saving garden to file
    protected static void saveGarden(){
        ArrayList<String> myStringWithoutDup;                                   // Create a new ArrayList
        myStringWithoutDup = new ArrayList<>(new HashSet<>(myString));          // To check if the arrayList has the same items
        FileUtils.saveGardenToFile(myStringWithoutDup);
    }
    // the getter for getting JFrame GardenJFrame
    public static JFrame getGardenJFrame() {
        return gardenJFrame;
    }
    // the getter for getting ArrayList<ImageDisplay> MyGarden
    public static ArrayList<ImageDisplay> getMyGarden() {
        return myGarden;
    }
    // the getter for getting ArrayList<String> MyString
    public static ArrayList<String> getMyString() {
        return myString;
    }   
}
