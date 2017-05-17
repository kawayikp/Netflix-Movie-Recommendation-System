package coen272;

import java.util.List;

/*
 * user-based with basic pearson similarity method
 */
public class UB_Pearson_0 extends UB_Algorithm {

    public UB_Pearson_0(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double threshold) {
        super(train, test, rates, b, r, cp, k, threshold);
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

                for (int k = 0; k < TRAIN_COL; k++) {
                    if (_test[i][k] != 0 && _train[j][k] != 0) {
                        numerator1 = _test[i][k] - _avgRatesTest[i];
                        numerator2 = _train[j][k] - _avgRatesTrain[j];
                        numerator += numerator1 * numerator2;
                        d1 += numerator1 * numerator1;
                        d2 += numerator2 * numerator2;
                    }
                }

                if (d1 != 0 && d2 != 0) {
                    _similarity[i][j] = numerator / (Math.sqrt(d1) * Math.sqrt(d2));
                }

                if (_similarity[i][j] > 1) {
                    _similarity[i][j] = 1;
                }
                if (_similarity[i][j] < -1) {
                    _similarity[i][j] = -1;
                }
            }
            sortNeighbor(_similarity[i], i);
        }
        return _similarity;
    }

    @Override
    public List<Pair>[] predict() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (Pair p : _rates[i]) {

                double numerator = 0;
                double denominator = 0;
                int k = _k;
                int u = 0;

                while (k > 0 && u < TRAIN_ROW) {
                    int neighborIndex = _neighbors[i][u]._index;
                    double neighborSimilarity = _neighbors[i][u]._similarity;
                    if (_threshold <= Math.abs(neighborSimilarity)) {
                        if (_train[neighborIndex][p._index] != 0) {
                            numerator += neighborSimilarity * (_train[neighborIndex][p._index] - _avgRatesTrain[neighborIndex]);
                            denominator += Math.abs(neighborSimilarity);
                            k--;
                        }
                    } else {
                        break;
                    }
                    u++;
                }
                p._rate = _avgRatesTest[i];
                if (numerator != 0) {
                    p._rate += numerator / denominator;
                }
            }
        }
        return _rates;
    }
}

// very tricky of the code, if we check if(CommonCount = 0), the result is
// changed from 0.817107207355114  to 1.XXX
// I guess the precision of double operation will affect the result
/**
 * @Override public double[][] similarity() { for (int i = 0; i < TEST_ROW;
 *           i++) { for (int j = 0; j < TRAIN_ROW; j++) { double numerator1
 *           = 0; double numerator2 = 0; double numerator = 0; double d1 =
 *           0; double d2 = 0; int commonCount = 0;
 * 
 *           for (int k = 0; k < TRAIN_COL; k++) { if (_test[i][k] != 0 &&
 *           _train[j][k] != 0) { numerator1 = _test[i][k] -
 *           _avgRatesTest[i]; numerator2 = _train[j][k] -
 *           _avgRatesTrain[j]; numerator += numerator1 * numerator2; d1 +=
 *           numerator1 * numerator1; d2 += numerator2 * numerator2;
 *           commonCount++; } }
 * 
 *           if (commonCount != 0) { _similarity[i][j] = numerator /
 *           (Math.sqrt(d1) * Math.sqrt(d2)); }
 * 
 *           if (d1 != 0 && d2 != 0) { _similarity[i][j] = numerator /
 *           (Math.sqrt(d1) * Math.sqrt(d2)); }
 * 
 *           if (_similarity[i][j] > 1) { _similarity[i][j] = 1; }
 *           if(_similarity[i][j] < -1) { _similarity[i][j] = -1; }
 * 
 *           } } return _similarity; }
 **/
