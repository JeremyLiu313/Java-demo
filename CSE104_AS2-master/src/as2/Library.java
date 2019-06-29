package as2;

import java.util.*;

public class Library {
    private static List<book> library = new ArrayList<>();
    private static final String[] categories = {"Arts", "Business", "Comics", "IT", "Cooking", "Sports"};
    private static final String[] menuList = {"Add", "Update", "Search", "Delete", "Display", "Quit"};
    private static final String[] updateMenuList = {"T", "A", "C", "AC", "TC"};
    private static String command;
    private static String input;
    private static int index;
    private static Scanner scanner = new Scanner(System.in);

    // Main Method
    public static void main(String[] args) {
        showMenu();
    }
    // The method for getting a String from the user and return a String
    private static String getInput() {
        return scanner.nextLine();
    }
    
    // The method for getting an integer from the user and return an integer
    private static int getIntInput() {
        String str =getInput();
        int input;
        try {
            input = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            System.out.println("    o Please input an integer");
            input = getIntInput();
        }
        return input;
    }
    
    // The method for the interface menu
    private static void showMenu() {
        System.out.println("\uF0D8 Welcome to the library management system, functions provided include the following:");
        System.out.println("\to Add – to add a new book");
        System.out.println("\to Update – to update book info");
        System.out.println("\to Search – to enquire about book info");
        System.out.println("\to Delete – to delete a book");
        System.out.println("\to Display– to display book(s) info");
        System.out.println("\to Quit – to exit from the current level of interactions\n");
        System.out.print("\uF0D8 Enter your command here (Enter ‘Quit’ at any time to exit from current level): ");
        command = getInput();
        while (!Arrays.asList(menuList).contains(command)) {
            System.out.println("\uF0D8 Please input the correct command!");
            command = getInput();
        }
        switch (command) {
            case "Add":
                addMenu();
                break;
            case "Update":
                updateMenu();
                break;
            case "Search":
                searchMenu();
                break;
            case "Delete":
                deleteMenu();
                break;
            case "Display":
                displayMenu();
                break;
            case "Quit":
                System.out.println("\n\uF0D8 Quit Successfully!");
                System.exit(0);
                break;
        }
    }
    // Adding book menu
    private static void addMenu() {
        System.out.println("\uF0D8 Adding books...");
        System.out.print("\to Enter a new book ISBN: ");
        String ISBN = inputISBN();
        if (searchBook(ISBN)) {
            System.out.println("\to Book " + ISBN + " already exists!");
            System.out.println("\to Note: automatically exit from current level.\n");
            showMenu();
        }
        System.out.println("\t\tISBN: " + ISBN + " entered.");
        System.out.print("\to Enter the title: ");
        String title = getInput();
        System.out.println("\t\tTitle: " + title + " entered.");
        System.out.print("\to Enter the author: ");
        String author = getInput();
        while (!checkBook(author, title)) {
            System.out.println("\to Please re-enter Author or enter ‘T’ to re-enter title: ");
            input = getInput();
            if (input.equals("T")) {
                System.out.println("\to Please re-enter the title: ");
                title = getInput();
                System.out.println("\t\tTitle: " + title + " entered.");
            }
            else {
                author = input;
                System.out.println("\t\tAuthor: " + author + " entered.");
            }
        }
        System.out.println("\to There are six categories: Arts, Business, Comics, IT, Cooking, Sports.");
        System.out.print("\to Enter the category: ");
        String category = inputCategory();
        System.out.println("\t\tCategory: " + category + " entered.");
        System.out.print("\to Enter total copy number: ");
        int copyNum = inputCopies();
        System.out.println("\t\tCopy Number: " + copyNum + " entered.");
        System.out.println("\to Ready to add book: ");
        printListTitle();
        printList(ISBN, title, author, category, copyNum, copyNum);
        System.out.print("\to Enter ‘Y’ to add new book. Anything else to quit: ");
        input = getInput();
        if (input.equals("Y")) {
            addToLibrary(ISBN, title, author, category, copyNum);
            System.out.println("\to New book added successfully!");
            System.out.println("\to Note: automatically exit from current level.\n");
            showMenu();
        }
        else {
            System.out.println("\to Cancelled by user, automatically exit from current level.\n");
            showMenu();
        }
    }
    
