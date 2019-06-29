/*
 * This java class is for editing the text file
 * save remove load
 */
package garden;

import static garden.Garden.myString;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;

/**
 *
 * @author Yuanzhe Liu
 */
public class FileUtils {
    // The method for loading image
    public static BufferedImage loadImage(String imagePath){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Problem loading image: " + imagePath);
        }
        return image;
    }
    // The method for getting filenames and return a arraylist
    public static ArrayList<String> getFileNames(String dirPath){
        ArrayList<String> names = new ArrayList<>();
        File dir = new File(dirPath);
        if (dir.isDirectory()){
            for (File f : dir.listFiles()){
                String name = f.getPath();
                if (name.endsWith(".png")) {
                    names.add(f.getPath());
                }
            }
        }
        return names;
    }
    // The method for removing Flower
    public static void removeFlower() {
        int counter = 0;
        int count = 0;
        Garden.clearArrayList();
        Path filePath = Paths.get("garden.txt");
        if(!Files.exists(filePath)){
            System.out.println("There's no flowerbed to remove.");
            UserInterface.showMenu();
        }
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("ImageDisplay")){
                    Garden.myString.add(line);
                    count++;
                }
                else{
                    System.out.println("Bad line in file: "+ line);
                    UserInterface.showMenu();
                }   
            }
            Iterator iterator = myString.iterator();
            while(iterator.hasNext() && counter < count){                       //Display the removing information
                for (String Line : myString) {
                    counter++;
                    String[] data = Line.split(", | = ");                       // split the line to display flower for removing the flower 
                    String Path1 = data[4];
                    String Path2 = data[6];
                    String Path3 = data[8];
                    String Path4 = data[10];
                    int myPattern = Integer.parseInt(data[2]);
                    int x = Integer.parseInt(data[12]);
                    int y = Integer.parseInt(data[14]);
                    int width = Integer.parseInt(data[16]);
                    int height = Integer.parseInt(data[18]);
                    int flowerbed = Integer.parseInt(data[20]);
                    System.out.print(counter + ". ");
                    if (myPattern == 1){                                        // if the pattern is square, display the information
                        System.out.print("Pattern: Square ");
                        System.out.print(" Flowerbed: ");
                        if (flowerbed == 1) {
                            System.out.print("Yes");
                        }
                        else if (flowerbed == 2) {
                            System.out.print(" No");
                        }
                        System.out.print(" Location: X: " + x);
                        System.out.print(" Y: " + y);
                        System.out.print(" Width: " + width);
                        System.out.print(" Height: " + height);
                        System.out.println(" Flower: " + Path1);
                    }
                    else if (myPattern == 21) {                                 // if the pattern is row-Stripe, display the information
                        System.out.print("Pattern: Row-Stripe ");
                        System.out.print(" Flowerbed: ");
                        if (flowerbed == 1) {
                            System.out.print("Yes");
                        }
                        else if (flowerbed == 2) {
                            System.out.print(" No");
                        }
                        System.out.print(" Location: X: " + x);
                        System.out.print(" Y: " + y);
                        System.out.print(" Width: " + width);
                        System.out.print(" Height: " + height);
                        System.out.print(" Flower: " + Path1);
                        System.out.print(" " + Path2);
                        System.out.print(" " + Path3);
                        System.out.println(" " + Path4);
                    }
                    else if (myPattern == 22) {                                 // if the pattern is column Stripe, display the information
                        System.out.print("Pattern: Column-Stripe ");
                        System.out.print(" Flowerbed: ");
                        if (flowerbed == 1) {
                            System.out.print("Yes");
                        }
                        else if (flowerbed == 2) {
                            System.out.print(" No");
                        }
                        System.out.print(" Location: X: " + x);
                        System.out.print(" Y: " + y);
                        System.out.print(" Width: " + width);
                        System.out.print(" Height: " + height);
                        System.out.print(" Flower: " + Path1);
                        System.out.print(" " + Path2);
                        System.out.print(" " + Path3);
                        System.out.println(" " + Path4);
                    }
                    else if (myPattern == 3) {                                  // if the pattern is radnom, display the information
                        System.out.print("Pattern: Random ");
                        System.out.print(" Flowerbed: ");
                        if (flowerbed == 1) {
                            System.out.print("Yes");
                        }
                        else if (flowerbed == 2) {
                            System.out.print(" No");
                        }
                        System.out.print(" Location: X: " + x);
                        System.out.print(" Y: " + y);
                        System.out.print(" Width: " + width);
                        System.out.print(" Height: " + height);
                        System.out.print(" Flower: " + Path1);
                        System.out.print(" " + Path2);
                        System.out.print(" " + Path3);
                        System.out.println(" " + Path4);
                    }
                }
            }
            if (counter == 0) {                                                 // if the txt file is empty, jump to the first menu
                System.out.println("There's no flowerbed to remove.");
                UserInterface.showMenu();
            }
            else {
                System.out.println((counter + 1) + ". Exit");
            }
            int input = UserInterface.getIntInput();
            while (input < 1 || input > counter + 1) {                          // for checking if the input is valid
                System.out.println("Please input an integer between 1 and " + (counter + 1));
                input = UserInterface.getIntInput();
            }
            if (input == counter + 1) {                                         // for exit
                UserInterface.showMenu();
            }
            Garden.removeGarden(input);             // remove in the arraylist
            Garden.saveGarden();                    // save the arraylist to txt
            Garden.clearWindow();                   // dispose the window and open a new one
            readGardenFromFile();                   // add flower from file
            Garden.addBackground();                 // add the mud background
        } catch (Exception e) {
            System.out.println("Error!!!");
        }
        
    }
    // The method for reading garden from file
    public static void readGardenFromFile(){
        Path filePath = Paths.get("garden.txt");
        if(!Files.exists(filePath)){
            System.out.println("There is no file to read from.");
        }
        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.startsWith("ImageDisplay")){
                    imageFromString(line);
                }
                else{
                    System.out.println("Bad line in file: "+ line);
                }   
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + filePath);
        }
    }
    // The method for saving graden to the txt file
    public static boolean saveGardenToFile(ArrayList<String> toWrite) {
        try (FileWriter fw = new FileWriter("garden.txt", false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for(String str: toWrite){
                pw.println(str);                                                // write the arraylist to the txt text file
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    // The method for adding image from string line
    private static void imageFromString(String line){
        try {
            String[] data = line.split(", | = ");                               // split the line to several parts for adding flower
            String Path1 = data[4];
            String Path2 = data[6];
            String Path3 = data[8];
            String Path4 = data[10];
            int myPattern = Integer.parseInt(data[2]);
            int x = Integer.parseInt(data[12]);
            int y = Integer.parseInt(data[14]);
            int width = Integer.parseInt(data[16]);
            int height = Integer.parseInt(data[18]);
            int flowerbed = Integer.parseInt(data[20]);
            Garden.addFlower(myPattern, Path1, Path2, Path3, Path4, x, y, width, 
                    height, flowerbed);                                         // adding flower on screen
            Garden.myString.add(line);                                          // add the information of flower to myString for saving
        } 
        catch (Exception e) {
        }
    }
    // This method get the input and check if the input is valid.
    // if it is invalid, let the user input again
    public static int getXInput() {
        int x = UserInterface.getIntInput();
        while (x < 0 || x > 800) {
                System.out.println("Please input the correct location x");
                x = UserInterface.getIntInput();
        }
        return x;
    }
    // This method get the input and check if the input is valid.
    // if it is invalid, let the user input again
    public static int getYInput() {
        int y = UserInterface.getIntInput();
        while (y < 0 || y > 600) {
                System.out.println("Please input the correct location y");
                y = UserInterface.getIntInput();
        }
        return y;
    }
    // This method get the input and check if the input is valid.
    // if it is invalid, let the user input again
    public static int getWidthInput() {
        int width = UserInterface.getIntInput();
        while (width < 20 || width > 800) {
                System.out.println("Please input the correct width");
                width = UserInterface.getIntInput();
        }
        return width;
    }
    // This method get the input and check if the input is valid.
    // if it is invalid, let the user input again
    public static int getHeightInput() {
        int height = UserInterface.getIntInput();
        while (height < 20 || height > 600) {
                System.out.println("Please input the correct height");
                height = UserInterface.getIntInput();
        }
        return height;
    }
    // This method get the input and check if the input is valid.
    // if it is invalid, let the user input again
    public static int getImageIndex(int counter) {
        int input = UserInterface.getIntInput();
        if (input == counter) {
                System.out.println();
                UserInterface.showMenu();
            }
        while (input <= 0 || input > counter) {
            System.out.println("Please input the integer between 1-" + counter);
            input = UserInterface.getIntInput();
            if (input == counter) {
                System.out.println();
                UserInterface.showMenu();
            }
        }
        return input;
    }
}
