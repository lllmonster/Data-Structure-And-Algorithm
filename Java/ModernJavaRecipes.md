# Modern Java Recipes
## Chapter 1: The Basics
### Method Reference
double colon(::)
* object::instanceMethod (System.out::println)
* Class::staticMethod (Math::max)
* Class::instanceMethod (String::length)

### Constructor Reference
```java
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
```java
@FunctionalInterface
public interface PalindromeChecker{
    boolean isPalidrome(String s);
}
```

Functional interface can have default and static methods as well. Both of them have implementations, so they don't count against the single abstract method requirement.  
```java
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
```java
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
```java
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

```java
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
```java
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

Other Examples:
* Map.computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);
* Comparator.comparing(Function<? super T, ? extends U> keyExtractor)
* Comparator.thenComparing(Function<? super T, ? extends U> keyExtractor)

## Chapter 3: Streams

A stream is a sequence of elements that does not save the elements or modify the original source.

### Creating Streams
java.util.stream.Stream   
```java
// of
static <T> Stream<T> of(T... values);
// iterate
static <T> Stream<T> iterate(T seed, UnaryOperation<T> f);
// generate
static <T> Stream<T> generate(Supplier<T> s);
//
Arrays.stream(T[] array);
//
Collection.stream();
```  

Example: 
```java
// of
String names = Stream.of("a","b","c").collect(Collectors.joining(","));
// iterate
Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L)).limit(10).forEach(System.out::println);
// Generate
long count = Stream.generate(Math::random).limit(10).forEach(System.out::println);
```

The API also includes an overloaded of method that takes a single element T t. This method returns a singleton sequential stream containing a single element.

There are three child interfaces of Stream specifically for working with primitives: IntStream, LongStream, DoubleStream.

```java
List<Integer> ints = IntStream.range(10, 15).boxed().collect(Collectors.toList()); // 10,11,12,13,14
List<Integer> ints = IntStream.rangeClosed(10, 15).boxed().collect(Collectors.toList()); // 10,11,12,13,14,15
```

### Boxed Streams

Create a collection from a primitive stream
```java
// using boxed method
List<Integer> ints = IntStream.of(1,2,3,4,5).boxed().collect(Collectors.toList());
// using mapToObj method
List<Integer> ints = IntStream.of(1,2,3,4,5).mapToObj(Integer::valueOf).collect(Collectors.toList());
// using tthe three-argument version of collect
List<Integer> ints = IntStream.of(1,2,3,4,5).collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);
```

Convert an IntStream to an int array
```java
int[] intArr = IntSteam.of(1,2,3,4,5).toArray();
// or
int[] intArr = IntStream.of(1,2,3,4,5).toArray(int[]::new);
```

### Reduction Operations using Reduce
You want to produce a single value from stream operation - use the reduce method to acumulate calculations on each element.  

Example: Reduction operations on IntStream
```java
String[] strings = "this is an array of strings".split(" ");
long count = Arrays.stream(strings).map(String::length).count();
int totalLength = Arrays.stream(strings).mapToInt(String::length).sum();
OptionalDouble ave = Arrays.stream(strings).map(String::length).average();
OptionalInt max = Arrays.stream(strings).map(String::length).max();
OptionalInt min = Arrays.stream(strings).map(String::length).min();
// 1. count is a stream method, so no need to map to IntStream
// 2. sum and average are on the primitives streams only
// 3. max and min without Comparator only on primitives streams.
```

There are two overloaded versions of the reduce method in IntStrea
```java
OptionalInt reduce(IntBinaryOperation op);
int reduce(int identity, IntBinaryOperation op);
```

