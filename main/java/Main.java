import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * java Mentor
 * Test tasks
 * Simple calculator for kata academy
 * @Author Silichev Andrey
 * Примкчания
 * программа позволяет вводить римские цифры, как в нижнем, так и в верхнем регистре
 * Например I + I или i + i
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Simple calculator");
        System.out.println("Enter expression with arabic or romanian numbers");
        System.out.println("For exit, enter \'kata\'");

        while (true) {
            System.out.print("User expression: ");
            String data = new Scanner(System.in).nextLine().replace(" ", "").trim();

            if (data.equals("kata".toLowerCase())) {
                System.out.println("Goodbye!");
                break;
            }
            calc(data);
        }
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


        if (numbersTypeResult.equals("arabic")) {
            try {
                int operationResult = arabicNumbersCalculate(firstNumber, secondNumber, operationSign);
                System.out.println(operationResult);
            } catch (RuntimeException e) {
                System.out.println("Выход за пределы диапазона значений");
            }


        } else if (numbersTypeResult.equals("romanian")) {
            try {
                String result = romanianNumbersCalculate(firstNumber, secondNumber, operationSign);
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println("Значение римских цифр не может быть меньше 1");

            }
        }
    }

    /**
     * the result of the expression of the entered Roman numerals
     * @param number1
     * @param number2
     * @param sign
     * @return
     */
    private static String romanianNumbersCalculate(String number1, String number2, String sign) {
        int result = 0;

        HashMap<String, Integer> romanianToIntegers = new HashMap<>();
        romanianToIntegers.put("I", 1);
        romanianToIntegers.put("II", 2);
        romanianToIntegers.put("III", 3);
        romanianToIntegers.put("IV", 4);
        romanianToIntegers.put("V", 5);
        romanianToIntegers.put("VI", 6);
        romanianToIntegers.put("VII", 7);
        romanianToIntegers.put("VIII", 8);
        romanianToIntegers.put("IX", 9);
        romanianToIntegers.put("X", 10);

        int firstNumber = 0;
        int secondNumber = 0;


        firstNumber = romanianToIntegers.get(number1);
        secondNumber = romanianToIntegers.get(number2);

        if (firstNumber < 1 || secondNumber > 10) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Выход за пределы диапазона значений");
                throw new RuntimeException(e);
            }
        }

        if (sign.equals("+")) {
            result = firstNumber + secondNumber;
        } else if (sign.equals("-")) {
            result = firstNumber - secondNumber;
        } else if (sign.equals("*")) {
            result = firstNumber * secondNumber;
        } else if (sign.equals("/")) {
            result = (int)(firstNumber / secondNumber);
        }

        if (result < 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        String romanReuslt = arabicToRoman(result);

        return romanReuslt;
    }

    /**
     * Convert arabic number to roman numbers
     * @param number
     * @return
     */
    public static String arabicToRoman(int number) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();

        for(int i=0;i<values.length;i++) {
            while(number >= values[i]) {
                number -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }


    /**
     * the result of the expression of the entered Arabic numerals
     * @param number1
     * @param number2
     * @param sign
     * @return
     */
    private static int arabicNumbersCalculate(String number1, String number2, String sign) {
        int result = 0;

        int firstNumber = Integer.parseInt(number1);
        int secondNumber = Integer.parseInt(number2);

        if (firstNumber < 1 || secondNumber > 10) {
            System.out.println("Выход за пределы значений");
        }
        String operationSign = sign;

        if (sign.equals("+")) {
            result = firstNumber + secondNumber;
        } else if (sign.equals("-")) {
            result = firstNumber - secondNumber;
        } else if (sign.equals("*")) {
            result = firstNumber * secondNumber;
        } else if (sign.equals("/")) {
            result = (int)(firstNumber / secondNumber);
        }

        return result;
    }


    /**
     * check entered numbers
     * Arabic or Roman
     * @param number1
     * @param number2
     * @return
     */
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

    /**
     * Get arithmetic sign from a string
     * @param data
     * @return
     */
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

    /**
     * checking the input string for validity
     * @param data
     */
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

        int counter = 0;

        for (String value : values) {
            if (inputData.contains(value)) {
                counter += 1;
            }
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
