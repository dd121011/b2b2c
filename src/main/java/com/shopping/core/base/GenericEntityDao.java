package com.shopping.core.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
//import org.springframework.orm.jpa.JpaCallback;
//import org.springframework.orm.jpa.JpaTemplate;
//import org.springframework.orm.jpa.support.JpaDaoSupport;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

public class GenericEntityDao implements EntityManager
{
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNamedQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, Class arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(CriteriaUpdate arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Query createQuery(CriteriaDelete arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, Class... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void detach(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityTransaction getTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isJoinedToTransaction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void joinTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T merge(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
//	 public int batchUpdate(final String jpql, final Object[] params) {
//		    Object ret = getJpaTemplate().execute(new JpaCallback()
//		    {
//		      public Object doInJpa(EntityManager em) throws PersistenceException {
//		        Query query = em.createQuery(jpql);
//		        int parameterIndex = 1;
//		        if ((params != null) && (params.length > 0)) {
//		          for (Object obj : params) {
//		            query.setParameter(parameterIndex++, obj);
//		          }
//		        }
//		        query.setHint("org.hibernate.cacheable", Boolean.valueOf(true));
//		        return Integer.valueOf(query.executeUpdate());
//		      }
//		    });
//		    return ((Integer)ret).intValue();
//		  }

	public int batchUpdate(String jpql, Object[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List executeNamedQuery(String queryName, Object[] params, int begin, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public List executeNativeNamedQuery(String nnq) {
		// TODO Auto-generated method stub
		return null;
	}

	public List executeNativeQuery(String nnq, Object[] params, int begin, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public int executeNativeSQL(String nnq) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> List find(Class<T> entityClass, String query, Map params, int begin, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T get(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getBy(Class<T> entityClass, String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public List query(String query, Map params, int begin, int max) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Object newInstance) {
		// TODO Auto-generated method stub
		
	}

	public void update(Object transientObject) {
		// TODO Auto-generated method stub
		
	}
	}