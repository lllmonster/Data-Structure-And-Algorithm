# Basic Knowledge
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
## String
```
s.substr(1,2);
s.find(c);
s.rfind(c);
s.erase(1);
s.replace(14,2,"ssjjjj");
s.insert(1, "lucky");
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

## Inheritance
The base class and the derived class.  
  ```
  class Daughter: public Mother
  {
    public:
      Daughter();
  }
  ```
  The derived class **does inherit** the constructor and deconstructor of the base class.  

## Protected Member  
`public` anywhere in the program can access this staff.  
`private` only inside the class has access to the variable.  
`protected` anything inside the class, and its friend, any child class have access to the variable.

## Polymorphism
use same function on different objects.

## Virtual Function
a template func

## Abstract Class & Pure Virtual Function
`virtual void func() = 0;` this is a pure virtual func.  
Any derived classes must need to override it.  

Abstract class is a class with pure virtual function.  

## Function Template
```
template <class bucky>
bucky addCrap(bucky a, bucky b) {
  return a + b;
}
```

## Class Template
```
template <class T>
class Bucky {
    T first, second;
    public:
      Bucky(T a, T b){
          first = a;
          second = b;
      }
      T bigger();
};

template <class T>
T Bucky<T>::bigger(){
    return (first>second?first:second);
}

int main () {
    Bucky <int> bo(1,2);
    cout << bo.bigger();
}
```

## Template Specialization
```
template <class T>
class Spunky{
  public:
    Spunky(T x) {
      cout << x << " is not a char" << endl;
    }
};

template<>
class Spunky<char>{
  public:
    Spunky(char x) {
      cout << x << " is a char" << endl;
    }
};
int main() {
  Spunky<int> so1(1);
  Spunky<double> so2(1.2);
  Spunky<char> so3('a');
}
```

## Exception Handling
```
int main() {
  try {
    int monsAge = 30;
    int sonsAge = 34;

    if (sonsAge > monsAge) {
      throw 99;
    }
  } catch(int x) {
    cout << "son cannot be older than mom, error number: " << x << endl;
  }
}
```

```
int main() {
  try {
    int up = 30;
    int down = 34;

    if (sonsAge > monsAge) {
      throw 99;
    }
  } catch(... ) {
    cout << "son cannot be older than mom, error number: " << x << endl;
  }
}
```

## File
```
#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    ofstream buckyFile;
    buckyFile.open("tuna.txt");

    // Another expression
    // ofstream buckyFile("chicken.txt");

    buckyFile << "I love tuna" << endl;
    buckyFile.close();

}
```
Custorm file structure  
`ofstream` : write  
`ifstream` : read (close file automatically)   
