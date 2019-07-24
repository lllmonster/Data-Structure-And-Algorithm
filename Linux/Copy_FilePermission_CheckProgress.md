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

### File Permission

#### check the number of file Permission

```
$ stat -c "%a %n" file.txt
$ stat -c "%a %n" ./folder/
```



### Check Process

```
$ pgrep kdc
$ kill $(pid)
```

### Create and extract a tar.gz archive

```
$ tar -czvf tar-archive-name.tar.gz source-folder-name
$ tar -xzvf tar-archive-name.tar.gz
```

Where,

**x:** This option tells *tar* to extract the files.

**v:** The “v” stands for “verbose.” This option will list all of the files one by one in the archive.

**z:** The *z option* is very important and tells the *tar* command to uncompress the file (*gzip*).

**f:** This options tells *tar* that you are going to give it a file name to work with.

### Create Users and Groups

create users without a home dir and can be locked out of logging in

` # useradd username`

create users with a home dir

`# useradd -m username`

`# useradd -m username username -p Password `

create groups

`# groupadd groupname`

add users to the group

`# usermod -a -G groupname username`

check which users are already a member of a group

`# grep groupname /etc/group`

Check users in the system 

`# cut -d: -f1 /etc/passwd`



