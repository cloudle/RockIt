group = "le.cloud"
version = "0.0.1"

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath(kotlin("gradle-plugin", "1.2.40"))
	}
}

plugins {
	idea
	id("org.jetbrains.intellij") version "0.3.12"
	kotlin("jvm") version "1.2.40"
}

intellij {
	version = "2018.2.5"
	pluginName = "Rock-IntelliJ-Plugin"
	updateSinceUntilBuild = false
}

task("hello") {
	doLast {
		println("Hello, Gradle")
	}
}
