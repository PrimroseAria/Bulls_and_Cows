package bullscows;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;


public class Main {
    Scanner scanner = new Scanner(System.in);

    public static int size() {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String sizeText = "";
        try {
            while (true) {
                sizeText = scanner1.nextLine();
                int size1 = Integer.valueOf(sizeText);



                if (size1 < 1) {
                    System.out.printf("Error: can't generate a secret number with a length of %d because there arent enough unique digits", size1);
                    System.exit(0);
                } else {
                    return size1;

                }
            }
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", sizeText);
            System.exit(0);
            return 1;
        }


    }

    public static void grader(String sol) {
        Scanner answers = new Scanner(System.in);

        int turn = 0;


        String[] solution1 = sol.split("");
        //String[] answer1 = answer.split("");
        while (true) {
            int bulls = 0;
            int cows = 0;

            turn++;
            System.out.printf("Turn %d:\n", turn);

            String answer = answers.nextLine();
            String[] answer1 = answer.split("");

            for (int i = 0; i < answer.length(); i++) {
                String currentNum = answer1[i];
                for (int j = 0; j < sol.length(); j++) {
                    if (currentNum.equals(solution1[j])) {
                        if (currentNum.equals(solution1[i])) {
                            bulls++;
                        } else {
                            cows++;
                        }
                    }
                }
            }
            if (cows == 0 && bulls == 0) {
                System.out.printf("Grade: None.\n", sol);
            } else if (cows > 0 && bulls > 0) {
                System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows, sol);
            } else if (cows > 0 && bulls == 0) {
                System.out.printf("Grade: %d cow(s).\n", cows, sol);
            } else {
                if (bulls == solution1.length) {
                    System.out.printf("Grade: %d bulls", bulls);
                    System.out.println("Congratulations! You guessed the secret code.\n");
                    break;
                } else {
                    System.out.printf("Grade: %d bull(s).\n", bulls, sol);
                }
            }
        }

    }

    public static StringBuilder numberGenerator(int size) {

        Scanner scanner4 = new Scanner(System.in);
        int highestIntValue = 0;
        int highestCharValue = 0;
        String hiddenPassword = "";
        StringBuilder replacementChar = new StringBuilder();
        int codeSize = 0;
        String codeSizeText = "";

        Random random = new Random();

        StringBuilder randomNum = new StringBuilder();

        System.out.println("Input the number of possible symbols in the code:");
        try {
            codeSizeText = scanner4.nextLine();
            codeSize = Integer.valueOf(codeSizeText);


        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", codeSizeText);
            System.exit(0);
        }
        int letterSize = 97 + (codeSize - 10);

        if (codeSize < size ) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", size, codeSize);
            System.exit(0);
        } else if (codeSize > 36) {
            System.out.println("Error: code size is too big");
            System.exit(0);
        } else {

            for (int k = 0; k < size; k++) {
                int randomChoice = random.nextInt(9);
                if (codeSize <= 10) {
                    int mathRandom = random.nextInt(codeSize - 1);
                    randomNum.append(mathRandom);

                } else {
                    if (randomChoice > 5) {
                        int mathRandom = random.nextInt(9);
                        randomNum.append(mathRandom);

                    } else {
                        int ran = random.nextInt('a', letterSize);
                        char c = (char) ran;
                        randomNum.append(c);
                    }
                }
            }
            randomNum.reverse();
            String ranNum = randomNum.toString();

            int end = 200;
            outerLoop:
            while (end != 0) {
                for (int i = 0; i < ranNum.length(); i++) {

                    char currentCharacter = randomNum.charAt(i);

                    for (int j = 0; j < ranNum.length(); j++) {

                        if (i == j) {

                        } else if (currentCharacter == randomNum.charAt(j)) {


                            double randomChoice2 = Math.random() * 10;
                            if (codeSize <= 10) {
                                double mathRandom = Math.random() * 10;
                                int pseudoRandomNumber = (int) mathRandom;
                                replacementChar.append(pseudoRandomNumber);
                            } else {
                                if (randomChoice2 > 5) {
                                    double mathRandom = Math.random() * 10;
                                    int pseudoRandomNumber = (int) mathRandom;
                                    replacementChar.append(pseudoRandomNumber);
                                } else {
                                    double ran2 = Math.random() * (letterSize - 97) + 97;
                                    int ch = (int) ran2;
                                    char c = (char) ch;
                                    replacementChar.append(c);
                                }
                            }

                            //replacementChar.append(pseudoRandomNumber2);
                            replacementChar.reverse();
                            String replacement = String.valueOf(replacementChar.charAt(0));
                            randomNum.replace(i, i + 1, replacement);

                            i = 10;
                            j = 10;
                        }
                    }
                }
                end--;
            }
            for (int h = 0; h < randomNum.length(); h++) {
                char currentCharacter2 = randomNum.charAt(h);
                int characterIntValue = (int) currentCharacter2;

                for (int j = 0; j < randomNum.length(); j++) {
                    if (characterIntValue < 58) {
                        //System.out.println(characterIntValue);

                        highestIntValue = (Math.max(characterIntValue, highestIntValue));

                    } else {
                        //System.out.println(characterIntValue);

                        highestCharValue = (Math.max(characterIntValue, highestCharValue));

                        //(currentCharacter2 > randomNum.charAt(j))
                    }
                }
            }
            char highValueInt = (char) highestIntValue;
            char highValue = (char) ((codeSize - 11) + 97);

            for (int p = 0; p < randomNum.length(); p++) {
                hiddenPassword += "*";
            }

            //System.out.printf("range is 0 - %c\n", highValueInt);
            //System.out.printf("letter range is a - %c", highValue);

            randomNum.delete(size, randomNum.length());
            if (codeSize <= 10) {
                System.out.printf("The secret is prepared: %S (0 - %S).\n", hiddenPassword, codeSize - 1);
            } else {
                System.out.printf("The secret is prepared: %S (0 - 9, a - %S).\n", hiddenPassword, highValue);
            }
            System.out.println("Okay, let's start a game!");
            //System.out.println(randomNum);
            return randomNum;
        }
        return randomNum;
    }


    public static void main(String[] args) {

        String secretCode = numberGenerator(size()).toString();

        grader(secretCode);


    }
}
