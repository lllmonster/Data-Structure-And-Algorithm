import java.util.IntSummaryStatistics;
class Solution {
//     public double average(int[] salary) {
//         List<Integer> s = Arrays.stream(salary).boxed().collect(Collectors.toList());
//         Collections.sort(s);
//         int sum = 0;
//         for (int i = 1; i < s.size() - 1 ; i++) {
//             sum += s.get(i);
//         }
        
//         return (double) sum/(s.size()-2);
//     }
    
    public double average(int[] salary) {
        IntSummaryStatistics stat = Arrays.stream(salary).boxed().collect(Collectors.summarizingInt(i -> i));
        return (double)(stat.getSum() - stat.getMax() - stat.getMin()) / (stat.getCount() - 2);
    }
}

