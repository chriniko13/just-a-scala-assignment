### Just a Scala Assignment

###### Assignee: Nikolaos Christidis (nick.christidis@yahoo.com)
<hr>

##### Technical Approach
For input file which is less or equal to 10 lines it uses simple recursive solution.
For bigger input file it uses bottom-up dynamic programming approach.


##### Build project
* Execute: `sbt compile`


##### Run project (main class)
* Execute: `sbt run`


##### Package as jar
* Execute: `sbt package`
* (Optional) you can run it then: `java -jar target/scala-2.12/chriniko-scala-assignment_2.12-0.1.jar`


##### Run Tests
* Execute: `sbt test`


##### Test Coverage Reports (with the help of: [sbt-scoverage](https://github.com/scoverage/sbt-scoverage))
* Execute: `sbt clean coverage test`
* Execute: `sbt coverageReport`
* Then go to: `target/scala-2.12/scoverage-report/index.html` and open file url to a browser.


##### Test Utilities
* You can use the `TrianglePathFileCreator.scala` in order to generate a triangle path file (random or not)
 