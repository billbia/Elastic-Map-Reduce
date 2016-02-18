import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


//Answer for Question7
public class Question7 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//read the q6 file
		List<String> input = new ArrayList<>();
		File file = new File(args[0]);
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			input.add(sc.next());
		}
		sc.close();
		if (input.isEmpty()) {
			System.out.println(0);
			return;
		}
		FileInputStream fstream = new FileInputStream(args[1]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//List of os
		List<OS> list = new ArrayList<>();
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			for (String str : input) {
				if (split[1].equals(str)) {
					int view = Integer.parseInt(split[0]);
					list.add(new OS(view, str+","));
				}
			}			
		}
		//sort the list according to the descending of views and reverse alphabet of name 
		Collections.sort(list, new Comparator<OS>() {
			@Override
			public int compare(OS o1, OS o2) {
				if (o1.num == o2.num) {
					return o2.name.compareTo(o1.name);
				} else {
					return o2.num - o1.num;
				}
			}
		});
		StringBuilder sb = new StringBuilder();
		for (OS os : list) {
			sb.append(os.name);
		}
		//get rid of the last char
		System.out.println(sb.toString().substring(0, sb.length() - 1));
	}
}
class OS {
	//num of total view
	int num;
	//name of OS
	String name;
	public OS(int num, String name) {
		this.num = num;
		this.name = name;
	}
}
