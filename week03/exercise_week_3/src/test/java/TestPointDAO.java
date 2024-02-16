import app.JPQL_Points.HibernateConfig;
import app.JPQL_Points.Point;
import app.JPQL_Points.PointDAO;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPointDAO {

    static EntityManagerFactory emf;
    static PointDAO pointDAO;

    @BeforeAll
    static void beforeAllTests() {
        // Initialize EntityManagerFactory before tests
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        pointDAO = new PointDAO(); // Initialize PointDAO before all tests
    }

    @AfterAll
    static void afterAllTests() {
        // Close EntityManagerFactory after all tests
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    void testStorePoints() {
        pointDAO.storePoints();

        long totalPoints = pointDAO.getTotalPoints();
        assertTrue(totalPoints >= 1000);
    }

    @Test
    void testGetTotalPoints() {
        long totalPoints = pointDAO.getTotalPoints();
        assertNotNull(totalPoints);
    }

    @Test
    void testGetAverageX() {
        double averageX = pointDAO.getAverageX();
        assertNotNull(averageX);
    }

    @Test
    void testRetrieveAllPoints() {
        List<Point> points = pointDAO.retrieveAllPoints();
        assertNotNull(points); // Example assertion
        assertFalse(points.isEmpty()); // Ensure the list is not empty
    }
}
