import java.util.Scanner;

/**
 * Interaktyvus IBAN numerių tikrinimas.
 * Vartotojo paprašoma įvesti sąskaitos numerį ir programa išveda ar numeris yra teisingas.
 *
 * @author Saulius Jackunas 2018-10-17
 */

public class ManualValidation {

    public static void main(String[] args) {

        // Initializing a string to be used for user input
        String iban = null;

        // Creating an AutoCloseable Scanner for user input
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Please enter your IBAN No. to check validity: ");
            iban = scanner.nextLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Defining output after entered IBAN has been validated
        if (HelperClass.validateIban(iban)) System.out.println("Your IBAN " + iban + " is valid.");
        else System.out.println("Your IBAN " + iban + " is invalid.");
    }
}
