package co.edu.escuelaing.framework;

import java.io.IOException;
import java.net.Socket;

import co.edu.escuelaing.framework.output.StringBuilderOutput;
import co.edu.escuelaing.framework.resources.ApiResource;
import co.edu.escuelaing.framework.resources.HtmlResource;
import co.edu.escuelaing.framework.resources.ImageResource;
import co.edu.escuelaing.framework.resources.JsResource;
import co.edu.escuelaing.framework.resources.NotFoundResource;
import co.edu.escuelaing.framework.resources.Resource;

public class Response {

    public static void response(String method, String path, Socket clientSocket) throws IOException {
        Resource resource = identifyResource(method, path, clientSocket);
        StringBuilder buf = new StringBuilder();
        resource.print(new StringBuilderOutput(buf, clientSocket));
    }

    private static Resource identifyResource(String method, String path, Socket clientSocket) throws IOException {
        if (path.endsWith(".js")) {
            return new JsResource(path);
        } else if (path.endsWith(".html")) {
            return new HtmlResource(path);
        } else if (path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".gif") || path.endsWith(".jpeg")) {
            return new ImageResource(path);
        } else if (!path.contains(".")) {
            return new ApiResource(method, path);
        } else {
            return new NotFoundResource();
        }

    }
}
