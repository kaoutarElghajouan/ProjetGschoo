package fpl.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import fpl.dao.EtudiantDao;

import fpl.entities.Etudiant;



@Repository
public class EtudiantDaoImpl  extends HibernateDaoSupport implements EtudiantDao {

	    @Autowired
		private SessionFactory sessionfactory ;
	    
	    @Autowired
		public void setupSessionFactory(SessionFactory sessionFactory) {
			this.setSessionFactory(sessionFactory);
		}
		
		
		@Override
		public void Save(Etudiant o) 
		{
			sessionfactory.getCurrentSession().saveOrUpdate(o);
			//getHibernateTemplate().saveOrUpdate(o);
			
		}

		@Override
		public void delete(Etudiant o) 
		{
			sessionfactory.getCurrentSession().delete(o);
			//getHibernateTemplate().delete(o);
			
		}

		@Override
		public Etudiant getByID(int id)
		{
			
			return sessionfactory.getCurrentSession().get(Etudiant.class, id); 
			//return getHibernateTemplate().get(Etudiant.class, id);
		}

		
		@SuppressWarnings("unchecked")
		@Override
		public List<Etudiant> getAll() 
		{
			
			
			//return sessionfactory.getCurrentSession().createQuery("from Etudiant").list();
			
			DetachedCriteria c=DetachedCriteria.forClass(Etudiant.class).addOrder(Order.asc("id"));
			return (List<Etudiant>)getHibernateTemplate().findByCriteria(c);
		}


		@Override
		public List<Etudiant> getByExample(Etudiant e, MatchMode mode, boolean detached) {
			Example example =Example.create(e);
			example.enableLike(mode);
			example.ignoreCase();
			example.excludeZeroes();
			
			
			DetachedCriteria c=DetachedCriteria.forClass(Etudiant.class).add(example);
			@SuppressWarnings("unchecked")
			List<Etudiant> etudiants =(List<Etudiant>)getHibernateTemplate().findByCriteria(c);
			
			if (detached) {
				for (Etudiant etudiant :etudiants) 
					  getHibernateTemplate().evict(etudiant);
			}
			
			return etudiants;
			
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<Object[]> distributionByEntity(String entity) {
			DetachedCriteria critaria=DetachedCriteria.forClass(Etudiant.class)
					                                    .setProjection(Projections.projectionList().
					                                     add(Projections.groupProperty(entity)).
					                                     add(Projections.rowCount()));
			return (List<Object[]>) getHibernateTemplate().findByCriteria(critaria);
		}

	}



