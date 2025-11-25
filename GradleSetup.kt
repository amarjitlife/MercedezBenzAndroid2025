
__________________________________________________________________________

Installation Of Gradle Build System
__________________________________________________________________________

	1. Download Gradle Build Sytem File In ~/Documents/Softwares/
			https://gradle.org/
			https://gradle.org/releases/
			Download : gradle.9.2.1.zip

	2. Unzip gradle-9.2.1-bin.zip In ~/Documents/Softwares/
			Gradle Executable Are In Following Path

			/home/amarjit/Documents/Softwares/gradle-9.2.1/bin

	3. Add Gradle bin Directory Path in PATH Variable
			In Unix/Linux/Mac
				Add Following In .profile Or .bash_profile

				export JAVA_HOME="/usr/lib/jvm/java-1.21.0-openjdk-amd64"
				export GRADLE_PATH="/home/amarjit/Documents/Softwares/gradle-9.2.1/bin"
				export KOTLIN_PATH="/home/amarjit/Documents/Softwares/Compilers/kotlinc/bin"

				export PATH=$PATH:$JAVA_HOME/bin/:$KOTLIN_PATH:$GRADLE_PATH

			To Reload/ReApply Changed .profile Or .bash_profile Configuration
				Run Following Command In Terminal
					 . ~/.profile
					 . ~/.bash_profile

				Kill Terminal and Relaunch
					Changed Configuration Will Be Reloaded

			In Windows Add In Environment Variables
				Modify PATH Variable
					NOTE:: Please convert Following Path To w.r.t. Windows Notations
					//		Forward Will Become Backward Slash
					//		: Will Become ;
					//		Root Directory C:
					With /home/amarjit/Documents/Softwares/gradle-9.2.1/bin

__________________________________________________________________________

Creating Gradle Project
__________________________________________________________________________

1. Create Project Directory
		e.g. CoroutinesConcepts
		In ~/Documents/Trainings/MercedezBenz/CoroutinesConcepts

		mkdir CoroutinesConcepts

2. Make CoroutinesConcepts Directory As Current
		cd CoroutinesConcepts

3. Creating Gradle Project
		Select Following Options For gradle init Command

		Select type of build to generate:
		  1: Application
		  2: Library
		  3: Gradle plugin
		  4: Basic (build structure only)
		Enter selection (default: Application) [1..4] 1

		Select implementation language:
		  1: Java
		  2: Kotlin
		  3: Groovy
		  4: Scala
		  5: C++
		  6: Swift
		Enter selection (default: Java) [1..6] 2

		Enter target Java version (min: 7, default: 21): 21

		Project name (default: GradleProject): 

		Select application structure:
		  1: Single application project
		  2: Application and library project
		Enter selection (default: Single application project) [1..2] 1

		Select build script DSL:
		  1: Kotlin
		  2: Groovy
		Enter selection (default: Kotlin) [1..2] 1

		Select test framework:
		  1: kotlin.test
		  2: JUnit Jupiter
		Enter selection (default: kotlin.test) [1..2] 1

4. Build Gradle Project
		gradle build

5. Run Gradle Project
		gradle run

		Should Run Hello World Program Successfully!

__________________________________________________________________________

Creating Coroutine
__________________________________________________________________________


1. Import Coroutine Library In App.kt File

	import kotlinx.coroutines.*

2. Add Dependency For Coroutine Library
	 In File build.grade.kts Add Following

	 dependencies {
	    // This dependency is used by the application.
	    implementation(libs.guava)
	    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2") // For general coroutine functionality
	    // Add other coroutine modules as needed, e.g., kotlinx-coroutines-test for testing
	}

3. Write Your Code in App.kt File

	import kotlinx.coroutines.*


	// Concurrently executes both sections
	suspend fun doWorld() = coroutineScope { // this: CoroutineScope

	    launch { // C1 : Child
	        delay(2000L)
	        println("World 2")
	    }

	    launch { // C2: Child
	        delay(1000L)
	        println("World 1")
	    }
	    println("Hello")
	}

	// Sequentially executes doWorld followed by "Done"
	fun playWithCoroutines3() = runBlocking { // Run Blocking Coroutine : Parent
	    launch { // Run Blocking Coroutine : Parent
	        doWorld()
	        println("Done")
	    }
	}


__________________________________________________________________________
__________________________________________________________________________



