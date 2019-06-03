package com.khtm.demo.camel.camelservicedatabase.camel;

import com.khtm.demo.camel.camelservicedatabase.camel.beans.CamelFileProcessorBean;
import com.khtm.demo.camel.camelservicedatabase.datamodel.Root;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CamelDatabaseRoute extends RouteBuilder {

    @Autowired
    public CamelFileProcessorBean camelFileProcessorBean;

    @Autowired
    private Environment environment;

    @Override
    public void configure() throws Exception {
        from("file:" + environment.getProperty("file-route.from"))
                .id("Camel-File-Transfer")
                .to("file:" + environment.getProperty("file-route.to"))
                .log(LoggingLevel.INFO, "File Moved to Destination");

        from("file:" + environment.getProperty("file-route.to"))
                .log(LoggingLevel.INFO, "New file found for analysis")
                .bean(camelFileProcessorBean, "analysisFile(${body})")
                .log(LoggingLevel.INFO, "${body}")
                .choice()
                    .when().simple("${body.id} == 'mysql'")
                        .log(LoggingLevel.INFO, "This data must enter into mysql database.")
                        .to("direct:mysql")
                    .when().simple("${body.id} == 'activemq'")
                        .log(LoggingLevel.INFO, "This data must enter into activemq.")
                        .to("direct:activemq");

        from("direct:mysql")
                .split().simple("${body.users}").streaming()
                .to("sql:insert into testdb.tbl_camel_user (name, lastname) " +
                        "values (:#${body.name}, :#${body.lastName})?dataSource=#dataSourceMysql");

        from("direct:activemq")
                .split().simple("${body.users}").streaming()
                .bean(camelFileProcessorBean,"getUserJson(${body})" )
                .to("activemq:queue:camel_user");
    }
}
