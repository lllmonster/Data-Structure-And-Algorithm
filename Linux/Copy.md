### Copy file/folder from one server to another serve through SSH

#### Copy  File

```
$ ssh user@domain.com
$ cd /path/of/the/source/directory
$ scp file.txt 0.0.0.0:/path/of/the/destination/directory/
// 0.0.0.0 is the IP address of the destination server (check with $ hostname -I)
```

#### Copy Folder

```
$ scp -r foldername 0.0.0.0:/path/of/the/destination/directory/
```

#### Copy excluding files
```
rsync -avr --exclude='path1/to/exclude' --exclude='path2/to/exclude' source destination
```

#### Remote Copy with excluding some files

```
rsync -av -e ssh --exclude='*.out' /path/to/source/ user@hostB:/path/to/dest/
```
Where,
-a : Recurse into directories i.e. copy all files and subdirectories. Also, turn on archive mode and all other options (-rlptgoD)
-v : Verbose output
-e ssh : Use ssh for remote shell so everything gets encrypted
--exclude='*.out' : exclude files matching PATTERN e.g. *.out or *.c and so on.










