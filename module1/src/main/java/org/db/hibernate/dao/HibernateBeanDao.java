package org.db.hibernate.dao;

import org.db.dao.BeanDao;
import org.db.hibernate.bean.Bean;
import org.springframework.stereotype.Repository;

@Repository("beanDao")
public class HibernateBeanDao extends AbstractHibernateBaseDao<Bean, Long> implements BeanDao {

    @Override
    protected Class<Bean> getDomainClass() {
        return Bean.class;
    }

    @Override
    public Long findNumberOfBeans() {
        return getObjectCount();
    }
}
