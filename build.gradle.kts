import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
}
group = "com.epam.cdp.caclulator"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}


dependencies {
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.0")
}

