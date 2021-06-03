package com.elmenus.test.order.repository;

import com.elmenus.ElmenusApp;
import com.elmenus.order.dao.OrderRepository;
import com.elmenus.order.model.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Heba.ElGreatly on 6/3/2021.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElmenusApp.class)
@PropertySource("classpath:application.properties")
public class OrderRepositoryTests {

    @Inject
    private OrderRepository orderRepository;


    @Test
    @Transactional
    public void verifyOrderIsCreated(){
        Orders order = orderRepository.getOne(2);
        assertNotNull(order.getCreatedOn());
    }


    @Test
    @Transactional
    public void verifyOrderIsInPreOrderedStatus(){
        Orders order = orderRepository.getOne(1);
        assertNull(order.getCreatedOn());
    }
}
