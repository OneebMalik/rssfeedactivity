Rss Feed Activity
==============

### Build Requirements
1. Java SE Development Kit 8 or higher
2. Java SE Runtime Environment 8 or higher
3. Apache Ant
4. Preferably MacOS (where it has been tested fully).

### Build Instructions
1. Download files to your local machine.
2. In the root directory of the project, enter `ant clean compile jar` in your terminal of choice to create a compiled jar file.
3. To run, type `java -jar build/jar/RssFeedActivity.jar` if you are in the project root.
4. You can use the included `companies.xml` file in the root project directory as your input, or create your own. Just make sure the XML file is well-formed and complies with the input file requirements.

### Testing Instructions
* Type `ant test` in your terminal (making sure you're in the root project directory) to run the included JUnit tests.

### Input File Requirements
1. The input file must be a well-formed XML file.
2. The root element should be `<list>`.
3. Within `<list>`, you can have multiple `<company name="">` elements with a string name attribute.
4. Further, each `<company>` elements can have multiple `<url>` elements with valid URLs.

### Assumptions
1. The company RSS feed is RSS version 2.0 as stated in the `<rss>` element.
2. Input of company and URL key value pairs is from an XML document meeting the above mentioned requirements.
