import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> cls : classes) {
            Component comp = cls.getAnnotation(Component.class);
            if (comp == null) {
                System.out.println("[Container] Skipped: " + cls.getSimpleName() + " (no @Component)");
                continue;
            }
            try {
                Object instance = cls.getDeclaredConstructor().newInstance();
                String name = comp.value();
                if (name.isEmpty()) {
                    String s = cls.getSimpleName();
                    name = Character.toLowerCase(s.charAt(0)) + s.substring(1);
                }
                beans.put(name, instance);
                System.out.println("[Container] Registered: " + name + " (" + cls.getSimpleName() + ")");
            } catch (ReflectiveOperationException e) {
                System.out.println("[Container] Skipped: " + cls.getSimpleName() + " (cannot instantiate: " + e + ")");
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            Class<?> cls = bean.getClass();
            for (Field f : cls.getDeclaredFields()) {
                Value v = f.getAnnotation(Value.class);
                if (v == null) continue;
                String key = v.value();
                String raw = config.get(key);
                if (raw == null) {
                    System.out.println("[Container] Skipped: " + key + " (missing in config)");
                    continue;
                }
                try {
                    f.setAccessible(true);
                    Class<?> type = f.getType();
                    if (type == int.class || type == Integer.class) {
                        f.set(bean, Integer.parseInt(raw.trim()));
                    } else if (type == String.class) {
                        f.set(bean, raw);
                    } else {
                        System.out.println("[Container] Skipped: " + key + " (unsupported type " + type.getSimpleName() + ")");
                        continue;
                    }
                    System.out.println("[Container] Injected: " + key + " -> "
                            + cls.getSimpleName() + "." + f.getName());
                } catch (IllegalAccessException | NumberFormatException e) {
                    System.out.println("[Container] Skipped: " + key + " (" + e.getMessage() + ")");
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    Iterable<Object> getBeans() {
        return beans.values();
    }
}