    // Update book menu
    private static void updateMenu() {
        int oldNum, intInput;
        String oldStr;
        System.out.println("\uF0D8 Updating Book...");
        checkEmpty();
        System.out.print("\to Input ISBN: ");
        String ISBN = inputISBN();
        if (!searchBook(ISBN)) {
            System.out.println("\to Can't find the book!");
            System.out.println("\to Automatically exit from current level.\n");
            showMenu();
        }
        System.out.print("\to Enter type of information you want to update, ‘T’ for title, ‘A’ for author,\n" +
                " ‘C’ for category, ‘TC’ for total copy number, ‘AC’ for available number: ");
        command = getInput();
        while (!Arrays.asList(updateMenuList).contains(command)) {
            System.out.println("\t\tPlease input the correct information!");
            command = getInput();
        }
        switch (command) {
            case "T":
                oldStr = library.get(index).getTitle();
                System.out.print("\to Enter your new title: ");
                input = getInput();
                library.get(index).setTitle(input);
                System.out.println("\t\tBook " + ISBN + "‘s title has been updated from " + oldStr + " to "
                        + input + " successfully.");
                break;
            case "A":
                oldStr = library.get(index).getAuthor();
                System.out.print("\to Enter your new author: ");
                input = getInput();
                library.get(index).setAuthor(input);
                System.out.println("\t\tBook " + ISBN + "‘s author has been updated from " + oldStr + " to "
                        + input + " successfully.");
                break;
            case "C":
                oldStr = library.get(index).getCategory();
                System.out.println("\to There are six categories: Arts, Business, Comics, IT, Cooking, Sports.");
                System.out.print("\to Enter your new category: ");
                input = inputCategory();
                library.get(index).setCategory(input);
                System.out.println("\t\tBook " + ISBN + "‘s category has been updated from " + oldStr + " to "
                        + input + " successfully.");
                break;
            case "AC":
                oldNum = library.get(index).getAvailableCopies();
                System.out.print("\to Enter your new available number: ");
                intInput = inputCopies();
                if (intInput > library.get(index).getTotalCopies()){
                    System.out.println("\to The number of available copies must be lower than the total copies!");
                    System.out.println("\to Note: automatically exit from current level.\n");
                    showMenu();
                }
                library.get(index).setAvailableCopies(intInput);
                System.out.println("\t\tBook " + ISBN + "‘s available copy number has been updated from " + oldNum
                        + " to " + intInput + " successfully.");
                break;
            case "TC":
                oldNum = library.get(index).getTotalCopies();
                if (oldNum != library.get(index).getAvailableCopies()) {
                    System.out.println("\to Please return all the books before doing this!");
                    System.out.println("\to Note: automatically exit from current level.\n");
                    showMenu();
                }
                System.out.print("\to Enter your new total copy number: ");
                intInput = inputCopies();
                library.get(index).setAvailableCopies(intInput);
                library.get(index).setTotalCopies(intInput);
                System.out.println("\t\tBook " + ISBN + "‘s total copy number has been updated from " + oldNum
                        + " to " + intInput + " successfully.");
                break;
        }
        System.out.println("\to Note: automatically exit from current level.\n");
        showMenu();
    }
    
    // Search book menu
    private static void searchMenu() {
        System.out.println("\uF0D8 Searching book...");
        checkEmpty();
        System.out.print("\to Enter your keyword: ");
        input = getInput();
        if (searchKeywords(input)) {
            System.out.print("\to Enter ‘Y’ to search other books, anything else to quit: ");
            input = getInput();
            if (input.equals("Y")) {
                searchMenu();
            }
            else {
                System.out.println("\to Note: automatically exit from current level.\n");
                showMenu();
            }
        }
        System.out.println("\to Can't find the book.");
        System.out.println("\to Note: automatically exit from current level.\n");
        showMenu();
    }
    
    // The menu for deleting book
    private static void deleteMenu() {
        System.out.println("\uF0D8 Deleting book...");
        checkEmpty();
        System.out.print("\to Enter the book’s ISBN or title + author(without space between '+'): ");
        input = getInput();
        if (checkISBN(input)) {
            searchBook(input);
            System.out.println("\t\tFound book: ");
            printListTitle();
            printList(library.get(index));
        }
        else if (checkBook(input)) {
            System.out.println("\t\tFound book: ");
            printListTitle();
            printList(library.get(index));
        }
        else {
            System.out.println("\to Can't find the book, automatically exit from current level.\n");
            showMenu();
        }
        System.out.print("\to Enter ‘Y’ to delete the book. Anything else to quit: ");
        input = getInput();
        if (input.equals("Y")) {
            if (library.get(index).getAvailableCopies() == library.get(index).getTotalCopies()) {
                System.out.println("\t\tBook with ISBN " + library.get(index).getISBN() + " has been deleted successfully.");
                library.remove(library.get(index));
            }
            else {
                int lentNum = library.get(index).getTotalCopies() - library.get(index).getAvailableCopies();
                System.out.println("\t\tSorry this book cannot be deleted.");
                System.out.println("\t\tThere are " + lentNum + " copies have been lent out.");
            }
            System.out.println("\to Note: automatically exit from current level.\n");
            showMenu();
        }
        else {
            System.out.println("\to Cancelled by user, automatically exit from current level.\n");
            showMenu();
        }
    }
    
    // The menu for displaying books
    private static void displayMenu() {
        System.out.println("\uF0D8 Displaying book...");
        checkEmpty();
        System.out.print("\to Enter ‘C’ for displaying group by category, or ‘A’ for displaying group by author: ");
        input = getInput();
        switch (input) {
            case "C":
                Collections.sort(library, new categoryComparator());
                searchKeywords("");
                break;
            case "A":
                Collections.sort(library, new authorComparator());
                searchKeywords("");
                break;
            default:
                System.out.println("\to Please input the correct command!");
                System.out.println("\to Note: automatically exit from current level.\n");
                break;
        }
        System.out.println("\to Note: automatically exit from current level.\n");
        showMenu();
    }
    
