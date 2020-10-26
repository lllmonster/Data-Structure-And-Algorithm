class Solution {
    /**
    There are multiple ways to solve this issue. (TODO)
    Method 1: Bucket Sort o(n)
    */
    public int[] topKFrequent(int[] nums, int k) {
        return  bucketSort(nums, k);
    }
    
    private int[] bucketSort(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] bucket = new List[nums.length+1];
        map.forEach((key, value) -> {
            if (bucket[value] == null) {
                bucket[value] = new ArrayList();
            }
            bucket[value].add(key);
        });
        
        List<Integer> ans = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && ans.size() < k; i--) {
            if (bucket[i] != null) {
                ans.addAll(bucket[i]);
            }
        }
        
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}