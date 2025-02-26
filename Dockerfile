FROM gradle:7.4.0-jdk17

WORKDIR /app

COPY /app .

RUN gradle shadowJar

CMD ./build/install/app/bin/app