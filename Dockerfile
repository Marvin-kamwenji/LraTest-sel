# syntax=docker/dockerfile:1

FROM openjdk:11

COPY . /src/main/java

WORKDIR /src/main/java

RUN ["javac", "E:\Coding\My projects\Backend Projects\Java Projects\LraStatusTests\src\main\java\EntryPoint.java"]


ENTRYPOINT ["java", "E:\Coding\My projects\Backend Projects\Java Projects\LraStatusTests\src\main\java\EntryPoint.java"]