package epoch;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.TreeMap;

public class URLMap {
	
	private static String convertEpochToDate(long epoch){
		Date dt = new Date((long) epoch);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dtStr = df.format(dt);
		return dtStr;
	}
	
	private static void createMap(String[] inputLines){
		Map<Long, Map<String,Integer>> dateToURLMap = new TreeMap<Long, Map<String,Integer>>();
		
		for (String line : inputLines) {
			String[] tokens = line.split("\\|");
			long epoch = Long.parseLong(tokens[0]) * 1000;
			epoch = (long)Math.floor(epoch / (24*60*60*1000));
			epoch = epoch * (24*60*60*1000);
			Map<String, Integer> urlToCntMap = dateToURLMap.get(epoch);
			if (urlToCntMap == null) {
				urlToCntMap = new HashMap<String, Integer>();
			}
			Integer cnt = urlToCntMap.get(tokens[1]);
			if (cnt == null) {
				cnt = 0;
			}
			cnt++;
			urlToCntMap.put(tokens[1], cnt);
			dateToURLMap.put(epoch, urlToCntMap);
		}
		for(Entry<Long, Map<String,Integer>> e : dateToURLMap.entrySet()){
			System.out.println(convertEpochToDate(e.getKey())+" GMT");
			Map<String,Integer> urlToCntMap = e.getValue();
			@SuppressWarnings("unchecked")
			Entry<String,Integer>[] countEntries = urlToCntMap.entrySet().toArray(new Entry[urlToCntMap.size()]);
			Arrays.sort(countEntries, new Comparator<Entry<String,Integer>>(){
				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			for(Entry<String,Integer> en : countEntries){
				System.out.println(en.getKey()+" "+en.getValue());
			}
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try{
			System.out.println("Enter a input file name");
			/*
			 * Enter the full path of the file
			 * Example: /home/nkatre/workspace/URLVisitor/src/epoch/input.txt
			 */
			String filename = in.nextLine();
			File f = new File(filename);
			if(f.exists()){
				String[] inputLines= null;
			    List<String> strList = new ArrayList<String>();
			    try 
			    { 
			        FileInputStream fstream = new FileInputStream(filename); 
			        DataInputStream data_input = new DataInputStream(fstream); 
			        @SuppressWarnings("resource")
					BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
			        String str_line; 
			        while ((str_line = buffer.readLine()) != null) 
			        { 
			            str_line = str_line.trim(); 
			            if ((str_line.length()!=0))  
			            { 
			            	strList.add(str_line);
			            } 
			        }
			        inputLines = (String[])strList.toArray(new String[strList.size()]);
			    }
			    catch (Exception e)  
			    {
			        System.err.println("Error: " + e.getMessage());
			    }
				createMap(inputLines);
			}
			else{
			    System.out.println("Input file not present in current directory");
			}
		}
		finally{
			in.close();
		}
	}
}
/*
Time Complexity = O(n)
where n = number of input lines in the input file
*/