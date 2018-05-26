# String
A string is an array of `unicode character`.
### Basic Operation
```
// length
String s = "morning";
StringBuilder str = new StringBuilder(s);
len = str.length();

str.reverse();//reverse

str.toString();//convert to String

s == null || s.isEmpty(); //judge if exists or empty
```
### Compare Function
*Problem:* Can we use "==" to compare two strings?
1. C++ support `operator overloading`, so we can use "==";
2. Java doesn't support `operator overloading`, we cannot use "==" to compare two strings. When we use "==", it actually compares whether those two objects are the same object.

```
public class Main {
  public static void main(Sting[] args) {
    String s1 = "HelloWorld";
    String s2 = s1; // s2 is another reference to s1
    String s3 = new String(s1) // s3 is a copy of s1
    (s1 == "HelloWorld"); // true since string is immutable and s1 is binded to "HelloWorld"
    (s2 == s1); // true since s1 and s2 is the reference of the same object
    (s3 == s1); // false since s3 is refered to another new object
    // we use 'equals' or 'compareTo'
    s1.equals(s3); // true
    s1.compareTo(s3) == 0; // true
  }
}
```
### Immutable or Immutable
In C++, the string is `mutable`. That is to say, you can modify the string just like what you did in an array.  
In Java, the string is `immutable`. This feature will bring several problems.
```
// string extra operations
String s1 = "Hello World";
// 1. concatenate
s1 += "!";
// 2. find
s1.charAt(0);// H
s1.indexOf('o');//4, the position of first 'o'
s1.lastIndexOf('o');//7, the position of last 'o'
// 3. get substring
s1.substring(6,11); // World
```
We should be aware of `the time complexity` of these built-in operations.  
The finding operation and subString operation's time complexity is O(n).  

When we do `concatenate` operation, we create a new string. Since the string is immutable, concatenation works by first allocating enough space for the new string, copy the contents from the old string and append to the new string. Therefore, the time complexity is O(n^2).

If we want string to be mutable, there are some substitutions:
1. covert it to a char array
```
String s = "Hello World";
char[] str = s.toCharArray();
str[5] = ','; // str becomes "Hello,World"
```
2. If we have to concatenate strings often, it will be better to use some other data structures like `StringBuilder`. O(n) time complexity.
```
int n = 10000;
StringBuilder str = new StringBuilder();
for (int i = 0; i < n; i++) {
  str.append("hello");
}
String s = str.toString();
```
