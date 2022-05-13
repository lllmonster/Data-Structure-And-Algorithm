/**
Java 8
 */
class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] input = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(input, (a,b) -> Integer.bitCount(a) == Integer.bitCount(b) ? a-b : Integer.bitCount(a) - Integer.bitCount(b));
        return Arrays.stream(input).mapToInt(Integer::intValue).toArray();
    }
}

/**
Normal Java */
class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] cur = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) 
            cur[i] = arr[i];
        
        // Arrays.sort(cur, (a,b) -> Integer.bitCount(a) == Integer.bitCount(b) ? a-b : Integer.bitCount(a)-Integer.bitCount(b));
        
        Arrays.sort(cur, Comparator.comparing(i -> Integer.bitCount(i)*10000+i));
        
        for (int i = 0; i < arr.length; i++)
            arr[i] = cur[i];
        
        return arr;
    }
}