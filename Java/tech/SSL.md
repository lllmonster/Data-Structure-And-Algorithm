## SSL and Java Keytool Tutorial

### Table

[Introduction](#introduction)

[SSL](###SSL)

[Private Keys](###private-keys)

[Public Key](###public-key)

[Root Certificate](###root-certificate)

[Certificate Authorities](###Certificate-authorities)

[Certificate Chain](###certificate-chain)

[Keystore using Java Keytool](###keystore-using-java-keytool)

[Truststore and Keystore](###truststore-and-keystore)

[Keytool Commands](###keytool-commands)

[Configure SSL on Apache Tomcat](###configure-ssl-on-apache-tomcat)

[Reference](###Reference)

[Understand](###understand)

### Introduction

Most of sites use the Socket Layer (SSL) protocol to secure their Internet applications. SSL allows the data from a client, such a web browser, to be encrypted prior to transmission.

### SSL

A HTTP-based SSL connection is always initiated by the client using a URL starting with https:// instead of with http://. At the beginning of an SSL session, an SSL handshake is performed. This handshake produces the cryptographic parameters of the session. 

Process:

 	1. A browser requests a secure page (usually https://)
 	2. The web server sends its public key with its certificate.
 	3. The browser checks that the certificate was issued by a trusted party (usually a trusted root CA), that the certificate is still valid and that the certificate is related to the site contacted.
 	4. The browser then uses the public key, to encrypt a random symmetric encryption key and sends it to the server with the encrypted URL required as well as other encrypted http data.
 	5. The web server decrypts the symmetric encryption key using its private key and uses the symmetric key to decrypt the URL and http data.
 	6. The web server sends back the requested html document and http data encrypted with the symmetric key.
 	7. The browser decrypts the http data and html document using the symmetric key and displays the information.

The world of SSL has, essentially, three types of certificates: private keys, public keys (also called public certificates or site certificates), and root certificates.

### Private Keys

The private key contains the identity information of the server, along with a key value. It should keep this key safe and protected by password because it’s used to negotiate the hash during the handshake. It can be used by someone to decrypt the traffic and get your personal information. It like leaving your house key in the door lock.

### Public Key

The public certificate (public key) is the portion that is presented to a client, it likes your personal passport when you show in the Airport. The public certificate, tightly associated to the private key, is created from the private key using a Certificate Signing Request (CSR). After you create a private key, you create a CSR, which is sent to your Certificate Authority (CA). The CA returns a signed certificate, which has information about the server identity and about the CA.

### Root Certificates

Root CA Certificate is a CA Certificate which is simply a Self-signed Certificate. This certificate represents a entity which issues certificate and is known as Certificate Authority or the CA such as VeriSign, Thawte, etc.

### Certificate Authorities

Companies who will sign certificates for you such as VeriSign, Thawte, Commodo, GetTrust. Also, many companies and institutions act as their own CA, either by building a complete implementation from scratch, or by using an open source option, such as OpenSSL.

### Certificate Chain

When a server and client establish an SSL connection, a certificate is presented to the client; the client should determine whether to trust this certificate, a process called the certificate chain. The client examines the issuer of a certificate, searches its list of trusted root certificates, and compares the issuer on the presented certificate to the subjects of the trusted certificates.

If a match is found, the connection proceeds. If not, the Web browsers may pop up a dialog box, warning you that it cannot trust the certificate and offering the option to trust the certificate.

### Keystore using Java keytool

Java Keytool is a key and certificate management utility. It allows users to manage their own public/private key pairs and certificates. Java Keytool stores the keys and certificates in what is called a **keystore**. It protects private keys with a password.

Each certificate in a Java keystore is associated with a unique alias. When creating a Java keystore you will first create the .jks file that will initially only contain the private key, then generate a CSR. Then you will import the certificate to the keystore including any root certificates.

### Truststore and Keystore

A keystore contains private keys, and the certificates with their corresponding pubic keys. Keystore file, keystore.jks, contains the server’s certificate, including its private key.

A truststore contains certificates from other parties that you expect to communicate with, or from Cerificate Authorities that you trust to identify other parties. Truststore file, cacerts.jks, contains the server’s trusted certificates, including public keys for other entities. For a trusted certificate, the server has confirmed that the public key in the certificate belongs to the certificate’s owner. Trusted certificates generally include those of certification authorities(CAs).

### Keytool Commands

create a keystore and key pair

`keytool -genkey -alias keyAlias -keyalg RSA -keystore keystore.jks -storepass password`

create a keystore and a self-signed certificate in a keystore, keystore.jks, of type JKS suing the default key algorithm

`keytool -genkey -alias keyAlias -keyalg RSA  -keypass changeit  -storepass changeit -keystore keystore.jks`

create a certificate signing request (CSR), client.csr, for an existing keystore

`keytool -certreq -alias keyAlias -keystore keystore.jks -storepass password -file client.csr`

display available certificates from a keystore, keystore.jks

`keytool -list -v -keystore keystore.jks`

display certificate information from a keystore, keystore,jks

`keytool -list -v -alias keyAlias -keystore keystore.jks`

generate certificate to the file client.cer, and export it from a keystore

`keytool -export -alias keyAlias -file client.cer -keystore keystore.jks`

create the truststore file cacerts.jks and import the certificate to the truststore

`keytool -import -v -trustcacerts -alias keyAlias -file client.cer -keystore cacerts.jks`

delete a certificate

`keytool -delete -alias keyAlias -keystore keystore.jks`

change a keystore password

`keytool -storepasswd -new new_storepass -keystore keystore.jks -storepass password`

change a private key password

`keytool -keypasswd -alias client -keypass old_password -new new_password -keystore client.jks -storepass password`

### Configure SSL on Apache Tomcat

Configure Tomcat's sever to support for SSL or https connection. Adding a connector element in Tomcat\conf\server.xml

`<Connector port="8443" maxThreads="150" scheme="https" secure="true"
SSLEnabled="true" keystoreFile="/home/ashraf/Desktop/JavaCodeGeek/.keystore" keystorePass="password" clientAuth="false" keyAlias="tomcat" sslProtocol="TLS" />`

Start Tomcat and go to <https://localhost:8443/>, you will find the following security issue where the browser will present untrusted error messages. In the case of e-commerce, such error messages result in immediate lack of confidence in the website and organizations risk losing confidence and business from the majority of consumers, that's normal as your certificate isn't signed yet by CA such as Thawte or Verisign who will verify the identity of the requester and issue a signed certificate.

You can click Proceed anyway till you receive you signed certificate.

### Reference

[Tutorial Details](https://www.javacodegeeks.com/2014/07/java-keystore-tutorial.html)

[Oracle Doc](<https://docs.oracle.com/cd/E19830-01/819-4712/ablqw/index.html>)

### Understand

Keystore is to store private key. And truststore is to store the public key. For the server, you need to generate a certificate of client and then import it into the truststore of server. In this way, you build the connection between sever and client.
