# Salesforce SOQL Parser

This is a java parser for  [SOQL](https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/sforce_api_calls_soql.htm) (Salesforce Query Language) implemented with [ANTLR 3](http://www.antlr3.org/). This parser was built following to the [SOQL syntax](https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/sforce_api_calls_soql_select.htm) definition provided by Salesforce.

There is a another implementation of the parser based on [ANTLR 4](http://www.antlr.org/) not yet released. The SOQL ANTLR 4 grammar can be seen [here](https://github.com/mulesoft/salesforce-soql-parser/blob/antlr4/SOQL.g4).

## Usage

You can use the parser generated by ANTLR straight away, but the simplest way to parse and process queries is by using the SOQLParserHelper.
With the SOQLParserHelper you can create a simpler object representation of the SOQL query.

```java
public String printSelectClause(String soqlText)
{
    //Use the SOQLParserHelper to create a SOQLQuery object containing the SOQL query data
    SOQLQuery queryData = SOQLParserHelper.createSOQLData(soqlText);
        
    //Get the object representation of part of the SOQL query 
    SelectClause selectClause = queryData.getSelectClause();
        
    //Use the toSOQLText method of any part of the query to convert it back to text
    return selectClause.toSOQLText();
}
```

You can also use the SOQLParserHelper to obtain the ANTRL AST of a SOQL query.

```java
SOQLCommonTree queryDataAST = SOQLParserHelper.createSOQLParserTree(soqlText);
```

To simplify query processing the SOQLQuery class accepts visitors. The easiest way to implement visitors for SOQLQuery is extending the SOQLDataBaseVisitor<T> class.

## Maven Configuration

### Maven dependency

```xml
<dependency>
    <groupId>org.mule.tools</groupId>
    <artifactId>salesforce-soql-parser</artifactId>
    <version>2.0</version>
</dependency>
```

### Maven repositories

```xml
<repositories>
    <repository>
        <id>mulesoft-releases</id>
        <name>MuleSoft Releases Repository</name>
        <url>http://repository.mulesoft.org/releases/</url>
        <layout>default</layout>
    </repository>
    <repository>
        <id>mulesoft-snapshots</id>
        <name>MuleSoft Snapshots Repository</name>
        <url>http://repository.mulesoft.org/snapshots/</url>
        <layout>default</layout>
    </repository>
</repositories>
```

## License

Common Public Attribution License Version 1.0 (CPAL)