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