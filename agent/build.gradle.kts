plugins {
    alias(libs.plugins.dependency)
    alias(libs.plugins.native)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSpring)
    alias(libs.plugins.springBoot)
}

group = "wiki.zyue"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation(libs.bundles.spring.boot)
    implementation(libs.bundles.kotlin.support)
    developmentOnly(libs.spring.boot.devtools)
    testImplementation(libs.bundles.spring.boot.test)
}


tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
