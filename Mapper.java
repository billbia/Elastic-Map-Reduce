import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author chujian bi
 * My mapper class to filter all illegal input and transfer data to reducer
 */
public class Mapper {
	public static void main(String[] args) throws IOException {
		//Get the date information from environment
		String env = System.getenv("mapreduce_map_input_file");
		///pagecounts- -220000.gz
		if (env != null) {
			int dot = env.lastIndexOf(".");
			if (dot != -1) {
				env = env.substring(0, dot);
			}
			int sta = env.lastIndexOf("/");
			if (sta != -1) {
				env = env.substring(sta + 1);
			}	
		}
		//pagecounts-20151231-220000;
		String[] split = env.split("-");
		String date = split[1];
		//get the each line of the input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		String strLine;
		//filter all illegal input
		while ((strLine = br.readLine()) != null) {
			String[] line = strLine.split("\\s+");
			if (line.length > 3 && line[0].equals("en")) {
				if (checkStart(line)) {
					if (!Character.isLowerCase(line[1].charAt(0))) {
						if (checkEnd(line)) {
							if (!line[1].equals("404_error/") && !line[1].equals("Main_Page") && !line[1].equals("Hypertext_Transfer_Protocol") && !line[1].equals("Search")) {
								String pageTitle = line[1];
								String view = line[2];
								System.out.println(pageTitle + "\t" +pageTitle+" "+ view +" "+date);
							}
						}
					}
				}
			}
		}
	}
	//check the start of pagetitles
	private static boolean checkStart(String[] line) {
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
	private static boolean checkEnd(String[] line) {
		return !line[1].endsWith(".jpg") && 
				!line[1].endsWith(".gif") && 
				!line[1].endsWith(".png") && 
				!line[1].endsWith(".JPG") && 
				!line[1].endsWith(".GIF") && 
				!line[1].endsWith(".PNG") && 
				!line[1].endsWith(".txt") && !line[1].endsWith(".ico");
	}
}
