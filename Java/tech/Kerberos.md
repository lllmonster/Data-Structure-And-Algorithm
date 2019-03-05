### Configuration Kerberos Server and Client

#### How does Kerberos Authentication work

Assuming there are three machines, Domain Controller, Client, and Server.

The procedure is as follow:

 	1. Client requests an authentication ticket (TGT) from the Key Distribution Center (KDC)
 	2. The KDC verifies the credentials and sends back an encrypted TGT and session key
 	3. TGT is encrypted using the Ticket Granting Service (TGS) secret key
 	4. Clients store the TGT and when it expires the local session manager will request another TGT

If Client is requesting access to a service or other resource on the network, this is the process:

 	1. Client sends the current TGT to the TGS with the Service Principal Name (SPN) of the resource the client wants to access
 	2. KDC verifies the TGT of the user and that the user has access to the service
 	3. TGS sends a valid session key for the service to the client
 	4. Client forwards the session key to the service for access

#### Configuration a kerberos Server 

Install kerberos server

```
# yum install krb5-server krb5-libs krb5-auth-dialog
```

Edit KDC Configuration files

```
# vim /etc/krb5.conf
# vim /var/kerberos/krb5kdc/kdc.conf
# vim /var/kerberos/krb5kdc/kadm5.acl
```

Create KDC database

```
# kdb5-util create -r EXAMPLE.COM -s
```

Add administrators to the kadm5.acl file

```
*/admin@EXAMPLE.COM*
```

Start the Kerberos daemons on the master KDC

```
# krb5kdc
# kadmind
# tail /var/log/krb5kdc.log  //Info: commencing operation
# tail /var/log/kadmin.log   // Info: starting
```

Add SPN

```
# kadmin.local
kadmin.local: addprinc admin/admin@EXAMPLE.COM
```

Verify principal that you have created sucessfully

```
# kinit admin/admin@EXAMPLE.COM
```

Generate a keytab file

```
# kadmin.local: xst -k example.keytab admin/admin@EXAMPLE.COM
# kadmin.local: xst -k example1.keytab user/admin@EXAMPLE.COM
```

Verify keytab file

```
# kinit -k -t example.keytab admin/admin@EXAMPLE.COM
```

Merge keytab file

```
# ktutil
   ktutil: rkt example.keytab
   ktutil: rkt example1.keytab
   ktutil: wkt merge_example.keytab
   ktutil: clear
```

Verify merge_keytab file

```
# kinit -k -t merge_example.keytab admin/admin@EXAMPLE.COM
# kinit -k -t merge_example.keytab user/admin@EXAMPLE.COM
```

Display keytab entries

```
# klist -e -k -t example.keytab
```

Trace log

```
#  env KRB5_TRACE=/dev/stderr kinit -k -t example.keytab admin/admin@EXAMPLE.COM
```

#### Configuration a Kerberos Client

Install Kerberos on Client

```
# yum install krb5-workstation krb5-libs
```

Make sure KDC server exists in the `/etc/hosts/` file 

Copy `krb5.conf`, `kdc.conf`, `kadm5.acl` , `example.keytab`files from KDC server to the client.

Verify

```
# kinit -k -t example.keytab admin/admin@EXAMPLE.COM
```

#### Trouble Shooting

kinit: unable to reach any KDC in realm EXAMPLE.COM

- Make sure sever is started

- Make sure krb5.conf is correct

- Is the EXAMPLE.COM domain declared in your DNS (or /etc/hosts file)?

- Verify all DNS servers are working fine. And if connected successfully between client and server, Firewall rules

  ```
  $ telnet kdcserver 88
  $ systemctl stop firewalld
  $ systemctl status firewalld
  ```

#### Reference

[Turtorial](https://www.varonis.com/blog/kerberos-authentication-explained/)

[MIT-KDC](https://web.mit.edu/kerberos/krb5-devel/doc/admin/install_kdc.html)

