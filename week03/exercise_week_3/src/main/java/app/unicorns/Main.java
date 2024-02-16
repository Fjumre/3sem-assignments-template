package app.unicorns;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;


public class Main {

    public static void main(String[] args) {
        // Create an EntityManagerFactory for your persistence unit
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        // Instantiate UnicornDAO with the EntityManager
        UnicornDAO unicornDAO = new UnicornDAO(em);


        System.out.println(unicornDAO.readAllUnicorns());

        // Create a new Unicorn
        Unicorn unicorn = new Unicorn("Jeff", 2, 52);



        // Save the Unicorn

        UnicornDAO.create(em, unicorn);

        System.out.println(unicornDAO.readAllUnicorns());
        // Assuming the Unicorn's ID was generated and set upon persisting
        int unicornId = unicorn.getId();

        // Update the Unicorn
        unicorn.setPowerStrength(90.0); // Increase the power strength
        unicornDAO.update(unicorn);

        System.out.println(unicornDAO.readAllUnicorns());

        // Fetch the updated Unicorn by ID
        Unicorn updatedUnicorn = unicornDAO.findById(unicornId);
        if (updatedUnicorn != null) {
            System.out.println("Fetched Unicorn: " + updatedUnicorn);
        } else {
            System.out.println("Unicorn not found.");
        }

        // Delete the Unicorn by ID
        unicornDAO.delete(unicornId);

        System.out.println(unicornDAO.readAllUnicorns());

        // Clean up resources
        em.close();
        emf.close();
    }


}