FROM maven:3.8.4-openjdk-11 AS server_build

WORKDIR /usr/src/app

COPY . ./

RUN mvn install -DskipTests

FROM openjdk:11 AS prod

WORKDIR /server

COPY --from=server_build /usr/src/app/target/carto-server.jar carto-server.jar

ENV PORT=5000
ENV FIREBASE_ADMIN_JSON=EXAMPLE

ENV PGHOST=EXAMPLE
ENV PGPORT=EXAMPLE
ENV PGDATABASE=EXAMPLE
ENV PGUSER=EXAMPLE
ENV PGPASSWORD=EXAMPLE

ENTRYPOINT ["java", "-jar","carto-server.jar"]

EXPOSE ${PORT}
