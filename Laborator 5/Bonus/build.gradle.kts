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
    implementation("org.freemarker:freemarker:2.3.31")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.0" )
    implementation ("org.apache.poi:poi-ooxml:5.2.3")
    implementation ("org.jgrapht:jgrapht-core:1.5.1")


}

tasks.test {
    useJUnitPlatform()
}