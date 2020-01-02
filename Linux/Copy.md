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










