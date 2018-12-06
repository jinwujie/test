package com.demo;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.pojo.Customer;
import com.demo.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDateJpaApplicationTests {

	@Inject
	private CustomerService cs;

	@Autowired
	EntityManager em;

	@Ignore
	@Test
	public void test() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Customer> c = cb.createQuery(Customer.class);
		Root<Customer> emp = c.from(Customer.class);
		c.select(emp).where(cb.equal(emp.get("name"), "John Smith"));
		System.out.println();
	}

	@Ignore
	@Test
	public void test1() {
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			customer.setId(i + 5);
			customer.setFirstName("jin");
			customer.setLastName("wujie" + i);
			cs.save(customer);
		}

		List<Customer> list = cs.findAll();
		for (Customer c : list) {
			System.out.println(c);
		}
	}

	public void test2() {
		String persistenceUnitName = "SpringDateJPA";
		
		//1、创建 EntityManagerFactory
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(persistenceUnitName);
		
		//2、 创建EntityManager 相当于 hibernate 中的SessionFactory
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//3、开启事务
		EntityTransaction  transaction=entityManager.getTransaction();//获取事务对象
		transaction.begin();//开启事务
		//4、进行持久化操作
		Customer customer = new Customer();
		customer.setFirstName("jin");
		customer.setLastName("wujie");
		//持久化数据
		entityManager.persist(customer);
		//5、提交事务
		transaction.commit();
		//6、关闭 EntityManager
		entityManager.close();
		//7、关闭 EntityManagerFactory
		entityManagerFactory.close();
	}

}
