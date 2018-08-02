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
## Pointer
```
int main()
{
    int fish = 5;
    cout << $fish << endl;

    int *fishPointer;
    fishPointer = &fish;
    cout << fishPointer << endl;

    return 0;
}

```
## Pass By Reference
```
#include <iostream>

using namespace std;

void passByValue(int x);
void passByReference(int *x);

int main()
{
    int a = 5;
    int b = 5;

    passByValue(a);
    passByReference(&b);

    cout << "passByValue is " << a << endl;
    cout << "passByReference is " << b << endl;
}

void passByValue(int x) {
    x = 99;
}

void passByReference(int *x) {
    *x = 66;
}
```

## sizeof()
char : 1 byte  
int : 4 bytes      
float : 4 bytes    
double: 8 bytes  
```
int main()
{
    double a[10];

    cout << sizeof(a) << endl;
    cout << sizeof(a) / sizeof(a[0]) << endl;
}
```

## Constant Object
`const`: It's unable to modify.  
const object need const func.  
```
void Sally::print() const{
    cout << "cost func print" << endl;
}
```

## Member Initialization
```
Sally::Sally(int a, int b)
: regVar(a),
constVar(b)
{
}
```

## Composition
using other class inside the class.  

## Friend Function
## This Pointer
`this` stores the address of the object.  
`h`  
`this->h`  
`(*this).h`

## Operator Overloading
fuction with the same name, but with different parameters.  
