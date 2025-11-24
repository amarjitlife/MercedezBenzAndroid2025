
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

				export JAVA_HOME="/usr/lib/jvm/java-1.21.0-openjdk-amd64"
				export GRADLE_PATH="/home/amarjit/Documents/Softwares/gradle-9.2.1/bin"
				export KOTLIN_PATH="/home/amarjit/Documents/Softwares/Compilers/kotlinc/bin"

				export PATH=$PATH:$JAVA_HOME/bin/:$KOTLIN_PATH:$GRADLE_PATH

			In Windows
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
		gradle init

4. Build Gradle Project
		gradle build

5. Run Gradle Project
		gradle run

__________________________________________________________________________

		