Example
```java
// summing numbers
int sum = IntStream.rangeClosed(1,10).reduce((x,y) -> x + y).orElse(0);
// printing values of x and y
int sum = IntStream.rangeClosed(1,10).reduce((x, y) -> {
    System.out.printf("x=%d, y=%d%n", x, y);
    return x + y;
}).orElse(0);
// doubling the values during the sum
int sum = ntStream.rangeClosed(1,10).reduce(0, (x,y) -> x + 2 * y).orElse(0);

// Binary operators
int sum = Stream.of(1,2,3,4,5).reduce(0, Integer::sum);
Interger max = Stream.of(1,2,3,4,5).reduce(Integer.MIN_VALUE, Integer::max);

String s = Stream.of("this","is","a","list").reduce("", String::concat);//thisisalist
String s = Stream.of("this","is","a","list").collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
String s = Stream.of("this","is","a","list").collect(Collectors.joinint());
```

The most generatl form of reduce
`<U> U reduce(U identity, BiFunction<U, ? super T,U> accumulator, BinaryOperation<U> combiner)`

Example
```java
public class Book {
    private Integer id;
    private String title;
}

HashMap<Integer, Book> bookMap = books.stream().reduce(new HashMap<Integer, Book>(),
                                                        (map, book) -> {
                                                            map.put(book.getId(), book);
                                                            return map;
                                                        },
                                                        (map1, map2) -> {
                                                            map1.putAll(map2);
                                                            return map1;
                                                        });
```

### Check Sorting using Reduce
Example
```java
List<String> sorted = strings.stream().sorted(Comparator.comparingInt(String::length)).collect(toList());
List<String> sorted = strings.stream().reduce((prev, curr) -> {
    assertTrue(prev.length() <= curr.length());
    return curr;
})
```

### Debugging Streams with peek
Invoke the peek intermediate operation wherever you want to see the individual elements of a stream as they are processed.  

```java
public int sumDoubleDivisibleBy3(int start, int end) {
    return IntStream.rangeClosed(start, end).
                    .peek(n -> System.out.printf("original: %d%n", n))
                    .map(n -> n * 2)
                    .peek(n -> System.out.printf("doubled: %d%n", n))
                    .filter(n -> n % 3 == 0)
                    .peek(n -> System.out.printf("filtered: %d%n", n))
                    .sum();
}
```

### Converting Strings to Streams and Back
Example
```java
// checking for palindromes in java 7 or earlier
public boolean isPalindromes(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
        sb.append(c);
    }
    String forward = sb.toString().toLowerCase();
    String backward = sb.reverse().toString().toLowerCase();
    return forward.equals(backward);
}

// using java 8 streams
public boolean isPalindromes(String s) {
    String forward = s.toLowerCase().codePoints()
                                    .collect(StringBuilder::new,
                                            StringBuilder::appendCodePoint,
                                            StringBuilder::append)
                                    .toString();
    String backward = new StringBuilder(forward).reverse().toString();
    return forward.equals(backward);
}
```

### Counting Elements
Example
```java
// Stream.count
long count = Stream.of(1,2,3,4,5).count();
// Collectors.counting
long count = Stream.of(1,2,3,4,5).collect(Collectors.counting());
```

### Summary Statisitcs
Use summaryStatics method to count/sum/min/max/average of a stream of numerical values.  

Example
```java
DopubleSummaryStatistics stats = DoubleStream.generate(Math::random).limit(1_000_000).summaryStatistics();

// Customize
DoubleSummaryStatistics teamStats = teams.stream().mapToDouble(Team::getSalary).collect(DoubleSummaryStatistics::new,
                                                                                        DoubleSummaryStatistics::accept,
                                                                                        DoubleSummaryStatistics::combine);
// Or
teamStats = teams.stream().collect(Collectors.summarizingDouble(Team::getSalary));                                                    
```

### Finding the First Element in a Stream 
Use java.util.strea.Stream.findFirst or findAny to find the first element in a stream that satisfy a particular condition.  

Example
```java
// finding the first even integer
Optional<Integer> firstEven = Stream.of(1,2,3,4,5).filter(n -> n % 2 == 0).findFirst();
```

If the stream has no encounter order, then any element may be returned. If it has, then it will always return the same element whether we do the search using a sequential or a parallel stream.  
The API defines encounter order as the order in which the source of data makes its elements available. A list and an array both have an encounter order, but a Set does not.  

