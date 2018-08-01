## String
In some languages (like C++), the string is mutable. That is to say, you can modify the string just like what you did in an array.   
```
#include <iostream>

using namespace std;

int main()
{
    // 1. initialize
    string s1 = "hello";
    string s2 = s1;
    string s3(s1);
    
    // 2. compare
    if (s1 == "hello"){}
    if (s1.compare("hello")){}
    
    // 3. find 
    s1.find('o');
    s1.rfind('o');
    
    // 4. get substring
    s1.substr(1,2);
}
```
