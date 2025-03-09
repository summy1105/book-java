package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/43164 lv3
public class TravelRoute {
    static class Ticket{
        String departure;
        String desdination;
        int idx;
        public Ticket(String departure, String desdination, int idx){
            this.departure = departure;
            this.desdination = desdination;
            this.idx = idx;
        }
    }
    HashMap<String, TreeSet<Ticket>> adjacencyMap;
    boolean[] visit;
    boolean isDone=false;
    public String[] solution(String[][] tickets) {
        adjacencyMap = new HashMap<>();
        int idx=0;
        for(String[] ticket : tickets){
            TreeSet<Ticket> ticketSet = adjacencyMap.getOrDefault(ticket[0], new TreeSet<>((t1, t2)->{
                if(!t1.desdination.equals(t2.desdination)) return t1.desdination.compareTo(t2.desdination);
                return t1.idx - t2.idx;
            }));
            ticketSet.add(new Ticket(ticket[0], ticket[1], idx++));
            adjacencyMap.put(ticket[0], ticketSet);
        }
        visit = new boolean[idx];
        String[] answer = new String[tickets.length+1];
        dfs(answer, "ICN", 0);
        return answer;
    }

    public void dfs(String[] path, String curAirport, int depth){
        path[depth] = curAirport;
        if(depth == path.length-1) {
            isDone = true;
            return;
        }

        TreeSet<Ticket> ticketSet = adjacencyMap.get(curAirport);
        if(ticketSet==null) return;

        for(Ticket next : ticketSet){
            if(!visit[next.idx] && !isDone){
                visit[next.idx] = true;
                dfs(path, next.desdination, depth+1);
                visit[next.idx] = false;
            }
        }
    }

    @Test
    public void ex1() {
        String[][] tickets = {
                {"EZE", "TIA"},
                {"EZE", "HBA"},
                {"AXA", "TIA"},
                {"ICN", "AXA"},
                {"ANU", "ICN"},
                {"ADL", "ANU"},
                {"TIA", "AUA"},
                {"ANU", "AUA"},
                {"ADL", "EZE"},
                {"ADL", "EZE"},
                {"EZE", "ADL"},
                {"AXA", "EZE"},
                {"AUA", "AXA"},
                {"ICN", "AXA"},
                {"AXA", "AUA"},
                {"AUA", "ADL"},
                {"ANU", "EZE"},
                {"TIA", "ADL"},
                {"EZE", "ANU"},
                {"AUA", "ANU"}
        };

        String[] result = solution(tickets);
        Assertions.assertThat(result).containsExactly("ICN", "AXA", "AUA", "ADL", "ANU", "AUA", "ANU", "EZE", "ADL", "EZE", "ANU", "ICN", "AXA", "EZE", "TIA", "AUA", "AXA", "TIA", "ADL", "EZE", "HBA");
        String[] route1 = {"ICN", "AXA", "TIA", "AUA", "AXA", "EZE", "TIA", "ADL", "EZE", "HBA", "ICN", "AXA", "AUA", "ANU", "EZE", "HBA", "EZE", "HBA", "ADL", "EZE", "HBA"};
        String[] route2 = {"ICN", "AXA", "AUA", "ADL", "ANU", "AUA", "ANU", "EZE", "ADL", "EZE", "ANU", "ICN", "AXA", "EZE", "TIA", "AUA", "AXA", "TIA", "ADL", "EZE", "HBA"};
    }
}
