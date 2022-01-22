package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;
import java.util.Stack;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        Integer posibility = null;
        if (!(x == null || y == null)) {
            if (x.isEmpty()) {
                posibility = 1;
            }
            if (y.isEmpty() && !(x.isEmpty())) {
                posibility = 0;
            }
            if (posibility == null) {
                Stack<Object> xStack = new Stack<>();
                Stack<Object> yStack = new Stack<>();
                for (Object object : x) {
                    xStack.push(object);
                }
                for (Object object : y) {
                    yStack.push(object);
                }
                Object UnitX = null;
                Object UnitY = null;
                int xSize = xStack.size();
                int value = 0;
                do {
                    UnitX = xStack.peek();
                    UnitY = yStack.pop();
                    if (UnitX == UnitY) {
                        value++;
                        xStack.pop();
                    }
                    if (xStack.size() == 0) {
                        break;
                    }
                } while (!yStack.isEmpty());
                if (value == xSize) {
                    posibility = 1;
                } else posibility = 0;
            }
            if (posibility == 1) {
                System.out.println(true);
                return true;
            }
            System.out.println(false);
            return false;
        } else {
            throw new IllegalArgumentException();
        }

    }
}
