package graphweb;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:25:26
 * To change this template use File | Settings | File Templates.
 */
public class User {
    String username;
    String password;
    public User(){

    }
    public void copy(User user){
        this.username=user.username;
        this.password=user.password;
    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username+","+password;
    }
}
