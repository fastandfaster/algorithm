package algorithm_2019_4;

import java.util.HashMap;

public class FractionToRecurringDecimal {

    public static void main(String[] args) {
//        int numerator = 4;
//        int denominator = 333;
/*        int numerator = 1;
        int denominator = 6;*/

/*        int numerator = -50;
        int denominator = 8;*/
        int numerator = -1;
        int denominator = -2147483648;
        System.out.println(fractionToDecimal(numerator, denominator));
    }

    /*
    Special cases: integer overflow
    If it overflows, it goes back to the minimum value
    and continues from there. If it underflows, it goes back to the maximum value and continues from there.
    When the Math.abs is applied to the denominator, the overflow happens and Math.abs can't apply to max, min properly,
    therefore need to convert Integer to Long first
    * */
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";
        StringBuilder result = new StringBuilder();
        int sign = 1;
        if (numerator > 0 && denominator < 0) sign = -1;
        else if (numerator < 0 && denominator > 0) sign = -1;
        long numer = numerator;
        long denomi = denominator;
        numer = Math.abs(numer);
        denomi = Math.abs(denomi);
        long divisor = numer / denomi;
        long reminder = numer % denomi;
        result.append(divisor);
        if (reminder != 0)
            result.append(".");
        HashMap<Long, Integer> map = new HashMap<>();
        while (reminder != 0) {
            //time 10 as the numerator of next round
            numer = reminder * 10;
            //Use numerator as the key because if numerator is same, the divisor and reminder are same
            divisor = numer / denomi;
            reminder = numer % denomi;
            //Find the same numerator indicates the repeating
            if (map.containsKey(numer)) {
//                System.out.println("same divisor=" + divisor + " reminder=" + reminder + " numerator="+ numer);
//                System.out.println("position="+map.get(numerator));

                result.insert(map.get(numer), "(");
                result.append(")");
                break;
            } else {
//                System.out.println("not same divisor=" + divisor + " reminder=" + reminder + " numerator="+ numer + " denomi="+ denomi);
                map.put(numer, result.length());
                result.append(divisor);
            }
        }
        if (sign == -1) return "-" + result.toString();
        else
            return result.toString();
    }
}
