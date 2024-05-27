public class RomanConverter {
    public static String toRoman(int number) {
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return tens[number/10] + units[number%10];
    }
}
