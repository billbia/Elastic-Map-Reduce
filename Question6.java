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
//Answer for question6
//output looks like
//Jurassic_Park_(film),The_Hunger_Games_(film),Fifty_Shades_of_Grey_(film),The_Martian_(film),Interstellar_(film)
public class Question6 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//read the q6 file
		List<String> input = new ArrayList<>();
		File file = new File("src/q6");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			input.add(sc.next());
		}
		sc.close();
		if (input.isEmpty()) {
			System.out.println(0);
			return;
		}
		FileInputStream fstream = new FileInputStream("output.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//every movie's largest number of visit
		int sum = 0;
		//a list to store all result of single visit
		List<Film> list = new ArrayList<>();
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			for (String str : input) {
				if (split[1].equals(str)) {
					for (int i = 0; i < 31; i++) {
						int temp = Integer.parseInt(split[2 + i].split(":")[1]);
						if (temp > sum) {
							sum = temp;
						}
					}
					list.add(new Film(sum, str+","));
					sum = 0;
				}
			}	
		}
		//descending order of list
		Collections.sort(list, new Comparator<Film>() {
			@Override
			public int compare(Film o1, Film o2) {
				return o2.num - o1.num;
			}			
		});
		StringBuilder sb = new StringBuilder();
		for (Film f : list) {
			sb.append(f.name);
		}
		//get rid of the last char
		System.out.println(sb.toString().substring(0, sb.length() - 1));
	}
}
//this class to store some information of a film
class Film {
	//num of the largest num access
	int num;
	//name of movie
	String name;
	public Film(int num, String name) {
		this.num = num;
		this.name = name;
	}	
}
