## Software/Tools used:
- IntelliJ Idea Community Edition 2022.3
- JDK 19.0.1 (https://www.oracle.com/za/java/technologies/downloads/#jdk19-mac)
- Docker
- Maven  

## Decisions made:
- I chose Docker to simplify testing on both Windows and Linux during dev
- End-to-end automated testing was done

## How to run the app:
You can either run it from the console/terminal or using Docker. But before attempting either remember to install jdk19.0.1.

### From the console:
Run the jar file 

`java -jar ./out/artifacts/CodingChallenge_jar/CodingChallenge.jar ./testfiles/validInput.txt`

### With Docker
1. Install docker
2. Open a console at the root folder of my project
2. Build the docker image using the following command: `docker build -t antonp_codingchallenge .`. When building the 
image, all test input files (found under the '/CodingChallenge/testfiles' folder) will be copied into the container at the path '/tmp/testfiles/'  
3. Run the container by executing this command:
`docker run antonp_codingchallenge ./testfiles/validInput.txt`