The `findAny` method is explicitly nondeterministic, meaning it is free to select any element of the stream. This allows optimization in parallel operations.  

Both findFirst and findAny are short-circuiting, terminal operations. 

### Using anyMatch, allMatch, and noneMatch
To determine if any elements in a stream match a Predicate, of if all match.  

Example
```java
// Prime numebr check
public boolean isPrime(int num) {
    int limit = (int)(Math.sqrt(num) + 1);
    return num == 2 || num > 1 && IntStream.range(2, limit).noneMatch(divisor -> num % divisor == 0);
}

// Test
private Primes calculator = new Primes();
@Test
public void testIsPrimeUsingAllMatch() throws Exception {
    assertTrue(IntStream.of(2,3,5,7,11).allMatch(calculator::isPrime));
}
@Test
public void testIsPrimeWithComposites() throws Exception {
    assertFalse(IntStream.of(2,4,6,8,10).anyMatch(calculator::isPrime));
}
```


### Stream flatMap Versus map

map - if each element is transformed into a single value.  
`<R> Stream<R> map(Function<? super T, ? extends R> mapper)`  
flatMap - if each element will be transformed to multiple values and the resulting stream needs to be flattened.  
`<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`

Example
```java
// a one-to-many relationship
public class Customer {
    private String name;
    private List<Order> orders = new ArrayList<>();
}
// using map on Customer to name
customers.stream().map(Customer::getName).forEach(System.out::println);
// using map on Customer to orders
customers.stream().map(Customer::getOrders).forEach(System.out::println); // return Stream<List<Order>>
customers.stream().map(customer -> customer.getOrders().stream()).forEach(System.out::println); // return Stream<Stream<Order>>

// using flatMap on Customer to orders
customers.stream().flatMap(customer -> customer.getOrders().stream()).forEach(System.out::println); // return Stream<Order>
```

### Concatenating Streams 
The concat method on Stream combines two streams into a single one, which works if the number of streams is small. Otherwise use flatMap.  
`static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)` . 
Both input streams must hold elements of the same type.  

Example
```java
Stream<String> first = Stream.of("a","b","c").parallel();
Stream<String> second = Stream.of("x","y","z");
List<String> strings = Stream.concat(first, second).collect(Collectors.toList());

// concatenating multiple streams - can grow unwieldy if too many are used - StackOverflowException
List<String> strings = Stream.concat(Stream.concat(first, second), third).collect(Collectors.toList());
// using flatMap to concatenate streams
List<String> strings = Stream.of(first, second, third).flatMap(Function.identity()).collect(Collectors.toList());
```

### Lazy Streams 
With a collection, all of the operations would have to be performed before moving to the next step.  
With streams, the intermediate operations form a pipeline, but nothing happens until the terminal operation is reached. Then the stream processes only as many values as are necessary.  

## Chapter 4: Comparators and Collectors

### Sorting using a Comparator 
Example
```java
// sorting strings lexicographically
public List<String> defaultSort() {
    Collections.sort(strings);
    return strings;
}

public List<String> defaultSortUsingStreams() {
    return strings.stream().sorted().collect(Collectors.toList());
}

// sorting strings by length
public List<String> lengthSortUsingSorted() {
    return strings.stream().sorted((s1, s2) -> s1.length() - s2.length()).collect(Collectors.toList());
}

public List<String> lengthSortUsingComparator() {
    return strings.stream().sorted((Comparator.comparingInt(String::length)).collect(Collectors.toList());
}

// sorting by length, then equal lengths lexicographically
public List<String> lengthSortThenAlphaSort() {
    return strings.stream().sorted(comparing(String::length).thenComparing(naturalOrder())).collect(Collectors.toList());
}

// example with object
public List<Golfer> sortByScoreThenLastThenFirst() {
    return golfers.stream().sorted(comparingInt(Golfer::getScore).thenComparing(Golfer::getLast).thenComparing(Golfer::getFirst)).collect(toList());
}
```


