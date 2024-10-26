plugins {
    java
    `maven-publish`
}

group = "dev.dirs"
version = "26"

//java {
    // Ensures compatibility with older versions of Java
    //toolchain.languageVersion.set(JavaLanguageVersion.of(8))
//}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13")
    testImplementation("com.novocode:junit-interface:0.11")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
}

tasks.test {
    useJUnit()

    // Test options
    testLogging {
        events("passed", "skipped", "failed")
    }

    // Pass JUnit options
    systemProperty("junit.framework.TestCase", "-a")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set("directories")
                description.set("A Java project for managing directories")
                url.set("https://github.com/dirs-dev/directories-jvm")

                licenses {
                    license {
                        name.set("Mozilla Public License 2.0")
                        url.set("https://opensource.org/licenses/MPL-2.0")
                    }
                }

                scm {
                    connection.set("scm:git:git@github.com:dirs-dev/directories-jvm.git")
                    developerConnection.set("scm:git:git@github.com:dirs-dev/directories-jvm.git")
                    url.set("https://github.com/dirs-dev/directories-jvm")
                }

                developers {
                    developer {
                        id.set("soc")
                        name.set("Simon Ochsenreither")
                        url.set("https://github.com/soc")
                        roles.set(listOf("Project Lead"))
                    }
                }
            }
        }
    }

    repositories {
        // Set up Maven repository publishing (if necessary)
        // The following lines are commented out but can be enabled if publishing is needed.
        /*
        maven {
            val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
        }
        */
    }
}

