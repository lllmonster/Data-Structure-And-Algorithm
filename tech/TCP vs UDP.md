#### What is TCP
TCP (Transmission Control Protocol) is a connection-oriented protocol that computers use to communicate over the internet.
TCP provides error-checking and guarantee delivery of data and that packets will be delivered in the order they were sent.

#### What is UDP
UDP(User Datagram Protocol) is a coneection-less protocol that works just like TCP but assumes that error-checking and recovery services are not required. 
Instead, UDP continuously sends datagrams to the recipient whether they receive them or not.

#### Similarity
They both work on the transport layer of the TCP/IP protocol stack and both use the IP protocol

#### Difference
TCP | UDP
--- | ---
Connection | Connection-less
reliable | unreliable
Flow control | continuous stream
Order & Sequence | no order
slow | fast

#### Usage
**TCP**
* World Wide Web (http, https)
* SSH
* FTP (File Transfer Protocol)
* Email (SMTP, IMAP/POP)

**UDP**
* VPN tunneling
* Streaming videos
* Online games
* Live broadcasts
* DNS (Domain Name System)
* VolP (Voice over Internet Protocol)
* TFTP (Trivial File Transfer Protocol)