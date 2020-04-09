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
```
NOTE:  `static final` is only for primitive types and immutable types, like int/string. Never use `static final` for Array which is mutable and can be modified. It's not safe.
```

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

## Modifier

### Static 

#### What is Static Variable in Java

Static variable is a variable which belongs to the class and initialized only once at the start of the execution.

* It is a variable which belongs to the class and not to object (instance)
* Static variables are initialized only once, at the start of execution. These variables will be initialized first, before the initialization of any instance variables.
* A single copy to be shared by all instance of the class
* A static variable can be accessed directly by the class name and doesn't need any object

#### What is Static Method in Java

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

### Synchronization
Multi-threaded programs may often come to a situation when multiple threads try to access the same resources and finally produce erroneous and unforeseen results.
Synchronization method make sure that only thread can access the resource at a given point of time.
```java
synchronized(sync_object)
{
   // Access shared variables and other shared resources
}
```

## Threads

### Thread Local

#### What is Thread Local?

Thread Local can be considered as a scope of access, like a request scope or session scope. It's a *thread* scope. You can set any object in Thread Local and this object will be *global* and *local* to the specific thread which is accessing this object.

* Values stored in Thread Local are *global* to the thread, meaning that they can be accessed from anywhere inside that thread. If a thread calls methods from several classes, then all the methods can see the Thread Local variable set by other methods. The value need not be passed explicitly. It's like how you use global variables.
* Values stored in Thread Local are *local* to the thread, meaning that each thread will have its own Thread Local variable. One thread cannot access/modify other thread's Thread Local variables.

#### When to use Thread Local?

Consider you have a Servlet which calls some business methods. You have a requirement to generate a unique transaction id for each and every request this servlet process and you need to pass this transaction id to the business methods, for logging purpose. One solution would be passing this transaction id as a parameter to all the business methods. But this is not a good solution as the code is redundant and unnecessary.

To solve that, you can use Thread Local. You can generate a transaction id (either in servlet or better in a filter) and set it in the Thread Local. After this, whatever the business method, that this servlet calls, can access the transaction id from the thread local.

This servlet might be servicing more than one request at a time. Since each request is processed in separate thread, the transaction id will be unique to each thread local and will be accessible from all over the thread's execution (global).

#### How to use Thread Local?

```java
public class Context {
    private String transactionId = null;
    public void setTransactionId(String id) {
        this.transactionId = id;
    }
    public String getTransactionId() {
        return this.transactionId;
    }
}

public class MyThreadLocal {
    public static final ThreadLocal userThreadLocal = new ThreadLocal();
    public static void set(Context user) {
        userThreadLocal.set(user);
    }
    public static Context get() {
        return userThreadLocal.get();
    }
    public static void unset() {
        userThreadLocal.remove();
    }
}
public Demo extends Thread {
    public static void main(String args[]) {
        Thread t1 = new Demo();
        t1.start();
        Thread t2 = new Demp();
        t2.start();
    }
    @Override
    public void run() {
        Context cxt = new Context();
        cxt.setTransactionId("id");
        MyThreadLocal.set(cxt);
        new BusinessService().businessMethod();
        MyThreadLocal.unset();
    }
}
public class BusinessService {
    public void businessMethod() {
        Context cxt = MyThreadLocal.get();
        System.out.println(cxt.getTransactionId());
    }
}
```



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