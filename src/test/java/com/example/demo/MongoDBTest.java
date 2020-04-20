package com.example.demo;

import com.example.demo.mongodb.document.UserDocument;
import com.example.demo.mongodb.repository.UserDocumentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class MongoDBTest
{
    private static Logger logger = LoggerFactory.getLogger(MongoDBTest.class);

    @Autowired
    UserDocumentRepository userDocumentRepository;

    @Before
    public void setup()
    {
        Set<String> roles = new HashSet<>();
        roles.add("manage");
        UserDocument userDocument = new UserDocument("1", "user", "12345678", "name", "emain@com.cn", new Date(), roles);

        userDocumentRepository.save(userDocument);
    }
    @Test
    public void findAll()
    {
        List<UserDocument> userDocuments = userDocumentRepository.findAll();
        Assert.assertNotNull(userDocuments);
        for(UserDocument userDocument : userDocuments)
        {
            logger.info("===user=== userid:{} username:{} pass:{} registrationDate:{}",userDocument.getUserId(),
                    userDocument.getUserName(),userDocument.getPassword(),userDocument.getRegistrationDate());
        }
    }
}
