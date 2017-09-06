package org.db.dao;

import org.db.hibernate.bean.Bean;

public interface BeanDao extends BaseDao<Bean, Long> {

    Long findNumberOfBeans();
}
