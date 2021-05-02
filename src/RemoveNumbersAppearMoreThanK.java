package interview.com;

public class RemoveNumbersAppearMoreThanK {
    public static int[] solution(int[] data, int n) {
        // Your code here
        if (n == 0) {
            return new int[0];
        }
        int index = 0;
        while (index < data.length) {
            if(count(data, data[index]) <= n) {
                index++;
            } else {
                data = remove(data, data[index]);
            }
        }

        return data;
    }

    private static int[] remove(int[] data, int datum) {
        int index = 0;
        int [] result = new int[data.length - count(data, datum)];
        for (int n: data) {
            if (n != datum) {
                result[index] = n;
                index++;
            }
        }
        return result;
    }

    public static int count(int[] data, int n) {
        int cnt = 0;
        for (int datum : data) {
            if (datum == n) {
                cnt++;
            }
        }
        return cnt;
    }
}
