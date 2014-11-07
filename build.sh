#!/bin/bash

clear; time mvn clean compile assembly:single
java -jar target/Part-of-Speech-Tagger-1.0.1-jar-with-dependencies.jar

