package graphweb;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 24 janv. 2010
 * Time: 14:35:39
 * To change this template use File | Settings | File Templates.
 */
public class LoginForm  extends ServletService {
    private String userName;
    private String password;

    @Override
    public String execute() throws Exception {
        User user=new User();
        user.setPassword(password);
        user.setUsername(userName);
        UserAuthenticator ua=new UserAuthenticator();
        boolean result= ua.authenticateUser(user,context);
        if(result){
            session.put("User",user);
            return "success";
        }
        else
            return "fail";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