### Converting a Stream into a Collection 

* Collectors.toList();  
* Collectors.toSet();  
* Colelctors.toCollection();  
* Collectors.toCollection(LinkedList::new);
* Collectors.toMap(Actor::getName, Actor::getRole);

To array : stream().toArray(String[]::new);

### Adding a Linear Collection to a Map

Problem: add a collection of objects to a Map, where the key is one of the object properties and the value is the object itself.  
Solution: Collectors.toMap() + Function.identity 

Example
```java
// Book (id, name, price)
// add books to a Map
Map<Integer, Book> bookMap = books.stream().collect(Collectors.toMap(Book::getId, b -> b));
Map<Integer, Book> bookMap = books.stream().collect(Collectors.toMap(Book::getId, Function.identity()));
```

### Sorting Maps 
Static Methods in Map.Entry  
- comparingByKey()
- comparingByValue()  

Example 
```java
// reading the dictionary file into a map
try (Stream<String> lines = Files.lines(dictionary)) {
    Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
        .collect(Collectors.groupingBy(String::length, Collectors.counting())) // Map<Integer, List<String>> -> Map<Integer, Long>

    // sorting the map by key
    map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
} catch (IOException e) {
    e.printStackTrace();
}
```

### Partitioning and Grouping 
- Collectors.partitioningBy  - split elements into those that satisfy a Predicate and those that do not.  
- Collectors.groupingBy - produces a map of categories, where the values are the elements in each category.  

Example
```java
// partitioning strings by even or odd lengths
Map<Boolean, List<String>> lengthMap = strings.stream().collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));

// grouping strings by length
Map<Integer, List<String>> lengthMap = strings.stream().collect(Collectors.groupingBy(String::length));
```

### Downstream Collectors 

To postprocess the collections returned by a groupingBy or partitioningBy operation   

```java
// counting the partitioned strings
Map<Boolean, List<String>> lengthMap = strings.stream().collect(Collectors.partitioningBy(s -> s.length() % 2 == 0, Collectors.counting()));
Map<Integer, List<String>> lengthMap = strings.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
```

Collectors methods similar to Stream methods
| Stream | Collectors |
| --- | --- |
| count | counting 
| map | mapping 
| min | minBy 
| max | maxBy 
| IntStream.sum | summingInt 
| IntStream.summarizing | summarizingInt 

### Finding Max and Min Values 

Example 
```java
// Employee(name, salary, department)
// using Stream.max
Optional<Employee> optionalEmp = employees.stream().max(Comparator.comparingInt(Employee::getSalary));
// using map
optionalEmp = employees.stream().mapToInt(Employee::getSalary).max();
// using Collectors.maxBy
optionalEmp = employees.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));
// using Collectors.maxBy as a downstream collector
optionalEmp = employees.stream().collect(Collectors.groupinBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
```

### Creating Immutable Collections

Collectors.collectingAndThen - to create an immutable list, set or map.  

```java
// java 7 - create unmodifiable lsit of sets
Collections.unmodifiableList(Arrays.asList(elements));
Collections.unmodifiableSet(new HashSet<>(Arrays.asList(elements)));

// java 8
Arrays.stream(elements).collect(collectingAndThen(toList(), Collections::unmodifiableList));
Arrays.stream(elements).collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
```

### Implementing the Collector Interface 

Example
```java
// using collect to return an unmodifiable SortedSet
public SortedSet<String> oddLengthStringSet(String... strings) {
    Collector<String, ?, SortedSet<String>> intoSet = Collector.of(
                    TreeSet<String>::new,                                // supplier to create a new TreeSet
                    SortedSet::add,                                      // BiConsumer to add each string to the TreeSet
                    (left, right) -> {left.addAll(right); return left;}, // BinaryOperator to combine two sortedset instances into one
                    Collections::unmodifiableSortedSet);                 // finisher function to create an unmodifiable set.
    return Stream.of(strings).filter(s -> s.length()%2==0).collect(intoSet);
}
```

