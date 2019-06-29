/*
 * This class is for the user interface
 */
package garden;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Yuanzhe Liu
 */
public class UserInterface {
    // create a new Scanner kb
    private static Scanner kb;
    // This First-Menu
    public static void showMenu() {
        System.out.println("------------------------------");
        System.out.println("Welcome to my Garden!");
        System.out.println();
        System.out.println("Please select:");
        System.out.println("1. Add Flowerbed");
        System.out.println("2. Remove Flowerbed");
        System.out.println("3. Save and Exit");
        System.out.println();
        int command = getIntInput();
        switch (command) {
            case 1:
                addImageMenu();
                break;
            case 2:
                System.out.println("Removing animal...");
                FileUtils.removeFlower();
                System.out.println("Removed sucessfully!");
                showMenu();
                break;
            case 3:
                System.out.println("Save successfully!");
                System.out.println("Exit!");
                Garden.closeWindow();
                System.exit(0);
                break;
            default:
                System.out.println("Please input a value from 1 to 3");
                showMenu();
        }
    }
    // The second menu for adding image
    private static void addImageMenu() {
        System.out.println("Please seclect the pattern you want:");
        System.out.println("1. Square");
        System.out.println("2. Stripe");
        System.out.println("3. Random");
        System.out.println("4. Exit");
        System.out.println();
        int command = getIntInput();
        switch (command) {
            case 1:
                getImageInput(command);
                Garden.saveGarden();
                Garden.clearWindow();
                FileUtils.readGardenFromFile();
                Garden.addBackground();
                showMenu();
                break;
            case 2:
                StripeMenu();
                break;
            case 3:
                getImageInput(command);
                Garden.saveGarden();
                Garden.clearWindow();
                FileUtils.readGardenFromFile();
                Garden.addBackground();
                showMenu();
                break;
            case 4:
                showMenu();
                break;
            default:
                System.out.println("Please input a value from 1 to 4");
                addImageMenu();
        }
    }
    // The third menu for adding stripe flower
    private static void StripeMenu(){
        System.out.println("Please seclect the pattern you want:");
        System.out.println("1. Row");
        System.out.println("2. Column");
        System.out.println("3. Exit");
        System.out.println();
        int command = getIntInput();
        switch (command) {
            case 1:
                command = 21;
                getImageInput(command);
                Garden.saveGarden();
                Garden.clearWindow();
                FileUtils.readGardenFromFile();
                Garden.addBackground();
                showMenu();
                break;
            case 2:
                command = 22;
                getImageInput(command);
                Garden.saveGarden();
                Garden.clearWindow();
                FileUtils.readGardenFromFile();
                Garden.addBackground();
                showMenu();
                break;
            case 3:
                addImageMenu();
                break;
            default:
                System.out.println("Please input a value from 1 to 3");
                StripeMenu();
        }
    }
    // This method is to get an integer
    public static int getIntInput() {
        kb = new Scanner(System.in);
        int input;
        try {
            input = Integer.parseInt(kb.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println("Please input an integer");
            input = getIntInput();
        }
        return input;
    }
    // This method is to get the information from user for adding flowerbed
    private static void getImageInput(int myPattern) {
        System.out.println("Adding flower...");
        System.out.println("Do you need a flowerbed");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("");
        int flowerbed = getIntInput();
        while (flowerbed < 1 && flowerbed > 2 ) {                               // to check if the user input the valid value
            System.out.println("Please input an integer between 1 and 2");
            flowerbed = getIntInput();
        }
        if (myPattern == 1) {                                                   // for adding square flowerbed
            ArrayList<String> fileNames = FileUtils.getFileNames("res");
            int counter = 1;
            System.out.println("Please selcet the flower image you want:");
            for(String s : fileNames){
                if(s.endsWith(".png")){
                    System.out.println(counter + ". " + s);                     // display the flower name
                }
                counter++;
            }
            System.out.println(counter + ". Exit");
            int input = getIntInput();
            if (input == counter) {
                System.out.println();
                showMenu();
            }
            while (input <= 0 || input > counter) {                             // to check if the user input the valid value
                System.out.println("Please input the integer between 1-" + counter);
                input = getIntInput();
                if (input == counter) {
                    System.out.println();
                    showMenu();
                }
            }
            String path = fileNames.get(input - 1);
            System.out.println("You selected image with " + path);
            System.out.println("Please input image width:");
            int width = FileUtils.getWidthInput();
            System.out.println("Please input image height:");
            int height = FileUtils.getHeightInput();
            System.out.println("Please input location x:");
            int x = FileUtils.getXInput();
            System.out.println("Please input location y:");
            int y = FileUtils.getYInput();
            Garden.addFlower(myPattern, path, path, path, path, x, y, width, 
                    height, flowerbed);
            MyString myStr= new MyString(myPattern, path, path, path, path, x, 
                    y, width, height, flowerbed);
            Garden.myString.add(myStr.toString());
        }
        else if (myPattern == 21 || myPattern == 22 || myPattern == 3) {        // for stripe and random flowerbed
            ArrayList<String> fileNames = FileUtils.getFileNames("res");
            int counter = 1;
            System.out.println("Please select four flowers you want:");
            for(String s : fileNames){
                if(s.endsWith(".png")){
                    System.out.println(counter + ". " + s);
                }
                counter++;
            }
            System.out.println(counter + ". Exit");
            System.out.println();                                               // get information from user
            System.out.println("Image 1:");
            int input = FileUtils.getImageIndex(counter);
            String path1 = fileNames.get(input - 1);
            System.out.println("And? Image 2:");
            input = FileUtils.getImageIndex(counter);
            String path2 = fileNames.get(input - 1);
            System.out.println("And? Image 3:");
            input = FileUtils.getImageIndex(counter);
            String path3 = fileNames.get(input - 1);
            System.out.println("And? Image 4:");
            input = FileUtils.getImageIndex(counter);
            String path4 = fileNames.get(input - 1);
            System.out.println("You selected image with " + path1 + ", " + 
                    path2 + ", " + path3 + " and " + path4);
            System.out.println("Please input image width:");
            int width = FileUtils.getWidthInput();
            System.out.println("Please input image height:");
            int height = FileUtils.getHeightInput();
            System.out.println("Please input location x:");
            int x = FileUtils.getXInput();
            System.out.println("Please input location y:");
            int y = FileUtils.getYInput();
            Garden.addFlower(myPattern, path1, path2, path3, path4, x, y, 
                    width, height, flowerbed);                                  // adding flower on screen
            MyString myStr= new MyString(myPattern, path1, path2, path3, path4, 
                    x, y, width, height, flowerbed);                            // adding the information to myString
            Garden.myString.add(myStr.toString());
        }
    }
}
