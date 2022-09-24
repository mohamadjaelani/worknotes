## Kafka
### Run zookeeper first
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
### Run kafka
    .\bin\windows\kafka-server-start.bat .\config\server.properties
### create topic
	.\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic TestTopic
### publish data to appropriate topic
	.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic TestTopic
  
	>{"Nama":"Mohamad Jaelani","Umur":44, "Pekerjaan":"Java Developer"} \
	>{"Nama":"Ahmad Fahmi","Umur":50, "Pekerjaan":"Uncal Consultant"} \
	>{"Nama":"Muhamad Indra","Umur":34, "Pekerjaan":"Java Developer"}
### consume data from appropriate topic
	.\bin\windows\kafka-console-consumer.bat --topic TestTopic --bootstrap-server localhost:9092 --from-beginning
  
	{"Nama":"Mohamad Jaelani","Umur":44, "Pekerjaan":"Java Developer"} \
	{"Nama":"Ahmad Fahmi","Umur":50, "Pekerjaan":"Uncal Consultant"} \
	{"Nama":"Muhamad Indra","Umur":34, "Pekerjaan":"Java Developer"}