## Chapter 5: Issues with Streams, Lambdas, and Method References 

### The java.util.Object Class 

static utility methods for null checking, comparisons, and more. 
- `static boolean deepEquals(Object a, Object b)`
- `static boolean equals(Object a, Object b)`
- `static int hash(Object... values)`
- `static String toString(Object o)`
- `static String toString(Object o, String nullDefault)`
- `static <T> T requireNotNull(T obj)`
- `static <T> T requireNotNull(T obj, String message)`
- `static <T> T requireNotNull(T obj, Supplier<String> messageSupplier)`
- `static boolean isNull(Object obj)`
- `static boolean nonNull(Object obj)`

Example
```java
// return a collection and filtering out nulls
List<String> nonNullStrings = strings.stream().filter(Object::nonNull).collect(Collectors.toList());
```

### Lambdas and Effectively Final 
Local variables accessed inside lambda expressions must be final or effectively final.  
Attributes can be both accessed and modified.  

### Streams of Random Numbers 
java.util.Random - three methods  
- IntStream ints()
- LongStream longs()
- DoubleStream doubles()
- DoubleStream doubles(long streamSize, double randomNumebrOrigin, double randomNumberBound)

SecureRandom is a subclass of Random. It provides a cryptographically strong random number generator.  

### Default Methods in Map 

| Method | Purpose
| --- | --- |
| compute | 
| computeIfAbsent | `V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction)`
| computeIfPresent | `V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)`
| forEach | 
| getOrDefault | `V getOrDefault(K key, V defaultValue)`
| merge | `V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction)`
| putIfAbsent | 
| remove | 
| replace | `V replace(K key, V value); OR V replace(K key, V oldValue, V newValue);`
| replaceAll | 


Example - computeIfAbsent - recursive calculation of Fibonacci numbers
```java
// old method - highly inefficient
long fib(long i) {
    if (i == 0) return 0;
    if (i == 1) return 1;
    return fib(i - 1) + fib(i - 2);
}

// with cache
private Map<Long, BigInteger> cache = new HashMap<>();
BigInteger fib(long i) {
    if (i == 0) return BigInteger.ZERO;
    if (i == 1) return BigInteger.ONE;
    return cache.computeIfAbsent(i, n -> fib(n-2).add(fib(n-1)));
}
```

Example - merge - calcuate full word counts
```java
public Map<String, Integer> fullWordCounts(String messgae) {
    Map<String, Integer> wordCounts = new HashMap<>();
    String testString = message.toLowerCase().replaceAll("\\W"," ");
    Arrays.stream(testString.split("\\s+")).forEach(word -> wordCounts.merge(word, 1, Integer::sum));
    return wordCounts;
}
```

### Default Method Conflict 
Java 8 supports both default and static methods in interface. Default methods provide an implementation, which is then inherited by the class. 

### Iterating Over Collections and Maps 

`default void forEach(Consumer<? super T> action)`

### Logging with a Supplier 

java.util.logging.Logger
- void info(String message)
- void info(Supplier<String> messageSupplier)

Example
```java
private Logger logger = Logger.getLogger(this.getClass().getName());
logger.info("this data is " + data.toString()); // argument always constructed
logger.info(() -> "this data is " + data.toString()); // argument only constructed if log level shows info messages. 
```

### Closure Composition 
Composition methods in java.util.function.Function 
- `default <V> Function<V,R> compose(Function<? super V, ? extends T> before)`
- `default <V> Function<T,V> andThen(Function<? super R, ? extends V> after)`

Example
```java
Function<Integer, Integer> add2 = x -> x + 2;
Function<Integer, Integer> mult3 = x -> x * 3;

Function<Integer, Integer> mult3add2 = add2.compose(mult3);
Function<Integer, Integer> add2mult3 = add2.andThen(mult3);

System.out.println(add2mult3(1));
```

