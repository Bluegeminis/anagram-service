To build the application:

1) Ensure that both Docker and Maven are installed and their executables in your PATH
2) Execute the buildAndContainerize.sh script
	This will build the Spring Boot executable jar and containerize it to bluegeminis/anagram-service
	The Maven build will execute unit and integration tests
3) Expose the service locally by using Docker compose
	Execute docker-compose build
	Execute docker-compose up
4) Exercise the service by calling the exerciseAnagramApp.sh