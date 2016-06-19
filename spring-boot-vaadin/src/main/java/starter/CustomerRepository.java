package starter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Nasir on 05-09-2015.
 */
public interface CustomerRepository extends MongoRepository<Customer, String>{

    public Customer findOneByFirstName(@Param("firstName")String firstName);
}
