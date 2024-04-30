plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.oracle.database.jdbc:ojdbc8-production:19.7.0.0")
    implementation ("org.apache.commons:commons-csv:1.8")
}

tasks.test {
    useJUnitPlatform()
}