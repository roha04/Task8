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
    testImplementation("com.h2database:h2:2.2.220")
    implementation("org.flywaydb:flyway-core:10.8.1")
    implementation(files("libs/h2-2.2.224.jar"))
    //runtimeOnly("com.h2database:h2:2.2.224")
}

tasks.test {
    useJUnitPlatform()
}