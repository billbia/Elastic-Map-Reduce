import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

//answer for questino8
public class Question8 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		FileInputStream fstream = new FileInputStream(args[0]);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		//total number of match
		int total = 0;
		Map<String, int[]> map = new HashMap<>();
		//in case of another Zorro film in the dataset
		//Map<String, Integer> times = new HashMap<>();
		while ((strLine = br.readLine()) != null) {
			String[] split = strLine.split("\\t");
			String title = split[1];
			//split the name by (
			String[] article = title.split("\\(");
			if (article.length == 2) {
				//the article name
				String name = article[0];
				//the last part of article name
				String end = article[1];
				//the index of right )
				int right = end.lastIndexOf(")");
				//the first _
				int left = end.indexOf("_");
				String type = null;
				//if there is no year for TV_series
				if (left != -1 && left <= right) {
					String pre = end.substring(0, left);
					type = end.substring(left + 1, right);
					if (type.equals("series") && pre.equals("TV")) {
						type = "TV_series";
					}					
				} else {
					type = end.substring(0, right);
					//get rid of other cases
					if (!type.equals("film")) {
						type = null;
					}
				}
				if (map.containsKey(name)) {
					//if match the total plus one
					if ("film".equals(type)) {
						map.put(name, new int[]{map.get(name)[0] + 1, map.get(name)[1]});
					}
					if ("TV_series".equals(type)) {
						map.put(name, new int[]{map.get(name)[0], map.get(name)[1] + 1});
					}
				} else {
					//if the first find set into hashmap
					if ("film".equals(type)) {
						map.put(name, new int[]{1,0});
					}
					if ("TV_series".equals(type)) {
						map.put(name, new int[]{0,1});
					}
				}
			}		
		}
		int sum = 0;
		for (Entry entry : map.entrySet()) {
			int[] result = (int[]) entry.getValue();
			if (result[0] != 0 && result[1] != 0) {
				sum += (result[0] + result[1]) / 2;
			}
		}
		System.out.println(sum);
	}
}
