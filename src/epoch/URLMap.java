package epoch;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	
	private static void createMap(String input){
		Map<Long, Map<String,Integer>> dateToURLMap = new TreeMap<Long, Map<String,Integer>>();
		String[] inputLines = input.split(",");
		
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

		String input = "1407564301|www.nba.com,1407478021|facebook,1407478022|facebook,1407481200|news.ycombinator,1407478028|google,1407564301|sports.yahoo,1407564300|cnn,1407564300|nba,1407564300|nba,1407564301|sports.yahoo,1407478022|google,1407648022|twitter";
		createMap(input);

	}

}