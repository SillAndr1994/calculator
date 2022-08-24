import java.util.Scanner;
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
        dataValidation(data);
        int firstNumber = findFirstNumber(data);
        String sign = findExpressionSign(data);
        int secondNumber = findSecondNumber(data, sign, firstNumber);

        if (secondNumber == 0) {
            System.out.println("Неверный формат данных");
            System.exit(0);
        }

        dataValidation(firstNumber, secondNumber);

        int result = selectOperation(firstNumber, secondNumber, sign);
        System.out.println(result);


    }


    /**
     * Select operation and calculate operation result
     * @param firstNumber
     * @param secondNumber
     * @param assign
     * @return
     */
    private static int selectOperation(int firstNumber, int secondNumber, String assign) {
        int result = 0;

        switch (assign) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = (int)(firstNumber / secondNumber);
                break;
        }

        return result;
    }

    /**
     * input validation
     * @param number1
     * @param number2
     */
    private static void dataValidation(int number1, int number2) {

        int firstNumber = number1;
        int secondNumber = number2;

        if ((firstNumber < 1 || secondNumber > 10) || (secondNumber < 1 || firstNumber > 10)){
            System.out.println("ошибка, выход за пределы одного из значений");
            System.exit(0);
        }

    }
    private static void dataValidation(String data) {
        String result = "";
        String inputData = data;
        String[] signs = {"+", "-", "*", "/"};

        for (String sign : signs) {
            if (inputData.contains(sign)) {
                result = sign;
            }
        }

        if (result.length() == 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Неверный формат данных");
                System.exit(0);
            }
        }

        int res;

        for (String sign : signs) {
            res = inputData.length() - inputData.replace(sign, "").length() / sign.length();

            if (res > 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Неверный формат данных");
                    System.exit(0);
                }
            }
        }


    }


    /**
     * Give second number from string
     */
    private static int findSecondNumber(String data, String assing, int firstNumber) {
        String tempString = new StringBuilder(String.valueOf(firstNumber)).append(assing).toString();
        int result = 0;

        try {
            result = Integer.parseInt(data.replace(tempString, ""));
            return result;
        } catch (NumberFormatException exception) {
            System.out.print("");
        }

        return result;
    }


    /**
     * Give first number from string using regular expressions (one way)
     *
     */
    private static int findFirstNumber(String inputData) {
        String firstNumber = inputData;

        Matcher matcher = Pattern.compile("\\d+").matcher(firstNumber);
        matcher.find();

        int i = Integer.valueOf(matcher.group());

        return i;
    }


    /**
     * Give empression sign from string
     * @param inputData
     * @return
     */
    private static String findExpressionSign(String data) {
        String result = "";
        String inputData = data;

        String[] signs = {"+","-","*","/"};

        for (int i = 0; i < signs.length; i++) {
            if (inputData.contains(signs[i])) {
                result = signs[i];
                break;
            }
        }

        return result;
    }

}
