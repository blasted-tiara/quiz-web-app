package com.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.service.User;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8081";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("com.example");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
        .configure() // configures settings from hibernate.cfg.xml
        .build();

        try {
            SessionFactory factory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();

            User user = new User();
            user.setUsername("John");
            user.setPasswordHash("asddfhrsgsdfgdsfa");

            session.persist(user);
            transaction.commit();
            session.flush();
            session.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        
        }

        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.shutdown();
    }
}

