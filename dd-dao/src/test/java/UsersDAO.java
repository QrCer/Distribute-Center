import org.springframework.stereotype.Repository;

/**
 * Created by QrCeric on 16/5/13.
 */
@Repository
public class UsersDAO {
    /*
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Users> getAllUser() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MqCon.class);
        return criteria.list();
    }

    public Integer addUser(Users users) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction transaction = session.beginTransaction();
        session.save(users);
//        transaction.commit();
        return users.getId();
    }
    */
}
