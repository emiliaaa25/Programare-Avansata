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
    implementation ("org.ow2.asm:asm:9.2")
    implementation ("org.ow2.asm:asm-util:9.2")
    implementation ("org.ow2.asm:asm-commons:9.2")
    implementation ("org.codehaus.janino:janino:3.1.2")

}

tasks.test {
    useJUnitPlatform()
}