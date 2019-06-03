package com.khtm.demo.camel.camelservicedatabase.camel.beans;

import com.google.gson.GsonBuilder;
import com.khtm.demo.camel.camelservicedatabase.datamodel.Root;
import com.khtm.demo.camel.camelservicedatabase.datamodel.User;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CamelFileProcessorBean {

    public Root analysisFile(String fileContent) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshall = context.createUnmarshaller();
        Root root = (Root) unmarshall.unmarshal(new InputStreamReader(IOUtils.toInputStream(fileContent)));
        return root;
    }

    public String getUserJson(User user){
        return new GsonBuilder().create().toJson(user, User.class);
    }

}
