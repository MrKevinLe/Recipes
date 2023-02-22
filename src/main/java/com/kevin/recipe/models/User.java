package com.kevin.recipe.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	Name input
	@NotEmpty(message="First Name is required!")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters")
	private String first_name;
	@NotEmpty(message="Last Name is required!")
    @Size(min=3, max=30, message="Last name must be between 3 and 30 characters")
	private String last_name;
//	Email input
	@NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
//	Password input
	@NotEmpty(message="Password is required!")
	@Size(min=8, max=128, message="Password must be between 8 and 128 characters")
	private String password;
//	Confirm Password Input
	@Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
	public User() {
		// TODO Auto-generated constructor stub
	}
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Recipe> recipe;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "user_recipe",
		joinColumns = @JoinColumn(name ="user_id"),
		inverseJoinColumns = @JoinColumn(name="recipe_id")
			)

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<Recipe> getRecipe() {
		return recipe;
	}
	public void setRecipe(List<Recipe> recipe) {
		this.recipe = recipe;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


}
