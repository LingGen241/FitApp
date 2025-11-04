import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.lang.System;

public class BMR_final {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to FitApp");

        System.out.print("May we know your name? ");
        String name = scanner.nextLine();
        System.out.println();

        System.out.println("Hi" + " " + name);
        System.out.println("Welcome" + " " + name.toUpperCase() + " " + "to our team");

        intro();

        System.out.println("Could we know your age, wegith, height,and gender? ");

        int age;
        double weight;
        double height;
        String gender;
        String check = scanner.next();

        if (check.equalsIgnoreCase("No")) {
            System.out.println("Sorry, to see you go.");
        } else {
            try {
                System.out.println();
                System.out.println("Great");
                System.out.println();
                System.out.println("What is your age? ");
                age = scanner.nextInt();
                System.out.println("What is your weight? kg");
                weight = scanner.nextDouble();
                System.out.println("What is your height? cm");
                height = scanner.nextDouble();

                while (age <= 0 || weight <= 0 || height <= 0) {
                    if (age <= 0) {
                        System.out.println("Invalid age. Please try again.");
                        age = scanner.nextInt();
                    }
                    if (weight <= 0) {
                        System.out.println("Invalid weight. Please try again.");
                        weight = scanner.nextDouble();
                    }
                    if (height <= 0) {
                        System.out.println("Invalid height. Please try again.");
                        height = scanner.nextDouble();
                    }
                }

                System.out.println("What is your gender? ");
                gender = scanner.next();

                if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F") && !gender.equalsIgnoreCase("male")
                        && !gender.equalsIgnoreCase("female")) {
                    throw new Exception("Invalid gender: " + gender + ". Gender must be M/F or Male/Female");
                }

                System.out.println();
                defaultDelay();
                System.out.println("Your age is: " + " " + age);
                defaultDelay();
                System.out.println("Your weight is: " + " " + weight);
                defaultDelay();
                System.out.println("Your height is: " + " " + height);
                defaultDelay();
                System.out.println("Your gender is: " + " " + gender.toUpperCase());
                defaultDelay();

                System.out.println();
                defaultDelay();
                bmi(weight, height, gender);
                defaultDelay();
                System.out.println("What is your excercise level? 1 - 5");
                double totalBmr;
                int userInput = scanner.nextInt();

                if (userInput == 1) {
                    totalBmr = calories(weight, age, height, gender) * 1.2;
                } else if (userInput == 2) {
                    totalBmr = calories(weight, age, height, gender) * 1.375;
                } else if (userInput == 3) {
                    totalBmr = calories(weight, age, height, gender) * 1.55;
                } else if (userInput == 4) {
                    totalBmr = calories(weight, age, height, gender) * 1.725;
                } else {
                    totalBmr = calories(weight, age, height, gender) * 1.9;
                }

                double carb = (totalBmr * 0.5) / 4;
                double protein = (totalBmr * 0.35) / 4;
                double fat = (totalBmr * 0.2) / 9;

                System.out.println(
                        "What is your goals? 1. To Lose Weight | 2. To Maintaince Weight | 3.To Gain Muscles and Lose Body Fat");
                int userDecision = scanner.nextInt();

                while (true) {
                    if (userDecision > 0 && userDecision < 4) {
                        break;
                    }
                    System.out.println("Invalid input.");
                    userDecision = scanner.nextInt();
                }
                if (userDecision == 1) {
                    defaultDelay();
                    System.out.printf("Your BMR is %.0f: \n", totalBmr - 400);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of carbohydrates per day.\n", carb);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of protein per day.\n", protein);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of fat per day.\n", fat);
                    defaultDelay();
                } else if (userDecision == 2) {
                    defaultDelay();
                    System.out.printf("Your BMR is %.0f: \n", totalBmr);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of carbohydrates per day.\n", carb);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of protein per day.\n", protein);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of fat per day.\n", fat);
                    defaultDelay();
                } else if (userDecision == 3) {
                    defaultDelay();
                    System.out.printf("Your BMR is %.0f: \n", totalBmr - 350);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of carbohydrates per day.\n", carb);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of protein per day.\n", protein + 25);
                    defaultDelay();
                    System.out.printf("You need %.0f grams of fat per day.\n", fat);
                    defaultDelay();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numeric values for age and weight.");
            }
        }
    }

    public static void bmi(double weitgt, double height, String gender) {
        System.out.println("Base on your gender, age, and heigth.");
        double bmindex = (weitgt / Math.pow(height / 100, 2));
        String category;

        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("male")) {
            if (bmindex < 20) {
                category = "Underweight";
            } else if (bmindex >= 20 && bmindex < 25) {
                category = "Normal weight";
            } else if (bmindex >= 25 && bmindex < 30) {
                category = "Overweight";
            } else {
                category = "Obesity";
            }
        } else {
            if (bmindex < 19) {
                category = "Underweight";
            } else if (bmindex >= 19 && bmindex < 24) {
                category = "Normal weight";

            } else if (bmindex >= 24 && bmindex < 29) {
                category = "Overweight";
            } else {
                category = "Obesity";
            }
        }
        defaultDelay();
        System.out.printf("Your BMI will be %.1f and your category is: %s\n", bmindex, category);
    }

    public static void intro() {
        System.out.println("This app is builded by Seth and Huy ");
        defaultDelay();
        System.out.println("The goal is to help people on their journey to meet their calories goals.");
        defaultDelay();
        System.out.println();
    }

    public static double calories(double weight, int age, double heigth, String gender) {
        if (gender.equalsIgnoreCase("M") || (gender.equalsIgnoreCase("male"))) {
            return (weight * 10) + (6.25 * heigth) - 5 * age + 5;
        } else {
            return (weight * 10) + (6.25 * heigth) - 5 * age - 161;
        }
    }

    public static void startTimer(int seconds) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
            }
        }, seconds * 1000);
    }

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void defaultDelay() {
        delay(200);
    }
}