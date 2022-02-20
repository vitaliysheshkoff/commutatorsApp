package txt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

public class Txt {
    String[] txtNames = {
            "/txt/mixed.txt",
            "/txt/1)1UtopDside.txt",
            "/txt/2)2UtopDbottom.txt",
            "/txt/3)DbottomDbottom.txt",
            "/txt/4)DsideDside.txt"
    };
    public Vector<String> read(int i) {
        Vector<String> collection = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Txt.class.getResourceAsStream(txtNames[i]))))  {
            collection = new Vector<>();
            String line = br.readLine();

            while (line != null) {
                collection.add(line);
                line = br.readLine();
            }
            return collection;
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
        return collection;
    }

    public String getRandomString(Vector<String> collection) {
        int rnd = new Random().nextInt(collection.size());
        return collection.get(rnd);
    }

    public String readFile() {
        StringBuilder table = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Txt.class.getResourceAsStream("/txt/wordsCorners.txt"), StandardCharsets.UTF_8))) {
            String line = br.readLine();
            while (line != null) {
                table.append(line);
                line = br.readLine();
            }
            return table.toString();
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
        return table.toString();
    }

    public String[][] txtToMatrix(String table) {
        StringTokenizer st = new StringTokenizer(table, ",");
        String[][] matrix = new String[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                matrix[i][j] = st.nextToken();
            }
        }
        return matrix;
    }

    public String showWords(String[][] matrix, String twoLetters) {
        String error = "Please, check input value";
        String letters = " АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШ";
        String UpperTwoLetters = twoLetters.toUpperCase();
        if (twoLetters.length() != 2)
            return error;
        char i = UpperTwoLetters.charAt(0);
        char j = UpperTwoLetters.charAt(1);
        if (letters.indexOf(i) == -1 || letters.indexOf(j) == -1)
            return error;
        return matrix[letters.indexOf(i)][letters.indexOf(j)];
    }
}