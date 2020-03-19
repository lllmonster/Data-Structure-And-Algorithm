## Summary 2020

01/08 Wed

#### Item 13: Minimize the accessibility of classes and members

```java
// Potential security hole, Array value can be changed.
public static final Thing[] VALUES = {...};

// One way to fix this issue
private static final Thing[] PRIVATE_VALUES = {...};
public static final List<Thing> VALUES = 
    Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

// The Other way
private static final Thing[] PRIVATE_VALUES = {...};
public static final Thing[] values() {
    return PRIVATE_VALUES.clone();
}
```

#### Item 14: In Public classes, use accessor methods, not public fields

```java
// Encapsulation of data by accessor methods and mutators
class Point {
private double x;
private double y;
public Point(double x, double y) {
this.x = x;
this.y = y;
}
public double getX() { return x; }
public double getY() { return y; }
public void setX(double x) { this.x = x; }
public void setY(double y) { this.y = y; }
}
```

01/09 Thu

#### Item 15: Minimize mutability

How to make a class immutable. 

* Ensure class can't be extended (Add final)
* Make all fields final & private
* Ensure exclusive access to any mutable components

