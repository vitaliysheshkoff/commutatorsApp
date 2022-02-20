package scramble;
import java.util.Random;
public class Scramble {

    private final int[] intScramble;
    private final String[] scramble;
    Random random = new Random();

    public String[] getScramble() {
        return scramble;
    }

    public Scramble(int SIZE) {
        intScramble = new int[SIZE];
        scramble = new String[SIZE];
    }

    private int getRandom(int n, boolean withZero) {
        if (withZero) {
            return random.nextInt(n);
        }
        return random.nextInt(n) + 1;
    }

    private int getRandom(int n, int unequal) {
        int result;
        do {
            result = getRandom(n, false);
        }
        while (result == unequal);
        return result;
    }

    private int getRandom(int n, int unequal1, int unequal2) {
        int result;
        do {
            result = getRandom(n, false);
        }
        while (result == unequal1 || result == unequal2);
        return result;
    }

    private int getRandomWithRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    private void getIntScramble(int[] array) {
        int n = 6;
        array[0] = getRandom(n, false);
        array[1] = getRandom(n, array[0]);
        for (int i = 2; i < array.length; i++) {
            if (array[i - 1] + array[i - 2] == 7) // 7 - sum of opposite sides
                array[i] = getRandom(n, array[i - 2], array[i - 1]);
            else
                array[i] = getRandom(n, array[i - 1]);
        }
    }

    public void createNewScramble() {
        getIntScramble(intScramble);
        String[] SCRAMBLE = {
                "L", "L'", "L2",
                "U", "U'", "U2",
                "B", "B'", "B2",
                "F", "F'", "F2",
                "D", "D'", "D2",
                "R", "R'", "R2"};
        for (int i = 0; i < intScramble.length; i++) {
            switch (intScramble[i]) {
                case 1:
                    scramble[i] = SCRAMBLE[getRandom(3, true)];
                    break;
                case 2:
                    scramble[i] = SCRAMBLE[getRandomWithRange(3, 5)];
                    break;
                case 3:
                    scramble[i] = SCRAMBLE[getRandomWithRange(6, 8)];
                    break;
                case 4:
                    scramble[i] = SCRAMBLE[getRandomWithRange(9, 11)];
                    break;
                case 5:
                    scramble[i] = SCRAMBLE[getRandomWithRange(12, 14)];
                    break;
                case 6:
                    scramble[i] = SCRAMBLE[getRandomWithRange(15, 17)];
                    break;
            }
        }
    }
}
