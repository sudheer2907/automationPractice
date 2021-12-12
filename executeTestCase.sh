#!/bin/bash
#Global Variables
project_location=''
tags='@TestAPI'

echo "Running Tests"
echo "Project Location: $project_location"
echo "ENV: $env"
echo "TAGS: $tags"
echo "Fetching latest code"
git pull

mvn clean test -Dcucumber.options="--tags $tags" -DEnvironment=$env
exit