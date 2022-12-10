FROM amazoncorretto:19
COPY ./out/artifacts/ /tmp
COPY ./testfiles/ /tmp/testfiles
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "CodingChallenge.jar"]