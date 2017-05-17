package coen272;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriteFile implements Base {
	public static void writeFileResult(List<Pair>[] rates, String path, int userIdStart) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));

			for (int i = 0; i < TEST_ROW; i++) {
				for (int j = 0; j < rates[i].size(); j++) {
					int result = (int)Math.round(rates[i].get(j)._rate);
					if (result < 1) {
						result = 1;
					}
					if (result > 5) {
						result = 5;
					}
					String s = "" + (userIdStart + i) + " " + (rates[i].get(j)._index + 1) + " " + result; 
					bw.write(s);
					bw.newLine();
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFileResultCombine(List<Pair>[] rates1, double p1, List<Pair>[] rates2, double p2, String path, int userIdStart) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < TEST_ROW; i++) {
				for (int j = 0; j < rates1[i].size(); j++) {
					int result = (int)Math.round(rates1[i].get(j)._rate * p1 + rates2[i].get(j)._rate * p2);
					if (result < 1) {
						result = 1;
					}
					if (result > 5) {
						result = 5;
					}
					String s = "" + (userIdStart + i) + " " + (rates1[i].get(j)._index + 1) + " " + result; 
					bw.write(s);
					bw.newLine();
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFileResultCombine(List<Pair>[] rates1, double p1, List<Pair>[] rates2, double p2, List<Pair>[] rates3, double p3,String path, int userIdStart) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < TEST_ROW; i++) {
				for (int j = 0; j < rates1[i].size(); j++) {
					int result = (int)Math.round(rates1[i].get(j)._rate * p1 + rates2[i].get(j)._rate * p2 + rates3[i].get(j)._rate * p3);
					if (result < 1) {
						result = 1;
					}
					if (result > 5) {
						result = 5;
					}
					String s = "" + (userIdStart + i) + " " + (rates1[i].get(j)._index + 1) + " " + result; 
					bw.write(s);
					bw.newLine();
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


