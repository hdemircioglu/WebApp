/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskBean;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import uk.ac.susx.inf.ianw.webApps.taskBroker.ejb.TaskBrokerBeanRemote;
import uk.ac.susx.inf.ianw.webApps.taskBroker.ejb.TaskBrokerException;
import uk.ac.susx.inf.ianw.webApps.taskBroker.entity.Task;

/**
 *
 * @author muratmenevse
 */

@Named(value="remoteTaskBean")
@SessionScoped
public class RemoteTaskBean implements Serializable{
    
    private Date dueDate;
    private String description;
    private String allocated;
    private String proposer;
    private short completed;
    
    @EJB TaskBrokerBeanRemote tasks;

    public RemoteTaskBean(){
        
    }
    
    public RemoteTaskBean(Date dueDate, String description, 
            String allocated, String proposer, short completed) {
        this.dueDate = dueDate;
        this.description = description;
        this.allocated = allocated;
        this.proposer = proposer;
        this.completed = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public short getCompleted() {
        return completed;
    }

    public void setCompleted(short completed) {
        this.completed = completed;
    }

  
    
    public Task[] getTasks() throws TaskBrokerException {
        return tasks.listTasks();
    
    }
    
    public String addTask() throws TaskBrokerException{
        Task task = new Task();
        task.setDueDate(getDueDate());
        task.setDescription(getDescription());
        tasks.allocateTask(task, getAllocated());
        
        return "task.xhtml";
    }
    
    public String deleteTask(Task task) throws TaskBrokerException{
        tasks.abandonTask(task.getId());
        
        return "task.xhtml";
        
    }
    
}


