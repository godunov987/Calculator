public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(calc("1 + 8"));
        System.out.println(calc("VI / III"));
        System.out.println(calc("VI * IX"));
        System.out.println(calc("I - II"));
        System.out.println(calc("I + 1"));
        System.out.println(calc("1 + 2 + 3"));
    }

    static int[] intervals={0, 1, 4, 5, 9, 10, 40, 50, 90, 100};
    static String[] numerals={"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};

    public static String calc(String input) throws Exception {
        boolean roman = false;
        String[] arr = input.split(" ");
        int response = 0;

        if (arr.length != 3) {
        throw new Exception();
        }

        int a;
        int b;


        if (checkRom(arr[0]) && checkRom(arr[2])) {
            a = checkNum(toArabic(arr[0]));
            b = checkNum(toArabic(arr[2]));
            roman = true;
        } else {
            a = checkNum(Integer.parseInt(arr[0]));
            b = checkNum(Integer.parseInt(arr[2]));
        }


        switch (arr[1]) {
            case "+":
                response = a + b;
                break;
            case "-":
                response = a - b;
                break;
            case "/":
                response = a / b;
                break;
            case "*":
                response = a * b;
                break;
            default:
                new Exception();
        }
        if (roman){
            return toRoman(response);
        }else {
            return String.valueOf(response);
        }
    }
    static boolean checkRom(String str) {
        String[] arr = new String[]{"I", "V", "X"};
        for (int i = 0; i < arr.length; i++) {
            if (str.contains(arr[i])){
                return true;
            }
        }
        return false;
    }
    static int checkNum(int num) throws Exception {
        if (num >= -10 && num <= 10) {
            return num;
        } else {
            throw new Exception();
        }

    }

    static int findFloor(final int number, final int firstIndex, final int lastIndex) {
        if(firstIndex==lastIndex)
            return firstIndex;
        if(intervals[firstIndex]==number)
            return firstIndex;
        if(intervals[lastIndex]==number)
            return lastIndex;
        final int median=(lastIndex+firstIndex)/2;
        if(median==firstIndex)
            return firstIndex;
        if(number == intervals[median])
            return median;
        if(number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);

    }

    static String toRoman(final int number) {
        int floorIndex=findFloor(number, 0, intervals.length-1);
        if(number==intervals[floorIndex])
            return numerals[floorIndex];
        return numerals[floorIndex]+toRoman(number-intervals[floorIndex]);
    }

    static int toArabic(String roman) {
        int result = 0;
        for (int i = intervals.length-1; i >= 0; i-- ) {
            while (roman.indexOf(numerals[i]) == 0 && numerals[i].length() > 0) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        return result;
    }
}
