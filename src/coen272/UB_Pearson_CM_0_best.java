package coen272;

import java.util.List;

/*
 * user-based with pearson similarity method + CM
 * + smooth average 		-> good
 * + Direchlet smoothing 	-> good
 * + CM						-> bad
 * 
 * Note: based on the best pearson which only chose positive similaritys but with CM, it become worse
 */

public class UB_Pearson_CM_0_best extends UB_Pearson_best {
    public UB_Pearson_CM_0_best(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double negativeThreshold,
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
                    if (_test[i][k] != 0 && _train[j][k] != 0) {
                        numerator1 = _test[i][k] - _avgRatesTestSmooth[i];
                        numerator2 = _train[j][k] - _avgRatesTrainSmooth[j];
                        numerator += numerator1 * numerator2;
                        d1 += numerator1 * numerator1;
                        d2 += numerator2 * numerator2;
                        commonCount++;
                    }
                }

                if (d1 != 0 && d2 != 0) {
                    _similarity[i][j] = numerator / (Math.sqrt(d1) * Math.sqrt(d2));
                    _similarity[i][j] *= (double) commonCount / (commonCount + _r);
                    _similarity[i][j] *= Math.pow(Math.abs(_similarity[i][j]), _cp - 1);
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
