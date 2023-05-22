package algos;

import core.IGrapheConst;

import java.util.*;

public class Dijkstra {
    public static long dijkstra(IGrapheConst g, String source, Map<String, Integer> dist, Map<String, String> prev) {
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        Set<String> visited = new HashSet<>();

        dist.put(source, 0);
        queue.add(source);

        long startTime = System.nanoTime();

        while (!queue.isEmpty()) {
            String currentVertex = queue.poll();

            if (visited.contains(currentVertex)) {
                continue;
            }

            visited.add(currentVertex);

            for (String succ : g.getSucc(currentVertex)) {
                int valuation = g.getValuation(currentVertex, succ);
                if (valuation == -1) {
                    continue;
                }

                int newDist = dist.get(currentVertex) + valuation;

                if (!dist.containsKey(succ) || newDist < dist.get(succ)) {
                    dist.put(succ, newDist);
                    prev.put(succ, currentVertex);
                    queue.add(succ);
                }
            }
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
