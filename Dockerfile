FROM amazoncorretto:19
COPY ./out/artifacts/CodingChallenge_jar/ /tmp
COPY ./testfiles/ /tmp/testfiles
WORKDIR /tmp
ENTRYPOINT ["java","-jar", "CodingChallenge.jar"]