package us.mattgreen;

import java.util.*;

public class Main {

    private Scanner keyboard;
    private Cookbook cookbook;

    List<Meal> listb = new ArrayList<Meal>();
    List<Meal> listl = new ArrayList<Meal>();
    List<Meal> listdes = new ArrayList<Meal>();
    List<Meal> listdin = new ArrayList<Meal>();

    public Main() {
        keyboard = new Scanner(System.in);
        cookbook = new Cookbook();

        FileInput indata = new FileInput("meals_data.csv");

        String line;

        System.out.println("Reading in meals information from file...");
        while ((line = indata.fileReadLine()) != null) {
            String[] fields = line.split(",");
            cookbook.addElementWithStrings(fields[0], fields[1], fields[2]);
        }

        runMenu();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Select Action");
        System.out.println("1. List All Items");
        System.out.println("2. List All Items by Meal");
        System.out.println("3. Search by Meal Name");
        System.out.println("4. List total, average, min, max and median calories");
        System.out.println("5. Exit");
        System.out.print("Please Enter your Choice: ");
    }

    private void runMenu() {
        boolean userContinue = true;

        while (userContinue) {
            printMenu();

            String ans = keyboard.nextLine();
            switch (ans) {
                case "1":
                    cookbook.printAllMeals();
                    break;
                case "2":
                    listByMealType();
                    break;
                case "3":
                    searchByName();
                    break;
                case "4":
                    calories();
                    break;
                case "5":
                    userContinue = false;
                    break;
            }
        }

        System.out.println("Goodbye");
        System.exit(0);
    }

    private void listByMealType() {
        // Default value pre-selected in case
        // something goes wrong w/user choice
        MealType mealType = MealType.DINNER;

        System.out.println("Which Meal Type");

        // Generate the menu using the ordinal value of the enum
        for (MealType m : MealType.values()) {
            System.out.println((m.ordinal() + 1) + ". " + m.getMeal());
        }

        System.out.print("Please Enter your Choice: ");
        String ans = keyboard.nextLine();

        try {
            int ansNum = Integer.parseInt(ans);
            if (ansNum < MealType.values().length) {
                mealType = MealType.values()[ansNum - 1];
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Meal Type " + ans + ", defaulted to " + mealType.getMeal() + ".");
        }

        cookbook.printMealsByType(mealType);
    }

    private void searchByName() {
        keyboard.nextLine();
        System.out.print("Please Enter Value: ");
        String ans = keyboard.nextLine();
        cookbook.printByNameSearch(ans);
    }

    private void calories() {
        MealType changer = MealType.BREAKFAST;

        FileInput indata = new FileInput("meals_data.csv");

        String line;


        while ((line = indata.fileReadLine()) != null) {
            String[] fields = line.split(",");
            MealType name = MealType.valueOf(fields[0].toUpperCase());
            Integer cal = Integer.valueOf(fields[2]);
            Meal mailflag = new Meal(name, fields[1], cal);
            if (name == MealType.BREAKFAST) {
                listb.add(mailflag);
            } else if (name == MealType.LUNCH) {
                listl.add(mailflag);
            } else if (name == MealType.DINNER) {
                listdin.add(mailflag);
            } else if (name == MealType.DESSERT) {
                listdes.add(mailflag);
            }

        }

        int smallestb = 500000;
        int largestb = 1;
        int breTotal = 0;

        for (Meal breakfast : listb) {
            int blist = breakfast.getCalories();
            breTotal = blist + breTotal;
            if (smallestb > blist) {
                smallestb = blist;
            }
            if (largestb < blist) {
                largestb = blist;
            }

        }
        System.out.println("Breakfast");

        System.out.println("Breakfast total: " + breTotal);
        System.out.println("Breakfast ave: " + breTotal / listb.size());
        System.out.println("Breakfast smallest: " + smallestb);
        System.out.println("Breakfast largest: " + largestb);
        int meadianb = listb.size()/ 2;
        System.out.println("median: " + listb.get(meadianb).getCalories());
        System.out.println("\n");


        System.out.println("Lunch");
        int smallestl = 500000;
        int largestl = 1;
        int lunTotal = 0;

        for (Meal lunch : listl) {
            int llist = lunch.getCalories();
            lunTotal = llist + lunTotal;
            if (smallestl > llist) {
                smallestl = llist;
            }
            if (largestl < llist) {
                largestl = llist;
            }
        }
            System.out.println("Lunch total: " + lunTotal);
            System.out.println("lunch ave: " + lunTotal / listl.size());
            System.out.println("Lunch smallest: " + smallestl);
            System.out.println("Lunch largest: " + largestl);
            int meadianl = listl.size()/ 2;
            System.out.println("median: " + listl.get(meadianl).getCalories());
            System.out.println("\n");



        System.out.println("Dinner");


            int smallestdin = 500000;
            int largestdin = 1;
            int dinTotal = 0;

            for (Meal breakfast : listdin) {
                int dinlist = breakfast.getCalories();
                dinTotal = dinlist + dinTotal;
                if (smallestdin > dinlist) {
                    smallestdin = dinlist;
                }
                if (largestdin < dinlist) {
                    largestdin = dinlist;
                }
            }
        System.out.println("Dinner total: " + dinTotal);
        System.out.println("Dinner ave: " + dinTotal / listdin.size());
        System.out.println("Dinner smallest: " + smallestdin);
        System.out.println("Dinner largest: " + largestdin);
        int meadiandin = listdin.size()/ 2;
        System.out.println("median: " + listdin.get(meadiandin).getCalories());
        System.out.println("\n");





            int desTotal = 0;
            int smallestdes = 500000;
            int largestdes = 1;

            for (Meal dessert : listdes) {
                int deslist = dessert.getCalories();
                desTotal = deslist + desTotal;


                if (smallestdes > deslist) {
                    smallestdes = deslist;
                }
                if (largestdes < deslist) {
                    largestdes = deslist;
                }

            }

            System.out.println("Dessert total: " + desTotal);
            System.out.println("Dessert ave: " + desTotal / listdes.size());
            System.out.println("Dessert smallest: " + smallestdes);
            System.out.println("Dessert largest: " + largestdes);
            int meadiandes = listdes.size()/ 2;
            System.out.println("median: " + listdes.get(meadiandes).getCalories());


    }

    }

