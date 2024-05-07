# CS157A FINAL PROJECT

# Requirements
- Java 8 or higher
- MySQL 8
- Maven (Optional; Maven wrapper included in project) 

# Instructions
## 1. Cloning Repo
- Run the following command in shell
```sh
git clone https://github.com/leo-kildani/CS157A-Final.git
./mvnw clean install
```

## 2. Setting up MySQL
- Run the following commands in shell
```sh
chmod +x ./run_sql.sh
./run_sql.sh
```
- By following the prompted instructions, a new user called "user" and password "password" will be made, and a new database called "cs157a_final" will be made. These will be used throughout the project.