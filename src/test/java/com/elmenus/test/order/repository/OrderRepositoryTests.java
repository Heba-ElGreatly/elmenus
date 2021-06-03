package com.elmenus.test.order.repository;

import com.elmenus.ElmenusApp;
import com.elmenus.order.dao.OrderRepository;
import com.elmenus.order.model.Orders;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
//    @RepeatedTest(4)
    @Transactional
    public void verifyTotalOrderIsGreaterThanTheMinimum(){
        Orders order = orderRepository.getOne(2);
        assertThat(order.getTotal(), Matchers.greaterThan(100.0));

    }

////@Test
//    @RepeatedTest(5)
//    public void test() {
//        System.out.println("@RepeatedTest Simple Example");
//    }
//
//    @RepeatedTest(3)
//     void repeatedTest(TestInfo testInfo) {
//        System.out.println("Executing repeated test");
//
//        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
//    }
}
