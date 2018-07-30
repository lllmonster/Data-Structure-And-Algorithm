# Basic
## Main Func  
```
#include <iostream>

using namespace std;

int main()
{
    int a;
    cout << "Hello" << endl;
    cin >> a;
    return 0;
}
```

## Variables
```
int x = 1;
```

## Functions
Use prototype function or Put the func before main func.  
```
#include <iostream>

using namespace std;

void print();

int main()
{
    print();
    return 0;
}

void print() {
    cout << "amazing print" << endl;
}
```
