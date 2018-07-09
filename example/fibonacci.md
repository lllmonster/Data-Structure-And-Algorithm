## recursive
time complexity: O(2^n)  
sapce: O(1)
```
public class Solution{
    public static int fabonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fabonacci(n-1) + fabonacci(n-2);
    }
    public static void main(String []args){
        System.out.println("Program start");
        int ans = fabonacci(9);
        System.out.println(ans);
    }
}
```

## dynamic programming
time complexity: O(n)  
sapce: O(n)  
```
public class Solution{
    public static int fabonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] f = new int[n + 1];
        f[0] = 0; f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
    public static void main(String []args){
        System.out.println("Program start");
        int ans = fabonacci(9);
        System.out.println(ans);
    }
}
```

## dynamic programming - space optimized
time complexity: O(n)  
sapce: O(1) 
```
public class Solution{
    public static int fabonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int a = 0, b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
    public static void main(String []args){
        System.out.println("Program start");
        int ans = fabonacci(9);
        System.out.println(ans);
    }
}
```
