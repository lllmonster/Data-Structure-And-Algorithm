# Basic Knowledge

## Primitives
* boolean - 1 bit
* byte - signed 8 bits
* short - signed 16 bits
* char - unsigned 16 bits
* int - signed 32 bits
* long - signed 64 bits
* float - 32 bits
* double - 64 bits

Naming:
* camelCase
* only `static final` use UPPER_SNAKE_CASE
* be descriptive with naming

Descirptive Variable Names  
`private static final double KILOMETER_TO_MILE = 0.621371d;`

String is object, not primitive!
```
StringBuilder sb = new StringBuilder();
sb.append('a');
String text = sb.toString();
```

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

## Inheritance
No multiple inheritance. Classes extend from one and only one class.  

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

## Concurrency
* Race Condition  
When output is dependent upon the uncontrollable sequence of events. In some cases, it works (no bug), in some cases, it fails (bug) but in all cases, it suffers from a race condition.
* DeadLock  
Two or more resources are waiting for each other to finish and thus neither ever do finish.
* Resource Starvation  
When a process/thread is perpetually denied resources.  
* Atomicity  
An operation appears to occur to components of a system instantaneously.
* Mutual Exclusion  
For concurrent programs, mutual exclusion ensures that no two concurrent process/threads are in a critical section at the same time.   
* Locks  
A synchronization mechanism to limit access to a shared resource; a way of implementing mutual exclusion.  
* Semaphores  
A particular type of Lock. Records how many units of a resource are available in conjunction with providing a safe means of adjusting the record and potentially waiting for resources to become available.  

## Threads

## Function in Java 8
Related Changes:  
* final
* default / static interface methods
* Lambda Expressions  
(parameters)->expression  
(parameters)->{statements;}
* Streams API  
* DataTime API

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
