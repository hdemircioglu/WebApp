/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userBean;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import toDoEJB.ListUser;
import toDoEJB.ListUserControllerLocal;

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

    public void setPassword(String password) {
        this.password = password;
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
            return "task.xhtml";
        }
        else {
            return "registration.xhtml";
        }       
    }
}
