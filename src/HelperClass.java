/**
 *A helper class for IBAN validation.
 *
 * @author Saulius Jackunas 2018-10-17
 */

public final class HelperClass {

    // Creating constants to be used for IBAN validation
    public final static int IBAN_NUMBER_LENGTH_LT = 20;
    public final static int IBAN_MOD = 97;

    // Creating a method for IBAN validation to be accessed in package only
    protected static boolean validateIban(String accNumber) {
        // Removing all spaces from the given IBAN number
        String newAccNumber = accNumber.trim().replaceAll("\\s+","");

        // Checking if the given IBAN has the length necessary
        if (newAccNumber.length() != IBAN_NUMBER_LENGTH_LT) return false;

        // Moving first 4 characters to the end of the given IBAN number
        newAccNumber = newAccNumber.substring(4) + newAccNumber.substring(0, 4);

        // Looping through the IBAN number and changing it to numerical
        // by assigning a numerical value to each letter found
        StringBuilder numericAccNumber = new StringBuilder();
        int numericValue = 0;
        for (int i = 0; i < newAccNumber.length(); i++) {
            numericValue = Character.getNumericValue(newAccNumber.charAt(i));

            if (numericValue <= -1) return false;
            else numericAccNumber.append(numericValue);
        }

        // Checking the final condition: If the IBAN is valid, the remainder equals 1 - returns true.
        return numericValue % IBAN_MOD == 1;
    }
}
