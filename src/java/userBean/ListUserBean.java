/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import UserEJB.ListUser;
import UserEJB.ListUserControllerLocal;

/**
 *
 * @author muratmenevse
 */
@Named(value="listUser")
@SessionScoped
public class ListUserBean implements Serializable {
private String username;
private String password;

@EJB ListUserControllerLocal users;
    /** Creates a new instance of VisitorBean */
    public ListUserBean(){
        
    }

    public ListUserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Password is securely stored in the Database 
     * @param password
     */
    public void setPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        
        //Converting byte to hex
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100,16).substring(1));
        }
        this.password = sb.toString();
    }
    
    public List<ListUser> getUsers() {
        return users.list();
    }
    
    public String submit() {
        ListUser user = new ListUser();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        if(users.checkName(getUsername())){
            users.add(user);
            return "userList.xhtml";  
        }
        else {
            return "registration.xhtml";
        }
    }

    public String delete(ListUser user) {
        users.delete(user);
        return "userList.xhtml";
    }
    
    public String login() {
        if(users.checkLogin(getUsername(), getPassword())){
            return "registration.xhtml";
        }
        else {    
            return "task.xhtml";
        }       
        
    }
}
