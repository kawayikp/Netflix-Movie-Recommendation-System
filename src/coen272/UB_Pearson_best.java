package coen272;

import java.util.List;

/*
 * user-based with basic pearson similarity method
 * + smooth average 		-> good
 * + Direchlet smoothing 	-> good
 * + choose similarity based by value rather than K neighbors
 * 		- choose useful users based on their similarity without choose K: like similaity <= a negative value or similarity >= 
 * 
 * 
 * best when choose all positive similarity and a few positive similarity which is <= -0.6
 */
public class UB_Pearson_best extends UB_Algorithm {
    private double _negativeThreshold;
    private double _positiveThreshold;

    public UB_Pearson_best(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double negativeThreshold,
            double positiveThreshold) {
        super(train, test, rates, b, r, cp, k, positiveThreshold);
        _negativeThreshold = negativeThreshold;
        _positiveThreshold = positiveThreshold;
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

    @Override
    public List<Pair>[] predict() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (Pair p : _rates[i]) {
                double numerator = 0;
                double denomulator = 0;

                for (int u = 0; u < TRAIN_ROW; u++) {
                    if (_train[u][p._index] != 0 && (_similarity[i][u] <= _negativeThreshold || _positiveThreshold <= _similarity[i][u])) {
                        denomulator += Math.abs(_similarity[i][u]);
                        numerator += _similarity[i][u] * (_train[u][p._index] - _avgRatesTrainSmooth[u]);
                    }
                }

                p._rate = _avgRatesTestSmooth[i];
                if (denomulator != 0) {
                    p._rate += numerator / denomulator;
                }
            }
        }
        return _rates;
    }
}