# EndPoint-Demo

## Directory Web App

Directory Web App is a simple web application that allows users to interactively create, move, and delete hierarchical directories.

## Features

- Create directories with the command `CREATE [path]`.
- Move directories with the command `MOVE [source] [destination]`.
- Delete directories with the command `DELETE [path]`.
- List the current directory structure with the command `LIST`.

## Prerequisites

To run this project, you need the following tools installed on your machine:
- Java Development Kit (JDK) 11 or later 
- Maven 3.6.0 or later

## Running the project

1. Clone the repository:
```
git clone https://github.com/zheny-247/EndPoint-Demo.git
```

2. Go to the project directory:
```
cd EndPoint-Demo
```

3. Build the project using Maven:
```
mvn clean package
```

4. Run the application:
```
java -jar target/demo-1.0.0.jar
```

5. Open your web browser and go to `http://localhost:8080`.
You should now see the Directory Web App interface, where you can interact with the application by entering commands in the input field.

## Troubleshoot
If you see the below error when running `mvn clean package`, you need to set JAVA_HOME correctly, check [Maven Support](https://docs.oracle.com/cd/E50457_04/12133/OEPUG/maven.htm#CHDHAGBA)
```
The JAVA_HOME environment variable is not defined correctly,
this environment variable is needed to run this program.
```
