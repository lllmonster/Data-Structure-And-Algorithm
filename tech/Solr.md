## Docker image
```
docker pull solr
docker run -p 8983:8983 -t solr
docker exec -it $solr_container_name bash
```

## Check if Solr is Running
`bin/solr status`

## Start Solr with a Specific Bundled Example
`bin/solr -e techproducts`

## Index the Techproducts Data
Solr includes the `bin/post` tool in order to facilitate indexing various types of documents easily.
`bin/post -c techproducts example/exampledocs/*`

## Launch Solr in Solr Cloud Mode
[Ref](https://lucene.apache.org/solr/guide/8_6/solr-tutorial.html#launch-solr-in-solrcloud-mode)
```
./bin/solr start -e cloud -force
[7575]
[7574]
[7573]
```

## Launch Zookeeper Ensemble
[Ref](https://lucene.apache.org/solr/guide/8_6/setting-up-an-external-zookeeper-ensemble.html))

## Glossary
### SolrCloud
Umbrella term for a suite of functionality in Solr,
which allows managing a `Cluster` of Solr Nodes for scalability, fault tolerance, and high availability.
### Shard 
In `SolrCloud`, a logical partition of a single Collection.
Every shard consistes of at lease one physical `Replica`, but there may be multiple Replicas distributed across multiple Nodes for fault tolerance.
### Replica
A `Core` that acts as a physical copy of a `Shard` in a SolrCloud Collection.
### Core
An individual Solr instance (represents a logical index).
Multiple cores can run on a single node.