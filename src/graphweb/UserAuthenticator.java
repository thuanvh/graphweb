package graphweb;

import javax.servlet.ServletContext;
import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: thuan
 * Date: 22 déc. 2009
 * Time: 20:32:17
 * To change this template use File | Settings | File Templates.
 */
public class UserAuthenticator {
    public String getRealFilePath(String filename, ServletContext context) {
        String file = context.getRealPath(filename);
        return file;
    }

    public boolean authenticateUser(User user, ServletContext context) {
        try {
            String file = getRealFilePath(UserUtils.DATA_FILE, context);
            System.out.println(file);
            User datauser = UserUtils.getUserByUserName(user.username, file);

            String filecode = getRealFilePath(CryptoUtils.KEY_FILE, context);
            System.out.println(filecode);
            String encryptpass = CryptoUtils.encrypt(user.password, new File(filecode));
            if (datauser != null) {
                if (datauser.getPassword().compareTo(encryptpass) == 0) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void changeUserPassword(User user, String newpass, ServletContext context) {
        try {
            String filecode = getRealFilePath(CryptoUtils.KEY_FILE, context);
            String encryptpass = CryptoUtils.encrypt(user.password, new File(filecode));
            user.setPassword(encryptpass);
            saveUser(user, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(User user, ServletContext context) {
        String file = getRealFilePath(UserUtils.DATA_FILE, context);
        UserUtils.saveUser(user, file);
    }
}
