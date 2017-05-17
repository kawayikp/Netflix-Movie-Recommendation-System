package coen272;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile implements Base {
    public static int[][] readTrainData(String path) {
        int[][] train = new int[TRAIN_ROW][TRAIN_COL];

        BufferedReader br = null;
        int rowIndex = 0;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = new String();
            while ((line = br.readLine()) != null) {
                String[] ss = line.split("\t");
                for (int colIndex = 0; colIndex < 1000; colIndex++) {
                    train[rowIndex][colIndex] = Integer.valueOf(ss[colIndex]);
                }
                rowIndex++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return train;
    }

    public static MyResult readTestData(String path, int userIdStart) {
        int[][] test = new int[TEST_ROW][TEST_COL];
        List<Pair>[] index = new ArrayList[TEST_ROW];
        for (int i = 0; i < TEST_ROW; i++) {
            index[i] = new ArrayList<>();
        }
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(path));
            String line = new String();
            while ((line = br.readLine()) != null) {
                String[] ss = line.split(" "); 	// or "\\s"
                int userId = Integer.valueOf(ss[0]);
                int movieId = Integer.valueOf(ss[1]);
                int rate = Integer.valueOf(ss[2]);
                test[userId - userIdStart][movieId - 1] = rate;
                if (rate == 0) {
                    index[userId - userIdStart].add(new Pair(movieId - 1, 0));
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MyResult(test, index);
    }
}

class MyResult {
    int[][] _test;
    List<Pair>[] _index;

    public MyResult(int[][] test, List<Pair>[] index) {
        _test = test;
        _index = index;
    }
}
