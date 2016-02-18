import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Filter {
	public List<WikeElement> filter(String text) throws Exception {
		FileInputStream fstream = new FileInputStream(text);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));	
		String strLine;
		List<WikeElement> list = new ArrayList<>();
		//Read and Filter File Line By Line
		while ((strLine = br.readLine()) != null) {
			String[] line = strLine.split("\\s+");
			if (line.length > 3 && line[0].equals("en")) {
				if (checkStart(line)) {
					if (!(line[1].charAt(0) >= 'a' && line[1].charAt(0) <= 'z')) {
						if (checkEnd(line)) {
							if (!line[1].equals("404_error/") && !line[1].equals("Main_Page") && !line[1].equals("Hypertext_Transfer_Protocol") && !line[1].equals("Search")) {
								WikeElement result = new WikeElement(line[1], Integer.parseInt(line[2]));
								list.add(result);
							}
						}
					}
				}
			}
		}
		//sort the result in a descending order
		Collections.sort(list, new Comparator<WikeElement>() {
			@Override
			public int compare(WikeElement o1, WikeElement o2) {
				return o2.num - o1.num;
			}
		});
		br.close();
		return list;
	}
	public static void main(String[] args) throws Exception {
		Filter file = new Filter();
		List<WikeElement> result = file.filter(args[0]);
		BufferedWriter writer = new BufferedWriter(new FileWriter("output"));
		//for loop generate all lines of data
		for (int i = 0; i < result.size(); i++) {
			writer.write(result.get(i).toString());
			//we don't need a new line for the last line
			if (i != result.size() - 1) writer.newLine();
		}
		writer.close();
	}
	//check the start of pagetitles
	private boolean checkStart(String[] line) {
		return !line[1].startsWith("Media:") && !line[1].startsWith("Special:")
				&& !line[1].startsWith("Talk:")
				&& !line[1].startsWith("User:")
				&& !line[1].startsWith("User_talk:")
				&& !line[1].startsWith("Project:")
				&& !line[1].startsWith("Project_talk:")
				&& !line[1].startsWith("File:")
				&& !line[1].startsWith("File_talk:")
				&& !line[1].startsWith("MediaWiki:")
				&& !line[1].startsWith("MediaWiki_talk:")
				&& !line[1].startsWith("Template:")
				&& !line[1].startsWith("Template_talk:")
				&& !line[1].startsWith("Help:")
				&& !line[1].startsWith("Help_talk:")
				&& !line[1].startsWith("Category:")
				&& !line[1].startsWith("Category_talk:")						
				&& !line[1].startsWith("Portal:")
				&& !line[1].startsWith("Wikipedia:")
				&& !line[1].startsWith("Wikipedia_talk:");
	}
	//check the end of each pagetitle
	private boolean checkEnd(String[] line) {
		return !line[1].endsWith(".jpg") && 
				!line[1].endsWith(".gif") && 
				!line[1].endsWith(".png") && 
				!line[1].endsWith(".JPG") && 
				!line[1].endsWith(".GIF") && 
				!line[1].endsWith(".PNG") && 
				!line[1].endsWith(".txt") && !line[1].endsWith(".ico");
	}
}
//The class contains every pagetitle and numbers of accesses
class WikeElement{
	String pagetile;
	int num;
	public String getPagetile() {
		return pagetile;
	}
	@Override
	public String toString() {
		return pagetile + "\t" + num;
	}
	public WikeElement(String pagetile, int num) {
		this.pagetile = pagetile;
		this.num = num;
	}
	public WikeElement() {	}
	public void setPagetile(String pagetile) {
		this.pagetile = pagetile;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}