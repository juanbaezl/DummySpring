package co.edu.escuelaing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import co.edu.escuelaing.framework.Request;
import co.edu.escuelaing.framework.Response;

public class RequestProcessor implements Runnable {

    private Socket clientSocket;
    private boolean bandera;

    public RequestProcessor(Socket clientSocket, boolean bandera) {
        this.clientSocket = clientSocket;
        this.bandera = bandera;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            if (bandera) {
                List<String> request = Request.lecturaRequest(in);
                String path = request.get(1), method = request.get(0);
                path = path.equals("/") ? "resources/index.html" : "resources" + path;
                System.out.println("Path: " + path);
                System.out.println("Method: " + method);
                Response.response(method, path, clientSocket);
            } else {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                System.out.println("Se ha apagado el servidor");
                out.println("El servidor web se ha apagado");
                out.close();
            }
            in.close();
            clientSocket.close();
            System.out.println(Thread.currentThread());
        } catch (Exception e) {
            System.out.println("No se pudo crear el hilo " + e.getCause() + e.getLocalizedMessage());
        }
    }

}
