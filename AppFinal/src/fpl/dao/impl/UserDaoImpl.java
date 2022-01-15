package fpl.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import fpl.dao.UserDao;
import fpl.entities.User;

@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Autowired
	private SessionFactory sessionfactory ;
    
    @Autowired
	public void setupSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void Save(User o) {
		sessionfactory.getCurrentSession().saveOrUpdate(o);
		getHibernateTemplate().saveOrUpdate(o);
	}

	@Override
	public void delete(User o) {
		sessionfactory.getCurrentSession().delete(o);
		getHibernateTemplate().delete(o);
		
	}

	@Override
	public User getByID(int id) {
		//return sessionfactory.getCurrentSession().get(User.class, id); 
		return getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		//return sessionfactory.getCurrentSession().createQuery("from User").list();
		
		DetachedCriteria c=DetachedCriteria.forClass(User.class).addOrder(Order.asc("id"));
		return (List<User>)getHibernateTemplate().findByCriteria(c);
	}

	@Override
	public List<User> getByExample(User u, MatchMode mode, boolean detached) {
		
		Example example =Example.create(u);
		example.enableLike(mode);
		example.ignoreCase();
		example.excludeZeroes();
		
		
		DetachedCriteria c=DetachedCriteria.forClass(User.class).add(example);
		@SuppressWarnings("unchecked")
		List<User> users =(List<User>)getHibernateTemplate().findByCriteria(c);
		
		if (detached) {
			for (User user :users) 
				  getHibernateTemplate().evict(user);
		}
		
		return users;
	}


}
