echo 'Running Tests'
set PATH=C:\Program Files\Java\jdk1.8.0_162\bin;C:\apache-maven-3.6.1-bin\apache-maven-3.6.1\bin;%PATH%
mvn -s settings.xml clean test -DProjectName=ITSM-E2E -Dcucumber.options="--tags @tags" -DEnvironment=QA -DTestRailUpdateFlag=false -DreportMail=false || true