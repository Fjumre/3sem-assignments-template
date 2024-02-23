import app.GLS.*;

import app.GLS.Package;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentDAOTest {

    private static EntityManagerFactory emfTest;

    private static ShipmentDAO shipmentDAO;


    @BeforeAll
    static void setUp() {
        emfTest = HibernateConfig.getEntityManagerFactoryConfigTest();

        shipmentDAO = new ShipmentDAO(emfTest);
    }

    @AfterAll
    static void tearDown() {
        if (emfTest != null) {
            emfTest.close();
        }
    }

    @Test
    void testCreate() {
        try (EntityManager em = emfTest.createEntityManager()) {
            em.getTransaction().begin();
            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());
            em.persist(shipment);

            em.getTransaction().commit();
            Shipment findShipment = em.find(Shipment.class, shipment.getId());
            assertNotNull(findShipment);
            assertEquals(shipment.getId(), findShipment.getId(), "The IDs of the created and found shipments should match.");
        }
    }

    @Test
    void testRead() {
        try (EntityManager em = emfTest.createEntityManager()) {
            em.getTransaction().begin();

            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());

            em.persist(shipment);
            em.getTransaction().commit();

            Shipment foundShipment = shipmentDAO.read((int) shipment.getId());
            assertNotNull(foundShipment);
            assertEquals(shipment.getId(), foundShipment.getId(), "The IDs of the persisted and found shipments should match.");
        }
    }

    @Test
    void testUpdate() {
        try (EntityManager em = emfTest.createEntityManager()) {
            em.getTransaction().begin();
            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());

            em.persist(shipment);
            em.getTransaction().commit();
            shipment.setShipmentDate(LocalDate.of(1922, Month.JANUARY, 1));
            shipmentDAO.update(shipment);

            Shipment updatedShipment = shipmentDAO.read((int) shipment.getId());
            assertEquals(LocalDate.of(1922, Month.JANUARY, 1), updatedShipment.getShipmentDate());
        }
    }

    @Test
    void testDelete() {

        try (EntityManager em = emfTest.createEntityManager()) {

            em.getTransaction().begin();

            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());

            em.persist(shipment);
            em.remove(shipment);
            em.getTransaction().commit();

            Shipment deleteShipment = shipmentDAO.read((int) shipment.getId());
            assertNull(deleteShipment, "Shipment should be null after deletion");

        }
    }

    @Test
    void findShipmentsByPackageTest() {
        try (EntityManager em = emfTest.createEntityManager()) {

            em.getTransaction().begin();

            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());

            em.persist(shipment);
            em.getTransaction().commit();
            List<Shipment> shipments = shipmentDAO.findShipmentsByPackage(pkg);

            assertNotNull(shipments, "Shipments should not be null");
        }
    }

    @Test
    void findPackagesWithShipmentsTest() {
        try (EntityManager em = emfTest.createEntityManager()) {

            em.getTransaction().begin();

            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            Location sourceLocation = new Location(40.712776, -74.005974, "New York, NY, USA");
            Location destinationLocation = new Location(34.052235, -118.243683, "Los Angeles, CA, USA");

            em.persist(pkg);
            em.persist(sourceLocation);
            em.persist(destinationLocation);

            Shipment shipment = new Shipment(pkg, sourceLocation, destinationLocation, LocalDate.now());

            em.persist(shipment);
            em.getTransaction().commit();
            List<Package> aPackages = shipmentDAO.findPackagesWithShipments();

            assertNotNull(aPackages, "It should not be null");
        }
    }
}