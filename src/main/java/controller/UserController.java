package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	
	private boolean desabilitarBotoes;
	
	public UserController() {
		super();
		this.user = new User();
		this.userSelected = new User();
		this.listUsers = new ArrayList<User>();
		this.endPointUser = new UserEndpoint();
		System.out.println("Construtor");
		this.refreshList();
	}
	
	public void refreshList(){
		this.listUsers = this.endPointUser.getUsers();
		this.desabilitarBotoes = true;
	}
	
	/**
	 * Put ou Post User
	 * @return evita erro na view
	 */
	public String createOrUpdate(){
		for (int i = 0; i < this.listUsers.size(); i++) {
			int id = this.listUsers.get(i).getId();
			if (this.user.getId() == id){
				System.out.println("PUT User");
				return null;
			}
		}
		
		this.endPointUser.postUser(this.user);
		
		return null;
	}
	
	public void onRowSelect(SelectEvent event) {
        this.userSelected = ((User) event.getObject());
        this.desabilitarBotoes = false;
    }
	
	/**
	 * Insere os valores de usuario no form
	 * @return evitar erro na view
	 */
	public String updateForm(){
		this.user = this.userSelected;
		return null;
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

	public boolean isDesabilitarBotoes() {
		return desabilitarBotoes;
	}

	public void setDesabilitarBotoes(boolean desabilitarBotoes) {
		this.desabilitarBotoes = desabilitarBotoes;
	}

	

}
