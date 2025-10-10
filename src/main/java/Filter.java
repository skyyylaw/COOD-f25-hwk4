import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Filter {

    /**
     * This method takes a List of Strings and a Source object, and returns a Set of
     * Strings in the input List that also are in the List returned by Source.get(),
     * i.e. the intersection of the two Lists.
     */
	public static Set<String> filter(List<String> list, Source source) {
		
		if (list == null || source == null) {
			return null;
		}
		
		if (list.isEmpty()) {
			return Collections.emptySet();
		}
		
		List<String> data = source.get();
		
		if (data == null) {
			return Collections.emptySet();
		}
		
		if (data.isEmpty()) {
			return Collections.emptySet();
		}
		
		Set<String> results = new HashSet<>();
		
		for (String s : list) {
			if (s != null && data.contains(s)) {
				results.add(s);
			}
		}
		
		return results;
		
	}
	
}
