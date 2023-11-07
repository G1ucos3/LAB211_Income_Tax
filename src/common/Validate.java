package common;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Validate {

    public String checkString(String s) {
        String str = "";
        while (str.isBlank() || str.isEmpty()) {
            Scanner sc = new Scanner(System.in);
            System.out.print(s + ": ");
            str = sc.nextLine();
        }
        return str;
    }

    public int checkLimit(String s, int min, int max) {
        int num = -1;
        while (num < min || num > max) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(s + ": ");
                num = sc.nextInt();
                if (num < min || num > max) {
                    throw new Exception();
                }
            } catch (InputMismatchException e) {
                System.err.println("Please enter a number.");
            } catch (Exception e) {
                System.err.println("Please input number between(" + min + " - " + max + ").");
            }
        }
        return num;
    }

    public double checkIncome(String s) {
        double income = 0;
        while (income <= 0) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(s + ": ");
                income = sc.nextDouble();
                if (income < 0) {
                    throw new Exception();
                }
            } catch (InputMismatchException e) {
                System.err.println("Please enter a number.");
            } catch (Exception e) {
                System.err.println("Please input a number bigger than 0.");
            }
        }
        return income;
    }
    
    public boolean inputYN(String s) {
        while (true) {
            String str = checkString(s);
            if (str.equalsIgnoreCase("Y")) {
                return true;
            } else if (str.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please enter Y or N");
        }
    }
}
