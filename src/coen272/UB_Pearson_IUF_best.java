package coen272;

import java.util.List;

/*
 * user-based with pearson similarity method + IUF
 * + smooth average 		-> good
 * + Direchlet smoothing 	-> good
 * + choose user by similarity without k
 * 
 * best when only choose positive similarity 
 */
public class UB_Pearson_IUF_best extends UB_Pearson_best {

    public UB_Pearson_IUF_best(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double negativeThreshold,
            double positiveThreshold) {
        super(train, test, rates, b, r, cp, k, negativeThreshold, positiveThreshold);
    }

    @Override
    public double[][] similarity() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (int j = 0; j < TRAIN_ROW; j++) {
                double numerator1 = 0;
                double numerator2 = 0;
                double numerator = 0;
                double d1 = 0;
                double d2 = 0;
                int commonCount = 0;

                for (int k = 0; k < TRAIN_COL; k++) {
                    if (_testIUF[i][k] != 0 && _trainIUF[j][k] != 0) {
                        numerator1 = _testIUF[i][k] - _avgRatesTestIUFSmooth[i];
                        numerator2 = _trainIUF[j][k] - _avgRatesTrainIUFSmooth[j];
                        numerator += numerator1 * numerator2;
                        d1 += numerator1 * numerator1;
                        d2 += numerator2 * numerator2;
                        commonCount++;
                    }
                }

                if (d1 != 0 && d2 != 0) {
                    _similarity[i][j] = numerator / (Math.sqrt(d1) * Math.sqrt(d2));
                    _similarity[i][j] *= (double) commonCount / (commonCount + _r);
                }

                if (_similarity[i][j] > 1) {
                    _similarity[i][j] = 1;
                }
                if (_similarity[i][j] < -1) {
                    _similarity[i][j] = -1;
                }
            }
        }
        return _similarity;
    }
}