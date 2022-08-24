import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * java Mentor
 * Test tasks
 * Simple calculator
 */
public class Main {
    public static void main(String[] args) {
        String data = new Scanner(System.in).nextLine().replace(" ", "").trim();
        calc(data);
    }

    /**
     * ENtry point
     * @param data
     */
    public static void calc(String data) {
        try {
            dataValidation(data);
        } catch (RuntimeException e) {
            System.out.println("Неверный формат данных");
        }

        String operationSign = getSign(data);

        String firstNumber = data.substring(0, data.indexOf(operationSign)).toUpperCase();
        String secondNumber = data.substring(data.indexOf(operationSign) + 1).toUpperCase();

        String numbersTypeResult = "";

        try {
            numbersTypeResult = checkDataType(firstNumber, secondNumber);
        } catch (RuntimeException e) {
            System.out.println("Неверный формат данных");
        }

        dataValidation(firstNumber, secondNumber, numbersTypeResult);
    }

    private static void dataValidation(String number1, String number2, String numbersType) {
        if (numbersType.equals("arabic")) {
            System.out.println("туц туц");
        } else if (numbersType.equals("romanian")) {
            System.out.println("пам пам");
        }
    }

    private static String checkDataType(String number1, String number2) {
        String result = "";

        List<ArrayList> romanNumbers = new ArrayList(Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"));
        List<ArrayList> arabicNumbers = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));

        if (romanNumbers.contains(number1) && romanNumbers.contains(number2)) {
            result = "romanian";
        } else if (arabicNumbers.contains(number1) && arabicNumbers.contains(number2)) {
            result = "arabic";
        } else if ( (romanNumbers.contains(number1) && arabicNumbers.contains(number2)) || (romanNumbers.contains(number2) && arabicNumbers.contains(number1))){
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    private static String getSign(String data) {
        String result = "";

        String inputData = data;

        String[] values = {"-", "+", "/", "*"};

        for (String value : values) {
            if (inputData.contains(value)) {
                result = value;
                break;
            }
        }
        return result;
    }

    private static void dataValidation(String data) {
        String result = "";

        String inputData = data;
        String[] values = {"-", "+", "/", "*"};

        for (int i = 0; i < values.length; i++) {
            if (inputData.contains(values[i])) {
                result = values[i];
            }
        }

        if (result.length() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        char[] dataChars = inputData.toCharArray();

        for (String value : values) {
            int counter = 0;

            for (char dataChar : dataChars) {
                if (value.equals(String.valueOf(dataChar))) {
                    counter += 1;
                }

                if (counter > 1) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        for (String value : values) {
            for (int i = 0; i < dataChars.length; i++) {
                if ((String.valueOf(dataChars[0]).equals(value)) || (String.valueOf(dataChars[dataChars.length-1]).equals(value))) {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

    }
}
