package com.tsystems.javaschool.tasks.calculator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        try {
            /*if (statement == null) {
                return null;
            }*/
            statement = statement + "$";
            Stack<Character> Simbols = new Stack<Character>();
            Stack<Double> Numbers = new Stack<Double>();
            Simbols.push('$');

            String NumConst = "0123456789,";
            String SimbConst = "$()+-*/";
            int[][] TranslationTable = {{6, 1, 1, 1, 1, 1, 5},
                    {5, 1, 1, 1, 1, 1, 3},
                    {4, 1, 2, 2, 1, 1, 4},
                    {4, 1, 2, 2, 1, 1, 4},
                    {4, 1, 4, 4, 2, 2, 4},
                    {4, 1, 4, 4, 2, 2, 4}};
            HashMap<Character, Integer> Functions = new HashMap<>();
            Functions.put('$', 0);
            Functions.put('(', 1);
            Functions.put('+', 2);
            Functions.put('-', 3);
            Functions.put('*', 4);
            Functions.put('/', 5);
            Functions.put(')', 6);
            int lenght = statement.length();
            for (int i = 0; ; ) {
                String element = "";

                if ((SimbConst.contains(String.valueOf(statement.charAt(i)))) == true) {

                    switch (TranslationTable[Functions.get(Simbols.peek())][Functions.get(statement.charAt(i))]) {
                        case 1:
                            Simbols.push(statement.charAt(i));
                            i++;
                            break;
                        case 2:
                            double value2 = Numbers.pop();
                            double value1 = Numbers.pop();
                            char znak = Simbols.pop();
                            double Result1 = 0;
                            switch (znak) {
                                case '+':
                                    Result1 = value1 + value2;
                                    break;
                                case '-':
                                    Result1 = value1 - value2;
                                    break;
                                case '*':
                                    Result1 = value1 * value2;
                                    break;
                                case '/':
                                    if (value2 == 0) {
                                        return null;
                                    } else Result1 = value1 / value2;
                                    break;
                            }

                            Numbers.push(Result1);
                            Simbols.push(statement.charAt(i));
                            i++;
                            break;
                        case 3:
                            char deleted = Simbols.pop();
                            i++;
                            break;
                        case 4:
                            double Num2 = Numbers.pop();
                            double Num1 = Numbers.pop();
                            char Znak = Simbols.pop();
                            double Result2 = 0;
                            switch (Znak) {
                                case '+':
                                    Result2 = Num1 + Num2;
                                    break;
                                case '-':
                                    Result2 = Num1 - Num2;
                                    break;
                                case '*':
                                    Result2 = Num1 * Num2;
                                    break;
                                case '/':
                                    if (Num2 == 0) return null;
                                    else Result2 = Num1 / Num2;
                                    break;
                            }

                            Numbers.push(Result2);
                            break;
                        case 5:
                            return null;
                        case 6:
                            NumberFormat nf = new DecimalFormat("#.#####");
                            return nf.format(Numbers.pop()).replace(",", ".");
                    }
                } else {
                    while (NumConst.contains(String.valueOf(statement.charAt(i))) == true || String.valueOf(statement.charAt(i)).equals(".")) {
                        element += String.valueOf(statement.charAt(i));
                        i++;
                    }
                    double Num = Double.parseDouble(element);

                    Numbers.push(Num);

                }
            }


        }catch (Exception e){
            return null;
        }
    }

}
