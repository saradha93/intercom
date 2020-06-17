# intercom - take home test 
The code is in Plain Java under 'distcalc' project. 
External libraries used : json-simple-1.1.1.jar (For JSON parsing), junit-4.13.jar and hamcrest-core-1.3.jar (For Junit). 
The src folder has three classes - 'Invoker.java' is the class that has the main method which needs to executed to run the program. 
'JsonFileParser.java' and 'DistanceCalculator.java' are classes that have the responsibilities of reading andparsing JSON input and selecting the customers to be invited. ParserAndDistanceCalculatorTests class contains junit tests. Configurable variables are retrieved from 'configuration.properties' under resources folder for maintainability and ease of change. The lib folder has the external dependencies and the input file - 'customers.txt'. Output of the program is in 'output.txt'.
