package app.GLS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShipmentDAO {

    private EntityManagerFactory emf;
    public ShipmentDAO(EntityManagerFactory emf) {
        this.emf= emf;
    }

    public void create(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(shipment);
            em.getTransaction().commit();
        }
    }

    public Shipment update(Shipment shipment) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Shipment updatedShipment = em.merge(shipment);
            em.getTransaction().commit();
            return updatedShipment;
        }
    }

    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Shipment shipment = em.find(Shipment.class, id);
            if (shipment != null) {
                em.remove(shipment);
            }
            em.getTransaction().commit();
        }
    }

    public Shipment read(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Shipment.class, id);
        }
    }
    public List<Shipment> findShipmentsByPackage(Package aPackage){
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Shipment> query= em.createQuery("SELECT sh FROM Shipment sh WHERE sh.aPackage= ?1", Shipment.class);
            query.setParameter(1, aPackage);
            return query.getResultList();
        }
    }
    public List<Package> findPackagesWithShipments() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Package> query = em.createQuery("SELECT p FROM Package p JOIN FETCH p.shipmentSet", Package.class);
            return query.getResultList();
        }
    }
}
