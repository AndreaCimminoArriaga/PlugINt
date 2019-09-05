# 1. Install the helio-core dependency in your local repository using this script
mvn clean install -DskipTests
mvn install:install-file -Dfile=./target/core-0.1.0.jar -DgroupId=cim.plugint -DartifactId=core -Dversion=0.1.0 -Dpackaging=jar

# 2. You are ready to go and use the framework 