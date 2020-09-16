# Mongo DB
## Installation
[REF](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)

## Install using .tgz Tarball on RedHat
[REF](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat-tarball/)  
### Version: 
MongoDB 4.4 Community Edition on RHEL
### Prerequisites:
* sudo yum install libcurl openssl xz-libs  
* ulimit > 64000  
* Configure SELinux if enforced  
### Install:
```
curl https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-rhel70-4.4.0.tgz -O
tar -xzvf mongodb-linux-x86_64-rhel70-4.4.0.tgz
```
### Default Directories: 
(if install using tarball, have to create default directories by yourself, non-default directory is also fine)	
/var/lib/mongo (the data directory)
/var/log/mongodb (the log directory)
```
sudo mkdir -p /var/lib/mongo
sudo mkdir -p /var/log/mongodb
sudo chown -R nomad:nomad /var/lib/mongo
sudo chown -R nomad:nomad /var/log/mongodb
```
### Run MongoDB
```
mongod --dbpath /var/lib/mongo --logpath /var/log/mongodb/mongod.log --fork
```
### Verify
Verify that MongoDB has started successfully by checking the process output for the following line in the log file /var/log/mongodb/mongod.log:
`[initandlisten] waiting for connections on port 27017`
### Begin using Mongo
`mongo`

## Additional Information
### Localhost Binding by Default	
By default, MongoDB launches with bindIP set to 127.0.0.1, which binds to the localhost network interface.
This means that the mongod can only accept coonections from clients that are running on the same machine.
Remote clients will not be able to connect to the mongod, and the mongod will not be able to initialize a replica set unless this value is set to a valid network interface.
	
The value can be configured either:
 * in the MongoDB configuration file with bindIp, or 
 * via the command line argument --bind_ip

