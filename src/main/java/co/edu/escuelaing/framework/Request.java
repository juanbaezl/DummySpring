package co.edu.escuelaing.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Request {

    private String method;
    private String path;
    private HashMap<String, String> parameters = new HashMap<String, String>();

    public Request(String method, String path) {
        this.method = method;
        if (path.contains("?")) {
            this.path = path.substring(0, path.indexOf("?"));
            String parts = path.substring(path.indexOf("?") + 1);
            List<String> params = Arrays.asList(parts.split("&"));
            for (String param : params) {
                String[] keyValue = param.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
            System.out.println("parameters: " + this.parameters);
            System.out.println("path request: " + this.path);
        } else {
            this.path = path;
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    /**
     * Metodo que lee la peticion del cliente
     * 
     * @param in BufferedReader de la peticion
     * @return Path al cual quiere acceder el cliente
     * @throws IOException
     */
    public static List<String> lecturaRequest(BufferedReader in) throws IOException {
        return Arrays.asList(in.readLine().split(" "));
    }
}
