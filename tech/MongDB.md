# Mongo DB
## Installation
[REF](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)
### Version
MangoDB 4.2 Community Edition on RHEL
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