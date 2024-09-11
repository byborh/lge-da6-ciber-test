package eiffel.da6.basic_rest;

import eiffel.da6.basic_rest.utils.CORSFilter;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in eiffel.da6.basic_rest package
        //final ResourceConfig rc = new ResourceConfig().packages("eiffel.da6.basic_rest")
                //.register(new CORSFilter());

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
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }


    // Question
    //
    // IV-a :
    // ça analyse notre code source pour détecter des erreurs

    // V-a :
    // 11 problèmes par Qodana et 32 erreurs par SonarLint

    // b :
    // Sonarlist a une liste d'erreurs pour chaque type de langage(java, docker, js...) et les compares avec celui de notre code


    // d :
    // les deux sont bons, mais dans SonarList, il y a plus de détais et d'erreurs qui peuvent soubvenir

    // e :
    // les eux, mais pour avoir plus de détails, j'aurais dis SonarList
}

