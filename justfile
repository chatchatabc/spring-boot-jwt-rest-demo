# build maven project
build:
   mvn -DskipTests clean package

# build frontend application with npm
frontend-build:
    cd "{{justfile_directory()}}/src/main/frontend" && npm run build

# init or re-init book_store database
db-init:
   mysql -u root -p123456  -e 'drop database IF EXISTS book_store; CREATE DATABASE book_store DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci;'

# display dependency tree
dependency-tree:
   mvn dependency:tree > dependency-tree.txt

# display oudated dependencies
dependency-updates:
   mvn versions:display-dependency-updates > dependency-updates.txt

# lint by spotbugs
lint:
  mvn compile spotbugs:check
  mvn compile spotbugs:gui

# mysql cli in container
mysql:
  docker-compose exec mysql /usr/bin/mysql -u root -p123456 book_store

# mysql cli for local
mysql-local:
  mysql -u root book_store -p123456 book_store

# clean project
clean:
   mvn clean
   rm -rf node
   rm -rf src/main/frontend/node
   rm -rf src/main/frontend/node_modules
   rm -rf src/main/frontend/dist