import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.*;

public class Mcq_test {
    // variable declaraton
    public final static String splitBy = ",";
    public static String line = "";
    static String name;

    // another variable
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // input the user name
        inputName();
        // choose the topic from the csv file
        selectTheTopics();
    }

    // Create the function that's taking the username
    public static void inputName() {
        System.out.println("Please Input Your Name : ");
        Mcq_test.name = input.nextLine();
        if (isValidName(Mcq_test.name) == true) {
            System.out.println("welcome " + Mcq_test.name);
            System.out.println();
        } else {
            inputName();
        }
    }

    // create function that validate the name
    public static boolean isValidName(String name) {
        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{0,30}$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

    // create the menuFunction that will display the MCQ's topic
    public static void showMenu() {
        System.out.println("Please Select The Mcq's topic : ");
        System.out.println("=======================");
        System.out.println("[1] CSS");
        System.out.println("[2] Java");
        System.out.println("=======================");
        System.out.println("type 1 || 2 as the format");
        System.out.println("your Input is : ");
    }

    // Create the function that's printing the mcq's topics
    public static void selectTheTopics() {
        // variable declaration
        int chooseTopic;
        // check if the user choose the topic out of the number then repeat till correct
        do {
            // print the MCQ's topic
            showMenu();
            chooseTopic = input.nextInt();
            switch (chooseTopic) {
                case 1:
                    System.out.print(Mcq_test.name + " had been choosen HTML");
                    System.out.println();
                    setMcqTopic("src\\CSS.csv");
                    break;
                case 2:
                    System.out.print(Mcq_test.name + " had been choosen Javascript");
                    setMcqTopic("src\\Java.csv");
                    System.out.println();
                    break;
                default:
                    System.out.println("The Topic is not visible, please try again !");
                    break;
            }
        } while (chooseTopic != 1 && chooseTopic != 2); // if the input of user is not same with 1 or 2 it will repeat the
    }

    // create the function that set the MCQ's topic
    public static void setMcqTopic(String topic) {
        try {
            // variable declaration
            int userScore = 0;
            int getWrong = 0;
            String userSetAnswer;
            String userGetAnswer;
            String line = "";
            String splitBy = ";";

            BufferedReader in = new BufferedReader(new FileReader(topic));
            // declaring variable that can be used inside this scope
            while ((line = in.readLine()) != null) {
                String[] numOfQuest = line.split(splitBy);
                do {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(numOfQuest[i]);
                    }
                    System.out.print("Your Answer Is : ");
                    Scanner userAnswer = new Scanner(System.in);
                    userSetAnswer = userAnswer.nextLine();
                    System.out.println();
                    if (userSetAnswer.equalsIgnoreCase("a")) {
                        userGetAnswer = numOfQuest[1];
                    } else if (userSetAnswer.equalsIgnoreCase("b")) {
                        userGetAnswer = numOfQuest[2];
                    } else if (userSetAnswer.equalsIgnoreCase("c")) {
                        userGetAnswer = numOfQuest[3];
                    } else if (userSetAnswer.equalsIgnoreCase("d")) {
                        userGetAnswer = numOfQuest[4];
                    } else {
                        System.out.println("== >> Please type a, b, c or d << ==");
                        System.out.println();
                        userGetAnswer = null;
                    }
                } while ((userSetAnswer.equalsIgnoreCase("a") || userSetAnswer.equalsIgnoreCase("b")
                        || userSetAnswer.equalsIgnoreCase("c") || userSetAnswer.equalsIgnoreCase("d")) == false);

                // checking for the score
                if (userGetAnswer.equals(numOfQuest[5])) {
                    System.out.println("correct");
                    userScore++;
                } else if (userGetAnswer.equals(numOfQuest[5]) == false) {
                    System.out.println("wrong");
                    getWrong++;
                } else {
                    System.out.println("Please type a, b, c or d");
                }
            }
            System.out.println("Correct is : " + userScore);
            System.out.println("Wrong is : " + getWrong);
            System.out.println("Your score is : " + userScore * 10 + "%");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
