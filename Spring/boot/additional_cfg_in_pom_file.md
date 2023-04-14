### add below on build element in pom.xml file
    <resources>
			<resource>
				<directory>src/main/resources</directory>
				<filtering>true</filtering>
			</resource>
		</resources>
### add below on project element in pom.xml file. profiles element is sibling of buile element
    <profiles>
        <profile>
          <id>Development</id>
          <activation>
            <activeByDefault>false</activeByDefault>
          </activation>
          <properties>
            <build.profile.id>development</build.profile.id>
            <profileActive>development</profileActive>
          </properties>
        </profile>
        <profile>
          <id>Staging</id>
          <activation>
            <activeByDefault>false</activeByDefault>
          </activation>
          <properties>
            <build.profile.id>staging</build.profile.id>
            <profileActive>staging</profileActive>
          </properties>
        </profile>
        <profile>
          <id>Production</id>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
          <properties>
            <build.profile.id>production</build.profile.id>
            <profileActive>production</profileActive>
          </properties>
        </profile>
      </profiles>