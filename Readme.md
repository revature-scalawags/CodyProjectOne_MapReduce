# Word Count
Simple word counter MapReduce program for Hadoop

## Set up Hadoop
We'll be using Big Data Europe's dockerized Hadoop cluster:
```bash
git clone https://github.com/big-data-europe/docker-hadoop && cd docker-hadoop
docker-compose up -d
```

## Prepare dataset
```bash
# Create folder and populate with data
mkdir input && cd input
curl -o data.txt https://url-to-data.txt

# Create HDFS directory and put the dataset onto it
hadoop fs -mkdir -p input
hdfs dfs -put input/* input
```

## Package and copy the Word Count app into the cluster
```bash
sbt assembly
```

Use `docker ps` to get the Container ID of the namenode container, then
```bash
docker cp target/scala-2.13/word-count-assembly-1.0.jar {CONTAINER ID}:word-count-assembly.jar
```

## Enter the namenode container and run
```bash
docker exec -it namenode bash
hadoop jar word-count-assembly.jar input output
```

To read output:
```bash
hdfs dfs -cat output/part-r-00000
```
