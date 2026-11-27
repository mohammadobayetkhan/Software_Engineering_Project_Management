import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                System.out.println("[Container] Skipped: " + clazz.getSimpleName() + " (no @Component)");
                continue;
            }
            Component component = clazz.getAnnotation(Component.class);
            String name = component.value();
            if (name.isEmpty()) {
                name = clazz.getSimpleName();
                name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
            }
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beans.put(name, instance);
                System.out.println("[Container] Registered: " + name + " (" + clazz.getSimpleName() + ")");
            } catch (ReflectiveOperationException e) {
                System.out.println("[Container] Failed to register: " + clazz.getSimpleName()
                        + " (" + e.getClass().getSimpleName() + ")");
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            for (Field field : bean.getClass().getDeclaredFields()) {
                Value annotation = field.getAnnotation(Value.class);
                if (annotation == null) continue;
                String key = annotation.value();
                if (!config.containsKey(key)) {
                    System.out.println("[Container] Missing config: " + key + " -> "
                            + bean.getClass().getSimpleName() + "." + field.getName());
                    continue;
                }
                try {
                    field.setAccessible(true);
                    String configuredValue = config.get(key);
                    if (field.getType() == String.class) {
                        field.set(bean, configuredValue);
                    } else if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(bean, Integer.parseInt(configuredValue));
                    } else {
                        System.out.println("[Container] Unsupported @Value type: " + field.getType().getName());
                        continue;
                    }
                    System.out.println("[Container] Injected: " + key + " -> "
                            + bean.getClass().getSimpleName() + "." + field.getName());
                } catch (ReflectiveOperationException | NumberFormatException e) {
                    System.out.println("[Container] Injection failed: " + key + " -> "
                            + bean.getClass().getSimpleName() + "." + field.getName());
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    public Map<String, Object> getBeans() {
        return beans;
    }
}
