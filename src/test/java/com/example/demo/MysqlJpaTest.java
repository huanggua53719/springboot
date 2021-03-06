package com.example.demo;

import com.example.demo.jpa.entity.Department;
import com.example.demo.jpa.entity.Role;
import com.example.demo.jpa.entity.User;
import com.example.demo.jpa.repository.DepartmentRepository;
import com.example.demo.jpa.repository.RoleRepository;
import com.example.demo.jpa.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class MysqlJpaTest
{
    private static Logger logger = LoggerFactory.getLogger(MysqlJpaTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData()
    {
        userRepository.deleteAll();
        departmentRepository.deleteAll();
        roleRepository.deleteAll();

        Department department = new Department();
        department.setName("开发部");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());

        Role role = new Role();
        role.setName("admin");
        roleRepository.save(role);
        Assert.assertNotNull(role.getId());

        User user = new User();
        user.setName("user");
        user.setCreatedate(new Date());
        user.setDepartment(department);

        List<Role> roleList = roleRepository.findAll();
        Assert.assertNotNull(roleList);
        user.setRoles(roleList);

        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void findPage()
    {
        List<String> propertis = new ArrayList<>();
        propertis.add("id");
        Pageable pageable = PageRequest.of(0,10,Sort.by(Sort.Direction.ASC,"id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);
        for(User user : page.getContent())
        {
            logger.info("===user=== user.name:{} department.name:{} role.name:{} ",user.getName(),
                    user.getDepartment().getName(),user.getRoles().get(0).getName());
        }
    }

}
