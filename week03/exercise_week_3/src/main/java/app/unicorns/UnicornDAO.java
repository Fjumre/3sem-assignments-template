package app.unicorns;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;


public class UnicornDAO {

    private EntityManager em;

    // Constructor
    public UnicornDAO(EntityManager em) {
        this.em = em;
    }

    public static void create(EntityManager em, Unicorn unicorn) {
        em.getTransaction().begin();
        em.persist(unicorn);
        em.getTransaction().commit();
    }

    public Unicorn update(Unicorn unicorn) {
        em.getTransaction().begin();
        Unicorn updatedUnicorn = em.merge(unicorn);
        em.getTransaction().commit();
        return updatedUnicorn;
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Unicorn unicorn = findById(id);
        if (unicorn != null) {
            em.remove(unicorn);
        }
        em.getTransaction().commit();
    }

    public Unicorn read (int id){

        Unicorn unicorn= em.find(Unicorn.class, id);
        return unicorn;
    }

    public List<Unicorn> readAllUnicorns(){

        Query query= em.createQuery("SELECT u from Unicorn u", Unicorn.class);

        return query.getResultList();
    }
    public Unicorn findById(int id) {
        return em.find(Unicorn.class, id);
    }
}
