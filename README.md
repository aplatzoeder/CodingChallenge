## Tools used:
- IntelliJ Idea Community Edition 2022.3
- JDK 19.0.1
- Docker

## How to run the app:

### Console
// to do

### Docker
1. Open a console at the root folder
2. Build the docker image using the following command: `docker build -t antonp_codingchallenge .`. When building the 
image, all test input files (found under the '/CodingChallenge/testfiles' folder) are will be copied into the container at the path '/tmp/testfiles/'  
3. Run the container by executing this command:
`docker run antonp_codingchallenge /tmp/testfiles/input1.txt`

P.S. All test