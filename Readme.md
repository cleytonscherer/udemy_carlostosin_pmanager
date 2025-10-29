docker run -d --name mongodb -p 27017:27017 mongodb/mongodb-community-server:latest
-v mongo_data:/data/db

docker run -d --name mongo -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -p 27017:27017 -v mongo_data:/data/db mongodb/mongodb-community-server:latest