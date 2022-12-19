# Modern Java Recipes
## Chapter 1: The Basics
### Method Reference
double colon(::)
* object::instanceMethod (System.out::println)
* Class::staticMethod (Math::max)
* Class::instanceMethod (String::length)

### Constructor Reference
```Java
List<String> names = people.stream().map(Person::getName).collect(Collectors.toList());
List<Person> people = names.stream().map(Person::new).collect(Collectors.toList());
// used for copying
people = Stream.of(before).map(Person::new).collect(Collectors.toList());
// varargs constructor
public Person(String... names) {
    this.name = Arrays.stream(names).collect(Collectors.joining(" "));
}
names.stream().map(name -> name.split(" ")).map(Person::new).collect(Collectors.toList());
// Arrays
Person[] people = names.stream().map(Person::new).toArray(Person[]::new);
```

### Functional Interfaces
A functional interface in java 8 is an interface with a single, abstract, method. As such, it can be the target for a lamda expression or method reference.  
```Java
@FunctionalInterface
public interface PalindromeChecker{
    boolean isPalidrome(String s);
}
```

Functional interface can have default and static methods as well. Both of them have implementations, so they don't count against the single abstract method requirement.  
```Java
@FunctionalInterface
public interface MyInterface {
    int myMethod();
    default String sayHello() {return "HelloWorld";}
    static void myStaticMethod() {System.out.println("static hello");}
}
```

You can use `default` or `static` keyword on the interface method, and add the implementation in the normal way. But you cannot override a static method.

Static methods in interface removes the need to create separate utility classes. You can call static methods from interface name directly.  

## Chapter 2: The java.util.function Package

The interface in java.util.function fall into four categories:
1. Consumers: take a generic argument and return nothing
2. Suppliers: take no argument and return a value
3. Predicates: take an argument and return a boolean
4. Functions: take a single argument and return a value.

### Consumers
java.util.function.Consumer - accept(T t) method  
Example : The forEach method in Iterable, `default void forEach(Consumer<? super T> action)`  
```Java
List<String> strings = Arrays.asList("a","b","c");
// Anonymous inner class implementation
strings.forEach(Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});
// Expression lambda
strings.forEach(s -> System.out.println(s));
// Method reference
strings.forEach(System.out::println);
```

Other Examples:
* Optional.ifPreseng(Consumer<? super T> consumer);
* Stream.forEach(Consumer<? super T> action);
* Stream.peek(Consumer<? super T> action);

### Suppliers
java.util.function.Supplier - T get() method  
Example: Math.random() as a supplier
```Java
// Anonymous inner class implementation
DoubleSupplier randomSupplier = new DoubleSupplier(){
    @Override
    public double getAsDouble() {
        return Math.random();
    }
};
// Expression lambda
randomSupplier = () -> Math.random();
// Method reference
randomSupplier = Math::random;
```

Other Examples:  
* Optional.orElseGet();
* Optional.orElseThrow(Supplier< X extends Exception>);
* Objects.requireNonNull(T obj, Supplier\<String> messageSupplier);
* CompletableFuture.supplyAsync(Supplier\<U> supplier);
* Logger

### Predicates
java.util.function.Predicate - boolean test(T t) method  
Predicate are used primarily to filter streams. 
Given a stream of items, the filter method takes a Predicate and returns a new stream that includes only the items that satisfy the given predicate.  

Example: Arrays.stream(names).filter(s -> s.length() == 5).collect(Collectors.joining(" "));

```Java
public class ImplementPredicate {
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> START_WITH_S = s -> s.startWith("S");
}

public class ImplementPredicateTest {
    
    privet String[] names = Stream.of("a","b","c").sorted().toArray(String[]::new);

    public static String getNamesOfLength5() {
        return Arrays.stream(names).filter(ImplementPredicate.LENGTH_FIVE).collect(Collectors.joining(","));
    }
}
```

Other exmaples:
* Optional.filter(Predicate<? super T> predicate);
* Collection.removeIf(Predicate<? super T> filter);
* Stream.allMatch(Predicate<? super T> predicate);
* Collectors.partitioningBy(Predicate<? super T> predicate);

### Functions

java.util.function.Function - R apply(T t) method  
Example: Stream.map
```Java
List<String> strings = Arrays.asList("a","b","c");
// Anonymous inner class implementation
List<Integer> nameLengths = names.stream().map(new Function<String, Integer>() {
    @Override
    public Integer apply(String s) {
        return s.length();
    }
}).collect(Collectors.toList());
// Expression lambda
nameLengths = names.stream().map(s -> s.length()).collect(Collectors.toList());
// Method reference
nameLengths = names.stream().map(String::length).collect(Collectors.toList());
```

Other Exampels:
* Map.computerIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
* Comparator.comparing(Function<? super T, ? extends U> keyExtractor)
* Comparator.thenComparing(Function<? super T, ? extends U> keyExtractor)
