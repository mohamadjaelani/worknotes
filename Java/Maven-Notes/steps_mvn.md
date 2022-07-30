#### 1. type -> mvn archetype:generate
	[INFO] Scanning for projects...
	[INFO]
	[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
	[INFO] Building Maven Stub Project (No POM) 1
	[INFO] --------------------------------[ pom ]---------------------------------
	[INFO]
	[INFO] >>> maven-archetype-plugin:3.2.1:generate (default-cli) > generate-sources @ standalone-pom >>>
	[INFO]
	[INFO] <<< maven-archetype-plugin:3.2.1:generate (default-cli) < generate-sources @ standalone-pom <<<
	[INFO]
	[INFO]
	[INFO] --- maven-archetype-plugin:3.2.1:generate (default-cli) @ standalone-pom ---
	[INFO] Generating project in Interactive mode
	[INFO] No archetype defined. Using maven-archetype-quickstart (org.apache.maven.archetypes:maven-archetype-quickstart:1.0)
	Choose archetype:
	1: remote -> am.ik.archetype:elm-spring-boot-blank-archetype (Blank multi project for Spring Boot + Elm)
	........
	3114: remote -> za.co.absa.hyperdrive:component-archetype (-)
	3115: remote -> za.co.absa.hyperdrive:component-archetype_2.11 (-)
	3116: remote -> za.co.absa.hyperdrive:component-archetype_2.12 (-)
	Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 1924: 
#### 2. type -> maven-archetype-quickstart
	1: remote -> com.github.ywchang:maven-archetype-quickstart (Provide up-to-date java quickstart archetype)
	2: remote -> com.haoxuer.maven.archetype:maven-archetype-quickstart (a simple maven archetype)
	3: remote -> org.apache.maven.archetypes:maven-archetype-quickstart (An archetype which contains a sample Maven project.)
	Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): 3:
#### 3. choose -> 3
	Choose org.apache.maven.archetypes:maven-archetype-quickstart version:
	1: 1.0-alpha-1
	2: 1.0-alpha-2
	3: 1.0-alpha-3
	4: 1.0-alpha-4
	5: 1.0
	6: 1.1
	7: 1.3
	8: 1.4
	Choose a number: 8:
#### 4. choose -> 8 (the latest)
#### 5. Define value for property 'groupId': nu-aing
#### 6. Define value for property 'artifactId': ngoprek-java
#### 7. Define value for property 'version' 1.0-SNAPSHOT: : (press enter for default)
#### 8. Define value for property 'package' nu-aing: : ngoprek.java.baru
	Confirm properties configuration:
	groupId: nu-aing
	artifactId: ngoprek-java
	version: 1.0-SNAPSHOT
	package: ngoprek.java.baru
	 Y: :
#### 9. Y
	[INFO] ----------------------------------------------------------------------------
	[INFO] Using following parameters for creating project from Archetype: maven-archetype-quickstart:1.4
	[INFO] ----------------------------------------------------------------------------
	[INFO] Parameter: groupId, Value: nu-aing
	[INFO] Parameter: artifactId, Value: ngoprek-java
	[INFO] Parameter: version, Value: 1.0-SNAPSHOT
	[INFO] Parameter: package, Value: ngoprek.java.baru
	[INFO] Parameter: packageInPathFormat, Value: ngoprek/java/baru
	[INFO] Parameter: package, Value: ngoprek.java.baru
	[INFO] Parameter: groupId, Value: nu-aing
	[INFO] Parameter: artifactId, Value: ngoprek-java
	[INFO] Parameter: version, Value: 1.0-SNAPSHOT
	[INFO] Project created from Archetype in dir: E:\GIT\Java\belajar-juga\ngoprek-java
	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time:  08:16 min
	[INFO] Finished at: 2022-07-28T09:46:49+07:00
	[INFO] ------------------------------------------------------------------------
