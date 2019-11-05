## Kryo

### Introduction

Kryo is a Java serialization framework with a focus on speed, efficiency, and a user-friendly API.

The Kryo class orchestrates the serialization process and maps classes to *Serializer* instances which handle the details of converting an object's graph to a byte representation.

Once the bytes are ready, they're written to a stream using an *Output* object. This way they can be stored in a file, a database or transmitted over the network.

### Basic

```java
public void init() {
    kryo = new Kryo;
    output = new Output(new FileOutputStream("file.dat"));
    input = new Input(new FileInputStream("file.dat"));
}
public void serializeAndDeserialize() {
    Object obj = "helloworld";
    kryo.writeClassAndObject(output, obj);
    output.close();
    Object newObj = kryo.readClassAndObject(input);
    input.close();
    
    kryo.writeObject(output, someString);
    kryo.writeObject(output, someDate);
    String readString = kryo.readObject(input, String.class);
    Date readDate = kryo.readObject(input, Date.class);
}
```

### Custom Serializers

```java
public class Person {
    private String name = "John Doe";
    private int age = 18;
    private Date birthDate = new Date(933191282821L);
 
    // standard constructors, getters, and setters
}

public class PersonSerializer extends Serializer<Person> {
    public void write(Kryo kryo, Output output, Person object) {
        output.writeString(object.getName());
        output.writeLong(object.getBirthDate().getTime());
    }
 
    public Person read(Kryo kryo, Input input, Class<Person> type) {
        Person person = new Person();
        person.setName(input.readString());
        long birthDate = input.readLong();
        person.setBirthDate(new Date(birthDate));
        person.setAge(calculateAge(birthDate));
        return person;
    }
 
    private int calculateAge(long birthDate) {
        // Some custom logic
        return 18;
    }
}

public void test_main() {
    Person person = new Person();
    person.setAge(0);
     
    kryo.register(Person.class, new PersonSerializer());
    kryo.writeObject(output, person);
    output.close();
 
    Person readPerson = kryo.readObject(input, Person.class);
    input.close();
 
    assertEquals(readPerson.getName(), "John Doe");
    assertEquals(readPerson.getAge(), 18);
}
```

