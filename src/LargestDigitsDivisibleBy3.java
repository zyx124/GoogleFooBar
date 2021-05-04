package interview.com;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class LargestDigitsDivisibleBy3 {

    public static int divisionBy3(int[] l) {
        if (l.length == 0) {
            return 0;
        }
        int sum = 0;

        // Sort the array in order to remove the smallest first
        Arrays.sort(l);
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        Queue<Integer> q3 = new LinkedList<>();

        for (int d : l) {
            sum += d;
            if (d % 3 == 0) {
                q1.add(d);
            } else if (d % 3 == 1) {
                q2.add(d);
            } else {
                q3.add(d);
            }
        }

        int result = 0;

        // check the remainder of the sum to see which queue should be polled.
        // For example, if the remainder is 2, either two elements from q2 or one from q3 should be removed.
        if (sum % 3 == 0) {
            result = getResult(q1, q2, q3);

        } else if (sum % 3 == 1) {
            if (!q2.isEmpty()) {
                q2.remove();
            } else {
                if (!q3.isEmpty()) {
                    q3.remove();
                }
                if (!q3.isEmpty()) {
                    q3.remove();
                } else {
                    return 0;
                }

            }
            result = getResult(q1, q2, q3);

        } else if (sum % 3 == 2) {
            if (!q3.isEmpty()) {
                q3.remove();
            } else {
                if (!q2.isEmpty()) {
                    q2.remove();
                }
                if (!q2.isEmpty()) {
                    q2.remove();
                } else {
                    return 0;
                }
            }
            result = getResult(q1, q2, q3);
        }

        return result;
    }

    private static int getResult(Queue<Integer> q1, Queue<Integer> q2, Queue<Integer> q3) {

        List<Integer> result = new ArrayList<>();
        while (!q1.isEmpty()) {
            result.add(q1.poll());
        }
        while (!q2.isEmpty()) {
            result.add(q2.poll());
        }
        while (!q3.isEmpty()) {
            result.add(q3.poll());
        }
        Collections.sort(result);

        int r = 0;
        for (int i = 0; i < result.size(); i++) {
            r += result.get(i) * Math.pow(10, i);
        }
        return r;

    }
}
