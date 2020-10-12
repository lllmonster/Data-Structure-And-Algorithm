# Vault
Vault comes with various pluggable components called secrets engines and authentication methods allowing you to integrate with external systems. 
The purpose of those components is to manage and protect your secrets in dynamic infrastructure (e.g. database credentials, passwords, API keys)

## Installation on Centos
```
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo
sudo yum -y install vault
vault // verify
```

## Your First Secret
### Write a secret
```
vault kv put secret/hello foo=world
vault kv put secret/hello foo=world excited=yes
```
### Get a secret
```
vault kv get secret/hello
vault kv get -field=excited secret/hello
vault kv get -format=json secret/hello | jq -r .data.data.excited
```
### Delete a secret
```
vault kv delete secret/hello
```
