import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите математическое выражение по типу (5 + 6) или (X * III), затем нажмите ENTER");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine().trim().toUpperCase();
        s.close();
        try {
            String result = calc(input);
            System.out.println("Результат = " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    public static String calc(String input) {
        String [] array = input.split(" ");
        if (array.length != 3){
            throw new IllegalArgumentException("Неверная запись. Ожидается: число оператор число");
        }
        String firstOperand = array[0];
        String secondOperand = array[2];
        String operator = array[1];
        boolean isFirstNumberRoman = isRoman(firstOperand);
        boolean isSecondNumberRoman = isRoman(secondOperand);

        if (isFirstNumberRoman && isSecondNumberRoman){
            int firstArabicNumber = RomanNumbers.valueOf(firstOperand).getValue();
            int secondArabicNumber = RomanNumbers.valueOf(secondOperand).getValue();
            if (firstArabicNumber<1 || firstArabicNumber>10 || secondArabicNumber<1 || secondArabicNumber>10){
                throw new IllegalArgumentException("Допустимый диапозон чисел от 1 до 10 включительно");
            }
            int result = calculate(firstArabicNumber,secondArabicNumber,operator);
            if (result<1){
                throw new IllegalArgumentException("Результат меньше единицы недопустим в римской системе счисления");
            }
            return RomanConverter.toRoman(result);
        }else if (!isFirstNumberRoman && !isSecondNumberRoman){
            int firstArabicNumber =  Integer.parseInt(firstOperand);
            int secondArabicNumber = Integer.parseInt(secondOperand);
            if (firstArabicNumber<1 || firstArabicNumber>10 || secondArabicNumber<1 || secondArabicNumber>10){
                throw new IllegalArgumentException("Допустимый диапозон чисел от 1 до 10 включительно");
            }
            int result = calculate(firstArabicNumber,secondArabicNumber,operator);
            return String.valueOf(result);
        }else{
            throw new IllegalArgumentException("Используются одновременно разные системы вычисления");
        }
    }
    public static int calculate(int numOne, int numTwo, String operator){
        switch (operator){
            case "+":
                return numOne + numTwo;
            case "-":
                return numOne - numTwo;
            case "*":
                return numOne * numTwo;
            case "/":
                if(numTwo!=0){
                    return numOne / numTwo;
                }else{
                    throw new ArithmeticException("На ноль делить нельзя!");
                }
            default:
                throw new IllegalArgumentException("Неподходящий оператор между числами");

        }
    }
    public static boolean isRoman(String str) {
        try{
            RomanNumbers.valueOf(str);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}