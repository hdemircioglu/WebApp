/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package userBean;


import java.io.Serializable;
import java.util.Date;
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

    public ListUserBean(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String submit() {
        ListUser user = new ListUser();
        user.setUsername(getUsername());
        user.setPassword(getPassword());
        user.setCreationTime(new Date());
        users.add(user);
        return "index.xhtml";
    }

    public String delete(ListUser user) {
        users.delete(user);
        return "index.xhtml";
    }

}
