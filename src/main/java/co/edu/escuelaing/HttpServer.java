package co.edu.escuelaing;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

/**
 * Clase que crea un servidor web con instancia unica
 */
public class HttpServer {

    // Singleton servidor
    private static HttpServer _instance = new HttpServer();

    // Bandera de salida
    private static boolean bandera = true;

    /**
     * Metodo que retorna la instancia del servidor
     * 
     * @return servidor
     */
    public static HttpServer getInstance() {
        return _instance;
    }

    /**
     * Metodo que inicia el servidor web y acepta sockets
     * Para parar este se debe escribir a este servidor con echo client o math
     * client la palabra 'exit'
     * 
     * @throws IOException
     */
    public void start() throws IOException {
        ExecutorService poolDeHilos = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = startServer();
        while (bandera) {
            Socket clientSocket = startSocket(serverSocket);
            RequestProcessor processor = new RequestProcessor(clientSocket, bandera);
            poolDeHilos.execute(processor);
        }
        serverSocket.close();
    }

    /**
     * Metodo que inicia el servidor web
     * 
     * @return socket del servidor
     */
    private ServerSocket startServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port:");
            System.exit(1);
        }
        return serverSocket;
    }

    /**
     * Metodo que inicia los socket
     * 
     * @param serverSocket servidor al cual se le va a pedir que acepte las
     *                     conexiones
     * @return socket
     */
    private Socket startSocket(ServerSocket serverSocket) {
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        return clientSocket;
    }

    private int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt((System.getenv("PORT")));
        }
        return 35000;
    }
}
