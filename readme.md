[![Master Build](https://github.com/mwttg/java-wavefront-reader/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/mwttg/java-wavefront-reader/actions/workflows/build.yml)
[![codecov](https://codecov.io/gh/mwttg/wavefront-reader/branch/master/graph/badge.svg?token=FTO54B66BH)](https://codecov.io/gh/mwttg/wavefront-reader)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mwttg_java-wavefront-reader&metric=alert_status)](https://sonarcloud.io/dashboard?id=mwttg_java-wavefront-reader)

# Java Wavefront Reader

A library for reading wavefront files (.obj) and transforming them to Java Arrays of float. 
These arrays can then be used by OpenGL (for VertexBufferObjects). 
The arrays are collected inside a data structure called `Wavefront`.

## Requirements

* Java 16
* Maven 3.8.1 (only if you want to build/extend it)

## How to use

Import this library to your project

<!!TODO maven central repository!!>

call the following function:

```
WavefrontReader.fromResource(filename)
```

* `filename` is the path and filename to the `.obj` file in your resource folder.

This returns a `Wavefront` data structure with filled float arrays for:

* vertices
* texture coordinates (only if information in .obj file is available)
* normals (only if information in .obj file is available)

## The Process

![Process Overview][process-overview]

## Release Notes

### 2021-08-22
Release Version 1.0.0

Read an .obj file and create a `Wavefront` data structure supported is:
* vertices
* uv (texture coordinates)
* normals (for light)


[comment]: <> (collection of links sorted alphabetically ascending)
[process-overview]: documentation/images/Process.png
