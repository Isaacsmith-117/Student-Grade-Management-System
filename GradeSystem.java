
import java.util.Scanner;
public class GradeSystem {
    public static void clearScreen() {
        System.out.print("\033[2J"); // clear screen
        System.out.print("\033[H");  // move cursor về (1,1)
        System.out.flush();
    }

    //1. Add Student and grade(method)
    public static void updateArrays(double[] grades, String[] names, int count, Scanner input) {

        // Make sure there is room in the arrays
        if (count >= names.length || count >= grades.length) {
            System.out.println("The student list is full. Cannot add another student.");
            return;
        }
        // declare and initialize empty string
        String studentName = "";
        //ask for name input and validate it
        while (studentName.isEmpty()) {
            System.out.print("Enter student name: ");
            studentName = input.nextLine().trim();
            if (studentName.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }

        }

        //validate grade input
        double studentGrade = -1;

        while (studentGrade < 0 || studentGrade > 100) {
            System.out.print("Enter student grade: ");

            if (input.hasNextDouble()) {    //checks if the input is a double
                studentGrade = input.nextDouble();

                if (studentGrade < 0 || studentGrade > 100) {     //checks if double is between 0 and 100
                    System.out.println("Grade must be between 0 and 100.");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");  //handles any other bad input
                input.nextLine(); // clear the bad input
            }
        }

        input.nextLine();  //consume leftover newline so nextLine() works properly
        //store input into name and grade arrays

        names[count] = studentName;
        grades[count] = studentGrade;
    }

    //isaac
//method for option #2 in the menu: display all students and grades
    public static void printArrays(double[] grades, String[] names, int count) {
        for (int i=0;i<count;i++) {    //loops through arrays and stops at the amount of elements added
            System.out.println("Name: " + names[i] + "\nGrade: " + grades[i]);   //prints the name and subsequent grade at each position
            System.out.println();   //space for cleaner formatting
        }
    }

    public static double AverageCalculate (String[] Name, double [] Scores) {
        double sum = 0;
        int amount = 0;
        // assign Sum and the number of students
        for (int i = 0; i < 50; i++) {
            if (Name[i] != null) {
                // Only take the Score if when they have clearly name
                sum = sum + Scores[i];
                amount++;
            }
        }
        if (amount != 0) {
            // Avoid dividing by 0
            return (sum / amount);
        }
        else {
            return 0;
        }
    }

    // henry
    public static void findHighestAndLowest(String[] names, double[] grades, int count) {
        if (count == 0) {
            System.out.println("No student data available.");
            return;
        }

        double max = grades[0];
        double min = grades[0];
        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 1; i < count; i++) {
            if (grades[i] > max) {
                max = grades[i];
                maxIndex = i;
            }
            if (grades[i] < min) {
                min = grades[i];
                minIndex = i;
            }
        }

        System.out.println("Highest Grade: " + names[maxIndex] + " - " + max);
        System.out.println("Lowest Grade: " + names[minIndex] + " - " + min);
    }

    //kani
    public static void findStudentGrade(Scanner input, String[] names, double[] grades, int count){

        boolean found = false; // Keeps track if student is found

        // Keep looping until a valid student is found
        while(!found){
            System.out.print("Enter student name: ");
            String name = input.nextLine(); // get user input

            for(int i = 0; i < count; i++){  // Loop through all students
                if(names[i].equals(name)){  // check if names match
                    System.out.print(names[i] + "'s " + " Grade: " + grades[i]);  // print student grade
                    found = true; // student found, stop loop
                    break;
                }
            }
            if(!found){ // If no match was found, ask again
                System.out.println("Student not found. Try again");
            }
        }
    }

    public static void ShowMenu() {
        clearScreen();
        System.out.println("1. Add student and grade.");
        System.out.println("2. Display all student and grade.");
        System.out.println("3. Calculate class average.");
        System.out.println("4. Find a student.");
        System.out.println("5. Find the highest and lowest grade.");
        System.out.println("6. Exit.");
        System.out.print("Your option: ");
    }
    public static void main(String[] args) {
        ShowMenu();
        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();
        sc.nextLine(); // Fix the skipped-input

        double [] Grades = new double[50];
        String [] Names = new String[50];

        int index = 0; // Current index (count of students)


        while (true) {
            while (opt > 6 || opt < 1) {
                System.out.print("Option must be 1-6! \nYour option: ");
                opt = sc.nextInt();
                //Remove Skipped-input
            }
            switch (opt) {
                case 1:
                    clearScreen();
                    updateArrays(Grades, Names, index, sc);
                    index++;
                    break;
                case 2:
                    clearScreen();
                    printArrays(Grades, Names, index);
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Average grade: " + AverageCalculate(Names, Grades));
                    break;
                case 4:
                    clearScreen();
                    findStudentGrade(sc, Names, Grades, index);
                    break;
                case 5:
                    clearScreen();
                    findHighestAndLowest(Names, Grades, index);
                    break;
                case 6:
                    return;
            }
            ShowMenu();
            opt = sc.nextInt();
            sc.nextLine(); //Remove Skipped-input
        }

    }
}
