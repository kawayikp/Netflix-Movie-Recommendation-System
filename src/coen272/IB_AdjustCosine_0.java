package coen272;

import java.util.List;

/*
 * item based with basic adjusted cosine similarity method
 */
public class IB_AdjustCosine_0 extends IB_Algorithm {
    public IB_AdjustCosine_0(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double threshold) {
        super(train, test, rates, b, r, cp, k, threshold);
    }

    public double[][] similarity() {
        for (int i = 0; i < TRAIN_COL; i++) {
            for (int j = i + 1; j < TRAIN_COL; j++) {
                double numerator1 = 0;
                double numerator2 = 0;
                double numerator = 0;
                double d1 = 0;
                double d2 = 0;

                for (int k = 0; k < TRAIN_ROW; k++) {
                    if (_train[k][i] != 0 && _train[k][j] != 0) {
                        numerator1 = _train[k][i] - _avgRatesTrain[k];
                        numerator2 = _train[k][j] - _avgRatesTrain[k];
                        numerator += numerator1 * numerator2;
                        d1 += Math.pow(numerator1, 2);
                        d2 += Math.pow(numerator2, 2);
                    }
                }

                if (d1 != 0 && d2 != 0) {
                    _similarity[i][j] = numerator / Math.sqrt(d1 * d2);
                }

                if (_similarity[i][j] > 1) {
                    _similarity[i][j] = 1;
                }
                if (_similarity[i][j] < -1) {
                    _similarity[i][j] = -1;
                }
                _similarity[j][i] = _similarity[i][j];
            }
            sortNeighbor(_similarity[i], i);
        }
        return _similarity;
    }

    public List<Pair>[] predict() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (Pair p : _rates[i]) {

                double numerator = 0;
                double denomulator = 0;
                int u = 0;
                int k = _k;

                while (k > 0 && u < TRAIN_COL) {
                    int neighborIndex = _neighbors[p._index][u]._index;
                    double neighborSimilarity = _neighbors[p._index][u]._similarity;
                    if (_threshold <= Math.abs(neighborSimilarity)) {
                        if (_test[i][neighborIndex] != 0) {
                            numerator += neighborSimilarity * (_test[i][neighborIndex] - _avgRatesTest[i]);
                            denomulator += Math.abs(neighborSimilarity);
                            k--;
                        }
                    } else {
                        break;
                    }
                    u++;
                }

                p._rate = _avgRatesTest[i];
                if (denomulator != 0) {
                    p._rate += numerator / denomulator;
                }
            }
        }

        return _rates;
    }
}