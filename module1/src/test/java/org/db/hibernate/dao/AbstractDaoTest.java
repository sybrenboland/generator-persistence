package org.db.hibernate.dao;

import org.configuration.DaoTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoTestConfiguration.class })
@Transactional(transactionManager = "transactionManager")
abstract class AbstractDaoTest {

}
