package scramble;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** prepare scrambledCube as
 *
 *             |************|
 *             |*U1**U2**U3*|
 *             |************|
 *             |*U4**U5**U6*|
 *             |************|
 *             |*U7**U8**U9*|
 *             |************|
 * ************|************|************|************|
 * *L1**L2**L3*|*F1**F2**F3*|*R1**R2**R3*|*B1**B2**B3*|
 * ************|************|************|************|
 * *L4**L5**L6*|*F4**F5**F6*|*R4**R5**R6*|*B4**B5**B6*|
 * ************|************|************|************|
 * *L7**L8**L9*|*F7**F8**F9*|*R7**R8**R9*|*B7**B8**B9*|
 * ************|************|************|************|
 *             |************|
 *             |*D1**D2**D3*|
 *             |************|
 *             |*D4**D5**D6*|
 *             |************|
 *             |*D7**D8**D9*|
 *             |************|
 *
 * -> U1 U2 ... U9 R1 ... R9  F1 ... F9 D1... D9 L1 ... L9 B1 ... B9
 */

public class ScrambleImage {
    static final int  SIZE = 54;

    public ScrambleImage() {

    }

    /* print:
                  1  2  3
                  4  5  6
                  7  8  9
        37 38 39  19 20 21  10 11 12  46 47 48
        40 41 42  22 23 24  13 14 15  49 50 51
        43 44 45  25 26 27  16 17 18  52 53 54
                  28 29 30
                  31 32 33
                  34 35 36*/
    public static void ConvertScrambleToImage(String scramble, Rectangle[] rectangles) {
        for (int i = 0; i < SIZE; i++) {
            switch (scramble.charAt(i)) {
                case 'U':
                    rectangles[i+1].setFill(Color.rgb(255, 250, 240));
                    break;
                case 'F':
                    rectangles[i+1].setFill(Color.rgb(0, 255, 0));
                    break;
                case 'D':
                    rectangles[i+1].setFill(Color.rgb(255, 255, 0));
                    break;
                case 'B':
                    rectangles[i+1].setFill(Color.rgb(0, 0, 205));
                    break;
                case 'R':
                    rectangles[i+1].setFill(Color.rgb(178, 34, 34));
                    break;
                case 'L':
                    rectangles[i+1].setFill(Color.rgb(255, 165, 0));
                    break;
            }
        }
    }
}
