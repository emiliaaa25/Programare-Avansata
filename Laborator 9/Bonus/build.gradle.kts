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
    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.1")

    implementation("com.oracle.database.jdbc:ojdbc8-production:19.7.0.0")
    implementation("org.jgrapht:jgrapht-core:1.1.0")
    implementation ("org.apache.commons:commons-csv:1.8")
    implementation ("javax.persistence:javax.persistence-api:2.2")
    implementation ("org.eclipse.persistence:eclipselink:2.7.9")
    implementation ("org.choco-solver:choco-solver:4.10.7")


}

tasks.test {
    useJUnitPlatform()
}