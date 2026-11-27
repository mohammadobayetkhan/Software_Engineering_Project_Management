import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {
    // The spec names the element path(), but the starter code writes @Route("/hello"),
    // which Java only allows for an element called value(). Supporting both keeps
    // the spec and the starter code compiling: @Route("/x") and @Route(path = "/x").
    String path() default "";
    String value() default "";
}
