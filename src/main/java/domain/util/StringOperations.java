package domain.util;

import java.math.BigInteger;

public class StringOperations {

    public static void main(String[] args) {
        String test = "Hi, this is me! Old Roman cursive script, also called majuscule cursive and capitalis cursive," +
                " was the everyday form of handwriting used for writing letters," +
                " by merchants writing business accounts, by schoolchildren learning the Latin alphabet, " +
                "and even emperors issuing commands. " +
                "A more formal style of writing was based on Roman square capitals, " +
                "but cursive was used for quicker, information.";
        BigInteger converted = stringToBigintCustom(test);
        System.out.println(converted);
        System.out.println(bigintToStringCustom(converted));
    }

    public static BigInteger stringToBigintCustom(String message){

        int[] codePoints = message.codePoints().toArray();

        StringBuilder numericalRepresentation = new StringBuilder();

        for (int codePoint : codePoints) {
            String paddedCodePoint = String.format("%05d", codePoint); // Pad the code point with leading zeros to ensure a fixed length of 5 digits
            System.out.println(paddedCodePoint);
            numericalRepresentation.append(paddedCodePoint);
        }

        BigInteger plainTextNumber = new BigInteger(numericalRepresentation.toString());

        System.out.println("Text: " + message);
        System.out.println("Numerical representation: " + plainTextNumber);

        return plainTextNumber;
    }

    public static String bigintToStringCustom(BigInteger number){
        StringBuilder numericalRepresentation = new StringBuilder(number.toString());
        int start = 5 - numericalRepresentation.length() % 5;
        int zeros_lost = start % 5;

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < zeros_lost; i++) {
            numericalRepresentation.insert(0, 0);
        }

        int i = 0;
        while (i < numericalRepresentation.length()) {
            int endIndex = Math.min(i + 5, numericalRepresentation.length());
            String codePointStr = numericalRepresentation.substring(i, endIndex);
            int codePoint = Integer.parseInt(codePointStr);
            message.appendCodePoint(codePoint);
            i += 5;
        }

        return message.toString();
    }
}
