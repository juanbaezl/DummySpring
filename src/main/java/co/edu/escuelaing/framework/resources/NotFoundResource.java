package co.edu.escuelaing.framework.resources;

import co.edu.escuelaing.framework.output.Output;

public class NotFoundResource implements Resource {

    @Override
    public void print(Output output) {
        output.add("X-Response", "HTTP/1.1 404 Not Found");
        output.add("X-Body", "<!DOCTYPE html>" + "<html>"
                + "<h1>No se pudo encontrar el recurso</h1>" + "</html>");
        output.print(0);
    }

}
