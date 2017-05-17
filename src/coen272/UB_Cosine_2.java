package coen272;

import java.util.List;

/*
 * user-based with cosine similarity method
 * + average smooth 		
 * + Dirichlet smoothing	
 * + IUF					-> bad
 */

public class UB_Cosine_2 extends UB_Algorithm implements Base {

    public UB_Cosine_2(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double threshold) {
        super(train, test, rates, b, r, cp, k, threshold);
    }

    @Override
    public double[][] similarity() {
        for (int i = 0; i < TEST_ROW; i++) {
            for (int j = 0; j < TRAIN_ROW; j++) {
                double numerator = 0;
                double len1 = 0;
                double len2 = 0;
                int commonCount = 0;

                for (int k = 0; k < TRAIN_COL; k++) {
                    if (_testIUF[i][k] != 0 && _trainIUF[j][k] != 0) {
                        numerator += _testIUF[i][k] * _trainIUF[j][k];
                        len1 += Math.pow(_testIUF[i][k], 2);
                        len2 += Math.pow(_trainIUF[j][k], 2);
                        commonCount++;
                    }
                }
                if (len1 != 0 && len2 != 0) {
                    _similarity[i][j] = numerator / ((Math.sqrt(len1) * Math.sqrt(len2)));
                    _similarity[i][j] *= (double) commonCount / (commonCount + _r);
                } else {
                    _similarity[i][j] = 0;
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
                    if (_threshold <= neighborSimilarity) {
                        if (_train[neighborIndex][p._index] != 0) {
                            numerator += neighborSimilarity * _train[neighborIndex][p._index];
                            denominator += neighborSimilarity;
                            k--;
                        }
                    } else {
                        break;
                    }
                    u++;
                }
                p._rate = _avgRatesTestSmooth[i];
                if (numerator != 0) {
                    p._rate = numerator / denominator;
                }
            }
        }
        return _rates;
    }
}