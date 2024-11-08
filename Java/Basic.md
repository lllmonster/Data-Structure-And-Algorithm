<!-- TOC -->

- [Basic Knowledge](#basic-knowledge)
  - [Common Usage](#common-usage)
    - [Sort](#sort)
  - [Primitives](#primitives)
    - [Convert](#convert)
    - [String](#string)
    - [Array](#array)
    - [Stack](#stack)
    - [HashMap](#hashmap)
  - [Object Oriented Programming](#object-oriented-programming)
  - [Keyword](#keyword)
    - [Static](#static)
    - [Transient](#transient)
    - [InstanceOf](#instanceof)
    - [Volatile](#volatile)
  - [Inheritance](#inheritance)
  - [Polymorphism](#polymorphism)
  - [Abstract Class](#abstract-class)
  - [Object Class methods](#object-class-methods)
    - [Shallow Copy VS Deep Copy](#shallow-copy-vs-deep-copy)
  - [Interface](#interface)
  - [Exception](#exception)
  - [Generics](#generics)
  - [Collections](#collections)
    - [Sort \& Reverse \& Fill \& addAll](#sort--reverse--fill--addall)
    - [Frequency \& disjoint](#frequency--disjoint)
  - [Concurrency](#concurrency)
  - [Function in Java 8](#function-in-java-8)
    - [HashMap.merge()](#hashmapmerge)
    - [HashMap.computeIfAbsent](#hashmapcomputeifabsent)
    - [java.util.IntSummaryStatistics](#javautilintsummarystatistics)
  - [Design Patterns](#design-patterns)
  - [Testing](#testing)
  - [Library](#library)
  - [Framework](#framework)
    - [Aspect-Oriented Programming](#aspect-oriented-programming)
      - [What is Aspect-Oriented Programming](#what-is-aspect-oriented-programming)

<!-- /TOC -->
# Basic Knowledge

## Common Usage
### Sort
`Arrays.sort(arr)`  
`Collections.sort(list)`  
`Collections.sort(list, Collections.reverseOrder());`  

* Custom sort
`Arrays.sort(arr, (a,b) -> a-b);`


## Primitives
* boolean - 1 bit
* byte - signed 8 bits
* short - signed 16 bits
* char - unsigned 16 bits
* int - signed 32 bits
* long - signed 64 bits
* float - 32 bits
* double - 64 bits

Naming Principle:
* camelCase
* only `static final` use UPPER_SNAKE_CASE
* be descriptive with naming

Descirptive Variable Names  
`private static final double KILOMETER_TO_MILE = 0.621371d;`
```
NOTE:  `static final` is only for primitive types and immutable types, like int/string. Never use `static final` for Array which is mutable and can be modified. It's not safe.
```

### Convert 
* Bit:  
`>>` is arithmetic shift right, `>>>` is logical shift right

* Character to int  
`int a = Character.getNumbericValue(c);`

* Binary - int  
`String bit = Integer.toBinaryString(int);`  
`int a = Integer.parseInt(bit);`

* Interger[] to list  
`Arrays.asList(arr)`  

* int[] to list  
`List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());`

* list to String[]  
`String[] newarr = list.toArray(new String[list.size()]);`

* int[] to Integer[]  
`Integer[] input = Arrays.stream(arr).boxed().toArray(Integer[]::new);`

* Integer[] to int[]  
`int[] arr = Arrays.stream(input).mapToInt(Integer::intValue).toArray();`

* char[] to String  
`String s = String.valueOf(c);`  






### String

String is object, not primitive!
```Java
StringBuilder sb = new StringBuilder();
sb.append('a');
String text = sb.toString();
```

Common Method
```Java
String str = "hello";
str.startsWith("he");
str.endsWith("lo");
str.indexOf('e');
str.replace('h','g');
str.trim();
```

### Array
Arrays, zero based. Cannot use pointer to increment.
```
int[] arr = new int[100];
int[] arr = new int[] {1,2,3};
int size = arr.length;

// multidimensional Arrays
int[][] multi = new int[100][];
for (int i = 0; i < multi.length; i++) {
  multi[0] = new int[100];
}
int[][] multi = new int[] {{1,2},{3,4}};

// Arrays object
int a[] = new int[10];
Arrays.fill(a, 2);
Random random = new Random();
a[random.nextInt(10)] = random.nextInt();
Arrays.sort(a);
```

### Stack
`Object push(Object element)`  
`Object pop()` - Removes and return the top element of the stack  
`Object peek()` - Returns the element on the top of the stack but not remove it  
`boolean empty()`  
`int search(Object element)` - If the element is found, it returns the position from the top of the stack. Else, it returns -1. 

### HashMap
**To Use an Object as a key in HashMap, the key Object must override the hashCode and equals method.**
```Java
public class HashMapSample {
    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<Person, String>();
        map.put(new Person(100, "name 100"),  "this is person 100");
        map.put(new Person(200, "name 200"),  "this is person 200");

        Person key = new Person(100, "new name 100");
        if (map.containsKey(key)) {
            System.out.println(map.get(key));
        } else {
            System.out.println("NOT FOUND");
        }
    }
}

class Person {
    int id;
    String name;

    public Person (int id, String name) {
        this.id = id;
        this.name =  name;
    }

    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return person.id == this.id;
        }
        return false;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

```

## Object Oriented Programming
Immutable Objects:  
use `final` keyword. For class, they cannot be subclassed. For methods, they cannot be overridden.    

**Class Structure**
```

// class signature
public class Employee {

	// static variable
	private static final double DEFAULT_SALARY = 5000d;

	// static method
	public static Employee construct(String name, double salary) {
		return new Employee(name, salary);
	}

	// instance variable
	private final String name;
	private final double salary;

	// constructors
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	// instance methods
	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
}

```

**Encapsulation Constructs**  

Access Privileges  
* public - the class is accessible to everyone everywhere
* default - the class is only accessible from other classes within the same package.
* protected - be accessible to the class itself, everyone within the same package, and any subclass.
* private - be accessible only the class itself.  

## Keyword

### Static 

**What is Static Variable in Java**

The keyword static means that the particular member belongs to a type itself, rather than to an instance of that type.

Static variable is a variable which belongs to the class and initialized only once at the start of the execution.

* It is a variable which belongs to the class and not to object (instance)
* Static variables are initialized only once, at the start of execution. These variables will be initialized first, before the initialization of any instance variables.
* A single copy to be shared by all instance of the class
* A static variable can be accessed directly by the class name and doesn't need any object

**What is Static Method in Java**

Static method is a method which belongs to the class and not to the object. A static method can access only static data.

* It is a method which belongs to the class and not to the object (instance)
* A static method can access only static data. It can not access non-static data (instance variables)
* A static method can call only other static methods and cannot call a non-static method from it
* A static method can be accessed directly by the class name and doesn't need any object
* A static method cannot refer to "this" or "super" keywords in anyway.

static is a non-access modifier in Java which is applicable for the following:

blocks \\ variables \\ methods \\ nested classes    

when a member is declared static, it can be accessed before any objects of its class are created, and without reference to any object.

```java
public class Test{

     public static void main(String []args){
         Student s1 = new Student();
         s1.showData();
         Student s2 = new Student();
         s2.showData();
         Student.b++;
         s1.showData();
     }
}

class Student {
int a = 0; //initialized to zero
static int b = 0; //initialized to zero only when class is loaded not for each object created.

  Student(){
   //Constructor incrementing static variable b
   b++;
  }

   public void showData(){
      System.out.println("Value of a = "+a);
      System.out.println("Value of b = "+b);
   }
//public static void increment(){
//a++;
//}

}

```

output:

```
Value of a = 0
Value of b = 1
Value of a = 0
Value of b = 2
Value of a = 0
Value of b = 3
```

### Transient

Transient is a variables modifier used in *serialization*. At the time of serialization, if we don't want to save value of a particular variable in a file, then we use *transient* keyword. When JVM comes across transient keyword, it ignores original value of the variable and save default value of that variable data type.

**transient and static**: Since *static* fields are not part of state of the object, there's no use/impact of using *transient* keyword with static variables. 

**transient and final**: final variables are directly serialized by their values, so there is no use/impact of declaring final variable as transient.

```java
public class TransientExample {

	public static void main(String[] args) throws Exception {
		Student s1 = new Student(211, "ravi", 22);// creating object
		// writing object into file
		FileOutputStream f = new FileOutputStream("f.txt");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(s1);
		out.flush();

		out.close();
		f.close();
		// decompress
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("f.txt"));
		Student s = (Student) in.readObject();
        // output: 211 ravi 0
		System.out.println(s.id + " " + s.name + " " + s.age);
		in.close();
	}
}

class Student implements Serializable {
	int id;
	String name;
	transient int age;// Now it will not be serialized

	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
```

### InstanceOf
It's a keyword that is used for checking if a reference variable is containing a given type of object reference or not.

### Volatile
Volatile keyword is used to modify the value of a variable by different threads. It is also used to make classes thread safe. It means that multiple threads can use a method and instance of the classes at the same time without any problem. 

## Inheritance

No multiple inheritance. Classes extend from one and only one class.  
`extends` keyword is used. 

## Polymorphism  
Many distinct types referenced by their shared supertype.  
Java has covariant arrays.

## Abstract Class
Marking a class `abstract` allows you to define functionality that can be leveraged by subclasses without allowing code to directly instantiate the type.
```
public abstract class Employee {

	private final String name;
	private final double salary;

	protected Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
}
```
```
public class Programmer extends Employee {
	private final String languagePrefer;

	public Programmer(String name, double salary, String languagePrefer) {
		super(name, salary);
		this.languagePrefer = languagePrefer;
	}

	public String getLanguagePrefer() {
		return languagePrefer;
	}

	@Override
	public String toString() {
		return String.format("Name -  %s %nSalary - %.2f %nLanguage - %s",
						getName(), getSalary(), getLanguagePrefer());

	}
}
```

## Object Class methods
`getClass()` : return the Class class instance  
`hashCode()` : return a hash code value for this object  
`equals(Object obj)` : return true if this object equals to obj  
`clone()` : return a clone of this object  
`toString()` : return a String  
`notify()` : wakes up a thread listening for this objects moniotr.  
`wait()` : waits the current thread until another calls one of the notify methods.  
`finalize()` : called when no more references exist. Never override this.

### Shallow Copy VS Deep Copy

The Shallow Copy is the approach when we only field values and therefore the copy might be dependent on the original object.

In the deep copy approach, we make sure that all the objects in the tree are deeply copied, so the copy isn't dependent on earlier existing object that might ever change.

Example

```java
public class Test{

     public static void main(String []args){
        Address address = new Address("Downing St 10", "London", "England");
        User pm = new User("Prime", "Minister", address);
     	// shallow copy example
        User shallowCopy = new User(
            pm.getFirstName(), pm.getLastName(), pm.getAddress());
        printCopy(shallowCopy);
        address.setCountry("Great Britain");
        printCopy(shallowCopy);
		// deep copy example
        Address address1 = new Address("Downing St 10", "London", "England");
        User pm1 = new User("Prime", "Minister", address1);
        User deepCopy = new User(pm1);
        printCopy(deepCopy);
        address.setCountry("Great Britain");
        printCopy(deepCopy);
        
     }
    
    public static void printCopy(User user) {
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getAddress().getStreet() + " " +
        user.getAddress().getCity() + " " +
        user.getAddress().getCountry());
    }
}

class Address {
 
    private String street;
    private String city;
    private String country;
 
    // standard constructors, getters and setters
    Address(String street, String city, String country) {
        this.street = street;
        this.city = city;
        this.country = country;
    }
    // constructor for deep copy
    public Address(Address that) {
        this(that.getStreet(), that.getCity(), that.getCountry());
    }
    
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
	
}

class User {
 
    private String firstName;
    private String lastName;
    private Address address;
 
    // standard constructors, getters and setters
    User(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    // constructor for deep copy
    public User(User that) {
        this(that.getFirstName(), that.getLastName(), new Address(that.getAddress()));
    }
    public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		
}
```

Output

```
Prime
Minister
Downing St 10 London England
Prime
Minister
Downing St 10 London Great Britain
Prime
Minister
Downing St 10 London England
Prime
Minister
Downing St 10 London England
```

The above examples look easy, but sometimes they don't apply as a solution when we can't add an additional constructor or override the clone method. In this case, we can use an external library.

`````` java
// Apache Commons Lang
User deepCopy = (User) SerializationUtils.clone(pm);

//Json Serialization with Gson
User deepCopy = gson.fromJson(gson.toJson(pm), User.class);

// Json Serialization with Jackson
User deepCopy = objectMapper
      .readValue(objectMapper.writeValueAsString(pm), User.class);
``````

## Interface
Think of interfaces as higher level templates than classes. You're creating a type where you do not care about the implementation (Class) but want to express desired functionality.
```
public interface StringSplitter {
  String[] split(String input, String by);
}
```
**Interface Particulars**  
* All methods are public.   
* Interfaces cannot have default implementations.  
* Interface cannot have instance variables, but can have static final variables.  
* Interfaces can extend one another.  
* Classes can implement multiple interfaces.  

## Exception

## Generics
Enable code reuse in classes / interfaces. As methods have parameters which enable code reuse, generics enable the creation of classes / interfaces which can reuse the same code with different input.  

## Collections
### Sort & Reverse & Fill & addAll
```Java
List<String> list = new ArrayList<>();
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.reverse(list);
Collections.copy(desc, src);
Collections.fill(list, 'X');
Collections.addAll(list, new String[2]);
```
### Frequency & disjoint
```Java
Collections.frequency(list, "hello");
Collections.disjoint(l1, l2);
```

## [Concurrency](./Concurrency.md)


## Function in Java 8
Related Changes:  
* final
* default / static interface methods
* Lambda Expressions  
(parameters)->expression  
(parameters)->{statements;}
* Streams API  
* DataTime API
  
### HashMap.merge()

[Ref](https://www.topjavatutorial.com/java-8/java-8-map-merge/)

```Java
package com.topjavatutorial;
 
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
 
public class TestMap4 {
 
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, null);
        System.out.println(map); // {1=1, 2=2, 3=null}
 
        map.merge(1, 10, (x, y) -> x + y);
        // key 1 is present, so new value 10 will be added to previous value 1
        System.out.println(map); // {1=11, 2=2, 3=null}
 
        map.merge(2, 10, (x, y) -> x < y ? x : y);
        // Previous value for key=2 is less than new value. So, the old value
        // remains as per the BiFunction
        System.out.println(map); // {1=11, 2=2, 3=null}
 
        map.merge(3, 10, (x, y) -> x * y);
        // The old value for key=3 is null . But its not null * 3, the value 10
        // will be added for the key
        System.out.println(map); // {1=11, 2=2, 3=10}
 
        map.merge(4, 10, (x, y) -> x / y);
        // key=4 is not in map. So, BiFunction not evaluated, the element is
        // added to map
        System.out.println(map); // {1=11, 2=2, 3=10, 4=10}
 
        map.merge(1, 10, (x, y) -> null);
        // Since the BiFunction results in null, the element will be removed
        // from map
        System.out.println(map); // {2=2, 3=10, 4=10}
 
    }
 
}
 
Output :
{1=1, 2=2, 3=null}
{1=11, 2=2, 3=null}
{1=11, 2=2, 3=null}
{1=11, 2=2, 3=10}
{1=11, 2=2, 3=10, 4=10}
{2=2, 3=10, 4=10}
```

### HashMap.computeIfAbsent
To implement a multi-value map, Map<K,Collection<V>>,supporting multiple values per key:
`map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);`

Example:
```Java
public static Map<String, List<String>> convertListToMultiValueMap(List<Object> myObjs) {
    Map<String, List<String>> map = new HashMap<>();
    myObjs.forEach(obj -> {
        map.computeIfAbsent(obj.getId(), key -> new ArrayList<>()).add(obj.getValue());
    });
    return map;
}
```

Explanation:
The default implementation is equivalent to the following steps for this map, then returning the current value or null if nowabsent:  
```Java
 if (map.get(key) == null) {
     V newValue = mappingFunction.apply(key);
     if (newValue != null)
         map.put(key, newValue);
 }


```


### java.util.IntSummaryStatistics
```Java
import java.util.IntSummaryStatistics;

public double average(int[] salary) {
    IntSummaryStatistics stat = Arrays.stream(salary).boxed().collect(Collectors.summarizingInt(i -> i));
    return (double)(stat.getSum() - stat.getMax() - stat.getMin()) / (stat.getCount() - 2);
}
```
## Design Patterns
* Factory Pattern  
A factory class decouples the user from the implementing classes.  
* Builder Pattern  
A builder is used to construct complicated objects in steps.  
* Delegate Pattern
Known as composite pattern. The delegate pattern is when desired functionality is "delegated" to another object which has already implemented the functionality.   
* Decorator Pattern  
It is a dynamic way to add behavior to an entity at runtime by using the Delegate pattern.  
* Singleton Pattern  
Used when you want a single instance of class.


## Testing
JUnit - Framework for Unit Testing  

## Library
* Guava
* Jackson
* Mockito
* Junit

## Framework

### Aspect-Oriented Programming

#### What is Aspect-Oriented Programming

An aspect is a common feature that's typically scattered across methods, classes, object hierarchies, or even entire object models. It is behavior that looks and smells like it should have structure, but  you can't find a way to express this structure in code with traditional object-oriented techniques.

For example, metrics is one common aspect. To generate useful logs from your application, you have to sprinkle informative messages throughout your code. However, metrics is something that your class or object model really shouldn't be concerned about. After all, metrics is irrelevant to your actual application: it doesn't represent a customer or an account, and it doesn't realize a business rule. It's simply orthogonal.

As a development methodology, AOP recommends that you abstract and encapsulate crosscutting concerns.

For example, let's say you wanted to add code to an application to measure the amount of time it would take to invoke a particular method. In plain Java, the code would look something like the following:

```java
public class BankAccountDAO
{
  public void withdraw(double amount)
  {
    long startTime = System.currentTimeMillis();
    try
    {
      // Actual method body...
    }
    finally
    {
      long endTime = System.currentTimeMillis() - startTime;
      System.out.println("withdraw took: " + endTime);
    }
  }
}
```

This approach to metrics is very difficult to maintain, expand, and extend, because it's dispersed throughout your entire code base. And this is just a tiny example. In many cases, OOP may not always be the best way to add metrics to a class.

AOP gives you a way to encapsulate this type of behavior functionality. It allows you to add behavior such as metrics "around" your code.