Closure composition with Consumers
`default Consumer<T> andThen(Consumer<? super T> after)`  

Example
```java
Consumer<String> printer = System.out::println;
Consumer<String> logger = log::info;
Consumer<String> printThenLog = printer.andThen(logger);
```

Closure composition with Predicate
- `default Predicate<T> and(Predicate<? super T> other)`  
- `default Predicate<T> negate()`
- `default Predicate<T> or(Predicate<? super T> other)`  


### Using an Extracted Method for Exception Handling

Create a separate method that does the operation, handle the exception there, and invoke the extracted method in your lambda expression.  

### Checked Exceptions and Lambdas 
 Add a try/catch block to the lambda expression, or delegate to an extracted method to handle it.  

### Using a Generic Exception Wrapper 

Create special exception classes and add a generic method to accept them and return lambdas without exceptions.  

Example
```java
// a functional interface based on Function that throws Exception
@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {
    R apply(T t) throws E;
}

// a wrapper method to deal with exceptions
private static <T, R, E extends Exception> Function<T,R> wrapper(FunctionWithException<T,R,E> fe) {
    return arg -> {
        try {
            return fe.apply(arg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    };
}

// using a generic static wrapper method
public List<String> encodeValuesWithWrapper(String... values) {
    return Arrays.stream(values).map(wrapper(s -> URLEncoder.encodes(s, "UTF-8"))).collect(Collectors.toList());
}
```

## Chapter 6: The Optional Type 

### Creating the Optional 

- Optional.of(T value)
- Optional.ofNullable(T value)
- Optional.empty()

Optional instance are immutable:
1. are final and immutable
2. have no public constructors, and thus must be instantiated by factory methods
3. have implementations of equals, hashCode, and toString that are based only on their state 

### Retrieving values from Optional 

- get()
- orElse(T other)
  - orElseGet(Supplier<? extends T> other)
  - orElseThrow(Supplier<? extends X> exceptionSupplier)
- ifPresent()


### Optional in Getters and Setters 

You can wrap the result of getter methods in Optionals, but do not do the same for setters.

And the optional class is deliberately designed not to be serializable.

### Optional flatMap Versus Map 

Example
```java
// Manager (name), Department (manager, could be null)
// Extract a name from an Optional manager
Manager m = new Manager("m1");
Department d = new Department();
d.setBoss(m);

Department d1 = new Department(); // no boss

// 
d.getBoss().orElse(new Manag("Unknown").getName());
d1.getBoss().orElse(new Manag("Unknown").getName());

// 
d.getBoss().map(Manager::getName);
d1.getBoss().map(Manager::getName);
```

### Mapping Optionals 

Example
```java
public Optional<Employee> findEmployeeById(int id);

public List<Employee> findEmployeeByIds(List<Integer> ids) {
    return ids.stream().map(this::findEmployeeById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
}

// using Optional.map
public List<Employee> findEmployeeByIds(List<Integer> ids) {
    return ids.stream().map(this::findEmployeeById).flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty)).collect(Collectors.toList());
}
```

## Chapter 7: File I/O  

Methods in java.nio.files.Files that return streams
|Method|Return type|
|---|---|
|lines|Stream\<String>|
|list|Stream\<Path>|
|walk|Stream\<Path>|
|find|Stream\<Path>|

### Process Files  

Use static lines() method in either java.io.BufferedReader or java.nio.file.Files to return the contents of a file as a stream.  

Example
```java
try(Stream<String> lines = Files.lines(Paths.get("/usr/share/log"))) {
    lines.filter(s -> s.length() > 20)
         .sorted(Comparator.comparingInt(String::length).reversed())
         .limit(10)
         .forEach(w);
} catch(IOException e) {
    e.printStackTrace();
}

// using bufferedReader.lines method
try(Stream<String> lines = new BufferedReader(new FileReader("/usr/share/log")).lines()) {
    ...
}
```

