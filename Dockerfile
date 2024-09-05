FROM bitnami/spark:3.5.1

# Set the environment variable for Spark home if necessary
ENV SPARK_HOME=/opt/bitnami/spark
ENV SPARK_VER=3.5.1
ENV SCALA_VER=2.12
ENV DELTA_VER=3.1.0
ENV HADOOP_AWS_VER=3.3.4
ENV ELASTIC_HADOOP_VER=8.6.2
ENV AWS_JAVA_SDK_VER=1.12.425
ENV KAFKA_VER=2.6.2
ENV COMMONS_POOL2_VER=2.11.1

USER root
RUN apt-get update && apt-get install -y curl && apt-get clean
#RUN curl -k https://repo1.maven.org/maven2/org/apache/spark/spark-streaming-kafka-0-10_2.12/3.5.1/spark-streaming-kafka-0-10_2.12-3.5.1.jar -o /opt/bitnami/spark/jars/spark-streaming-kafka-0-10_2.12-3.5.1.jar
#
## Copy the Kafka jar to the Spark jars directory
#RUN curl -k https://repo1.maven.org/maven2/org/apache/spark/spark-sql-kafka-0-10_2.12/3.5.1/spark-sql-kafka-0-10_2.12-3.5.1.jar -o /opt/bitnami/spark/jars/spark-sql-kafka-0-10_2.12-3.5.1.jar
#
#RUN curl -k https://repo1.maven.org/maven2/org/apache/kafka/kafka-clients/3.7.1/kafka-clients-3.7.1.jar -o /opt/bitnami/spark/jars/kafka-clients-3.7.1.jar

RUN rm -rf /opt/bitnami/spark/jars/delta-core*
RUN rm -rf /opt/bitnami/spark/jars/delta-spark*
RUN curl -fsSL https://repo1.maven.org/maven2/io/delta/delta-spark_${SCALA_VER}/${DELTA_VER}/delta-spark_${SCALA_VER}-${DELTA_VER}.jar -o /opt/bitnami/spark/jars/delta-spark_${SCALA_VER}-${DELTA_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/delta-storage-s3-dynamodb-*
RUN curl -fsSL https://repo1.maven.org/maven2/io/delta/delta-storage-s3-dynamodb/${DELTA_VER}/delta-storage-s3-dynamodb-${DELTA_VER}.jar -o /opt/bitnami/spark/jars/delta-storage-s3-dynamodb-${DELTA_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/delta-contribs_*
RUN curl -fsSL https://repo1.maven.org/maven2/io/delta/delta-contribs_${SCALA_VER}/${DELTA_VER}/delta-contribs_${SCALA_VER}-${DELTA_VER}.jar -o /opt/bitnami/spark/jars/delta-contribs_${SCALA_VER}-${DELTA_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/hadoop-aws-*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/hadoop/hadoop-aws/${HADOOP_AWS_VER}/hadoop-aws-${HADOOP_AWS_VER}.jar -o /opt/bitnami/spark/jars/hadoop-aws-${HADOOP_AWS_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/spark-hadoop-cloud_*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/spark/spark-hadoop-cloud_${SCALA_VER}/${SPARK_VER}/spark-hadoop-cloud_${SCALA_VER}-${SPARK_VER}.jar -o /opt/bitnami/spark/jars/spark-hadoop-cloud_${SCALA_VER}-${SPARK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/kafka-clients-*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/kafka/kafka-clients/${KAFKA_VER}/kafka-clients-${KAFKA_VER}.jar -o /opt/bitnami/spark/jars/kafka-clients-${KAFKA_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/spark-streaming-kafka-0-10_*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/spark/spark-streaming-kafka-0-10_${SCALA_VER}/${SPARK_VER}/spark-streaming-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar -o /opt/bitnami/spark/jars/spark-streaming-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/spark-token-provider-kafka-0-10_*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/spark/spark-token-provider-kafka-0-10_${SCALA_VER}/${SPARK_VER}/spark-token-provider-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar -o /opt/bitnami/spark/jars/spark-token-provider-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/spark-sql-kafka-0-10_*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/spark/spark-sql-kafka-0-10_${SCALA_VER}/${SPARK_VER}/spark-sql-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar -o /opt/bitnami/spark/jars/spark-sql-kafka-0-10_${SCALA_VER}-${SPARK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/elasticsearch-spark-30_2.13-*
RUN curl -fsSL https://repo1.maven.org/maven2/org/elasticsearch/elasticsearch-spark-30_2.13/${ELASTIC_HADOOP_VER}/elasticsearch-spark-30_2.13-${ELASTIC_HADOOP_VER}.jar -o /opt/bitnami/spark/jars/elasticsearch-spark-30_2.13-${ELASTIC_HADOOP_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/aws-java-sdk-core-*
RUN curl -fsSL https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-core/${AWS_JAVA_SDK_VER}/aws-java-sdk-core-${AWS_JAVA_SDK_VER}.jar -o /opt/bitnami/spark/jars/aws-java-sdk-core-${AWS_JAVA_SDK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/aws-java-sdk-s3-*
RUN curl -fsSL https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-s3/${AWS_JAVA_SDK_VER}/aws-java-sdk-s3-${AWS_JAVA_SDK_VER}.jar -o /opt/bitnami/spark/jars/aws-java-sdk-s3-${AWS_JAVA_SDK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/aws-java-sdk-dynamodb-*
RUN curl -fsSL https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-dynamodb/${AWS_JAVA_SDK_VER}/aws-java-sdk-dynamodb-${AWS_JAVA_SDK_VER}.jar -o /opt/bitnami/spark/jars/aws-java-sdk-dynamodb-${AWS_JAVA_SDK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/aws-java-sdk-sts-*
RUN curl -fsSL https://repo1.maven.org/maven2/com/amazonaws/aws-java-sdk-sts/${AWS_JAVA_SDK_VER}/aws-java-sdk-sts-${AWS_JAVA_SDK_VER}.jar -o /opt/bitnami/spark/jars/aws-java-sdk-sts-${AWS_JAVA_SDK_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/delta-storage-*
RUN curl -fsSL https://repo1.maven.org/maven2/io/delta/delta-storage/${DELTA_VER}/delta-storage-${DELTA_VER}.jar -o /opt/bitnami/spark/jars/delta-storage-${DELTA_VER}.jar
RUN rm -rf /opt/bitnami/spark/jars/commons-pool2-*
RUN curl -fsSL https://repo1.maven.org/maven2/org/apache/commons/commons-pool2/${COMMONS_POOL2_VER}/commons-pool2-${COMMONS_POOL2_VER}.jar -o /opt/bitnami/spark/jars/commons-pool2-${COMMONS_POOL2_VER}.jar

COPY target/spark-demo-0.0.1-SNAPSHOT.jar /opt/bitnami/spark/jars/

# Set the appropriate ownership (optional, depending on permissions)
RUN chown -R 1001:1001 /opt/bitnami/spark/jars/*

# Optionally, run other commands or install dependencies as needed
USER 1001
