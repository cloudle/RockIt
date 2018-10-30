import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser

group = "le.cloud"
version = "0.0.1"

sourceSets {
	getByName("main").java.srcDirs("src/main/gen")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

buildscript {
	repositories {
		mavenCentral()
		maven { setUrl("https://jitpack.io") }
		maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
	}
	dependencies {
		classpath(kotlin("gradle-plugin", "1.2.40"))
		classpath("com.github.hurricup:gradle-grammar-kit-plugin:2018.1.7")
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

//val generateRockLexer = task<GenerateLexer>("generateRockLexer") {
//	source = "src/main/grammars/RockLexer.flex"
//	targetDir = "src/gen/le/cloud/lexer"
//	targetClass = "_RockLexer"
//	purgeOldFiles = true
//}
//
//tasks.withType<KotlinCompile> {
//	dependsOn(
//		generateRockLexer
//	)
//}
