# Documentation

## PlantUML
#### Generating the image
Java 16 and PlantUML don't want to work together.
I use `jenv` to manage different java versions on my MacOS system.
To generate a image from plantUML file do the following (in terminal):
* `jenv local 1.8`
* `mvn plantuml:generate`
* `jenv local 16`

## Set up Maven release
See: https://dzone.com/articles/how-to-create-and-release-a-jar-to-maven-central

## Maven build error
it gets ignored (when build), but.... it's there
here is the bug:
https://issues.apache.org/jira/browse/MJAVADOC-680