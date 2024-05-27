import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите математическое выражение по типу (5 + 6) или (X * III), затем нажмите ENTER");

        Scanner s = new Scanner(System.in);
        String mainString = s.nextLine().trim().toUpperCase();
        String [] array = mainString.split(" ");
        if (array.length != 3){
            throw new IllegalArgumentException("Невернрая запись. Ожидается: число оператор число");
        }
        String firstOperand = array[0];
        String secondOperand = array[2];
        String operator = array[1];

        boolean isFirstNumberRoman = isRoman(firstOperand);
        boolean isSecondNumberRoman = isRoman(secondOperand);

        try {
            if (isFirstNumberRoman && isSecondNumberRoman){
                int firstArabicNumber = RomanNumbers.valueOf(firstOperand).getValue();
                int secondArabicNumber = RomanNumbers.valueOf(secondOperand).getValue();
                int result = calculate(firstArabicNumber,secondArabicNumber,operator);
                if (result<1){
                    throw new IllegalArgumentException("В римской системе вычесления не может быть отрицательного значения или нуля(?)");
                }
                System.out.println("Результат данного выражения = " + RomanConverter.toRoman(result));
            }else if (isFirstNumberRoman!=isSecondNumberRoman){
                throw new IllegalArgumentException("Используются одновременно разные системы вычисления");
            }else{
                int firstArabicNumber =  Integer.parseInt(firstOperand);
                int secondArabicNumber = Integer.parseInt(secondOperand);
                if (firstArabicNumber<1 || firstArabicNumber>10 || secondArabicNumber<1 || secondArabicNumber>10){
                    throw new IllegalArgumentException("Допустимый диапозон чисел от 1 до 10 включительно");
                }
                int result = calculate(firstArabicNumber,secondArabicNumber,operator);
                System.out.println("Результат данного выражения = " + result);
            }
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Произошла ошибка - " + e.getMessage());
        } finally {
            s.close();
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

    public static boolean isRoman(String str){
        try {
            RomanNumbers.valueOf(str);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}