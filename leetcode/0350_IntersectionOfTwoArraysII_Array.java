class Solution {
    /**
    HashMap O(n) O(n)
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        List<Integer> res = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i) && map.get(i) > 0) {
                res.add(i);
                map.put(i, map.get(i)-1);
            } 
        }
        int[] finalres = new int[res.size()];
        for(int i = 0; i < finalres.length; i++) {
            finalres[i] = res.get(i);
        }
        return finalres;
    }
}