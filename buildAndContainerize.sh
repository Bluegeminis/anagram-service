#!/bin/bash
mvn clean package
docker build -t bluegeminis/anagram-service .