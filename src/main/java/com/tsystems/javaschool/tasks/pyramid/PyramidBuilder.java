package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        try {
            boolean isItPosible = false;
            int sum = 0;
            for (Integer number : inputNumbers) {
                if (number == null) {
                    throw new CannotBuildPyramidException();
                }
            }
            int i = 0;
            while (sum <= inputNumbers.size()) {
                sum += i;

                if (sum == inputNumbers.size()) {
                    isItPosible = true;
                    break;
                }
                i++;
            }
            if (isItPosible == false) {
                throw new CannotBuildPyramidException();
            }
            Collections.sort(inputNumbers);


            int[][] result = new int[i][i + i - 1];

            Queue<Integer> queue = new LinkedList<>();
            for (Integer object : inputNumbers) {
                queue.offer(object);
            }

            result[0][(i + i - 1) / 2] = 10;
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < i + i - 1; k++) {
                    if (result[j][k] != 0) {
                        if (result[j][k] == 10) {
                            result[j][k] = queue.poll();
                        }

                        if (j != i - 1 && k != 0 && k != i + i - 2) {
                            result[j + 1][k - 1] = 10;
                            result[j + 1][k + 1] = 10;
                        }
                    }

                    System.out.print(result[j][k] + " ");
                }
                System.out.println();
            }
            return result;
        } catch (OutOfMemoryError e) {
            throw new CannotBuildPyramidException();
        }

    }


}
