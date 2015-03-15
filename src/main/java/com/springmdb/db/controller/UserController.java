package com.springmdb.db.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmdb.db.entity.User;
import com.springmdb.db.service.UserService;

@Component
@ManagedBean
public class UserController implements Serializable {

	private static final long serialVersionUID = 3482630974477253188L;

	@Autowired
	private UserService userService;
	private List<User> users;
	private User selectedUser;

	@PostConstruct
	public void init() {
		users = userService.allUsers();
		selectedUser = new User();
	}

	@PreDestroy
	public void destroy() {
		selectedUser = new User();
	}
	
	public void onAddUser() {
		selectedUser = new User();
		clearUserFields();
	}

	public void addUser() {
		userService.addUser(selectedUser);
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
								selectedUser.getName() + " isimli kullanýcý ","baþarýyla eklendi"));
		RequestContext.getCurrentInstance().execute("PF('addUserDlgWW').hide()");
		clearUserFields();
		onUpdateUsersFromDB();
	}
	
	public void updateUser() {
		users.add(selectedUser);
		userService.updateUser(selectedUser);
		RequestContext.getCurrentInstance().execute("PF('editUserDlgWW').hide()");
		onUpdateUsersFromDB();
	}

	public void deleteUser() {
		if (selectedUser == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Lütfen tablodan bir seçim yapýn", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			users.remove(selectedUser);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, selectedUser
							.getName() + " isimli kullanýcý ",
							"baþarýyla silindi"));
			userService.deleteUser(selectedUser.get_id());
			selectedUser = null;
			onUpdateUsersFromDB();
		}
	}

	public void clearUserFields() {
		selectedUser.set_id(null);
		selectedUser.setName(null);
		selectedUser.setSurname(null);
		selectedUser.setPhone(null);
	}

	public void onUpdateUsersFromDB() {
		users = userService.allUsers();
	}

	public void onPageLoad(ComponentSystemEvent event) {

		users = userService.allUsers();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
