import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static List<String> validate(Object obj) {
        List<String> violations = new ArrayList<>();
        if (obj == null) return violations;

        for (Field field : obj.getClass().getDeclaredFields()) {
            Range range = field.getAnnotation(Range.class);
            if (range == null || field.getType() != int.class) continue;
            try {
                field.setAccessible(true);
                int actual = field.getInt(obj);
                if (actual < range.min() || actual > range.max()) {
                    violations.add(field.getName() + " - " + range.message() + " (actual: " + actual + ")");
                }
            } catch (IllegalAccessException e) {
                violations.add(field.getName() + " - Unable to read field");
            }
        }
        return violations;
    }
}
