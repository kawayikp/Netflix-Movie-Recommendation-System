package coen272;

public interface Base {
	public static final int TRAIN_ROW = 200;
	public static final int TRAIN_COL = 1000;
	public static final int TEST_ROW = 100;
	public static final int TEST_COL = 1000;
	
	/*-------------------------intput---------------------------*/
	public static final String _FILE_TRAIN_PATH = "inputdata/train.txt";
	public static final String _FILE_TEST5_PATH = "inputdata/test5.txt";
	public static final String _FILE_TEST10_PATH = "inputdata/test10.txt";
	public static final String _FILE_TEST20_PATH = "inputdata/test20.txt";
	
	/*-------------------------cosine---------------------------*/
	public static final String _FILE_RESULT5_1_PATH = "outputdata/result05_1.txt";
	public static final String _FILE_RESULT10_1_PATH = "outputdata/result10_1.txt";
	public static final String _FILE_RESULT20_1_PATH = "outputdata/result20_1.txt";
	
	/*-------------------------pearson---------------------------*/
	public static final String _FILE_RESULT5_2_PATH = "outputdata/result05_2.txt";
	public static final String _FILE_RESULT10_2_PATH = "outputdata/result10_2.txt";
	public static final String _FILE_RESULT20_2_PATH = "outputdata/result20_2.txt";
	
	/*-------------------------pearson iuf---------------------------*/
	public static final String _FILE_RESULT5_3_PATH = "outputdata/result05_3.txt";
	public static final String _FILE_RESULT10_3_PATH = "outputdata/result10_3.txt";
	public static final String _FILE_RESULT20_3_PATH = "outputdata/result20_3.txt";
	
	/*-------------------------pearson cm---------------------------*/
	public static final String _FILE_RESULT5_4_PATH = "outputdata/result05_4.txt";
	public static final String _FILE_RESULT10_4_PATH = "outputdata/result10_4.txt";
	public static final String _FILE_RESULT20_4_PATH = "outputdata/result20_4.txt";
	
	/*-------------------------item based---------------------------*/
	public static final String _FILE_RESULT5_5_PATH = "outputdata/result05_5.txt";
	public static final String _FILE_RESULT10_5_PATH = "outputdata/result10_5.txt";
	public static final String _FILE_RESULT20_5_PATH = "outputdata/result20_5.txt";
	
	/*-------------------------combination---------------------------*/
	public static final String _FILE_RESULT5_6_PATH = "outputdata/result05_6.txt";
	public static final String _FILE_RESULT10_6_PATH = "outputdata/result10_6.txt";
	public static final String _FILE_RESULT20_6_PATH = "outputdata/result20_6.txt";
}

class Pair {
	int _index;
	double _rate;

	public Pair(int index, double rating) {
		_index = index;
		_rate = rating;
	}
	
	@Override
	public String toString() {
		return "[" + _index + " " + _rate + "]";
	}
}

class Neighbor {
	int _index;
	double _similarity;

	public Neighbor(int index, double similarity) {
		_index = index;
		_similarity = similarity;
	}
	
	public String toString() {
		return _similarity + ", ";
	}
}
