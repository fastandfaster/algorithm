package algorithm_2019_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReconstructItinerary {

    public static void main(String[] args) {
//        String[][] tickets = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL", "SFO"}};
        String[][] tickets = {{"EZE", "TIA"}, {"EZE", "HBA"}, {"AXA", "TIA"},
                {"JFK", "AXA"}, {"ANU", "JFK"}, {"ADL", "ANU"}, {"TIA", "AUA"}, {"ANU", "AUA"},
                {"ADL", "EZE"}, {"ADL", "EZE"}, {"EZE", "ADL"}, {"AXA", "EZE"}, {"AUA", "AXA"},
                {"JFK", "AXA"}, {"AXA", "AUA"}, {"AUA", "ADL"}, {"ANU", "EZE"}, {"TIA", "ADL"},
                {"EZE", "ANU"}, {"AUA", "ANU"}};
//        String[][] tickets = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        ReconstructItinerary reconstructItinerary = new ReconstructItinerary();
        System.out.println(reconstructItinerary.findItinerary(tickets));
//["JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","AUA","AXA","TIA","ADL","EZE","HBA"]
    }

    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, List<String>> map = new HashMap<>();
        //Build hash to be searchable
        for (String[] ticket : tickets
        ) {
            String from = ticket[0];
            String to = ticket[1];
            List<String> destinations = new ArrayList<>();
            if (map.containsKey(from)) {
                destinations = map.get(from);
            }
            destinations.add(to);
            destinations.sort((s1, s2) -> {
                return s1.compareTo(s2);
            });
            map.put(from, destinations);
        }
        System.out.println(map);
        List<String> result = new ArrayList<>();
        if (dfs(map, "JFK", result, tickets.length + 1))
            return result;
        System.out.println(result);
        return null;
    }

    private boolean dfs(HashMap<String, List<String>> map,
                        String curFrom,
                        List<String> result, Integer size) {
        result.add(curFrom);
        if (result.size() == size) {
            return true;
        }
        if (!map.containsKey(curFrom)) {
            return false;
        }
        //Find the lexical order to
        List<String> destinations = map.get(curFrom);
        //The correct "to" position returns true
        //There are some short path not go through all nodes
        for (int i = 0; i < destinations.size(); i++) {
            String destination = destinations.get(i);
            destinations.remove(i);
            if(dfs(map, destination, result, size))
                return true;
            //BackTrack -- need to add it back
            //Another way to understand is if the node has not
            //been used, it should be put it back because it
            //can be used in following route
            result.remove(result.size() - 1);
            destinations.add(i, destination);
        }
        return false;
    }
}
