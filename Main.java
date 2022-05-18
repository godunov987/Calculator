package com.company;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class FirstClass {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String str;
        while (true) {
            str = in.nextLine();
            System.out.println(calc(str));
        }


    }


    public static String calc(String input) throws Exception {

        String[] arr = input.split(" ");
        checkStr(arr[0]);
        checkStr(arr[2]);

        String response = null;
        if (arr[2].contains("\"")) {
            switch (arr[1]) {
                case "+":
                    arr[0] = arr[0].replace("\"", "");
                    arr[2] = arr[2].replace("\"", "");
                    response = "\"" + arr[0] + arr[2] + "\"";
                    break;
                case "-":
                    arr[2] = arr[2].replace("\"", "");
                    response = arr[0].replace(arr[2], " ");
                    break;
            }

        } else {
            int x = Integer.parseInt(arr[2]);
            checkNum(x);
            switch (arr[1]) {
                case "*":
                    arr[0] = (arr[0].replace("\"", ""));
                    response = "\"" + arr[0].repeat(x) + "\"";
                    break;
                case "/":
                    arr[0] = arr[0].replace("\"", "");
                    response = "\"" + new String(arr[0].toCharArray(), 0, arr[0].length() / x) + "\"";
                    break;


            }
        }

        if (parseInt(String.valueOf(response.length())) > 40) {
            int limit = 40;
            String b = response.codePointCount(0, response.length()) > limit ?
                    response.substring(0, response.offsetByCodePoints(0, limit)) : response;
            response = b + "...";


        }
        return response;

    }


    public static void checkNum(int x) throws Exception {
        if (x > 10) {
            throw new Exception();
        }
    }

    public static void checkStr(String x) throws Exception {
        if (x.length() >= 12) {

            throw new Exception();


        }


    }

}





