    // The method for adding the book to the library
    // parameter is the information of book
    // and add it yo the list: library
    private static void addToLibrary(String ISBN, String title, String author, String category, int copyNum) {
        library.add(new book(ISBN, title, author, category, copyNum, copyNum));
    }

    // The method for searching keywords
    // if exists, then print the information
    // parameter is the keywords and return a boolean value
    private static boolean searchKeywords(String keyWords) {
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getTitle().contains(keyWords)) {
                // for printing the List title
                if (j == 0) {
                    System.out.println("\t\tFound book(s): ");
                    printListTitle();
                }
                printList(library.get(i));
                index = i;
                j++;
                flag = true;
            }
        }
        return flag;
    }
    
    // The method for searching ISBN
    // parameter is the ISBN and return a boolean value
    private static boolean searchBook(String ISBN) {
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getISBN().equals(ISBN)) {
                index = i;
                return true;
            }
        }
        return false;
    }
    
    // The method for searching title and author
    // parameter is the keywords and return a boolean value
    private static boolean searchBook(String title, String author) {
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getTitle().equals(title) && library.get(i).getAuthor().equals(author)) {
                index = i;
                return true;
            }
        }
        return false;
    }
    
    // The method for checking author and title
    // parameter is the author and title and return a boolean value
    private static boolean checkBook(String author, String title) {
        for (book book : library) {
            if (book.getAuthor().equals(author) && book.getTitle().equals(title)) {
                return false;
            }
        }
        return true;
    }
    // A method for deleting book when the user input 'title+author'
    // spliting the line of checking if the book exists
    // return a boolean value
    private static boolean checkBook(String line) {
        String title = "";
        String author = "";
        try {
            for (String retval: line.split("\\+", 2)){
               if (title.isEmpty()) {
                   title = retval;
               }
               else {
                   author = retval;
               }
            }
        }
        catch (Exception e) {
            return false;
        }
        return searchBook(title, author);
    }
    
    // for getting the copy number
    // 0 < num <= 20
    // and return a integer
    private static int inputCopies() {
        int copyNum = getIntInput();
        while (copyNum > 20 || copyNum < 1) {
            System.out.println("\t\tThe copy number must be an integer and no more than 20 (0 < N <= 20)");
            System.out.print("\to Enter total copy number: ");
            copyNum = getIntInput();
        }
        return copyNum;
    }

    private static boolean checkISBN(String ISBN) {
        if (ISBN.length() != 10 && ISBN.length() != 13) {
            return false;
        }
        for (int i = 0; i < ISBN.length(); i++) {
            if (!Character.isDigit(ISBN.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static String inputISBN() {
        String ISBN = getInput();
        while (!checkISBN(ISBN)) {
            System.out.println("\to ISBN is a 10 or 13-digit number, please input the correct ISBN!");
            ISBN = inputISBN();
        }
        return ISBN;
    }

    private static String inputCategory() {
        String category = getInput();
        while (!Arrays.asList(categories).contains(category)) {
            System.out.println("\to Please input the correct category!");
            category = getInput();
        }
        return category;
    }
    
    // A method for printing the list title
    private static void printListTitle() {
        System.out.printf("\t\t%-15s%-20s%-20s%-15s%-15s%-15s\n", "ISBN", "Title", "Author", "Category", "Total Copies", "Available Copies");
    }
    
    // A method for printing the list from book
    private static void printList(book book) {
        System.out.printf("\t\t%-15s%-20s%-20s%-15s%-15d%-15d\n", book.getISBN(), book.getTitle(),
                book.getAuthor(), book.getCategory(), book.getTotalCopies(), book.getAvailableCopies());
    }
    
    // A method for printing the list from book's information
    private static void printList(String ISBN, String title, String author, String category, int totalCopies,
                                  int availableCopies) {
        System.out.printf("\t\t%-15s%-20s%-20s%-15s%-15d%-15d\n", ISBN, title, author, category, totalCopies, availableCopies);
    }
    
    // A method for checking whether the library is empty and give notification
    private static void checkEmpty() {
        if (library.isEmpty()) {
            System.out.println("\to There's no book in the library, please add books first!");
            System.out.println("\to Note: automatically exit from current level.\n");
            showMenu();
        }
    }
    
    // Creating a new comparator for sorting the library by category
    private static class categoryComparator implements Comparator {
        @Override
        public int compare(Object object1, Object object2) {
            book book1 = (book) object1;
            book book2 = (book) object2;
            return book1.getCategory().compareTo(book2.getCategory());
        }
    }
    
    // Creating a new comparator for sorting the library by author
    private static class authorComparator implements Comparator {
        @Override
        public int compare(Object object1, Object object2) {
            book book1 = (book) object1;
            book book2 = (book) object2;
            return book1.getAuthor().compareTo(book2.getAuthor());
        }
    }
}