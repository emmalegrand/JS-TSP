package examples.jstsp;

import java.util.*;

public class ToolSwitchingBruteForce {

    ToolSwitchingInstance data;

    public ToolSwitchingBruteForce(ToolSwitchingInstance data) {
        this.data = data;
    }

    public void solve() {
        int[] array = new int[data.n];
        for (int i = 0; i < data.n; i++) {
            array[i] = i;
        }
        BestSolution best = new BestSolution(new int[data.n], Integer.MAX_VALUE);
        generatePermutations(array, 0, best);

        evaluate(best.array); // to print the solution (optional

        System.out.println("Best solution: " + best.value);
        System.out.println("Best solution: " + Arrays.toString(best.array));
    }


    private void generatePermutations(int[] array, int start, BestSolution best) {
        if (start >= array.length) {
            int cost = evaluate(array);
            //System.out.println(cost);
            if (cost < best.value) {
                //System.out.println("New best solution: " + cost);
                best.value = cost;
                System.arraycopy(array, 0, best.array, 0, array.length);
            }
            // System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = start; i < array.length; i++) {
            swap(array, start, i);
            generatePermutations(array, start + 1, best);
            swap(array, start, i); // backtrack
        }
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }



    public int evaluate(int[] array) {
        // keep the soonest needed tool first

        int [][] soonest = new int[data.n+1][data.m];
        for (int t = 0; t < data.m; t++) {
            soonest[array.length][t] = Integer.MAX_VALUE-1;
        }
        for (int i = array.length-1; i >= 0; i--) {
            for (int t = 0; t < data.m; t++) {
                if (data.matrix.get(array[i]).get(t)) {
                    soonest[i][t] = i;
                } else {
                    soonest[i][t] = soonest[i+1][t];
                }
            }
        }

        Set<Integer> current = new HashSet<>();
        current.addAll(data.tools[array[0]]);
        int cost = current.size();

        for (int i = 1; i < array.length; i++) {
            List<Integer> next = data.tools[array[i]];

            for (int tool : next) {
                if (!current.contains(tool)) {
                    current.add(tool);
                    cost++;
                }
            }

            while (current.size() > data.c) {
                int max = Integer.MIN_VALUE;
                int maxIndex = -1;
                for (int tool : current) {
                    if (soonest[i][tool] > max) {
                        max = soonest[i][tool];
                        maxIndex = tool;
                    }
                }
                current.remove(maxIndex);
            }
        }
        return cost;
    }

}

class BestSolution {
    int[] array; int value;
    BestSolution(int[] array, int value) {
        this.array = array;
        this.value = value;
    }
}