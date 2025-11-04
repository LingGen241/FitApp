import java.util.Scanner;

public class BMR_final {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to FitApp");

        System.out.print("May we know your name? ");
        String name = scanner.nextLine();
        System.out.println();

        System.out.println("Hi " + name);
        System.out.println("Welcome " + name.toUpperCase() + " to our team");
        intro();

        System.out.println("Could we know your age, weight, height, and gender? (Yes/No)");
        String check = scanner.next();

        if (check.equalsIgnoreCase("No")) {
            System.out.println("Sorry to see you go.");
            return;
        }

        int age = 0;
        double weight = 0;
        double height = 0;
        String gender = "";

        // Loop until valid numeric inputs are entered
        while (true) {
            try {
                System.out.println();
                System.out.print("What is your age? ");
                age = Integer.parseInt(scanner.next());
                System.out.print("What is your weight (kg)? ");
                weight = Double.parseDouble(scanner.next());
                System.out.print("What is your height (cm)? ");
                height = Double.parseDouble(scanner.next());

                if (age <= 0 || weight <= 0 || height <= 0) {
                    System.out.println("Values must be positive numbers. Please try again.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values for age, weight, and height.\n");
                scanner.nextLine(); // clear invalid input
            }
        }

        // Get gender with validation
        while (true) {
            System.out.print("What is your gender (M/F)? ");
            gender = scanner.next();
            if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")
                    || gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                break;
            } else {
                System.out.println("Invalid gender. Please enter M/F or Male/Female.\n");
            }
        }

        // Display inputs
        System.out.println();
        delay();
        System.out.println("Your age is: " + age);
        delay();
        System.out.println("Your weight is: " + weight + " kg");
        delay();
        System.out.println("Your height is: " + height + " cm");
        delay();
        System.out.println("Your gender is: " + gender.toUpperCase());
        delay();
        System.out.println();

        // BMI section
        bmi(weight, height, gender);
        delay();

        // BMR and activity level
        double baseBmr = calories(weight, age, height, gender);
        double totalBmr = 0;

        System.out.println("What is your exercise level? (1-5)");
        System.out.println("1. Little or no exercise");
        System.out.println("2. Light exercise (1–3 days/week)");
        System.out.println("3. Moderate exercise (3–5 days/week)");
        System.out.println("4. Hard exercise (6–7 days/week)");
        System.out.println("5. Very hard exercise (twice per day or physical job)");
        System.out.print("Enter your choice: ");

        int activityLevel = 0;
        while (true) {
            try {
                activityLevel = Integer.parseInt(scanner.next());
                if (activityLevel < 1 || activityLevel > 5) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();
            }
        }

        if (activityLevel == 1)
            totalBmr = baseBmr * 1.2;
        else if (activityLevel == 2)
            totalBmr = baseBmr * 1.375;
        else if (activityLevel == 3)
            totalBmr = baseBmr * 1.55;
        else if (activityLevel == 4)
            totalBmr = baseBmr * 1.725;
        else
            totalBmr = baseBmr * 1.9;

        delay();
        System.out.printf("%nYour BMR (resting metabolism) is: %.0f kcal%n", baseBmr);
        delay();
        System.out.printf("Your TDEE (daily calorie need) is: %.0f kcal%n", totalBmr);
        delay();

        // Goal selection
        System.out.println();
        System.out.println("What is your goal?");
        System.out.println("1. Lose Weight");
        System.out.println("2. Maintain Weight");
        System.out.println("3. Gain Muscle and Lose Body Fat");
        System.out.print("Enter your choice (1–3): ");

        int goal = 0;
        while (true) {
            try {
                goal = Integer.parseInt(scanner.next());
                if (goal < 1 || goal > 3) {
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values (1–3).");
                scanner.nextLine();
            }
        }

        // Macro calculation
        double caloriesTarget;
        if (goal == 1) {
            caloriesTarget = totalBmr - 500;
        } else if (goal == 2) {
            caloriesTarget = totalBmr;
        } else {
            caloriesTarget = totalBmr + 100;
        }

        if (caloriesTarget < 0)
            caloriesTarget = 0;

        double pctCarb = 0.50;
        double pctProtein = 0.30;
        double pctFat = 0.20;

        double carbGrams = (caloriesTarget * pctCarb) / 4.0;
        double proteinGrams = (caloriesTarget * pctProtein) / 4.0;
        double fatGrams = (caloriesTarget * pctFat) / 9.0;

        if (goal == 3) { // extra protein for muscle gain
            proteinGrams += 75;
        }

        // Output results
        System.out.println();
        delay();
        System.out.printf("Your target daily calories: %.0f kcal%n", caloriesTarget);
        delay();
        System.out.printf("Carbohydrates: %.0f grams per day%n", carbGrams);
        delay();
        System.out.printf("Protein: %.0f grams per day%n", proteinGrams);
        delay();
        System.out.printf("Fat: %.0f grams per day%n", fatGrams);
        delay();

        System.out.println("\nThank you for using FitApp! Keep pushing toward your goals.");
    }

    public static void bmi(double weight, double height, String gender) {
        System.out.println("Based on your gender, age, and height...");
        double bmiValue = weight / Math.pow(height / 100, 2);
        String category;

        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            if (bmiValue < 19)
                category = "Underweight";
            else if (bmiValue < 24)
                category = "Normal weight";
            else if (bmiValue < 29)
                category = "Overweight";
            else
                category = "Obesity";
        } else {
            if (bmiValue < 20)
                category = "Underweight";
            else if (bmiValue < 25)
                category = "Normal weight";
            else if (bmiValue < 30)
                category = "Overweight";
            else
                category = "Obesity";
        }

        delay();
        System.out.printf("Your BMI is %.1f, which is categorized as %s.%n", bmiValue, category);
    }

    public static void intro() {
        System.out.println("This app is built by Huy.");
        delay();
        System.out.println("The goal is to help people on their journey to meet their calorie goals.");
        delay();
        System.out.println();
    }

    public static double calories(double weight, int age, double height, String gender) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")) {
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
    }

    public static void delay() {
        try {
            Thread.sleep(300); // wait for 0.3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}