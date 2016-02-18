import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//This is the answer for question 3
public class Question3 {
	public static void main(String[] args) throws IOException {
		//File file = new File("input.txt");
		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		int max = 0;
		//the name of previous line
		String prevName = null;
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			String et = split[19].split(":")[1];
			int eightteen = Integer.parseInt(et);
			//compare this line with global max
			if (max < eightteen) {
				max = eightteen;
				prevName = split[1];
			}
		}
		System.out.println(prevName +"\t"+max);
	}
}
