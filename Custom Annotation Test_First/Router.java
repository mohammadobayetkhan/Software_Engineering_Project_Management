import java.lang.reflect.Method;

public class Router {
    public static String dispatch(Container c, String path) {
        for (Object bean : c.getBeans().values()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                Route route = method.getAnnotation(Route.class);
                if (route != null && route.path().equals(path)) {
                    try {
                        if (method.getParameterCount() != 0 || method.getReturnType() != String.class) {
                            return "Route error: method must have no parameters and return String";
                        }
                        method.setAccessible(true);
                        return (String) method.invoke(bean);
                    } catch (ReflectiveOperationException e) {
                        return "Route error: " + e.getClass().getSimpleName();
                    }
                }
            }
        }
        return "404 Not Found: " + path;
    }
}
