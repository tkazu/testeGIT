package util;

import javax.persistence.EntityManager;

public class Persistencia {
	EntityManager manager;
	public EntityManager getEntityManager(){
		if(manager == null){
			manager = EntityManagerUtil.getInstance().createEntityManager();
		}
		return manager;
	}
}
