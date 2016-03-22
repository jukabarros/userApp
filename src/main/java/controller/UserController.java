package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import endpoint.UserEndpoint;
import model.User;

@ManagedBean(name="userController")
@ViewScoped
public class UserController implements Serializable{

	private static final long serialVersionUID = -3770573459254222700L;
	
	private User user;
	
	private User userSelected;
	
	private List<User> listUsers;
	
	private UserEndpoint endPointUser;
	
	public UserController() {
		super();
		this.user = new User();
		this.userSelected = new User();
		this.listUsers = new ArrayList<User>();
		this.endPointUser = new UserEndpoint();
		this.refreshList();
	}
	
	public void refreshList(){
		this.listUsers = this.endPointUser.getUsers();
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("User Selected", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	/*
	 * GET /users
	 */
	public void getAll(){
		this.endPointUser.getUsers();
	}

	// GET AND SET
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<User> listUsers) {
		this.listUsers = listUsers;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	

}
