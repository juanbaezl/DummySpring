package co.edu.escuelaing.framework.resources;

import co.edu.escuelaing.framework.output.Output;

public class ImageResource implements Resource {

    private String path = "";

    public ImageResource(String path) {
        this.path = path;
    }

    @Override
    public void print(Output output) {
        output.add("Content-Type", "image/" + path.substring(path.lastIndexOf(".") + 1));
        output.add("X-File", path);
        output.print(1);
    }

}