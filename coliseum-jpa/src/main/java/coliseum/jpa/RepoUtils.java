package coliseum.jpa;

import org.hibernate.proxy.HibernateProxy;

public class RepoUtils {

    public static boolean isProxy(Object object) {
        return object instanceof HibernateProxy;
    }

    public static boolean isNotProxy(Object object) {
        return !isProxy(object);
    }
}
