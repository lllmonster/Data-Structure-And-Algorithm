# Head First Design Pattern
- [Head First Design Pattern](#head-first-design-pattern)
  - [Definition](#definition)
  - [The Observer Pattern](#the-observer-pattern)
  - [The Decorator Pattern](#the-decorator-pattern)
  - [The Factory Pattern](#the-factory-pattern)
  - [The Singleton Pattern](#the-singleton-pattern)
  - [The Command Pattern](#the-command-pattern)
  - [The Adapter and Facade Patterns](#the-adapter-and-facade-patterns)
  - [The Template Method Pattern](#the-template-method-pattern)
  - [The Iterator and Composite Patterns](#the-iterator-and-composite-patterns)
- [APPENDIX A - Note](#appendix-a---note)
  - [Difference between abstract class and interface](#difference-between-abstract-class-and-interface)

## Definition
OO Basic
* Abstraction
* Encapsulation
* Polymorphism
* Inheritance

OO Principles
* Encapsulate what varies
* Favor composition over inheritence
* Program to interfaces, not implementations
* Strive for loosely coupled designs between objects that interact.
* Classes should be open for extension but closed for modification
* Depend on abstractions. Do not depend on concrete classes.
* Principle of Least Knowledge - talk only to you immediate friends.
* Don't call us, we'll call you.
* Keep each class to a single responsibility.

OO Patterns
* The Strategy Pattern: defines a family of algorithms, encapsulates each other and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it. 
* The Observer Pattern: defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
  * Example: one publisher + multi subscribers
* The Decorator Pattern: Attach additional responsibilities to an object dynamically. Decorateors provide a flexible alternative to subclassing for extending functionality.
  * Example: Java I/O Decorator
    * InputStream
      * FileInputStrem
      * StringBufferInputStream
      * ByteArrayInputStream
      * FilterInputStream
        * BufferedInputStream
        * DataInputStream
        * LineNumberInputStream
* The Factory Pattern: Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to the subclasses.
  * The Abstract Factory Pattern: provides an interface for creating families of related or dependent objects without specifying their concrete classes.
* The Singleton Pattern: ensures a class has only one instance, and provides a global point of access to it.
* The Command Pattern: encapsulates a request as an object, thereby letting you parameterize other objcts with different request, queue or log requests, and support undoable operations.  
* The Adapter Pattern: converts the interface of a class into another interface the clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.  
* The Facade Pattern: provides a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the susystems easier to use.  
* The Template Method Pattern: defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Template Methods lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.  
* The Iterator Pattern: provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.  
* The Composite Pattern: allows you to compose objects into tree structure to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.  
* The State Pattern:
* The Proxy Pattern:
* Compound Patterns:
* Better Living with Patterns:
* Leftover Patterns:


Comparsion
| Pattern | Intent |
| --- | --- |
| Decorator | Converts one interface to another | 
| Adapter | Doesnot alter the interface, but adds responsibility | 
| Facade | Makes an interface simpler | 


| Pattern | Description |
| --- | --- |
| Template Method | Encapsulate interchangeable behaviors and use delegation to decide which behavior to use | 
| Strategy | Subclasses decide how to implement steps in an algorithm | 
| Factory Method | Subclasses decide which concrete classes to create | 

## The Observer Pattern
Publishers + Subscribers = Observer Pattern - Defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.  

```Java
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

public interface Observer {
    public void update(float temp, float humidity, float pressure);
}

public interface DisplayElement {
    public void display();
}
```
```Java
public class WeatherData implements Subject {
    private ArrayList observers;
    private float temp;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) observers.remove(i);
    }

    public void notifyObservers() {
        for (Observer o : observeres) {
            o.update(temp, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    // other WeatherData methods here
}
```
```Java
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temp;
    private float humidity;
    private Subject weatherData;

    public CurrentConditonsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void update(float temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions is bla.");
    }
}
```

## The Decorator Pattern
Starbuze coffee
```Java
public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstrct double cost();
}

public abstract class CondimentDecorator extends Beverage {
    public abatract String getDescription();
}
```
```Java
public class Espresso extends Beverage {
    public Expresso() {
        description = "Espresso";
    }

    public double cost() {
        return 1.99;
    }
}

public class Mocha extends CodimentDecorator {
    Beverage beverage;

    public Mocha (Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    public double cost() {
        return .20 + beverage.cost();
    }
}

public class StarbuzzCoffee {
    public static void main(String args[]) {
        Beverage beverage = new Espresso();
        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
    }
}
```

## The Factory Pattern
**Simple pizza factory** : define a class that encapsulates the object creation for all pizzas - isn't actually a Design Pattern, it's more of a programming idiom.
```Java
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        }

        return pizza;
    }
}

public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
```
Then if everyone want a PizzaStore in there own neighborhood, and you'd like more quality control for them.

**The Creator classes:**
* PizzaStore (createPizza(), orderPizza())
  * NYStylePizzaStore - createPizza()
  * ChicagoStylePizzaStore - createPizza()

**The Product classes:**
* Pizza
  * NYCheesePizza
  * NYPepperoniPizza
  * ChicagoCheesePizza
  * ChicagoPepproniPizza

```Java
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}

public class NYPizzaStore extends PizzaStore {
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new NYPepperoniPizza();
        } 
    }
}
```

## The Singleton Pattern
To create one and only one obejct.  
**Use Case**: thread pools, caches, dialog boxes, objects that handle preferences and registry settings, log objects, device drivers, graphics cards.
```Java
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}

// Dealing with multithreading - performace degrade
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton() {}
    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
// Dealing with multithreading2 - move to an early created instance
public class Singleton {
    private static Singleton uniqueInstance = new Singleton;
    private Singleton() {}
    public static Singleton getInstance() {
        return uniqueInstance;
    }
}
// Dealing with multithreading2 - double check locking
public class Singleton {
    // The volatile keyword ensures that multiple threads handle the uniqueInstance variable correctly when it is being initialized.
    private volatile static Singleton uniqueInstance;
    private Singleton() {}
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }    
            }
        }
        return uniqueInstance;
    }
}

```

## The Command Pattern
Client -> Receiver(action) -> concrete command(execute, undo)  
Invoker(set command) -> command Interface(execute, undo)  

```Java
public class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl() {}
    public void setCommand(Command command) {
        this.slot = command;
    }
    public void buttonWasPressed() {
        slot.execute();
    }
}

public class RemoteControlTest {
    public static void main(String args[]) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}
```
Practical test - Design a remote control  
```Java
public class RemoteControl {
    Command[] onCmds;
    Command[] offCmds;
    Command undoCommand;
    public RemoteControl() {
        onCmds = new Command[7];
        offCmds = new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCmds[i] = noCommand;
            offCmds[i] = noCommand;
        }
        undoCommand = noCommand;
    }
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCmds[slot] = onCommand;
        offCmds[slot] = offCommand;
    }
    public void onButtonWasPressed(int slot) {
        onCmds[slot].execute();
        undoCommand = onCmds[slot];
    }
    public void offButtonWasPressed(int slot) {
        offCmds[slot].execute();
        undoCommand = offCmds[slot];
    }
    public void undoButtonWasPushed() {
        undoCommand.undo();
    }
}

public class LighOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.off();
    }
    public void undo() {
        light.on();
    }
}

public class RemoteLoader {
    public static void main(String args[]) {
        RemoteControl remoteControl = new RemoteControl();
        Light livingRoomLight = new Light("LivingRoom");
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.offButtonWasPressed(0);
        remoteControl.undoButtonWasPushed();
    }
}
```
More uses of the Command Pattern : queuing requests, logging requests.  


## The Adapter and Facade Patterns
Enumeration-Iterator adapter
```Java
public class EnumerationIterator implements Iterator {
    Enumeration enum;
    public EnumerationIterator(Enumeration enum) {
        this.enum = enum;
    }
    public boolean hasNext() {
        return enum.hasMoreElements();
    }
    public Object next() {
        return enum.nextElement();
    }
    public void remove() {
        throw new UnsupportedOpeartionException();
    }
}
```

Facade Example - home therater facade
```Java
public class homeTheaterFacade {
    Amplifier amp;
    Tuner tuner;
    DvdPlayer dvd;
    CdPlayer cd;
    Projectore projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    public HomeTheaterFacade(...) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.cd = cd;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolumes(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}
```

## The Template Method Pattern

```Java
public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    abstract void brew();
    abstract void addConiments();
    void boilWater() {
        System.out.println("Boiling the water.");
    }
    void pourInCup() {
        System.out.println("Pouring into cup");
    }
}

public class Tea extends CaffeineBeverage {
    public void brew() {
        System.out.println("Steeping the tea");
    }
    public void addCondiments() {
        System.out.println("Adding Lemon");
    }
}

public class Coffee extends CaffeineBeverage {
    public void brew() {
        System.out.println("Dripping coffee");
    }
    public void addCondiments() {
        System.out.println("Adding Sugar and milk");
    }
}
```

Real world example - comparator
```Java
public class Duck implements Comparable {
    String name;
    int weight;
    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }
    public String toString() {
        return name + "-" + weight;
    }
    public int compareTo(Object object) {
        Duck otherDuck = (Duck) object;
        if (this.weight < otherDuck.weight) {
            return -1;
        } else if (this.weight == otherDuck.weight) {
            return 0;
        } else {
            return 1;
        }
    }
}

public static void main(String[] args) {
    Duck[] ducks = {new Duck("a",8),
                    new Duck("b",10),
                    new Duck("c",2)};
    Arrays.sort(ducks);
}
```

## The Iterator and Composite Patterns
If you want to print a list
```Java
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}
```
If you want to print an array
```Java
for (int i = 0; i < arr.length; i++>) {
    System.out.println(arr[i]);
}
```
One use list, the other one use arr, can I combine them together using one pattern?
```Java
Iterator it = list.createIterator();
// Iterator it = arr.createIterator();
while (it.hasNext()) {
    Object o = (Object) it.next();
}

```

Implementation
```Java
public class arrIterator implements Iterator {
    String[] arrs;
    int position = 0;

    public arrIterator(String[] arrs) {
        this.arrs = arrs;
    }
    public Object next() {
        String s = arrs[position];
        position++;
        return s;
    }
    public boolean hasNext() {
        if (position >= arrs.length || arrs[position] == null) {
            return false;
        }
        return true;
    }
}
```

## The State Pattern
Gumball Machine  
```Java
public class GumballMachine {
    final static int SOLD_OUT = 0;   
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;

    int state = SOLD_OUT;
    int count = 0;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    public void insertQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("You can't insert another quarter");
        } else if (state == NO_QUARTER) {
            state = HAS_QUARTER;
            System.out.println("You inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You can't insert a quarter, the machine is sold out");
        } else if (state == SOLD) {
            System.out.println("Please wait, we're already giving you a gumball");
        }
    }

    public void ejectQuarter() {
        if (state == HAS_QUARTER) {
            System.out.println("Quarter returned");
            state = NO_QUARTER;
        } else if (state == NO_QUARTER) {
            System.out.println("You haven't inserted a quarter");
        } else if (state == SOLD_OUT) {
            System.out.println("You can't eject, you haven't inserted a quarter yet");
        } else if (state == SOLD) {
            System.out.println("Sorry you already turned the crank");
        }
    }

    public vodi turnCrank() {
        //...
        if (state == HAS_QUARTER) {
            System.out.println("Your turned..");
            state = SOLD;
            dispense();
        }
    }

    public void dispense() {
        if (state == SOLD) {
            System.out.println("A gumball comes rolling out the slot");
            count--;
            if (count == 0) {
                System.out.println("Oops, out of gumballs");
                state = SOLD_OUT;
            } else {
                state = NO_QUARTER;
            }
        }
    }
} 
```
Want to add one more feature! 10% rate to get two gumballs at one time!  
Instead of maintaining our existing code, we're going to rework it to encapsulate state object in thier own classes and then delegate to the current state when an action occurs.   
1. First, define a State interface that contains a method for every action in the Gumball Machine.
2. Then implemenet a state class for every state of the machine. These classes will be responsible for the behavior of the machine when it is in the corresponding state.
3. Finally, get rid of all of our conditional code and instead delegate to the state class to do the work for us.
```Java
public interface State {
    public void insertQuarter();
    public void ejectQuarter();
    public void turnCrank();
    public void dispense();
}

public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    public void turnCrank() {
        System.out.println("You turned, but there's no quarter");
    }

    public void ejectQuarter() {
        System.out.println("You need to pay first");
    }
}

public class GumballMachine {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state = soldOutState;
    int count = 0;

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        this.count = numberGumballs;
        if (numberGumballs > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void setState(State state) {
        this.state = state;
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out the slot");
        if (count != 0) {
            count = count - 1;
        }
    }

}
```


# APPENDIX A - Note
## Difference between abstract class and interface
| Abstract class      | Interface |
| ----------- | ----------- |
| Abstract and non-abstract methods      | Only abstract methods       |
| can contain non-final variables   | variables are final by default        |
| variables can be final, non-final, static, non-static | only static and final |
| "extends" | "implements" |
| extend other class or implement other interfaces | can implement interface only | 
| can have private, protected class members | public by default |

Abstract classes have no restrictions on field and method modifiers, while in an interface, all are public by default. We can have instance and static initialization blocks in an abstract class, whereas we can never have them in the interface. Abstract classes may also have constructors which will get executed during the child object's instantiation.



