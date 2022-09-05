package co.edu.escuelaing.Runners;

import java.lang.reflect.Method;
import java.util.HashMap;

import co.edu.escuelaing.Annotations.RequestMapping;

public class RunRequest {

    static HashMap<String, Method> services = new HashMap<String, Method>();

    public static void scanner() throws Exception {
        for (Method m : Class.forName("co.edu.escuelaing.Examples.RequestExample").getMethods()) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                try {
                    String uri = m.getAnnotation(RequestMapping.class).value();
                    System.out.println(uri + ": " + m.invoke(null));
                    services.put("resources" + uri, m);
                } catch (Throwable ex) {
                    System.out.print("Error: " + ex);
                }
            }
        }
    }

    public static Method getMethod(String uri) {
        return services.get(uri);
    }
}
