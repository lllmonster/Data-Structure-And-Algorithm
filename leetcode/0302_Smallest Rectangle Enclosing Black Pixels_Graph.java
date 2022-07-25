/**
Similar with 200. Number of Island */

/**
Linear Search - o(mn),o(1)*/
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int t = x, b = x;
        int l = y, r = y;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (image[i][j] == '1') {
                    t = Math.min(t,i);
                    b = Math.max(b,i);
                    l = Math.min(l,j);
                    r = Math.max(r,j);
                }
            }
        }
        return (r-l+1)*(b-t+1);
    }
}

/**
dfs */
class Solution {
    private int t,b,l,r;
    public int minArea(char[][] image, int x, int y) {
        t = b = x;
        l = r = y;
        dfs(image, x, y);
        
        return (b-t+1) * (r-l+1);
    }
    
    private void dfs(char[][] image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[0].length
           || image[x][y] == '0') 
            return;
        image[x][y] = '0';
        t = Math.min(t,x);
        b = Math.max(b,x);
        l = Math.min(l,y);
        r = Math.max(r,y);
        dfs(image,x-1,y);
        dfs(image,x+1,y);
        dfs(image,x,y-1);
        dfs(image,x,y+1);
    }
}

/**
binary search - normal 
O(mlogn+nlogm)*/

class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int l = searchLeftCols(image, 0, y, 0, m);
        int r = searchRightCols(image, y+1, n, 0, m);
        int t = searchTopRows(image, 0, x, l, r);
        int b = searchBottomRows(image, x+1, m, l, r);
        
        return (b-t) * (r-l);
    }
    
    private int searchLeftCols(char[][] image, int i, int j, int t, int b) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = t;
            while (k < b && image[k][mid] == '0') k++;
            if (k < b) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
    
    private int searchRightCols(char[][] image, int i, int j, int t, int b) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = t;
            while (k < b && image[k][mid] == '0') k++;
            if (k < b) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }
    
    private int searchTopRows(char[][] image, int i, int j, int l, int r) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = l;
            while (k < r && image[mid][k] == '0') k++;
            if (k < r) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
    
    private int searchBottomRows(char[][] image, int i, int j, int l, int r) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = l;
            while (k < r && image[mid][k] == '0') k++;
            if (k < r) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }
}

/**
binary search - optimal 
O(mlogn+nlogm)*/
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int l = searchCols(image, 0, y, 0, m, true);
        int r = searchCols(image, y+1, n, 0, m, false);
        int t = searchRows(image, 0, x, l, r, true);
        int b = searchRows(image, x+1, m, l, r, false);
        
        return (b-t) * (r-l);
    }
    
    private int searchCols(char[][] image, int i, int j, int t, int b, boolean reverse) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = t;
            while (k < b && image[k][mid] == '0') k++;
            if (k < b == reverse) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
    
    private int searchRows(char[][] image, int i, int j, int l, int r, boolean reverse) {
        if (i == j) return i;
        while (i != j) {
            int mid = (i+j)/2;
            int k = l;
            while (k < r && image[mid][k] == '0') k++;
            if (k < r == reverse) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}

