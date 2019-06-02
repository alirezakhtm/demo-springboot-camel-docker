package com.khtm.demo.camel.camelservicedatabase.camel;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelDatabaseRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:D:\\Projects\\code-examples\\my-projects\\camel-route-directory\\input")
                .id("Camel-File-Transfer")
                .to("file:D:\\Projects\\code-examples\\my-projects\\camel-route-directory\\output")
                .log(LoggingLevel.INFO, "File Moved to Destination");

        from("file:D:\\Projects\\code-examples\\my-projects\\camel-route-directory\\output")
                .log(LoggingLevel.INFO, "New file found for analysis")
                //.log(LoggingLevel.INFO, "${body}");
                .to("bean:camelFileProcessorBean?method=analysisFile(${body})")
                //.split(body().tokenizeXML("users", "root").tokenizeXML("user", "users")).streaming()
                .log(LoggingLevel.INFO, "${body}");

    }
}
