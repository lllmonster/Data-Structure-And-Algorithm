# Extract Filename and Extension  
[REF](https://stackoverflow.com/questions/965053/extract-filename-and-extension-in-bash)

Get File name without the path  
```
filename=$(basename -- "$fullfile")
extension="${filename##*.}"
filename="${filename%.*}"
# Alternatively
filename="${fullfile##*/}"
```

Example  
```
~% FILE="example.tar.gz"

~% echo "${FILE%%.*}"
example

~% echo "${FILE%.*}"
example.tar

~% echo "${FILE#*.}"
tar.gz

~% echo "${FILE##*.}"
gz
```
