package starter.resources.response;

import lombok.Getter;
import lombok.Setter;
import starter.models.User;

@Getter @Setter
public class UserResponseWrapper {

    private String name;

    private int age;

    public UserResponseWrapper(User user) {
        name = user.getName();
        age = user.getAge();

    }
}
