package co.edu.escuelaing;

import co.edu.escuelaing.Runners.RunRequest;

/**
 * Clase que sirve de controlador del servidor web
 */
public class HttpServerController {

    /**
     * Metodo que inicia el servidor web
     * 
     * @param args
     */
    public static void main(String[] args) {
        HttpServer servidor = HttpServer.getInstance();
        try {
            RunRequest.scanner();
        } catch (Exception e1) {
            System.out.println("No se pudo escanear: " + e1);
            e1.printStackTrace();
        }
        try {
            servidor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
