package co.edu.escuelaing.framework.resources;

import co.edu.escuelaing.framework.output.Output;

public class JsResource implements Resource {

    private String path = "";

    public JsResource(String path) {
        this.path = path;
    }

    @Override
    public void print(Output output) {
        output.add("Content-Type", "application/javascript");
        output.add("X-File", path);
        output.print(0);
    }

}
