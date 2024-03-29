<!-- TOC -->

- [Intermediate Knowledge](#intermediate-knowledge)
    - [Comparator](#comparator)
    - [File Input/Output](#file-inputoutput)
        - [Read File - Read as one String](#read-file---read-as-one-string)
        - [Read File -  Read line by line](#read-file----read-line-by-line)
        - [Write File - Write String to File](#write-file---write-string-to-file)
        - [Gson - read Object from file](#gson---read-object-from-file)
    - [Pattern & Regluar Expression](#pattern--regluar-expression)
    - [Spring Framework](#spring-framework)
        - [IOC(Inversion of Control)](#iocinversion-of-control)

<!-- /TOC -->
# Intermediate Knowledge

## Comparator

**Example - MapComparator<List\<T\>>**
```Java
public class MapComparator<T extends Comparable<T>> implements Comparator<List<T>> {
    @Override
    public int compare(List<T> o1, List<T> o2) {
        for (int i = 0; i < Math.min(o1.size(), o2.size)); i++) {
            int c = o1.get(i).compareTo(o2.get(i));
            if (c != 0) {
                return c;
            }
        }
        return Integer.compare(o1.size(), o2.size());
    }
}

Map<List<String>, List<String>> map = new HashMap<>(new MapComparator<String>());
```

## File Input/Output

### Read File - Read as one String
```Java
byte[] encoded = Files.readAllBytes(Paths.get("input.txt"));
String str = new String(encoded, StandardCharsets.UTF_8);
return str;
```

### Read File -  Read line by line
```Java
List<String> lines = new ArrayList<>();
try {
    BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
    String line = reader.readLine();
    while (line != null) {
        lines.add(line);
        line = reader.readLine();
    }
    reader.close();
} catch (IOException e) {
    throw e;
}

return lines;
```

### Write File - Write String to File
```Java
Path path = FileSystems.getDefault().getPath("output.txt");
BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
writer.write(str);
writer.close();
```

### Gson - read Object from file
```Java
import com.google.gson.Gson;

Gson gson = new Gson();
Reader reader = Files.newBufferedReader(Paths.get("test.txt"));

MyClass myclass = gson.fromJson(reader, MyClass.class);
```

## Pattern & Regluar Expression
```Java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

Pattern p = Pattern.compile("test_pattern");
Matcher m = p.matcher("This is a test_pattern - Input String");
boolean matchFound = m.find();
while(m.find()) {
    String str = inputString.subString(m.start(), m.end());
}
```
***Regular Expression Patterns**

[ref](https://www.vogella.com/tutorials/JavaRegularExpressions/article.html)

| Regex | Matches| 
| --- | --- |
| this is text | Matches exactly "this is text" | 
| this\s+is\s+text | "this" can be followed with multi whitespace chars, "is" can be followed with multi whitespace chars | 
| `^\d+(\.\d+)?` | ^ defines that the pattern must start at beginning of a new line. \d+ matches one or serveral digits. ? makes the statement in brackets optional. \. mathces ".", parentheses are used for grouping. |

| Expression | Description| 
| --- | --- |
| [abc] | Find one char from the options between the brackets|
| [^abc] | Find one char not between the brackets | 
| [0-9] | Find one char from the range 0-9 |
| . | Matches any char
| ^regex | Finds regex that must match at the beginning of the line
| regex$ | Finds regex that must match at the end of the line
| [abc] | Match the letter a or b or c
| [abc][vz] | can match a or b or c followed by either v or z
| [^abc] | match any char except a or b or c
| [a-d1-7] | match a letter between a and d and figures from 1-7, but not d1
| x\|z | x or z
| \\d | 0-9
| \\s | whitespace
| \\w | word char, a-zA-Z_0-9
| * | occurs zero or more times 
| + | occurs one or more times
| ? | occurs no or one times
| {x} | occurs x number of times. \\d{3}
| {x,y} | occurs between x and y times \\d{1,4}

## Spring Framework
### IOC(Inversion of Control)
We can define inversion as a reversal of the natural order. The natural order is to decalre variables and then initailize those variables with instances of objects you create. like:  
`Question q1 = new Question();`  

If we were to apply the IOC, the developer could not use the `new` keyword and call the constructor of the Question class.

Instead, when needed, a magical and mysterious black box (IoC container) will provide instances of the Qeustion class. like:  
`Question q1 = MysteriousBlackBox.getInstance(Question.class)`  

If you introduces the IoC with the Spring Framework. It's like:  
`Qeustion q1 = context.getBean(Question.class)`

Here in Spring, call the IoC container that provisions instances the ApplicationContext, not MysteriousBlackBox.

**Benefits**

* maintain the creation, configuration, provisioning and lifecycle of all container-managed objects separately from the code where they are referenced. 
    * If the configuraiton for the database connection changes, a developer only needs to change the setting in the IoC container, not rewrite the code.
    * If an instance of a class get cached and pooled, the IoC container can take care of this
    * If an application uses only one instance of a class, and IoC container can provide this behavior.
* IoC container can provide a new implementation of a class, or even a specialized subclass.
* IoC container can provide a new and more efficient implementation at runtime.

**Summary**

The IoC pattern is a way of reversing the traditional approach to create and initialize java objects. Rather than create objects directly within their code, developers ask a third party, such as the Spring container or the Java EE context, to provide instances instead.  