## Security Checklist
[REF](https://docs.mongodb.com/manual/administration/security-checklist/)
* Enable Access Control and Enforce Authentication
* Configure Role-Based Access Control
* Encrypt Communication (TLS/SSL)
  [Configure-ssl](https://docs.mongodb.com/manual/tutorial/configure-ssl/)
* Encrypt and Protect Data
* Limit Network Exposure
* Audit System Activity
* Run MongoDB with a Dedicated User
* Run MongoDB with Secure Configuration Options
* Request a Security Technical Implementation Guide
* Consider Security Standards Compliance

## TLS/SSL
### TLS Version
Starting in version 4.0, MongoDB disables support for TLS 1.0 encryption on systems where TLS 1.1+ is available.
### TLS Libraries
Linux - OpenSSL
### Certificates
To use TLS/SSL with MongoDB, you must have the TLS/SSL certificates as PEM file.  
MongoDB can use any valid TLS/SSL certificate issued by a certificate authority or a self-signed certificate.  
### Set up mongod with TSL/SSL Certificate and Key
For mongod instance,  
| Setting | Notes |
| --- | --- |
| net.tls.mode | Set to requireTLS |
| net.tls.certificateKeyFile | Set to the path of the file that contains the TLS/SSL certificate and key. (The mongod instance presents this file to its clients to establish the instance's identity.) |

### Set up mongod with Client Certificate Validation
With these settings:  
* mongod presents its certificate key file to the client for verification
* mongod requires a certificate key file from the client to verify the client's identy

Configuration needed:  
| Setting | Notes |
| --- | --- |
| net.tls.mode | Set to requireTLS |
| net.tls.certificateKeyFile | Set to the path of the file that contains the TLS/SSL certificate and key. (The mongod instance presents this file to its clients to establish the instance's identity.) |
| net.tls.CAFile | Set to the path of the file that contains the certificate chain for verifying client certificates. (use this file to verify certificates presented by its clients. The certificate chain includes the certificate of the root Certificate Authority) |

### Example
[Openssl-certificate-generation-for-testing](https://docs.mongodb.com/manual/appendix/security/appendixB-openssl-server/#appendix-server-certificate)
[Script-in-Stackoverflow](https://stackoverflow.com/questions/35790287/self-signed-ssl-connection-using-pymongo#35967188)
* Connect to MongoDB Instance Using Encryption
  ```
  // server
  mongod --tlsMode requireTLS --tlsCertificateKeyFile <pem>
  // client
  mongo --tls --host hostname.example.com --tlsCAFile /etc/ssl/caToValidateServerCertificates.pem
  ```
* Connect to MongoDB Instance that Requires Client Certificates
  ```
  // server
  mongod --tlsMode requireTLS --tlsCertificateKeyFile /etc/ssl/mongodb.pem --tlsCAFile /etc/ssl/caToValidateClientCertificates.pem
  // client 
  mongo --tls --host hostname.example.com --tlsCertificateKeyFile /etc/ssl/client.pem --tlsCAFile /etc/ssl/caToValidateServerCertificates.pem
  ```

## Replication
Replication provides redundancy and high availability. With multiple copies of data on different database servers, replication provides a level of fault tolerance against the loss of a single database server.

Assuming we have three nodes in one replica set. One is the primary node and others are secondary nodes.

The primary node receives all write operations.  
The secondaries replicate the primary's oplog and apply the operations to their data sets. If the primary is unabailable, an eligible secondary will hold an election to elect itself the new primary.

Automatic Failover:  
When a primary does not communicate with the other members of the set for more than the configured `electionTimeoutMillis` period (10 seconds by default), an eligible secondary calls for an election to nominate itself as the new primary. The cluster attempts to complete the election of a new primary and resume normal operations.
  
## Get Started
Common Commands
```
// display the current database
db
// switch to the examples database
use examples
// connect to a remote host
mongo --host mongodb0.example.com:27017
// connect MongoDB Instance with Authentication
mongo "mongodb://alice@mongodb0.examples.com:28015/?authSource=admin"
mongo --username alice --password --authenticationDatabase admin --host mongodb0.examples.com --port 28015
// connect to a MongoDB Replica Set
mongo "mongodb://mongodb0.example.com.local:27017,mongodb1.example.com.local:27017,mongodb2.example.com.local:27017/?replicaSet=replA"
mongo --host replA/mongodb0.example.com.local:27017,mongodb1.example.com.local:27017,mongodb2.example.com.local:27017
// TLS/SSL Connection
mongo "mongodb://mongodb0.example.com.local:27017,mongodb1.example.com.local:27017,mongodb2.example.com.local:27017/?replicaSet=replA&ssl=true"
mongo --ssl --host replA/mongodb0.example.com.local:27017,mongodb1.example.com.local:27017,mongodb2.example.com.local:27017
```

## Appendix
### Version
MangoDB 4.4 Community Edition on RHEL
### Default Directory and User
/var/lib/mongo (the data directory)  
/var/log/mongodb (the log directory)  
mongod user (`sudo chown -R mongod:mongod <directory>`)  
SELinux status: disabled  
### Start MongoDB
```
# systemctl start mongod
# systemctl status mongod
// Start a mongo shell on the same host machine as the mongod, with default port 27017
# mongo
```
### Uninstall
```
# service mongod stop
# sudo yum erase $(rpm -qa | grep mongodb-org)
# sudo rm -r /var/log/mongodb
# sudo rm -r /var/lib/mongo
```
### Tutorial
#### Monitor
Enable MongoDB's free cloud-based monitoring service, which will then receive and display
metrics about your deployment (disk utilization, CPU, operation statistics, etc).

The monitoring data will be available on a MongoDB website with a unique URL accessible to you
and anyone you share the URL with. MongoDB may use this information to make product
improvements and to suggest MongoDB products and deployment options to you.

To enable free monitoring, run the following command: db.enableFreeMonitoring()
To permanently disable this reminder, run the following command: db.disableFreeMonitoring()

#### Get Started
MongoDB stores documents in `collections`. Collections are analogous to tables in relational databases. If a collection does not exist., MongoDB creates the collection when you first store data for that collection.
```
// Example, insert new documents into the inventory collection
db.inventory.insertMany([
   { item: "journal", qty: 25, status: "A", size: { h: 14, w: 21, uom: "cm" }, tags: [ "blank", "red" ] },
   { item: "notebook", qty: 50, status: "A", size: { h: 8.5, w: 11, uom: "in" }, tags: [ "red", "blank" ] },
   { item: "paper", qty: 10, status: "D", size: { h: 8.5, w: 11, uom: "in" }, tags: [ "red", "blank", "plain" ] },
   { item: "planner", qty: 0, status: "D", size: { h: 22.85, w: 30, uom: "cm" }, tags: [ "blank", "red" ] },
   { item: "postcard", qty: 45, status: "A", size: { h: 10, w: 15.25, uom: "cm" }, tags: [ "blue" ] }
]);

// MongoDB adds an _id field with an ObjectId value if the field is not present in the document
```