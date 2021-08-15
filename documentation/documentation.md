# Documentation

## PlantUML
#### Generating the image
Java 16 and PlantUML don't want to work together.
I use `jenv` to manage different java versions on my MacOS system.
To generate a image from plantUML file do the following (in terminal):
* `jenv local 1.8`
* `mvn plantuml:generate`
* `jenv local 16`