The Stream interface extends BaseStream, which is a subinterface of AutoCloseable. Streams can therefore be used inside the java 7 try-with-resources block. Whem exiting the block, the system will automatically invoke the close method, which will not only close the stream, it will also call any close handlers from the stream pipeline to release any resources. 


### Retrieving Files as a Stream 

You want to process all files in a directory as a Stream. - using Files.list method 

Example
```java
// using Files.list(path)
try(Stream<Path> list = Files.list(Paths.get("src/main/java"))) {
    list.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Walking the Filesystem 

You need to perform a depth-first traversal of the filesystem. - using Files.walk method   
- `public static Stream<Path> walk(Path start, FileVisitOption... options) throws IOException;`
- `public static Stream<Path> walk(Path start, int maxDepth, FileVisitOption... options) throws IOException;`

Example
```java
// walking the tree
try(Stream<Path> paths = Files.walk(Paths.get("src/main/java"))) {
    paths.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Searching the Filesystem 

You want to find files in a file tree that satisfy given properties. - using Files.find method.  

Example
```java
// Finding the nondirectory files in the fileio package
try(Stream<Path> paths = Files.find(Paths.get("src/main/java"), Integer.MAX_VALUE,
                                    (path, attributes) ->
                                            !attributes.isDirectory() && path.toString().contains("fileio"))) {
    paths.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

## Chapter 8: The java.time Package 

### Using the Basic Date-Time Classes 

java.time package - java.time classes are immutable     
|Method|Output|
|---|---|
|Instant.now()|2017-06-20T17:27:08.184Z
|LocalDate.now()|2017-06-20
|LocalTime.now()|13:27:08.318
|LocalDateTime.now()|2017-06-20T13:27:08.318
|ZonedDateTime.now()|2017-06-20T13:27:08.318-04:00\[America/NewYork]


Example - apply a time zone to a LocalDateTime
```java
LocalDateTime dateTime = LocalDateTime.now();
ZonedDateTime nyc = dateTime.atZone(ZoneId.of("America/New_York"));
ZonedDateTime london = nyc.withZoneSameInstant(ZoneId.of("Europe/London"));
```

### Creating Dates and Times from Existing Instances 

LocalDate  
- LocalDate plusDays(long daysToAdd)
- LocalDate plusWeeks(long weeksToAdd)
- LocalDate plusMonths(long monthsToAdd)
- LocalDate plusYears(long yearsToAdd)

LocalTime  
- LocalTime plusNanos(long nanosToAdd)
- LocalTime plusSeconds(long secondsToAdd)
- LocalTime plusMinutes(long minutesToAdd)
- LocalTime plusHours(long hoursToAdd)

Example - DateTimeFormatter
```java
DateTimeFormatter formatter = DateTimeFormatter.ofPatter("yyyy-MM-dd");
LocaDate date = LocaDate.now();
assertEquals("2017-02-05", date.format(formatter));
```

### Adjusters and Queries  

Create a TemporalAdjuster or formulate a TemporalQuery

- [ ] TODO: Here to save time, I did not note all details. Will add it later. 

Example - PaydayAdjuster
```java
public class PaydayAdjuster implements TemporalAdjuster {
    public Temporal adjustInto(Temporal input) {
        LocaDate date = LocalDate.from(input);
        int day;
        if (date.getDayOfMonth() < 15) {
            day = 15;
        } else {
            day = date.with(TemporalAdjuster.lastDayOfMonth()).getDayOfMonth();
        }
        date = date.withDayOfMonth(day);
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.with(TemporalAdjuster.previous(DayOfWeek.FRIDAY));
        }
        return input.with(date);
    }
}
```

### Convert from java.util.Date to java.time.LocalDate 

```java
public LocalDate convertFromUtilDateUsingInstant(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
}
```

### Parsing and Formatting 

using DateTimeFormatter classes. 

### Finding Time Zones with Unusual Offsets 

### Finding Region Names from Offsets 

### Time Between Events 

using between or until method  

