## 287. Find the Duplicate Number

Four Solutions:  
1. Cycle Detection, time complexity O(n), space complexity O(1) 
2. HashSet, time complexity O(n), space complexity O(n)
3. Binary Search, time complexity O(nlgn), space complexity O(1)
4. Sorting, time complexity O(nlgn), space complexity O(1) / O(n)

### Cycle Detection 
**Solution** :  
It is very similiar with `Linked List Cycle II` problem, which uses two pointers, one of them is slow, the other is fast.  
When there exists a cycle in it, slow pointer will equal to fast pointer in first pass.  
Then, put the slow in the initial position (slow = 0), run the second pass.  
When slow meets fast in the second pass, the position is what we want.

**Code**
```
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
};
```

### HashSet
```
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        unordered_set<int> set;
        for (int i : nums) {
            if (set.find(i) != set.end()) {
                return i;
            }
            set.insert(i);
        }
        return -1;
    }
};
```

### Sorting
```
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums[i] == nums[i + 1])
                return nums[i];
        }
        return -1;
    }
};
```

### Binary Search
It's a little difficult to do it in binary search. With computing the count before the mid number, we can know where the duplicate number is.  
```
class Solution {
public:
    int findDuplicate(vector<int>& nums) {
        int lo = 1;
        int hi = nums.size() - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            int cnt = 0;
            for (int ele : nums) {
                if (ele <= mid) cnt++;
            }
            if (cnt <= mid) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
};
```
