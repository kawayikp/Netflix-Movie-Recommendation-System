package coen272;

import java.util.List;

/*
 * item based with basic adjusted cosine similarity method
 * + average smooth			-> good
 * + Derechelet smooth 50	-> good
 * + chose specific similarity rather than chose k neighbor, like only chose positive similarity -> bad
 */
public class IB_AdjustCosine_1 extends IB_Algorithm {
    private double _negativeThreashold;
    private double _positiveThreshold;

    public IB_AdjustCosine_1(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double negativeThreshold,
            double positiveThreshold) {
        super(train, test, rates, b, r, cp, k, positiveThreshold);
        _negativeThreashold = negativeThreshold;
        _positiveThreshold = positiveThreshold;
    }

    public double[][] similarity() {
        for (int i = 0; i < TRAIN_COL; i++) {
            for (int j = i + 1; j < TRAIN_COL; j++) {
                double numerator1 = 0;
                double numerator2 = 0;
                double numerator = 0;
                double d1 = 0;
                double d2 = 0;
                int commonCount = 0;

                for (int k = 0; k < TRAIN_ROW; k++) {
                    if (_train[k][i] != 0 && _train[k][j] != 0) {
                        numerator1 = _train[k][i] - _avgRatesTrainSmooth[k];
                        numerator2 = _train[k][j] - _avgRatesTrainSmooth[k];
                        numerator += numerator1 * numerator2;
                        d1 += Math.pow(numerator1, 2);
                        d2 += Math.pow(numerator2, 2);
                        commonCount++;
                    }
                }

                if (d1 != 0 && d2 != 0) {
                    _similarity[i][j] = numerator / Math.sqrt(d1 * d2);
                    _similarity[i][j] *= (double) commonCount / (commonCount + _r);
                }

                if (_similarity[i][j] > 1) {
                    _similarity[i][j] = 1;
                }
                if (_similarity[i][j] < -1) {
                    _similarity[i][j] = -1;
                }
                _similarity[j][i] = _similarity[i][j];
            }
        }
        return _similarity;
    }

    public List<Pair>[] predict() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (Pair p : _rates[i]) {
                double numerator = 0;
                double denomulator = 0;
                for (int j = 0; j < TRAIN_COL; j++) {
                    if (_test[i][j] != 0 && p._index != j
                            && (_similarity[p._index][j] <= _negativeThreashold || _positiveThreshold <= _similarity[p._index][j])) {
                        numerator += _similarity[p._index][j] * (_test[i][j] - _avgRatesTestSmooth[i]);
                        denomulator += Math.abs(_similarity[p._index][j]);
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