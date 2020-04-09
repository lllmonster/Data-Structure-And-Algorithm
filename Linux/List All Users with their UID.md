# AWK

List all users with a /home folder:

`awk -F: '/\/home/ {printf "%s:%s\n",$1,$3}' /etc/passwd`

or all users with a UID >= 1000:

`awk -F: '($3 >= 1000) {printf "%s:%s\n",$1,$3}' /etc/passwd`

a combination

`awk -F: '/\/home/ && ($3 >= 1000) {printf "%s:%s\n",$1,$3}' /etc/passwd`

or for all entries

`awk -F: '{printf "%s:%s\n",$1,$3}' /etc/passwd`