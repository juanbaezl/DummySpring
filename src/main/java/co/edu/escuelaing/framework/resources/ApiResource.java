package co.edu.escuelaing.framework.resources;

import co.edu.escuelaing.Runners.RunRequest;
import co.edu.escuelaing.framework.output.Output;

public class ApiResource implements Resource {

    private String content = "text/plain";
    private int binary = 0;
    private String body = "";

    public ApiResource() {
    }

    public ApiResource(String method, String path) {
        System.out.println(path);
        try {
            this.body = RunRequest.getMethod(path).invoke(null).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public void print(Output output) {
        output.add("Content-Type", content);
        output.add("X-Body", body);
        output.print(binary);
    }

}
