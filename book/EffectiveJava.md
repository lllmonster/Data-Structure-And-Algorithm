<!-- TOC -->

- [Note - Third Edition](#note---third-edition)
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
            - [Item 28 & 29 & 30: Prefer lists to arrays & Favor generic types & Favor generic methods](#item-28--29--30-prefer-lists-to-arrays--favor-generic-types--favor-generic-methods)
        - [Item 31: Use bounded wildcards to increase API flexibility](#item-31-use-bounded-wildcards-to-increase-api-flexibility)
            - [Item 32: Combine generics and varargs judiciously](#item-32-combine-generics-and-varargs-judiciously)
            - [Item 33: Consider typesafe heterogeneous containers](#item-33-consider-typesafe-heterogeneous-containers)
    - [Chapter 6 - Enums and Annotations](#chapter-6---enums-and-annotations)
            - [Item 34: Use enums instead of int constants](#item-34-use-enums-instead-of-int-constants)
            - [Item 35: Use instance fields instead of ordinals](#item-35-use-instance-fields-instead-of-ordinals)
        - [Item 36: Use EnumSet instead of bit fields](#item-36-use-enumset-instead-of-bit-fields)
        - [Item 27: Use EnumMap instead of ordinal indexing](#item-27-use-enummap-instead-of-ordinal-indexing)
        - [TODO:](#todo)

<!-- /TOC -->
# Note - Third Edition

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

### Item 27: Use EnumMap instead of ordinal indexing
```Java
// Native stream-based approach - unlikely to produce an EnumMap
Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle));
```
The above code's problem is that it chooses its own map implementation, and in practice it won't be an EnumMap, so it won't match the space and time performance of the version with the explicit EnumMap.

```Java
// Using a stream and an EnumMap to associate data with an enum
Arrays.stream(garden).collect(groupingBy(p -> p.lifeCycle, () -> new EnumMap<>(LifeCycle.class), toSet()));
```

### TODO:
1. *AutoCloseable* object.