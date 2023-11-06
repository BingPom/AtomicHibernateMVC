package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Person;

public class RepositoryPerson {

	private EntityManager em;

	public RepositoryPerson(EntityManager em) {
		this.em = em;
	}

	public Person findPersonById(int id) {
		return em.find(Person.class, id);
	}

	public Person findPersonByName(String name) {
		TypedQuery<Person> q = em.createQuery("SELECT * FROM person WHERE name = :name", Person.class);
		q.setParameter("name", name);
		return q.getSingleResult();
	}

	public boolean savePerson(Person p) {
		try {
			if (p.getId() == null) {
				em.persist(p);
			} else {
				em.merge(p);
			}
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	public boolean deletePerson(Person p) {
		try {
			if (em.contains(p)) {
				em.remove(p);
			} else {
				em.merge(p);
			}
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}
}
