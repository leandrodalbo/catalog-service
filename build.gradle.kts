import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.boot.demo"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

ext {

    set("springCloudVersion", "2023.0.0")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.springframework.cloud:spring-cloud-config-client")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")


}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("spring.profiles.active", "dev")
}

tasks.withType<BootRun> {
    systemProperty("spring.profiles.active", "dev")
}

