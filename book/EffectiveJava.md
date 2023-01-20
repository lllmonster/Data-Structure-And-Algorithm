<!-- TOC -->

- [Note - Third Edition](#note---third-edition)
  - [TODO:](#todo)
  - [Chapter 2 - Creating and Destorying Objects](#chapter-2---creating-and-destorying-objects)
    - [Item 1 : Consider static factory methods instead of constructors](#item-1--consider-static-factory-methods-instead-of-constructors)
    - [Item 2: Consider a builder when faced with many constructor parameters](#item-2-consider-a-builder-when-faced-with-many-constructor-parameters)
    - [Item 3: Enforce the singleton property with a private constructor or an enum type](#item-3-enforce-the-singleton-property-with-a-private-constructor-or-an-enum-type)
    - [Item 4: Enforce noninstantiablility with a private constructor](#item-4-enforce-noninstantiablility-with-a-private-constructor)
      - [Item 5: Prefer dependency injection to hardwiring resources](#item-5-prefer-dependency-injection-to-hardwiring-resources)
      - [Item 6: Avoid creating unnecessay objects](#item-6-avoid-creating-unnecessay-objects)
      - [Item 7: Eliminate obsolete object references](#item-7-eliminate-obsolete-object-references)
      - [Item 8: Avoid finalizers and cleaners](#item-8-avoid-finalizers-and-cleaners)
      - [Item 9: Prefer try-with-resources to try-finally](#item-9-prefer-try-with-resources-to-try-finally)
  - [Chapter 3 - Methods Common to All Objects](#chapter-3---methods-common-to-all-objects)
    - [Item 10: Obey the general contract when overriding equals](#item-10-obey-the-general-contract-when-overriding-equals)
    - [Item 11: Always override hashcode when you override equals](#item-11-always-override-hashcode-when-you-override-equals)
      - [Item 12: Always override toString](#item-12-always-override-tostring)
      - [Item 13: Override clone judiciously](#item-13-override-clone-judiciously)
    - [Item 14: Consider implementing Comparable](#item-14-consider-implementing-comparable)
  - [Chapter 4 - Classes and Interfaces](#chapter-4---classes-and-interfaces)
      - [Item 15: Minimize the accessibilty of classes and members](#item-15-minimize-the-accessibilty-of-classes-and-members)
      - [Item 16: In public classes, use accessor methods, not public fields](#item-16-in-public-classes-use-accessor-methods-not-public-fields)
      - [Item 17: Minimize mutability](#item-17-minimize-mutability)
    - [Item 18: Favor composition and forwarding over inheritance](#item-18-favor-composition-and-forwarding-over-inheritance)
      - [Item 19: Design and document for inheritance or else prohibit it](#item-19-design-and-document-for-inheritance-or-else-prohibit-it)
      - [Item 20: Prefer interfaces to abstract classes.](#item-20-prefer-interfaces-to-abstract-classes)
      - [Item 21: Design interfaces for posterity](#item-21-design-interfaces-for-posterity)
      - [Item 22: Use interfaces only to define types](#item-22-use-interfaces-only-to-define-types)
      - [Item 23: Prefer class hierarchies to tagged classes](#item-23-prefer-class-hierarchies-to-tagged-classes)
      - [Item 24: Favor static member classes over nonstatic](#item-24-favor-static-member-classes-over-nonstatic)
      - [Item 25: Limit source files to a single top-level class](#item-25-limit-source-files-to-a-single-top-level-class)
  - [Chapter 5 - Generics](#chapter-5---generics)
      - [Item 26: Don't use raw types](#item-26-dont-use-raw-types)
      - [Item 27: Eliminate unchecked warnings](#item-27-eliminate-unchecked-warnings)
      - [Item 28 \& 29 \& 30: Prefer lists to arrays \& Favor generic types \& Favor generic methods](#item-28--29--30-prefer-lists-to-arrays--favor-generic-types--favor-generic-methods)
    - [Item 31: Use bounded wildcards to increase API flexibility](#item-31-use-bounded-wildcards-to-increase-api-flexibility)
      - [Item 32: Combine generics and varargs judiciously](#item-32-combine-generics-and-varargs-judiciously)
      - [Item 33: Consider typesafe heterogeneous containers](#item-33-consider-typesafe-heterogeneous-containers)
  - [Chapter 6 - Enums and Annotations](#chapter-6---enums-and-annotations)
      - [Item 34: Use enums instead of int constants](#item-34-use-enums-instead-of-int-constants)
      - [Item 35: Use instance fields instead of ordinals](#item-35-use-instance-fields-instead-of-ordinals)
    - [Item 36: Use EnumSet instead of bit fields](#item-36-use-enumset-instead-of-bit-fields)
    - [Item 37: Use EnumMap instead of ordinal indexing](#item-37-use-enummap-instead-of-ordinal-indexing)
    - [Item 38: Emulate extensible enums with interfaces](#item-38-emulate-extensible-enums-with-interfaces)
    - [Item 39: Prefer annotations to naming patterns](#item-39-prefer-annotations-to-naming-patterns)
      - [Item 40: Consistently use the Override annotation](#item-40-consistently-use-the-override-annotation)
      - [Item 41: Use marker interfaces to define types](#item-41-use-marker-interfaces-to-define-types)
  - [Chapter 7 - Lambdas and Streams](#chapter-7---lambdas-and-streams)
    - [Item 42: Prefer lambdas to anonymous classes](#item-42-prefer-lambdas-to-anonymous-classes)
      - [Item 43: Prefer method references to lambdas](#item-43-prefer-method-references-to-lambdas)
      - [Item 44: Favor the use of standard function interfaces](#item-44-favor-the-use-of-standard-function-interfaces)
      - [Item 45: Use streams judiciously](#item-45-use-streams-judiciously)
      - [Item 46: Pefer side-effect-free functions in streams](#item-46-pefer-side-effect-free-functions-in-streams)
      - [Item 47: Pefer Collection to Stream as a return type](#item-47-pefer-collection-to-stream-as-a-return-type)
      - [Item 48: Use caution when making streams parallel](#item-48-use-caution-when-making-streams-parallel)
  - [Chapter 8 - Methods](#chapter-8---methods)
      - [Item 49: Check parameters for validity](#item-49-check-parameters-for-validity)
    - [Item 50: Make defensive copies when needed](#item-50-make-defensive-copies-when-needed)
    - [Item 51: Design method signature carefully](#item-51-design-method-signature-carefully)
    - [Item 52: Use overloading judiciously](#item-52-use-overloading-judiciously)
      - [Item 53: Use varargs judiciously](#item-53-use-varargs-judiciously)
      - [Item 54: Return empty collections or arrays, not nulls](#item-54-return-empty-collections-or-arrays-not-nulls)
    - [Item 55: Return optionals judiciously](#item-55-return-optionals-judiciously)
      - [Item 56: Write doc comments for all exposed API elements](#item-56-write-doc-comments-for-all-exposed-api-elements)
  - [Chapter 9 - General Programming](#chapter-9---general-programming)
      - [Item 57: Minimize the scope of local variables](#item-57-minimize-the-scope-of-local-variables)
      - [Item 58: Prefer for-each loops to traditional for loops](#item-58-prefer-for-each-loops-to-traditional-for-loops)
      - [Item 59: Know and use the libraries](#item-59-know-and-use-the-libraries)
      - [Item 60: Avoid float and double if exact answers are required](#item-60-avoid-float-and-double-if-exact-answers-are-required)
      - [Item 61: Prefer primitive types to boxed primitives](#item-61-prefer-primitive-types-to-boxed-primitives)
      - [Item 62: Avoid strings where other types are more appropriate](#item-62-avoid-strings-where-other-types-are-more-appropriate)
      - [Item 63: Beware of performance of string concatenation](#item-63-beware-of-performance-of-string-concatenation)
      - [Item 64: Refer to objects by their interfaces](#item-64-refer-to-objects-by-their-interfaces)
      - [Item 65: Prefer interfaces to reflection](#item-65-prefer-interfaces-to-reflection)
      - [Item 66: Use native methods judiciously](#item-66-use-native-methods-judiciously)
      - [Item 67: Optimize judiciously](#item-67-optimize-judiciously)
      - [Item 68: Adhere to generally accpeted naming conventions](#item-68-adhere-to-generally-accpeted-naming-conventions)
  - [Chapter 10 - Exceptions](#chapter-10---exceptions)
      - [Item 69: Use exceptions only for exceptional conditions](#item-69-use-exceptions-only-for-exceptional-conditions)
      - [Item 70: Use checked exceptions for recoverable conditions and runtime exceptions for programming errors](#item-70-use-checked-exceptions-for-recoverable-conditions-and-runtime-exceptions-for-programming-errors)
      - [Item 71: Avoid unnecessay use of checked exceptions](#item-71-avoid-unnecessay-use-of-checked-exceptions)
    - [Item 72: Favor the use of standard exceptions](#item-72-favor-the-use-of-standard-exceptions)
      - [Item 73: Throw exceptions appropriate to the abstraction](#item-73-throw-exceptions-appropriate-to-the-abstraction)
      - [Item 74: Document all exceptions thrown by each method](#item-74-document-all-exceptions-thrown-by-each-method)
      - [Item 75: Include failure capture information in detail message](#item-75-include-failure-capture-information-in-detail-message)
      - [Item 76: Strive for failure atomicity](#item-76-strive-for-failure-atomicity)
      - [Item 77: Don't ignore exceptions](#item-77-dont-ignore-exceptions)
  - [Chapter 11 - Concurrency](#chapter-11---concurrency)
    - [Item 78: Synchronize access to shared mutable data](#item-78-synchronize-access-to-shared-mutable-data)
      - [Item 79: Avoid excessive synchronization](#item-79-avoid-excessive-synchronization)
      - [Item 80: Prefer executors, tasks, and streams to threads](#item-80-prefer-executors-tasks-and-streams-to-threads)
      - [Item 81: Prefer concurrency utilities to wait and notify](#item-81-prefer-concurrency-utilities-to-wait-and-notify)
      - [Item 82: Document thread safety](#item-82-document-thread-safety)
      - [Item 83: Use lazy initialization judiciously](#item-83-use-lazy-initialization-judiciously)
      - [Item 84: Don't depend on the thread scheduler](#item-84-dont-depend-on-the-thread-scheduler)
  - [Chapter 12 - Serialization](#chapter-12---serialization)
      - [Item 85: Prefer alternatives to Java serialization](#item-85-prefer-alternatives-to-java-serialization)
      - [Item 86: Implement Serizlizable with great caution](#item-86-implement-serizlizable-with-great-caution)
    - [Item 87: Consider using a custom serialized form](#item-87-consider-using-a-custom-serialized-form)
      - [Item 88: Write readObject methods defensively](#item-88-write-readobject-methods-defensively)
      - [Item 89: For instance control, prefer enum types to readResolve](#item-89-for-instance-control-prefer-enum-types-to-readresolve)
      - [Item 90: Consider serialization proxies instead of serialized instances](#item-90-consider-serialization-proxies-instead-of-serialized-instances)

<!-- /TOC -->
# Note - Third Edition

## TODO:
1. *AutoCloseable* object.


## Chapter 2 - Creating and Destorying Objects

### Item 1 : Consider static factory methods instead of constructors
Advantanges:
1. not required to create a new object each time they're invoked. 
2. can return an object of any subtype of their return type
3. the class of the returned obejct can vary from call to call as a function of the input parameters.

Disadvantages:
1. the class without public or protected constructors cannot be subclassed.

### Item 2: Consider a builder when faced with many constructor parameters
```Java
public class User
{
	//All final attributes
	private final String firstName; // required
	private final String lastName; // required
	private final int age; // optional
	private final String phone; // optional
	private final String address; // optional

	private User(UserBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	//All getter, and NO setter to provde immutability
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "User: "+this.firstName+", "+this.lastName+", "+this.age+", "+this.phone+", "+this.address;
	}

	public static class UserBuilder
	{
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;

		public UserBuilder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}
		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}
		//Return the finally consrcuted User object
		public User build() {
			User user =  new User(this);
			validateUserObject(user);
			return user;
		}
		private void validateUserObject(User user) {
			//Do some basic validations to check
			//if user object does not break any assumption of system
		}
	}
}
```

### Item 3: Enforce the singleton property with a private constructor or an enum type
```Java
// singleton with static factory
public class User {
    private static final User INSTANCE = new User();
    private User() {}
    public static User getInstance() {return INSTANCE;}
}
```

To make a singlton class *serializable* , declare all instance fields *transient* and provide a *readResolve* method.
```Java
private Object readResolve() {return INSTANCE;}
```

A third way to implement a singelton is to declare a single-element enum:
```Java
public enum User{
    INSTANCE;
}
```

### Item 4: Enforce noninstantiablility with a private constructor
```Java
public class Utility {
    private Utility() {
        throw new AssertionError();
    }
}
```

#### Item 5: Prefer dependency injection to hardwiring resources
#### Item 6: Avoid creating unnecessay objects
```Java
// Performance can be greatly improved
static boolean isRomanNumeral(String s) {
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
  + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}

// Reusing expensive object for improved performance
private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
  + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
static boolean isRomanNumberal(String s) {
    return ROMAN.matcher(s).matches();
}
```

#### Item 7: Eliminate obsolete object references

#### Item 8: Avoid finalizers and cleaners 
Just have your class implement AutoCloseable instead of writing a finalizer or cleaner. And requires its clients to invoke the close method on each instance when it's no longer needed.

#### Item 9: Prefer try-with-resources to try-finally
To be useable with this construct, a resource must implement the AutoClosable interface.
```Java
static String firstLineOfFile(String path) throws IOExcpetion{
    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```

## Chapter 3 - Methods Common to All Objects
### Item 10: Obey the general contract when overriding equals
```Java
@Override
public boolean equals(Object o) {
    if (o == this) {
        return true;
    }

    if (!(o instanceOf User)) {
        return false;
    }

    User uu = (User) o;
    return uu.name=name && uu.id==id;
}
```
Always override hashcode when you override equals.
Can use google's open source AutoValue framework / IDE to automatically generate these methods.

### Item 11: Always override hashcode when you override equals
```Java
@Override
publid int hashCode() {
    int result = Short.hashCode(name);
    result = 31*result + Short.hashCode(id);
    return result;
}
```
#### Item 12: Always override toString

#### Item 13: Override clone judiciously

### Item 14: Consider implementing Comparable
In Java 8
```Java
private static final Comparator<PhoneNumber> COMPARATOR = 
    comparingInt((PhoneNumber pn) -> pn.areaCode)
    .thenComparingInt(pn -> pn.prefix)
    .thenComparingInt(pn -> pn.lineNum);
public int compareTo(PhoneNumber pn) {
    return COMPARATOR.compare(this, pn);
}

// Comparator based on Comparator construction method
static Comparator<Object> hasCodeOrder = Comparator.comparingInt(o -> o.hashCode());
```

## Chapter 4 - Classes and Interfaces
#### Item 15: Minimize the accessibilty of classes and members
Instance fields of public classes should rarely be public
```Java
// Potential security hole
public static final Thing[] VALUES = {...};

// Alternative 1
private static final Thing[] PRIVATE_VALUES = {...};
public static final List<Thing> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```

#### Item 16: In public classes, use accessor methods, not public fields
#### Item 17: Minimize mutability
Five Rules:
1. Don't provide methods that modifies the object's state
2. Ensure that the class can not be extended
3. Make all fields final
4. Make all fields private
5. Ensure exclusive access to any mutable components

Advantages:
1. Immutable objects are simple
2. Immutable objects are inherently thread-safe; they require no synchronization and can be shared freely.

### Item 18: Favor composition and forwarding over inheritance
#### Item 19: Design and document for inheritance or else prohibit it
* Constructors must not invoke overridable methods.
* Neither clone nor readObject may invoke an overridable method.

#### Item 20: Prefer interfaces to abstract classes.
#### Item 21: Design interfaces for posterity
#### Item 22: Use interfaces only to define types
They should not be used merely to export constants.  


#### Item 23: Prefer class hierarchies to tagged classes
#### Item 24: Favor static member classes over nonstatic
There are four kinds of nested classes:
1. static member classes - public helper class
2. non-static member classes - adapter
3. anonymous classes
4. local classes

#### Item 25: Limit source files to a single top-level class


## Chapter 5 - Generics
#### Item 26: Don't use raw types

#### Item 27: Eliminate unchecked warnings
If you can't eliminate a warning, but you can prove that the code that provoked the warning is typesafe, then suppress the warning with an `@SuppressWarnings("unchecked")` annotation.

#### Item 28 & 29 & 30: Prefer lists to arrays & Favor generic types & Favor generic methods

### Item 31: Use bounded wildcards to increase API flexibility
Favor generic methods  
PECS - producer for *extends*, consumer for *super*  
All comparables and comparators are consumers.  


```Java
public void pushAll(Iterable<? extends E> src) {
    for (E e : src) {
        push(e);
    }
}

public void popAll(Collection<? super E> dst) {
    while (!isEmpty()) {
        dst.add(pop());
    }
}
```

#### Item 32: Combine generics and varargs judiciously
```Java
// Safe method with a generic varargs parameter
@SafeVarargs
static <T> List<T> flatten(List<? extends T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists) {
        result.addAll(list);
    }
    return result;
}
```

#### Item 33: Consider typesafe heterogeneous containers
```Java
// Typesafe heterogeneous container pattern -API
public class Favorites{
    public <T> void putFav(Class<T> type, T instance);
    public <T> T getFav(Class<T> type);
}

// Typesafe heterogeneous container pattern - client
public static void main(String[] args) {
    Favorites f = new Favorites();
    f.putFav(String.class, "JAVA");
    f.putFav(Integer.class, 0xcafebabe);
    f.putFav(Class.class, Favorites.class);

    String favString = f.getFav(String.class);
    int favInt = f.getFav(Integer.class);
    Class<?> favClass = f.getFav(Class.class);

}

// Typesafe heterogeneous container pattern - implementation
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();
    public <T> void putFav(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }
    public <T> T getFav(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
```

`Class.cast()` method is the dynamic analogue of Java's cast operator. It simply checks that its argument is an instance of the type represented by the     `Class` object.


## Chapter 6 - Enums and Annotations
#### Item 34: Use enums instead of int constants
```Java
//Enum type with data and behavior
public enum Planet {
    MERCURT(3.3, 2.4),
    VERNUS(4.9, 6.1),
    EARTH(6.0, 6.4);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;
    private static final double G = 6.8;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {return mass;}
    public double radius() {return radius;}
    public double surfaceGravity() {return surfaceGravity;}

}

public static void main(String[] args) {
    for (Planet p : Planet.values()) {
        // p
        // p.mass();
    }
}
```
```Java
// Enum type with constant-specific class bodies and data
public enum Operation {
    PLUS("+") {
        public double apply(double x, double y) {return x + y;}
    },
    MINUS("-") {
        public double apply(double x, double y) {return x - y;}
    },
    TIMES("*") {
        public double apply(double x, double y) {return x * y;}
    },
    DIVIDE("/") {
        public double apply(double x, double y) {return x / y;}
    };

    private final String symbol;
    Operation(String symbol) {this.symbol = symbol;}
    @Override public String toString() {return symbol;}
    public abstract double apply(double x, double y);
}

public static void main(String[] args) {
    for (Operation op : Operation.values()) {
        System.out.println("%f %s %f = %f%n", x, op, y, op.apply(x,y));
    }
}

// Implementing a fromString method on an enum type
private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e->e));
// Returns Operation for String, if any
public static Optional<Operation> fromString(String symbol) {
    return Optional.ofNullable(stringToEnum.get(symbol));
}
```

```Java
// The strategy enum pattern
enum PayrollDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;
    PayrollDay(PayType payType) {this.payType = payType;}
    PayrollDay() {this(PayType.WEEKDAY);}

    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }

    // The strategy enum type
    private enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MIN_PER_SHIFT ? 0 : (minsWorked - MIN_PER_SHIFT) * payRate / 2;
            }
        }
        WEEKEND {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };

        abstract int overtimePay(int mins, int payRate);
        private static final int MIN_PER_SHIFT = 8 * 60;
        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
```

#### Item 35: Use instance fields instead of ordinals
### Item 36: Use EnumSet instead of bit fields
```Java
// EnumSet - a modern replacement for bit fields
public class Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}
    
    // Any set could be passed in, but EnumSet is clearly best
    public void applyStyle(Set<Style> styles) {...}
}

text.applyStyle(EnumSet.of(Style.BOLD, Style.ITALIC));
```

### Item 37: Use EnumMap instead of ordinal indexing
```Java
// Native stream-based approach - unlikely to produce an EnumMap
Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle));
```
The above code's problem is that it chooses its own map implementation, and in practice it won't be an EnumMap, so it won't match the space and time performance of the version with the explicit EnumMap.

```Java
// Using a stream and an EnumMap to associate data with an enum
Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()));
```

### Item 38: Emulate extensible enums with interfaces 

while you cannot write an extensible enum type, you can emulate it by writing an interface to accompany a basic enum type that implements the interface.  

```java
public interface Operation {
    double apply(double x, double y);
}

public enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) {return x + y;}
    };

    private final String symbol;
    BasicOperation(String symbol) {this.symbol = symbol;}
    @Override public String toString() {return symbol;}
}

// Emulated extension enum
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {return Math.pow(x,y);}
    };

    private final String symbol;
    BasicOperation(String symbol) {this.symbol = symbol;}
    @Override public String toString() {return symbol;}
}
```

### Item 39: Prefer annotations to naming patterns 

```java
import java.lang.annotation.*;
/*
Indicates that the annotated method is a test method.
Use only on parameterless static methods.
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {}

public class Sample {
    @Test
    public static void m1() {} // Test should pass

    @Test
    public static void m2() {throw new RuntimeException();} // Test should fail

    @Test
    public void m3() {} // INVALID USE: nonstatic method

}
```

Now let's add support for tests that succeed only if they throw a particular exception. 
```java
// Annotation type with a parameter
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable> value();
}

public class Sample2 {
    @ExceptionTest(ArithmeticException.class)
    public static void m1() {
        throw new ArithmeticException();
    } // Test should pass

    @ExceptionTest(ArithmeticException.class)
    public static void m2() {
        throw new RuntimeException();
    } // Test should fail (wrong exception)

    @ExceptionTest(ArithmeticException.class)
    public static void m3() {} // shoudl fail (no exception)

}

```


```java
// Annotation type with array parameter
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();
}

public class Sample3 {
    @ExceptionTest({ArithmeticException.class, NullPointerException.class})
    public static void m1() {
        throw new ArithmeticException();
    } // Test should pass
}

```

As of java 8, there's another way to do multivalued annotations using `@Repeatable` meta-annotation.  

There's simply no reason to use naming patterns when you can use annotations instead. 

#### Item 40: Consistently use the Override annotation 
#### Item 41: Use marker interfaces to define types 

## Chapter 7 - Lambdas and Streams 
### Item 42: Prefer lambdas to anonymous classes 

```java
Collections.sort(words, (s1,s2) -> Integer.compare(s1.length(), s2.length()));
Collections.sort(words, comparingInt(String::length));
words.sort(comparingInt(String::length));
```

Lambda lack names and documentation; if a computation isn't slef-explanatory, or exceeds a few lines, don't put it in a lambda.  
You shoud rarely, if ever, serialize lambda.  
Don't use anonymous classes for function objects unless you have to create instances of types that aren't functional interfaces.  

#### Item 43: Prefer method references to lambdas

```java
map.merge(key, 1, (count, incr) -> count + incr);
map.merge(key, 1, Integer::sum);

// ()-> new TreeMap<K,V>
TreeMap<K,V>::new
// len -> new int[len]
int[]::new 
```

#### Item 44: Favor the use of standard function interfaces 
#### Item 45: Use streams judiciously 
#### Item 46: Pefer side-effect-free functions in streams  
#### Item 47: Pefer Collection to Stream as a return type 
#### Item 48: Use caution when making streams parallel 

## Chapter 8 - Methods 

#### Item 49: Check parameters for validity 

### Item 50: Make defensive copies when needed 
`Date` is obsolete and should no longer be used in new code. Use `Instant` instead.  
Do not use the `clone` method to make a defensive copy of a parameter whose type is subclassable by untrusted parties.  

### Item 51: Design method signature carefully 
* Choose method name carefully
* Don't go overboard in porviding convenience methods.  
* Avoid long parameter list 
* For parameter types, favor interfaces over classes 
* Prefer two element enum types to boolean parameters 

### Item 52: Use overloading judiciously
* the choice of which overloading to invoke is made at compile time 
* selection among overloaded methods is static, while selection among overridden methods is dynamic
* A safe, conservative policy is never to export two overloading with the same number of parameters 

#### Item 53: Use varargs judiciously
#### Item 54: Return empty collections or arrays, not nulls 
### Item 55: Return optionals judiciously 
* Optionals are similar in spirit to checked exceptions.
* Container types, including collections, maps, streams, arrays, and optionals should not be wrapped in optionals.  

```java
// using an optional to provide a chosen default value
Optional.of(s).orElse("no word");

// using an optional to throw a chosen exception
Optional.of(s).ofElseThrow(RuntimeException::new);

// using optional when you know there's a return value
Optional.of(s).get();
```

#### Item 56: Write doc comments for all exposed API elements 

## Chapter 9 - General Programming 
#### Item 57: Minimize the scope of local variables 
#### Item 58: Prefer for-each loops to traditional for loops 
#### Item 59: Know and use the libraries 
#### Item 60: Avoid float and double if exact answers are required 
use BugDecimal, int or long for monetary calculations.  
#### Item 61: Prefer primitive types to boxed primitives 
when you mix primitives and boxed primitives in an operation, the boxed primitive is auto-unboxed. But, slow    
#### Item 62: Avoid strings where other types are more appropriate  
#### Item 63: Beware of performance of string concatenation 
#### Item 64: Refer to objects by their interfaces 
#### Item 65: Prefer interfaces to reflection  
#### Item 66: Use native methods judiciously
It is rarely advisable to use native methods for imporved performance.  
#### Item 67: Optimize judiciously 
#### Item 68: Adhere to generally accpeted naming conventions 

## Chapter 10 - Exceptions 
#### Item 69: Use exceptions only for exceptional conditions 
#### Item 70: Use checked exceptions for recoverable conditions and runtime exceptions for programming errors 
#### Item 71: Avoid unnecessay use of checked exceptions 
### Item 72: Favor the use of standard exceptions 
* `IllegalArgumentException` - non-null parameter is inappropriate
* `IllegalStateException` - Object state is inappropriate for method invocation
* `NullPointerException` - parameter value is null where prohibited
* `IndexOutOfBoundsException` - Index parameter value is out of range
* `ConcurrentModificationException` - concurrent modification of an object has been detected where it is prohibited
* `UnsupportedOperationException` - Object does not support method  

#### Item 73: Throw exceptions appropriate to the abstraction 
Higher layers should catch lower-level exceptions.
#### Item 74: Document all exceptions thrown by each method 
#### Item 75: Include failure capture information in detail message
#### Item 76: Strive for failure atomicity
A failed method invocation should leave the object in the state that it was in piror to the invocation.   
#### Item 77: Don't ignore exceptions 

## Chapter 11 - Concurrency
### Item 78: Synchronize access to shared mutable data 
synchronization is not guaranteed to work unless both read and write operations are synchronized.  

```java
// Lock-free synchronization with java.util.concurrent.atomic
private static final AtomicLong nextSerialNum = new AtomicLong();
public static long generateSerialNumber() {
    return nextSerialNum.getAndIncrement();
}
```

#### Item 79: Avoid excessive synchronization 
#### Item 80: Prefer executors, tasks, and streams to threads 
#### Item 81: Prefer concurrency utilities to wait and notify 
#### Item 82: Document thread safety 
Lock fields should always be declared final.  
#### Item 83: Use lazy initialization judiciously 
#### Item 84: Don't depend on the thread scheduler 

## Chapter 12 - Serialization
#### Item 85: Prefer alternatives to Java serialization 
#### Item 86: Implement Serizlizable with great caution 
* A major cost of implementing Serializable is that it decreases the flexibility to change a class's implementation once it has been released. 

### Item 87: Consider using a custom serialized form 
Do not accept the default serialized form without first considering whether it is appropriate. Even if you decide serialized form is appropriate, you often must provide a `readObject` method to ensure invariants and security.   
Regardless of what serialized form you choose, decalre an explicit serial version UID in every serializable class you write.  
```java
private static final long serialVersionUID = randomLongValue;
```

#### Item 88: Write readObject methods defensively
#### Item 89: For instance control, prefer enum types to readResolve 
#### Item 90: Consider serialization proxies instead of serialized instances 