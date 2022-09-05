package co.edu.escuelaing.framework.resources;

import co.edu.escuelaing.framework.output.Output;

public class HtmlResource implements Resource {

    private String path = "";

    public HtmlResource(String path) {
        this.path = path;
        System.out.println("path resource: " + path);
    }

    @Override
    public void print(Output output) {
        output.add("Content-Type", "text/html");
        output.add("X-File", path);
        output.print(0);
    }

}
