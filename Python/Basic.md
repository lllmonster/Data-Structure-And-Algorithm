## Basic
```
print "Hello world"
a = ['a','b','c']

if a == b:
elif a > b:
else:

a = 10 //integer
b = 10.0 // float
name = "john"
```

## Standatd Data Type
* Numbers  
  * int
  * long
  * float
  * complex
* String  
  ```
  str "hello world"  
  print str       # "hello world"
  print str[0]    # "h"
  print str[2:5]  # "llo"
  print str[2:]
  print str + "test"
  ```
* List  
  `list = ['abcd', 786, 2.23, 'john']`
* Tuple  
  Tuple can be seen as read-only lists.
  `tuple = ('abcd', 786, 2.23, 'john')`
* Dictionary  
  ```
  dict = {'name':'john','code':6734}  
  dict = {}  
  dict['ones'] = 'this is one'
  dict[2] = 'this is two'
  ```
## String
```
find(str, begin, end)
rfind(str, begin, end)
len(str)
```
## List
```
len(list)
list(seq) # convert a tuple to lists
list.append(obj)
list.index(obj) 
list.insert(index, obj)
list.pop()
list.remove()
list.reverse()
list.sort()
```
## Dictionary  
```
dict.get(key, default = None)
dict.has_key(key)
dict.items()
dict.keys()
dict.values()
```
