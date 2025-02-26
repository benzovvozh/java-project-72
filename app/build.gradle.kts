plugins {
    id("java")
    checkstyle
    jacoco
    id("io.freefair.lombok") version "8.4"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
application { mainClass.set("hexlet.code.App") }

dependencies {
    implementation("com.h2database:h2:2.2.220")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("gg.jte:jte:3.1.9")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.0-rc1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0-rc1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()

}
tasks.jacocoTestReport { reports { xml.required.set(true) } }