import app.GLS.HibernateConfig;
import app.GLS.Package;
import app.GLS.PackageDAO;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.*;

public class PackageDAOTest {

    private static EntityManagerFactory emfTest;
    private static PackageDAO packageDAO;


    @BeforeAll
    static void setUp() {
        emfTest = HibernateConfig.getEntityManagerFactoryConfig();
        packageDAO = new PackageDAO(emfTest);
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
            Package pkg = new Package("uu33", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
            packageDAO.create(pkg);

            Package retrievedPackage = em.find(Package.class, pkg.getId());
            assertNotNull(retrievedPackage);
            assertEquals("Johnny Bravo", retrievedPackage.getSenderName(), "Sender name should match.");

        }
    }

    @Test
    void testRead() {
        Package pkg = new Package("oo44", "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
        packageDAO.create(pkg); // Assuming this is necessary for setup

        Package foundPackage = packageDAO.read((int) pkg.getId());
        assertNotNull(foundPackage);
        assertEquals(pkg.getTrackingNumber(), foundPackage.getTrackingNumber(), "Tracking numbers should match.");
    }

    @Test
    void testDelete() {
        Package pkg = new Package("dd33" + System.nanoTime(), "Johnny Bravo", "Jane Fonda", Package.DeliveryStatus.PENDING);
        packageDAO.create(pkg); // Create a package to delete

        packageDAO.delete((int) pkg.getId()); // Delete the package

        EntityManager em = emfTest.createEntityManager();
        Package deletedPackage = em.find(Package.class, pkg.getId());
        assertNull(deletedPackage);
        em.close();
    }
    @Test
    void testUpdate() {
        Package pkg = new Package("88888jj", "Mads Mikkelsen", "Jens Lyn", Package.DeliveryStatus.PENDING);
        packageDAO.create(pkg);

        pkg.setReceiverName("Bo Karlsen");
        packageDAO.update(pkg);

        Package updatedPackage = packageDAO.read((int) pkg.getId());
        assertEquals("Bo Karlsen", updatedPackage.getReceiverName());
    }

    @Test
    void testFindByTrackingNumber() {
        Package pkg = new Package("222ll", "Peter Jensen", "Kurt Haardgaard", Package.DeliveryStatus.PENDING);
        packageDAO.create(pkg);

        Package foundPackage = packageDAO.findByTrackingNumber("222ll");
        assertNotNull(foundPackage);
        assertEquals("Peter Jensen", foundPackage.getSenderName());
    }
}

