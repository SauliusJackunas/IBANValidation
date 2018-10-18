import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * IBAN numerių iš tekstinio failo tikrinimas.
 * Vartotojo paprašoma įvesti failo kelią ir pavadinimą. Programa nuskaito failą ir sutikrina sąskaitos numerius.
 * Rezultatus išveda į tokio pat pavadinimo failą su plėtiniu .out.
 *
 *         Pradinio failo struktūra: kiekviena eilutė - vienas sąskaitos numeris IBAN. Pvz:
 *             AA051245445454552117989
 *             LT647044001231465456
 *             LT517044077788877777
 *             LT227044077788877777
 *             CC051245445454552117989
 *
 *         Rezultato failo struktūra: IBAN;valid. Pvz:
 *             AA051245445454552117989;false
 *             LT647044001231465456;true
 *             LT517044077788877777;true
 *             LT227044077788877777;false
 *             CC051245445454552117989;false
 *
 * @author Saulius Jackunas 2018-10-17
 */

public class ValidationFromFile {

    // Creating a scanner for user input
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Please enter a path and a file name you would like to check (i.e.: d:\\documents\\my_file.txt):\r");

        // Getting file name and file path from user input and preparing the output file
        String fullPathToFile = scanner.nextLine();
        String outputFile = fullPathToFile.concat(".out");

        // Setting a path to a file
        Path inFile = Paths.get(fullPathToFile);

        // Checking if the file exists
        if (!Files.exists(inFile)) throw new FileNotFoundException(inFile.toString());

        // Creating new AutoCloseable scanner to read from file and new AutoCloseable writer to write to a new file
        try (Scanner scanner = new Scanner(inFile, "UTF-8");
        PrintWriter printWriter = new PrintWriter(outputFile, "UTF-8")) {

            String line;

            // Looping through each line in the source file and filling the new file with corresponding values
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                printWriter.print(line + ";" + HelperClass.validateIban(line) + "\n");
            }
        }
        System.out.println("IBAN checking from file finished.\nPlease check the output here: " + outputFile);
    }
}
