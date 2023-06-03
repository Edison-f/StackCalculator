package smartbox;

import java.util.*;
import java.lang.reflect.*;

import mvc.*;

public class Container extends Model {

    private Map<Class<?>, Component> providedInterfaces = new HashMap<Class<?>, Component>();
    private Map<Class<?>, Component> requiredInterfaces = new HashMap<Class<?>, Component>();
    private Map<String, Component> components = new HashMap<String, Component>();

    public Collection<Component> getComponents() {
        return components.values();
    }

    public void addComponent(String name) throws Exception {
        String qualName = "smartbox.components." + name;
        Object obj = Class.forName(qualName).getDeclaredConstructor().newInstance();
        addComponent((Component) obj);
    }


    private void addComponent(Component component) throws Exception {
        component.setContainer(this);

        components.put(component.name, component);

        for (Class<?> intf :
                component.getProvidedInterfaces()) {
            providedInterfaces.put(intf, component);
        }

        for (Class<?> intf :
                component.getRequiredInterfaces()) {
            requiredInterfaces.put(intf, component);
        }
        //???
        //find providers for the new component and hook him up:
        findProviders();
        // mvc stuff:
        changed();
    }

    public void remComponent(String name) throws Exception {
        Component component = components.get(name);
        components.remove(name);
        // unhook removed guy from any clients:
        for (Class<?> intf : component.getProvidedInterfaces()) {
            for (Component client : components.values()) {
                if (client.getRequiredInterfaces().contains(intf)) {
                    client.setProvider(intf, null);
                    requiredInterfaces.put(intf, client);
                }
            }
        }
        changed();
    }

    // each time we add a new component we try to connect as many clients and providers as we can:
    private void findProviders() throws Exception {
        Set<Class<?>> reqInterfaces = requiredInterfaces.keySet();
        for (Class<?> intf : reqInterfaces) {
            Component client = requiredInterfaces.get(intf);
            Component provider = providedInterfaces.get(intf);
            if (client != null && provider != null) {
                client.setProvider(intf, provider);
                //requiredInterfaces.remove(intf); this line makes iterator obsolete
                requiredInterfaces.put(intf, null);
            }
        }
    }

    public void launch(String name) throws Exception {
        try {
            initSupport();
            findProviders();
            Component component = components.get(name);
            if (component instanceof App) {
                component.getClass().getMethod("main").invoke(component);
            }
        } catch (Exception e) {
            mvc.Utilities.error(e);
            e.printStackTrace();
        }
    }

    // needed by File/Open
    public void initSupport() {
        super.initSupport();
        for (Component b :
                components.values()) {
            if (b.fields == null) {
                b.fields = new HashMap<>();
                b.computeProvidedInterfaces();
                b.computeRequiredInterfaces();
            }
        }

    }
}
