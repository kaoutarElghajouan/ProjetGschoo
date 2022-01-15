package fpl.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import fpl.dao.FiliereDao;

import fpl.entities.Filiere;
@Repository
public class FiliereDaoImpl  extends HibernateDaoSupport implements FiliereDao {
    @Autowired
	private SessionFactory sessionfactory ;
    
    @Autowired
	public void setupSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public void Save(Filiere o) 
	{
		sessionfactory.getCurrentSession().saveOrUpdate(o);
		getHibernateTemplate().saveOrUpdate(o);
		
	}

	@Override
	public void delete(Filiere o) 
	{
		sessionfactory.getCurrentSession().delete(o);
		getHibernateTemplate().delete(o);
		
	}

	@Override
	public Filiere getByID(int id)
	{
		
		return sessionfactory.getCurrentSession().get(Filiere.class, id); 
		//return getHibernateTemplate().get(Filiere.class, id);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Filiere> getAll() //HQL
	{
		
		
		//return sessionfactory.getCurrentSession().createQuery("from Filiere").list();
		
		DetachedCriteria c=DetachedCriteria.forClass(Filiere.class).addOrder(Order.asc("id"));
		return (List<Filiere>)getHibernateTemplate().findByCriteria(c);
	}




	@Override
	public List<Filiere> getByExample(Filiere f, MatchMode mode, boolean detached) {
		Example example =Example.create(f);
		example.enableLike(mode);
		example.ignoreCase();
		example.excludeZeroes();
		
		
		DetachedCriteria c=DetachedCriteria.forClass(Filiere.class).add(example);
		@SuppressWarnings("unchecked")
		List<Filiere> filieres =(List<Filiere>)getHibernateTemplate().findByCriteria(c);
		
		if (detached) {
			for (Filiere filiere :filieres) 
				  getHibernateTemplate().evict(filiere);
		}
		
		return filieres;
		
	}
	
		
		
}

