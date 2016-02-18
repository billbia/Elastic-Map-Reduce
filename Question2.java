import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//This is the answer for question 2
public class Question2 {
	public static void main(String[] args) throws IOException {
		//File file = new File("input.txt");
		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//min value
		int min = 0;
		String preName = null;
		//boolean flag to get the first row
		boolean flag = false;
		//check whether is printed or not
		boolean print = false;
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			if (flag == false) {
				min = Integer.parseInt(split[0]);
				flag = true;
				preName = split[1];
			} else {
				//if the min value is equal
				if (min == Integer.parseInt(split[0])) {
					//reverse alphabet order and we can simply update the preName
					if (split[1].compareTo(preName) > 0) {
						preName = split[1];
					}
				} else if (!print) {
					print = true;
					System.out.println(preName);
					System.out.println(min);
					//because of input has been sorted we can end the loop
					System.exit(0);
				}
			}
		}
		if (!print) {
			System.out.println(preName);
			System.out.println(min);
		}
	}
}
