import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//Answer for question5
public class Question5 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		FileInputStream fstream = new FileInputStream(args[2]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//this array to store everyday's view numbers
		int[] apple = new int[31];
		//this array stores everyday's twitter view numbers
		int[] tw = new int[31];
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			if (split[1].equals(args[1])) {
				for (int i = 0; i < 31; i++) {
					apple[i] = Integer.parseInt(split[2 + i].split(":")[1]);
				}
			}
			if (split[1].equals(args[0])) {
				for (int i = 0; i < 31; i++) {
					tw[i] = Integer.parseInt(split[2 + i].split(":")[1]);
				}
			}
		}
		int sum = 0;
		//compare and get the sum
		for (int i = 0; i < 31; i++) {
			if (tw[i] > apple[i]) {
				sum++;
			}
		}
		System.out.println(sum);
	}
}
