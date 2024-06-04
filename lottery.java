import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lottery {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Lottery Game!");
        System.out.println("Would you like to buy a ticket? (yes/no)");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("yes")) {
            playLottery();
        } else if (input.equals("no")) {
            System.out.println("Maybe next time. Goodbye!");
        } else {
            System.out.println("Invalid input. Please try again.");
        }

        scanner.close();
    }

    public static void playLottery() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int[] winningNumbers = generateWinningNumbers();

        System.out.println("You have bought a lottery ticket!");
        System.out.println("Please enter your 6 lottery numbers (1-49), separated by spaces:");

        int[] userNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            while (true) {
                try {
                    userNumbers[i] = scanner.nextInt();
                    if (userNumbers[i] < 1 || userNumbers[i] > 49) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 49.");
                    scanner.next();
                }
            }
        }

        Arrays.sort(winningNumbers);
        Arrays.sort(userNumbers);

        System.out.println("Lottery numbers: " + Arrays.toString(winningNumbers));
        System.out.println("Your numbers: " + Arrays.toString(userNumbers));

        int matches = countMatches(winningNumbers, userNumbers);
        if (matches == 6) {
            System.out.println("Congratulations! You have won the jackpot!");
        } else if (matches > 0) {
            System.out.println("Congratulations! You have matched " + matches + " number(s)!");
        } else {
            System.out.println("Sorry, you didn't win this time. Better luck next time!");
        }

        scanner.close();
    }

    public static int[] generateWinningNumbers() {
        Random random = new Random();
        int[] winningNumbers = new int[6];
        for (int i = 0; i < 6; i++) {
            winningNumbers[i] = random.nextInt(49) + 1; // Generate a number between 1 and 49
        }
        return winningNumbers;
    }

    public static int countMatches(int[] winningNumbers, int[] userNumbers) {
        int count = 0;
        for (int winningNumber : winningNumbers) {
            for (int userNumber : userNumbers) {
                if (winningNumber == userNumber) {
                    count++;
                }
            }
        }
        return count;
    }
