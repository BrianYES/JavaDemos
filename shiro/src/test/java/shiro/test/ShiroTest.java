package shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroTest {

    @Test
    public void testLogin() {
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-user.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("brian", "123456");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
    }

    @Test
    public void testRole() {
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("brian", "123456");
        subject.login(token);

        boolean role1 = subject.hasRole("role1");
        boolean role2 = subject.hasRole("role2");
        boolean role3 = subject.hasRole("role3");
        System.out.println(role1);
        System.out.println(role2);
        System.out.println(role3);

        subject.logout();
    }

    @Test
    public void testPermission() {
        IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager = securityManagerFactory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("brian", "123456");
        subject.login(token);

        boolean b1 = subject.isPermitted("/delete");
        boolean b2 = subject.isPermitted("user:select");
        boolean b3 = subject.isPermitted("/update");
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        subject.logout();
    }
}
