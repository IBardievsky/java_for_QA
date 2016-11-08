package ru.stqa.pft.mantis.appmanager;

        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.boot.MetadataSources;
        import org.hibernate.boot.registry.StandardServiceRegistry;
        import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
        import ru.stqa.pft.mantis.model.UserData;

        import java.util.List;

/**
 * Created by User on 11/8/2016.
 */
public class DbHelper {

  private SessionFactory sessionFactory;

  public DbHelper() {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

  public UserData user() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where id > 1").list();
    UserData user = result.stream().iterator().next();

    session.getTransaction().commit();
    session.close();
    return user;

  }
}
