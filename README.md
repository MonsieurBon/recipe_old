# Recipe

## Run Database
`sudo docker run --name recipe-db -p 127.0.0.1:3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:10.5.4`