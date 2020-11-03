class Solution {
    /**
    * The optimzie method: Two Pointers: o(log(min(m,n)))
              left_part          |        right_part
        A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
        B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
    * Binary search o(log(m+n))
    * scan o(m+n)
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // return omplusn(nums1, nums2);
        // return ologmplusn(nums1,nums2);
        // return ologmplusn1(nums1,nums2);
        return optimizedMethod(nums1, nums2);
    }

    private double optimizedMethod(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) return findMedianSortedArrays(nums2, nums1);
        int lo = 0, hi = n1;
        while (lo <= hi) {
            int mid1 = (lo + hi) / 2;
            int mid2 = (n1 + n2) / 2 - mid1;
            
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1-1];
            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2-1];
            int r1 = (mid1 == n1) ? Integer.MAX_VALUE : nums1[mid1];
            int r2 = (mid2 == n2) ? Integer.MAX_VALUE : nums2[mid2];
            
            if (l1 > r2) hi = mid1-1;
            else if (l2 > r1) lo = mid1+1;
            else {
                if ((n1+n2) %2 == 0)
                    return (double) (Math.max(l1, l2) + Math.min(r1, r2))/2;
                else 
                    return Math.min(r1, r2);
            }
        }
        return -1;
    }
    
    private double ologmplusn1(int[] nums1, int[]nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 > n2) return ologmplusn1(nums2, nums1);
        int target = (n1 + n2 + 1) / 2;
        int l = 0, r = n1;
        while (l <= r) {
            int m1 = (l + r) / 2;
            int m2 = target - m1;
            int left1 = m1 > 0 ? nums1[m1-1] : Integer.MIN_VALUE;
            int right1 = m1 < n1 ? nums1[m1] : Integer.MAX_VALUE;
            int left2 = m2 > 0 ? nums2[m2-1] : Integer.MIN_VALUE;
            int right2 = m2 < n2 ? nums2[m2] : Integer.MAX_VALUE;
            if (left1 <= right2 && left2 <= right1) {
                if ((n1+n2)%2 == 1) {
                    return Math.max(left1, left2);
                } else {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) * 0.5;
                }
            }
            if (left1 > right2) {
                r = m1 - 1;
            }
            if (left2 > right1) {
                l = m1 + 1;
            }
        }
        return 0.0;
    }
    
    private double ologmplusn(int[] nums1, int[]nums2) {
        if (nums1.length > nums2.length) {
            return ologmplusn(nums2,nums1);
        }
        
        int k = (nums1.length + nums2.length + 1)/2;
        int l = 0, r = nums1.length;
        int m1 = 0, m2 = 0;
        while (l < r) {
            m1 = (l + r) / 2;
            m2 = k - m1;
            if (nums1[m1] < nums2[m2-1]) {
                l = m1 + 1;
            } else {
                r = m1;
            }
        }
        m1 = l;
        m2 = k - l;
        double c1=0;
        if (m1 <= 0) {
            c1 = nums2[m2-1];
        } else if (m2 <= 0) {
            c1 = nums1[m1-1];
        } else {
            c1 = Math.max(nums1[m1-1], nums2[m2-1]);
        }
        if ((nums1.length + nums2.length)%2 != 0) {
            return c1;
        }
        
        double c2 = 0;
        if (m1 >= nums1.length) {
            c2 = nums2[m2];
        } else if (m2 >= nums2.length) {
            c2 = nums1[m1];
        } else {
            c2 = Math.min(nums1[m1], nums2[m2]);
        }
        return (c1 + c2) *0.5;
    }
    
    private double omplusn(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                list.add(nums1[i]);
                i++;
            } else {
                list.add(nums2[j]);
                j++;
            }
        }
        while (i < nums1.length) {
            list.add(nums1[i]);
            i++;
        }
        while(j < nums2.length) {
            list.add(nums2[j]);
            j++;
        }
        int len = list.size();
        double res = 0;
        if (len % 2 == 0) {
            res = (double)(list.get(len/2) + list.get(len/2-1))/2;
        }else {
            res = (double)list.get(len/2);
        }
        return res;
    }
}