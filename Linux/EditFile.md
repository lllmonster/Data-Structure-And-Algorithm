## Append string after certain string
```
sed '/pattern/a insert text here' filename
```

## Split String by space
```
echo "12|23|11" | awk '{split($0,a,"|"); print a[3],a[2],a[1]}'
```
