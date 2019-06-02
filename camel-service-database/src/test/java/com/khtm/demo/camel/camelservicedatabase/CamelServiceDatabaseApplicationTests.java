package com.khtm.demo.camel.camelservicedatabase;

import com.khtm.demo.camel.camelservicedatabase.datamodel.Root;
import com.khtm.demo.camel.camelservicedatabase.datamodel.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CamelServiceDatabaseApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void marshall() throws JAXBException {
        User user1 = new User();
        user1.setId(1);
        user1.setName("alireza");
        user1.setLastName("khatami doost");

        User user2 = new User();
        user2.setId(2);
        user2.setName("hamed");
        user2.setLastName("mirzaei");

        User user3 = new User();
        user3.setId(3);
        user3.setName("morteza");
        user3.setLastName("mosavi");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Root root = new Root();
        root.setUsers(users);
        root.setId("mysql");

        JAXBContext context = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(root, System.out);
    }

}
