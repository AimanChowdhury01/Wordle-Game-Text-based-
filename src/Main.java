import java.util.Scanner;
import java.util.regex.Pattern;//(credit: stackoverflow robby pond)
import java.util.regex.Matcher;
import java.util.Arrays;
import java.math.MathContext;
// done importing all necessary libraries
//main starts
public class Main {
    public static void main(String[] args){
        String secretWord = "RESUME";//default
        System.out.println("Welcome to Wordle!");
        int Max_Guess = 6;
        int Guess_no = 0;
        int corr_position = 0;
        int corr_letter = 0;
        //variable created//for cmd line is help
        if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
            System.out.println("To set the secret word, provide it as a command-line argument, or else default secret word is used");
            System.exit(0);
        }

        // If a command line argument is passed, use it as the secret word
        if (args.length == 1) {
            String arg = args[0].toLowerCase(); // Parse to omit case-sensitivity
            if (!ValidSecretWord(arg)) {
                System.out.println("Invalid secret word. It must contain 6 alphabetic characters.");
                System.exit(1); // Exit the program if the secret word is not valid
            }
            secretWord = arg.toUpperCase(); // Convert to uppercase to avoid conflict
        }
        // receive and deal with input
        Scanner scanner = new Scanner(System.in);
        while (Guess_no < Max_Guess) {
            System.out.println("Type a six-letter word or 'exit' to quit:");
            String guess = scanner.nextLine().toUpperCase(); //to avoid conflict in case sensitivity
            // if user exits
            if (guess.equals("EXIT")) {//case sensitivity to uppercase for input
                System.out.println("Game Exited");
                break; //breaks entire loop
            }
            //input control
            if (!ValidGuess(guess)) {
                System.out.println("Invalid Guess, Only Six-letter alphabetic characters are permitted");
                continue;
            } //restarts the loop
            //new variables created
            boolean[] guessMatch = new boolean[guess.length()];
            boolean[] secretWordMatch = new boolean[secretWord.length()];
            System.out.println("Correct letters in correct positions are: ");
            //iterate through string for correct letters in position
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess.charAt(i)) {
                    //individually states which letter where
                    System.out.println(guess.charAt(i) + " in position " + (i + 1));
                    corr_position++;
                    secretWordMatch[i] = true;
                    guessMatch[i] = true;
                }
            }
            //check if game already won
            if(corr_position == secretWord.length()){
                System.out.println("Congrats! You have guess the word correctly, game won.");
                break; //exit main
            }
            System.out.println("Correct letters in incorect positions: ");
            //iterate through string for correct letters in incorrect pos
            for (int i = 0; i < secretWord.length(); i++) {
                if (!secretWordMatch[i]) {
                    for (int c = 0; c < guess.length(); c++) {
                        if (!guessMatch[c] && secretWord.charAt(i) == guess.charAt(c)) {
                            System.out.println(guess.charAt(c) + " at position " + (c + 1));
                            corr_letter++;
                            secretWordMatch[i] = true;
                            guessMatch[c] = true;
                            break; //breaks this loop to check again
                        }
                    }

                }
            }
            corr_letter = 0;
            corr_position =0;
            //results as output keeps on incrementing with each guess
            System.out.println("Number of Guesses left: " +(Max_Guess - Guess_no - 1));
            Guess_no++;}//block user to guess more after 6 guess
        //game end without winning
        if(Guess_no == Max_Guess){
            System.out.println("Game over, You have used all your guesses. The Secret word was: " +secretWord);
            }
        }

     //method to check if guess is valid, inspired from stack overflow
    //private function used since intellij shows conflict otherwise
    private static boolean ValidGuess(String guess) {
        return guess.matches("[a-zA-Z]{6}");
}

// Method to check if the secret word has 6 alphabetic characters
    private static boolean ValidSecretWord(String secretWord) {
        return secretWord.matches("[a-zA-Z]{6}");}


}

// reference: https://stackoverflow.com/questions/5238491/check-if-string-contains-only-letters/5238545#5238545
// finalversion 09 feb 9:47pm
