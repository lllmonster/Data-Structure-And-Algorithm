### Java Heap memory usage using jmap and jstat command

[reference](<https://javaperformance.wordpress.com/2017/02/05/java-heap-memory-usage-using-jmap-and-jstat-command/>)

S0C – CURRENT SURVIVOR SPACE 0 CAPACITY (KB).
S1C  –  CURRENT SURVIVOR SPACE 1 CAPACITY (KB).
S0U – SURVIVOR SPACE 0 UTILIZATION (KB).
S1U  – SURVIVOR SPACE 1 UTILIZATION (KB).
EC    – CURRENT EDEN SPACE CAPACITY (KB).
EU    – EDEN SPACE UTILIZATION (KB).
OC    – CURRENT OLD SPACE CAPACITY (KB).
OU    – OLD SPACE UTILIZATION (KB).
PC     – CURRENT PERMANENT SPACE CAPACITY (KB).
PU     – PERMANENT SPACE UTILIZATION (KB).
YGC   – NUMBER OF YOUNG GENERATION GC EVENTS.
YGCT – YOUNG GENERATION GARBAGE COLLECTION TIME.
FGC   – NUMBER OF FULL GC EVENTS.
FGCT – FULL GARBAGE COLLECTION TIME.
GCT   – TOTAL GARBAGE COLLECTION TIME.

```
$ jstat -gc 22075 | tail -1 | awk '{split($0,a," "); sum=a[3]+a[4]+a[6]+a[8]; print sum " Kb"}'
$ jstat -gc 22075 | tail -1 | awk '{split($0,a," "); sum=a[1]+a[2]+a[5]+a[7]; print sum " Kb"}'
```

### Heap Utilization using jmap

```
$ jamp -heap pid
```

Calculate the Heap Utilization by adding the used value of `New Generation`+`concurrent mark-sweep generation`

