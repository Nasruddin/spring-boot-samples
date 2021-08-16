package starter.dao;

import org.springframework.stereotype.Repository;
import starter.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
        entityManager.persist(user);
        return;
    }
}
