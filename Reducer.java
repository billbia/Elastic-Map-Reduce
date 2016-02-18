import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bi
 *	This is my reducer class to get the result we need
 */
public class Reducer {
	private static final int LIMIT = 100000;
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//the next line read by reducer
		String strLine;
		//the page title in tht current line 
		String currTitle = null;
		//the page title in the last line
		String lastTitle = null;
		//the date of this title(yyyymmdd)
		String date = null;
		//the month of these data
		String month = null;
		//how many views of this article in this month
		int totalView = 0;
		//how many view in this single line
		int lineView = 0;
		//key should be the date value is the views
		//all date should be show so we need to make the map like this
		HashMap<String, Integer> map;
		
		
		while ((strLine = br.readLine()) != null && !(strLine = br.readLine()).isEmpty()) {
			String[] strings = strLine.split("\t");
			if (strings != null && strings.length == 3) {
				currTitle = strings[0];
				date = strings[2];
				month = date.substring(4,6);
				lineView = Integer.parseInt(strings[1]);
			}		
			
			if (currTitle.equals(lastTitle)) {
				totalView += lineView;
				int view = array[Integer.parseInt(date.substring(6))];
				array[Integer.parseInt(date.substring(6)) - 1] = view + lineView;
			} else {
				//we only take the view number over 100000
				if (currTitle != null && totalView > LIMIT) {
					StringBuilder sb = new StringBuilder();
					sb.append(totalView + "\t" + currTitle);
					for (int i = 0; i < array.length; i++) {
						sb.append("\t" + String.valueOf(20151201 + i) + ":" + array[i]);
					}
					System.out.println(sb.toString());
				}
				for (int i = 0; i < 31; i++) {
					array[i] = 0;
				}
				lastTitle = currTitle;
				totalView = lineView;
				array[Integer.parseInt(date.substring(6)) - 1] =lineView;
			}
		}
		//check if the last line meet our requirement
		if (currTitle != null && totalView > LIMIT) {
			StringBuilder sb = new StringBuilder();
			sb.append(totalView + "\t" + currTitle);
			for (int i = 0; i < array.length; i++) {
				sb.append("\t" + String.valueOf(20151201 + i) + ":" + array[i]);
			}
			System.out.print(sb.toString());
		}
	}	
}
