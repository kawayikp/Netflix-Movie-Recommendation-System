package coen272;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class IB_Algorithm implements Base {
    // input data
    protected int[][] _train;
    protected int[][] _test;
    protected List<Pair>[] _rates; // output result

    // input parameter
    protected int _b; // average smooth parameter
    protected int _r; // Dirichlet weight / significance weight parameter
    protected double _cp; // case amplification parameter
    protected int _k; // k neighbor
    protected double _threshold; // k neighbor threshold

    // output
    protected double[] _avgRatesTest;
    protected int[] _RatesTestCount;
    protected double[] _avgRatesTestSmooth;

    /*---for train average---*/
    protected double[] _avgRatesTrain;
    protected int[] _RatesTrainCount;
    protected double[] _avgRatesTrainSmooth;
    protected double _avgRateTrain;

    protected double[] _avgRatesTrainCol;
    protected int[] _RatesTrainCountCol;
    protected double[] _avgRatesTrainSmoothCol;
    protected double _avgRateTrainCol;

    //	protected double[] _iuf;
    //	protected double[][] _trainIUF;
    //	protected double[][] _testIUF;
    //	protected double[] _avgRatesTrainIUF;
    //	protected double[] _avgRatesTestIUF;
    //	protected double _avgRateTrainIUF;
    //
    //	protected double[] _avgRatesTrainIUFSmooth;
    //	protected double[] _avgRatesTestIUFSmooth;

    protected Neighbor[][] _neighbors;
    protected double[][] _similarity; // output similarity

    public IB_Algorithm(int[][] train, int[][] test, List<Pair>[] rates, int b, int r, double cp, int k, double threshold) {
        _train = train;
        _test = test;
        _rates = new ArrayList[TEST_ROW];
        for (int i = 0; i < TEST_ROW; i++) {
            _rates[i] = new ArrayList();
            for (Pair p : rates[i]) {
                _rates[i].add(new Pair(p._index, 0));
            }
        }

        _b = b;
        _r = r;
        _k = k;
        _cp = cp;
        _threshold = threshold;

        _avgRatesTest = new double[TEST_ROW];
        _RatesTestCount = new int[TEST_ROW];
        _avgRatesTestSmooth = new double[TEST_ROW];

        _avgRatesTrain = new double[TRAIN_ROW];
        _RatesTrainCount = new int[TRAIN_ROW];
        _avgRatesTrainSmooth = new double[TRAIN_ROW];
        _avgRateTrain = 0;

        _avgRatesTrainCol = new double[TRAIN_COL];
        _RatesTrainCountCol = new int[TRAIN_COL];
        _avgRatesTrainSmoothCol = new double[TRAIN_COL];
        _avgRateTrainCol = 0;

        //		_iuf = new double[TRAIN_COL];
        //		_trainIUF = new double[TRAIN_ROW][TRAIN_COL];
        //		_testIUF = new double[TEST_ROW][TEST_COL];
        //		_avgRatesTrainIUF = new double[TRAIN_ROW];
        //		_avgRatesTestIUF = new double[TEST_ROW];
        //
        //		_avgRatesTrainIUFSmooth = new double[TRAIN_ROW];
        //		_avgRatesTestIUFSmooth = new double[TEST_ROW];

        _neighbors = new Neighbor[TRAIN_COL][TRAIN_COL];
        _similarity = new double[TRAIN_COL][TRAIN_COL];

        calAvgRatesTest();
        calAvgRatesTrain();
        calAvgRatesTestSmooth();
        calAvgRatesTrainSmooth();

        calAvgRatesTrainCol();
        calAvgRatesTrainColSmooth();

        //		calIUF();
        //		calIUFRateAndAvg();
        //		calAvgRatesTestIUFSmooth();
        //		calAvgRatesTrainIUFSmooth();
    }

    protected void calAvgRatesTest() {
        for (int i = 0; i < TEST_ROW; i++) {
            int k = 0;
            for (int j = 0; j < TEST_COL; j++) {
                if (_test[i][j] != 0) {
                    _avgRatesTest[i] += _test[i][j];
                    k++;
                }
            }
            if (k != 0) {
                _avgRatesTest[i] /= k;
                _RatesTestCount[i] = k;
            }
        }
    }

    protected void calAvgRatesTrain() {
        int count = 0;
        for (int i = 0; i < TRAIN_ROW; i++) {
            int k = 0;
            for (int j = 0; j < TRAIN_COL; j++) {
                if (_train[i][j] != 0) {
                    _avgRatesTrain[i] += _train[i][j];
                    k++;
                    _avgRateTrain += _train[i][j];
                    count++;
                }
            }

            if (k != 0) {
                _avgRatesTrain[i] /= k;
                _RatesTrainCount[i] = k;
            }
        }

        _avgRateTrain /= count;
    }

    protected void calAvgRatesTrainCol() {
        int count = 0;
        for (int j = 0; j < TRAIN_COL; j++) {
            int k = 0;
            for (int i = 0; i < TRAIN_ROW; i++) {
                if (_train[i][j] != 0) {
                    _avgRatesTrainCol[j] += _train[i][j];
                    k++;
                    _avgRateTrainCol += _train[i][j];
                    count++;
                }
            }

            if (k != 0) {
                _avgRatesTrainCol[j] /= k;
                _RatesTrainCountCol[j] = k;
            }
        }
        _avgRateTrainCol /= count;
    }

    protected void calAvgRatesTestSmooth() {
        for (int i = 0; i < TEST_ROW; i++) {
            _avgRatesTestSmooth[i] = _RatesTestCount[i] * _avgRatesTest[i] / (_b + _RatesTestCount[i])
                    + _b * _avgRateTrain / (_b + _RatesTestCount[i]);
        }
    }

    protected void calAvgRatesTrainSmooth() {
        for (int i = 0; i < TRAIN_ROW; i++) {
            _avgRatesTrainSmooth[i] = _RatesTrainCount[i] * _avgRatesTrain[i] / (_b + _RatesTrainCount[i])
                    + _b * _avgRateTrain / (_b + _RatesTrainCount[i]);
        }
    }

    // ok
    protected void calAvgRatesTrainColSmooth() {
        for (int i = 0; i < TRAIN_COL; i++) {
            _avgRatesTrainSmoothCol[i] = _RatesTrainCountCol[i] * _avgRatesTrainCol[i] / (_b + _RatesTrainCountCol[i])
                    + _b * _avgRateTrainCol / (_b + _RatesTrainCountCol[i]);
        }
    }

    //	protected void calIUF() {
    //		for (int j = 0; j < TRAIN_COL; j++) {
    //			if (_RatesTrainCountCol[j] != 0) {
    //				_iuf[j] = Math.log((double) TRAIN_ROW / _RatesTrainCountCol[j]);
    //			} else {
    //				_iuf[j] = 1;
    //			}
    //		}
    //	}
    //
    //	protected void calIUFRateAndAvg() {
    //		int count = 0;
    //		for (int i = 0; i < TRAIN_ROW; i++) {
    //			for (int j = 0; j < TRAIN_COL; j++) {
    //				_trainIUF[i][j] = _train[i][j] * _iuf[j];
    //				_avgRatesTrainIUF[i] += _trainIUF[i][j];
    //				count++;
    //				_avgRateTrainIUF += _trainIUF[i][j];
    //			}
    //			_avgRatesTrainIUF[i] /= _RatesTrainCount[i];
    //		}
    //		_avgRateTrainIUF /= count;
    //
    //		for (int i = 0; i < TEST_ROW; i++) {
    //			for (int j = 0; j < TEST_COL; j++) {
    //				_testIUF[i][j] = _test[i][j] * _iuf[j];
    //				_avgRatesTestIUF[i] += _testIUF[i][j];
    //			}
    //			_avgRatesTestIUF[i] /= _RatesTestCount[i];
    //		}
    //	}
    //
    //	private void calAvgRatesTestIUFSmooth() {
    //		for (int i = 0; i < TEST_ROW; i++) {
    //			_avgRatesTestIUFSmooth[i] = _RatesTestCount[i] * _avgRatesTestIUF[i] / (_b + _RatesTestCount[i])
    //					+ _b * _avgRateTrainIUF / (_b + _RatesTestCount[i]);
    //		}
    //	}
    //
    //	private void calAvgRatesTrainIUFSmooth() {
    //		for (int i = 0; i < TRAIN_ROW; i++) {
    //			_avgRatesTrainIUFSmooth[i] = _RatesTrainCount[i] * _avgRatesTrainIUF[i] / (_b + _RatesTrainCount[i])
    //					+ _b * _avgRateTrainIUF / (_b + _RatesTrainCount[i]);
    //		}
    //	}

    protected void sortNeighbor(double[] similarity, int itenIndex) {
        for (int i = 0; i < TRAIN_COL; i++) {
            _neighbors[itenIndex][i] = new Neighbor(i, similarity[i]);
        }

        Arrays.sort(_neighbors[itenIndex], new Comparator<Neighbor>() {
            @Override
            public int compare(Neighbor n1, Neighbor n2) {
                return Double.compare(Math.abs(n2._similarity), Math.abs(n1._similarity));
            }
        });
    }

    abstract public double[][] similarity();

    abstract public List<Pair>[] predict();

}
