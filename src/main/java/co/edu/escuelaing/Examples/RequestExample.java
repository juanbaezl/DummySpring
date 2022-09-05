package co.edu.escuelaing.Examples;

import co.edu.escuelaing.Annotations.RequestMapping;

public class RequestExample {

    @RequestMapping("/hello")
    public static String index() {
        return "Greetings from Spring Boot!";
    }
}
