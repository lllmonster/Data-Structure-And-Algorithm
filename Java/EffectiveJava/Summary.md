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










### TODO:
1. *AutoCloseable* object.