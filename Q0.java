import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the main processing class for Question 0 of Project 1.1. This class processes the given dataset as directed.
 * @author xbai1 Date:01/21/2016 Project 1.1 for Cloud Computing
 */
public class Q0 {
    // Reader
    BufferedReader rd;
    // Writer
    BufferedWriter wt;
    // Cache writer
    BufferedWriter cacheWriter;
    // Split of one line
    String[] sArray;
    // Result list
    ArrayList<WikiOutput> list = new ArrayList<WikiOutput>();
    // Answer for Q2
    long countViews=0l;

    /**
     * Initialization constructor
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public Q0(String inputFile, String outputFile) throws IOException {
        rd = new BufferedReader(new FileReader(inputFile));
        wt = new BufferedWriter(new FileWriter(outputFile));
    }

    /**
     * Filter core method for Question 0
     * @throws IOException
     */
    private void filterRun() throws IOException {
        String s;
        int countInput=0;
        int countOutput=0;
        while (true) {
            // Filter for one line

            s = rd.readLine();
            if (s == null) {
                break;
            }
            countInput++;
            // Filter by rule 0: 4 elements exist
            if (!filter0(s))
                continue;
            // Split the string of the line into 4 elements
            sArray = s.split("\\s+");
            // Filter by other rules and add the result pair into list
            if (filter1(sArray[0]) && filter2(sArray[1]) && filter3(sArray[1]) && filter4(sArray[1])
                    && filter5(sArray[1])) {
                list.add(new WikiOutput(sArray[1], sArray[2]));
            }
        }

        // Sort the list
        Collections.sort(list);
        countOutput = list.size();
        // Write the result file
        for (WikiOutput wikiOutput : list) {
            wt.write(wikiOutput.title + "\t" + wikiOutput.count + "\n");
        }
        closeFile();
        writeCache(countInput,countOutput,countViews);
    }

    private void writeCache(int countInput, int countOutput, long countViews) throws IOException {
        cacheWriter = new BufferedWriter(new FileWriter("cache"));
        cacheWriter.write(countInput+"\n"+countOutput+"\n"+countViews+"\n");
        cacheWriter.close();
    }
    /**
     * close the reader and the writer
     * @throws IOException
     */
    private void closeFile() throws IOException {
        rd.close();
        wt.close();
    }

    /**
     * Main method
     * @param args
     *            input_file, output_file
     */
    public static void main(String[] args) {
        try {
            Q0 f = new Q0(args[0], args[1]);
            f.filterRun();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filter by rules. If it passes the rule test, return true. If not, return false.
     */

    // Filter by rule 0
    private boolean filter0(String s) {
        String[] sArray = s.split("\\s+");
        // prepare the answer for Q2
        countViews+=Long.parseLong(sArray[sArray.length-2]);
        // filter by rule 0
        if (sArray.length < 4)
            return false;
        return true;
    }

    // Filter by rule 1
    private boolean filter1(String s) {
        if (s.equals("en")) {
            return true;
        }
        return false;
    }

    // Filter by rule 2
    private boolean filter2(String s) {
        if (s.startsWith("Media:"))
            return false;
        if (s.startsWith("Special:"))
            return false;
        if (s.startsWith("Talk:"))
            return false;
        if (s.startsWith("User:"))
            return false;
        if (s.startsWith("User_talk:"))
            return false;
        if (s.startsWith("Project:"))
            return false;
        if (s.startsWith("Project_talk:"))
            return false;
        if (s.startsWith("File:"))
            return false;
        if (s.startsWith("File_talk:"))
            return false;
        if (s.startsWith("MediaWiki:"))
            return false;
        if (s.startsWith("MediaWiki_talk:"))
            return false;
        if (s.startsWith("Template:"))
            return false;
        if (s.startsWith("Template_talk:"))
            return false;
        if (s.startsWith("Help:"))
            return false;
        if (s.startsWith("Help_talk:"))
            return false;
        if (s.startsWith("Category:"))
            return false;
        if (s.startsWith("Category_talk:"))
            return false;
        if (s.startsWith("Portal:"))
            return false;
        if (s.startsWith("Wikipedia:"))
            return false;
        if (s.startsWith("Wikipedia_talk:"))
            return false;
        return true;
    }

    // Filter by rule 3
    private boolean filter3(String s) {
        char c = s.charAt(0);
        if (c >= 'a' && c <= 'z')
            return false;
        return true;
    }

    // Filter by rule 4
    private boolean filter4(String s) {
        if (s.endsWith(".jpg"))
            return false;
        if (s.endsWith(".gif"))
            return false;
        if (s.endsWith(".png"))
            return false;
        if (s.endsWith(".JPG"))
            return false;
        if (s.endsWith(".GIF"))
            return false;
        if (s.endsWith(".PNG"))
            return false;
        if (s.endsWith(".txt"))
            return false;
        if (s.endsWith(".ico"))
            return false;
        return true;
    }

    // Filter by rule 5
    private boolean filter5(String s) {
        if (s.equals("404_error/"))
            return false;
        if (s.equals("Main_Page"))
            return false;
        if (s.equals("Hypertext_Transfer_Protocol"))
            return false;
        if (s.equals("Search"))
            return false;
        return true;
    }
}

/**
 * Result object class
 * @author baixue
 */
class WikiOutput implements Comparable<WikiOutput> {
    // title of the log entry
    String title;
    // count of the log entry
    String count;

    WikiOutput(String t, String c) {
        title = t;
        count = c;
    }

    @Override
    public int compareTo(WikiOutput o) {
        Integer thisCount = Integer.parseInt(this.count);
        Integer oCount = Integer.parseInt(o.count);
        return oCount.compareTo(thisCount);
    }
}
