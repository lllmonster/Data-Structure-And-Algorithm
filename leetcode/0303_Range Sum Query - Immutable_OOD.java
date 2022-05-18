/**
my own solu - o(n),o(1)
Note that data is a reference to nums and is not a copy of it.*/
class NumArray {
    
    int[] data;
    public NumArray(int[] nums) {
        data = nums;
    }
    
    public int sumRange(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum+=data[i];
        }
        return sum;
    }
}

/**
optimal solu - with cache - o(1),o(n)*/
class NumArray {
    
    int[] sum;
    public NumArray(int[] nums) {
        sum = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            sum[i+1] = sum[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return sum[right+1]-sum[left];
    }
}

/**
optimal solu 1 */
class NumArray {
    
    int[] nums;
    public NumArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i-1];
        }
        this.nums = nums;
    }
    
    public int sumRange(int left, int right) {
        if (left == 0) return nums[right];
        return nums[right]-nums[left-1];
    }
}

/**
optimal solu 2 - using map
this one is not tested. Just sample code */
private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();

public NumArray(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            map.put(Pair.create(i, j), sum);
        }
    }
}

public int sumRange(int i, int j) {
    return map.get(Pair.create(i, j));
}