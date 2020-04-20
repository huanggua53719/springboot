package com.example.demo;

import com.example.demo.jpa.entity.Department;
import com.example.demo.jpa.entity.Role;
import com.example.demo.jpa.entity.User;
import com.example.demo.redis.repository.UserRedis;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class RedisTest
{
    private static Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    UserRedis userRedis;

    @Before
    public void setup()
    {

        Department department = new Department();
        department.setName("开发部");

        Role role = new Role();
        role.setName("admin");

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);

        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        user.setRoles(roleList);

        userRedis.delete(this.getClass().getName()+":userByname:"+user.getName());
        userRedis.add(this.getClass().getName()+":userByname:"+user.getName(), 10L, user);

    }

    @Test
    public void get()
    {
        User user = userRedis.get(this.getClass().getName() + ":userByname:user");
        Assert.assertNotNull(user);
        logger.info("===user=== user.name:{} department.name:{} role.name:{} ",user.getName(),
                user.getDepartment().getName(),user.getRoles().get(0).getName());
    }
}
