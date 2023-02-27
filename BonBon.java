//This program was made by Alenna for the CTE course "Software & Programming Development 2" instructed by Mr. Gross
//Email - alenna.castaneda@oneidaihla.org

//importing required classes
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BonBon {
    //creating attributes
    String name;
    int age;
    String owner;
    String gender;
    float cmHeight;
    Boolean correctDataType = false;
    static String delim = "\u0007";
    static float totalAge = 0;
    static float totalHeight = 0;

    //constructor
    public BonBon() {
        //setting attributes
        this.name = "empty";
        this.age = 0;
        this.owner = "empty";
        this.gender = "e";
        this.cmHeight = 0;
    }

    //driver
    public static void main(String[] args) {
        //creating file object
        File recordFile = new File("record.txt");
        //creating Scanner object that reads from System.in
        Scanner inputScan = new Scanner(System.in);
        //creating StringTokenizer Object
        StringTokenizer tokenizer;
        float avgAge;
        float avgHeight;

        try {
            //creating printWriter object
            PrintWriter recordWriter = new PrintWriter(new FileWriter(recordFile));
            //creating Scanner object that reads from recordFile
            Scanner fileScan = new Scanner(recordFile);

            //creating BonBon object
            BonBon template = new BonBon();
            
            System.out.println("Welcome to BonBon Daycare!\nPlease enter the following information for your BonBon.");
            System.out.println();

            //looping 5 times
            for (int i = 1; i <= 5; i++) {
                //resetting variables
                template.name = "empty";
                template.age = 0;
                template.owner = "empty";
                template.gender = "e";
                template.cmHeight = 0;

                System.out.println("BonBon "+i);
                System.out.println("Name:");
                //getting name from user input
                template.name = inputScan.nextLine();
                System.out.println();


                System.out.println("Age:");
                //setting attribute
                template.correctDataType = false;
                //looping until valid data is entered
                while (template.correctDataType != true || template.age < 0) {
                    //resetting variable
                    template.correctDataType = true;
                    try {
                        //getting age from user input
                        template.age = inputScan.nextInt();
                        
                        //if input is < 0
                        if (template.age < 0) {
                            System.out.println("Error - Age cannot be less than 0");
                            System.out.println();
                        }
                    //catching exception
                    } catch (InputMismatchException m) {
                        //updating attribute
                        template.correctDataType = false;
                        System.out.println("Error - Please enter a whole number of 0 or above");
                        //reading & discarding user input
                        inputScan.next();
                    }
                }
                System.out.println();
                //consuming leftover '/n' value from .nextInt()
                inputScan.nextLine();

                System.out.println("Gender (f or m):");
                //setting attribute
                template.correctDataType = false;
                //looping while data is invalid
                while (template.correctDataType != true) {
                    //resetting attribute
                    template.correctDataType = true;

                    //getting gender from user input
                    template.gender = inputScan.nextLine();
                    //setting value to lower case
                    template.gender.toLowerCase();

                    //if gender starts with f
                    if (template.gender.startsWith("f")) {
                        //setting gender
                        template.gender = "Female";
                        System.out.println(template.gender);
                    //if gender starts with m
                    } else if (template.gender.startsWith("m")) {
                        //setting gender
                        template.gender = "Male";
                        System.out.println(template.gender);
                    //if gender does not start with f or m
                    } else {
                        //updating attribute
                        template.correctDataType = false;
                        System.out.println("Error - Please enter 'female' or 'male'");
                    }
                    System.out.println();
                }

                System.out.println("Height (cm):");
                //setting attribute
                template.correctDataType = false;
                //while data is invalid
                while (template.correctDataType != true || template.cmHeight <= 0) {
                    //resetting variable
                    template.correctDataType = true;
                    try {
                        //getting height from user input
                        template.cmHeight = inputScan.nextFloat();

                        //if input is <= 0
                        if (template.cmHeight <= 0) {
                            System.out.println("Error - Height cannot be 0 centimeters or less");
                        }
                    //catching exception
                    } catch (InputMismatchException m) {
                        //updating attribute
                        template.correctDataType = false;
                        System.out.println("Error - Please enter a decimal greater than 0 (e.g., 0.1, 120, 15.5)");
                        //reading & discarding user input
                        inputScan.next(); 
                    }
                    System.out.println();
                }
                //consuming leftover '\n' value from .nextFloat()
                inputScan.nextLine();

                System.out.println("Owner:");
                //getting owner from user input
                template.owner = inputScan.nextLine();
                System.out.println();

                //writing bonbon data in record file
                recordWriter.println(i+delim+template.name+delim+template.age+delim+template.gender+delim+template.cmHeight+delim+template.owner);
            }
            
            //closing recordWriter & inputScan
            recordWriter.close();
            inputScan.close();

            //looping while there are more lines
            while (fileScan.hasNextLine()) {
                //setting attributes
                String line = fileScan.nextLine();
                tokenizer = new StringTokenizer(line);
                int column = 1;
                String BonBonID = "empty";
                String BBName = "empty";
                int BBAge = 0;
                String BBGender = "empty";
                float BBHeight = 0;
                String BBOwner = "empty";

                //looping while there are more tokens
                while (tokenizer.hasMoreTokens()) {
                    //getting token
                    String token = tokenizer.nextToken(delim);

                    //setting value based on column number
                    switch (column) {
                        case 1:
                            BonBonID = token;
                            break;
                        case 2:
                            BBName = token;
                            break;
                        case 3:
                            BBAge = Integer.parseInt(token);
                            break;
                        case 4:
                            BBGender = token;
                            break;
                        case 5:
                            BBHeight = Float.parseFloat(token);
                            break;
                        case 6:
                            BBOwner = token;
                            break;
                    }
                    //adding 1 to column
                    column++;
                }

                //adding age & height to respective totals
                totalAge += BBAge;
                totalHeight += BBHeight;

                //printing BonBon info
                System.out.println();
                System.out.println("BonBon Daycare Record\n-----------------------------");
                System.out.println("Name: "+BBName
                                +"\n"+"ID: "+BonBonID
                                +"\n"+"Age: "+BBAge
                                +"\n"+"Gender: "+BBGender
                                +"\n"+"Height (cm): "+BBHeight
                                +"\n"+"Owner: "+BBOwner);
                System.out.println("-----------------------------");
            }
            
            //getting average age & height
            avgAge = totalAge / 5;
            avgHeight = totalHeight / 5;

            //printing average age & height
            System.out.println("-----------------------------");
            System.out.println("Average Age: "+avgAge
                            +"\n"+"Average Height: "+avgHeight);
            System.out.println("-----------------------------");
            
            //closing fileScan
            fileScan.close();
        //catching exceptions
        } catch (FileNotFoundException f) {
            System.out.println("Error! File was not found. Please restart the program.");
        } catch (IOException io) {
            System.out.println("Error! Please restart the program.");
        }
    }
}