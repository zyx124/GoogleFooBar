package interview.com;

public class CalculateGearRadius {
    public static int[] getRadius(int[] pegs) {
        int[] distances = new int[pegs.length - 1];
        for (int i = 0; i < pegs.length - 1; i++) {
            distances[i] = pegs[i + 1] - pegs[i];
        }

        // We can easily get the relation between the first radius r0 and last radius rn:
        // r1/rn = 2 and r0 + (-1)^n * rn = d0 - d1 + d2 - d3 ... (-1)^(n-1) * d(n-1);
        // Therefore we can calculate the radius based on the above two equations in even or odd n separately.
        int sumEven = 0;
        int sumOdd = 0;
        for (int i = 0; i < distances.length; i++) {
            if (i % 2 == 0) {
                sumEven += distances[i];
            } else {
                sumOdd += distances[i];
            }
        }

        int sumDistance = sumEven - sumOdd;
        if (sumDistance <= 2) {
            return new int[] {-1, -1};
        }

        int[] r1 = new int[2];
        if (distances.length % 2 == 0) {
            r1[0] = 2 * sumDistance;
            r1[1] = 1;
        } else {
            if (sumDistance % 3 == 0) {
                r1[0] = 2 * sumDistance / 3;
                r1[1] = 1;
            } else {
                r1[0] = 2 * sumDistance;
                r1[1] = 3;
            }
        }

        // verify the validation of the calculated result by checking if there's radius less than 1 or even negative.
        int start = r1[0] / r1[1];
        for (int distance : distances) {
            if (distance - start < 1) {
                return new int[]{-1, -1};
            }
            start = distance - start;
        }

        return r1;
    }


}
