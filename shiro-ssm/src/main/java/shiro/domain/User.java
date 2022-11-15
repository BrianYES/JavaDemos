package shiro.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class User {

    private String userName;
    private String pswd;
    private String realName;
    private String email;
}
