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
