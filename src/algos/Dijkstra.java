package algos;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import core.IGrapheConst;

public class Dijkstra {
    public static long dijkstra(IGrapheConst g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        PriorityQueue<String> file = new PriorityQueue<>((s1, s2) -> dist.get(s1) - dist.get(s2));
        file.add(source);
        Integer newDist = 0, currentDist = 0;
        String current = null;
        long startTime = System.nanoTime();
        while (!file.isEmpty()) {
            current = file.poll();
            currentDist = dist.get(current);
            if (currentDist == null) {
                currentDist = 0;
            }
            for (String succ : g.getSucc(current)) {
                newDist = currentDist + g.getValuation(current, succ);
                if (!dist.containsKey(succ) || newDist < dist.get(succ)) {
                    dist.put(succ, newDist);
                    prev.put(succ, current);
                    file.add(succ);
                }
            }
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        PriorityQueue<String> file = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());
        file.add("toto");
        file.add("tet");
        file.add("tetetete");
        while (!file.isEmpty())
            System.out.println(file.poll());
    }
}