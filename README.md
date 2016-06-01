This is a bootstrap project for a primer on Spring Boot.  Stay Hungry. Stay Humble.

# Building the code.  Clean deletes the target dir and wipes out the previous build.  Compile is self-explanatory, and package generates the jar file along with the application's dependencies.
mvn3 clean compile package

# Start/Run the application
# It may take a few seconds to start. It's ready to process requests when you see the message 'Started ServiceApplication in xx.xx seconds'

# NOTE, the first version as it stands will not start because there are no classes yet. 
java -jar target/spring-boot-primer-1.0.0.jar

# To start the server in debug so that you may attach your debugger, use:
java -Xdebug -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5009 -jar target/spring-boot-primer-1.0.0.jar


# Send a request to the web service
#This is the base request for processing the sample blog and loan requests.  Note that the output is piped to python - this is done to pretty print the JSON output.
#You can use either of the sample payloads included at the project root.
curl -H 'Content-Type: application/json' -H 'Accept: application/json' -XPOST -d @data.json http://localhost:8080/[URI] | python -mjson.tool
