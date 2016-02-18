import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//answer for question9
public class Question9 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		FileInputStream fstream = new FileInputStream(args[0]);
		FileInputStream fstream1 = new FileInputStream(args[1]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(fstream1));
		String sencond;
		String strLine;
		//the longest descending days in all lines 
		int gloMax = 0;
		//the result how many titles have the same 
		int result = 0;
		//global max for longest descending order 
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			int localMax = findLogestDes(split);
			gloMax = Math.max(gloMax, localMax);
		}
		//second loop find all articles with max views
		while ((sencond = br1.readLine()) != null) {
			String[] split = sencond.split("\\t");
			int localMax = findLogestDes(split);
			if (localMax == gloMax) {
				result++;
			}
		}
		System.out.println(result);
	}
	//using dynamic programming to find the longest descending days;
	private static int findLogestDes(String[] split) {
		int local = 0;
		int max = 0;
		for (int i = 3; i < 33; i++) {
			int curr = Integer.parseInt(split[i].split(":")[1]);
			int prev = Integer.parseInt(split[i - 1].split(":")[1]);
			if (curr < prev) {
				local++;
			} else {
				local = 0;
			}
			max = Math.max(local, max);
		}
		return max;
	}
}
