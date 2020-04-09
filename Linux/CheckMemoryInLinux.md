### Sorting down process by memory usage
```bash
ps aux --sort -rss

# To show top 10 memory consuming processes:
ps aux --sort -rss | head -11
```

### Linux process memory usage

Sort by RAM    
`ps aux --sort -rss`  

Sort by PID   
`ps aux --sort -pid`  
