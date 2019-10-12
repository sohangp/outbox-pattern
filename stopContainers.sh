echo -e "\nStopping containers ....."
docker stop $(docker ps -a | grep postgres | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep kafka | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep zookeeper | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep connect | cut -d ' ' -f 1)
docker rm postgres
docker rm kafka
docker rm zookeeper
docker rm debezium-connect
echo -e "\nAll Containers stopped."

