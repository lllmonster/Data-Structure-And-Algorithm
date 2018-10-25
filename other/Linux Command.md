# vi command
## SAVE AND EXIT
`ZZ` save and exit   
`:q!` discard all changes, since the last save and exit  
`:w` save file but dont exit  
`:wq` save and exit  

## NAVIGATING  
`j,k,h,l` - down, up, left and right  
`^` (caret) - home  
`$` - end  
`G` - move to the last line  
`{` - move backward one paragraph  
`}` - move forward one paragraph  

## DELETE
`x` - delete a single character  
`dd` - delete the current line  

## UNDOING
`u` - undo the last action  

## REFERENCE
[link](https://www.cs.colostate.edu/helpdocs/vi.html)  

# ps command
`ps -elf`  
`ps` gives information about processes on Linux.  
`ps -ef` To find out information about all the processes in the system   
The `-e` option selects all processes in the system  
The `-f` option gives the full listing  
The `-L` option is for getting information about the threads  

`ps -ef | grep processname`  
`-e` and `-f` are options to the `ps` command, and `|` take the output of one command and pass it as the input to another.  

`ps` - list processes  
`-e` - show all processes, not just those belonging to the user  
`-f` - show processes in full format (more detailed than default)  
`command 1 | command 2` - pass output of command 1 as input to command 2  
`grep` - find lines containing a pattern  
`processname` - the pattern for grep to search for in the output of ps -ef  
So altogether means: look for lines containing processname in a detailed overview/snapshot of all current processes, and display those lines  

