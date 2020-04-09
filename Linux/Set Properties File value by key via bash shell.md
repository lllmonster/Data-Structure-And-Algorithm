Scripts
```bash
#!/bin/bash

############################
#script function
############################
setProperty(){
  awk -v pat="^$1=" -v value="$1=$2" '{ if ($0 ~ pat) print value; else print $0; }' $3 > $3.tmp
  mv $3.tmp $3
}
############################
### usage: setProperty $key $value $filename
setProperty "store.folder"              "/opt/current/store"     "env.properties"
```

[Reference](https://gist.github.com/kongchen/6748525)