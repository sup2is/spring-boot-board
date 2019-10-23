package me.sup2is.board;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DataSourceTest {

	@Autowired
	DataSource dataSource;
	
	@Test
	public void DBConnectionTest() {
		assertNotNull(dataSource);
	}
	
}
