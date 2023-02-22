package com.kevin.recipe.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kevin.recipe.models.Category;
import com.kevin.recipe.models.LoginUser;
import com.kevin.recipe.models.Recipe;
import com.kevin.recipe.models.User;
import com.kevin.recipe.models.UserRecipe;
import com.kevin.recipe.services.CategoryService;
import com.kevin.recipe.services.RecipeService;
import com.kevin.recipe.services.UserRecipeService;
import com.kevin.recipe.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private RecipeService recipeservice;
	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private UserRecipeService userRecipeserv;
	
	@GetMapping("/login")
	public String index(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("newlogin", new LoginUser());
		return "index.jsp";
	}
	
//	REGSITER ROUTE
	@PostMapping("/reg")
	public String register(@Valid @ModelAttribute("user")User user,
							BindingResult result, Model model, HttpSession session) {
        userservice.register(user, result);
        if(result.hasErrors()) {

            model.addAttribute("newlogin",new LoginUser());
            return "index.jsp";
        } else {
        	session.setAttribute("user_id", user.getId() );
        	return "redirect:/success";
        }
	}
	
//	LOGIN ROUTE
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newlogin")LoginUser newlogin,
						BindingResult result,
						Model model,
						HttpSession sesh) {
		User user = userservice.login(newlogin, result);
		if(result.hasErrors()) {
			model.addAttribute("user", new User());
			return "index.jsp";
		}else {
			sesh.setAttribute("user_id",user.getId());
			return "redirect:/success";
		}
		
	}
	
//	HOME PAGEEEE
	@GetMapping("/success")
	public String success(HttpSession session , Model model,Model model1) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}else {
////			Show all the Books
//			List<Baby> allBaby = babyservice.allBaby();
//			model1.addAttribute("allBaby",allBaby);
			List<Recipe> allrecipes = recipeservice.allRecipe();
			model1.addAttribute("allrecipes", allrecipes);
//			Access user in session
			User thisUser = userservice.findUser(userId);
			model.addAttribute("thisUser", thisUser);
//			show all category
			List<Category> allcategory = categoryservice.allCategory();
			model1.addAttribute("allcategory", allcategory);
			return "success.jsp";
		}
	}
//	HOME PAGEEEE w/o session
	@GetMapping("/")
	public String homepage(Model model,Model model1) {
			List<Recipe> allrecipes = recipeservice.allRecipe();
			model1.addAttribute("allrecipes", allrecipes);
			List<Category> allcategory = categoryservice.allCategory();
			model1.addAttribute("allcategory", allcategory);
			
			return "homepage.jsp";
		}
	
//	LOG OUT ROUTE
	@GetMapping("/logout")
	public String logout(HttpSession sesh) {
		sesh.invalidate();
		return "redirect:/";
	}
	
//	Create Category
	@GetMapping("/createC")
	public String createC(@ModelAttribute("category") Category category, Model model) {
		model.addAttribute("category", category);
		return"createC.jsp";
	}
	
//	Submit Category
	@PostMapping("/submitC")
	public String submitC(@Valid @ModelAttribute("category")Category category,
			BindingResult result,
			Model model) {
		if(result.hasErrors()) {
			model.addAttribute("category", category);
			return "createC.jsp";
		}else {
			categoryservice.createCategory(category);
			return "redirect:/success";
		}
	}
//	Create Recipe
	@GetMapping("/createR")
	public String CreateR(@ModelAttribute("recipe") Recipe recipe,
			HttpSession session,
			Model model,
			Model model1,
			Model model2) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}else {
//			form model
			model1.addAttribute("recipe", recipe);
//			pass in category
			List<Category> allcat = categoryservice.allCategory();
			model2.addAttribute("allcat", allcat);
//			save user_id with form
			Long userId1 = (Long)session.getAttribute("user_id");
			model.addAttribute("session", userId1);
//			show user in session
			User thisUser = userservice.findUser(userId);
			model.addAttribute("thisUser", thisUser);
		}
		return "createR.jsp";

	}
//	Submit Recipe
	@PostMapping("/submitR")
	public String submitBook(@Valid @ModelAttribute("recipe")Recipe recipe,
								BindingResult result,
								Model model,
								Model model2) {
		if(result.hasErrors()) {
			model.addAttribute("recipe", recipe);
			List<Category> allcat = categoryservice.allCategory();
			model2.addAttribute("allcat", allcat);
			return "createR.jsp";
		}else {
			recipeservice.createRecipe(recipe);
			return "redirect:/success";
		}
	}
//	Many to many
	@GetMapping("/likerecipe/{recipeId}")
	public String likedRecipe(@PathVariable Long recipeId, HttpSession session) {
		
//		1. grab the current user logged in from session
		Long userId = (Long) session.getAttribute("user_id");
//		2. find the user from the db using the id
		User thisLoggedInUser = userservice.findUser(userId);
		
//		get the book
		Recipe thisrecipe = recipeservice.findRecipe(recipeId);
		
//		make the M:N connection
//		thisLoggedInUser.getBooksRead().add(thisBook);
//		inverse
		thisrecipe.getUsers().add(thisLoggedInUser);
//		DON't FORGET TO SAVE TO THE DB
		recipeservice.updateRecipe(thisrecipe);
		return "redirect:/success";
	}
	
	@GetMapping("/unlikerecipe/{recipeId}")
	public String unlikedRecipe(@PathVariable Long recipeId, HttpSession session) {
		
//		1. grab the current user logged in from session
		Long userId = (Long) session.getAttribute("user_id");
//		2. find the user from the db using the id
		User thisLoggedInUser = userservice.findUser(userId);
		
//		get the book
		Recipe thisrecipe = recipeservice.findRecipe(recipeId);
		
//		make the M:N connection
//		thisLoggedInUser.getBooksRead().add(thisBook);
//		inverse
		thisrecipe.getUsers().remove(thisLoggedInUser);
//		DON't FORGET TO SAVE TO THE DB
		recipeservice.updateRecipe(thisrecipe);
		return "redirect:/success";
	}
	
	@GetMapping("/profile/{userID}")
	public String profilepage(@PathVariable Long userID,HttpSession session,Model model) {
//		get user in sesssion
		User thisUser = userservice.findUser(userID);
		model.addAttribute("thisUser", thisUser);
//		show liked recipes
		List<UserRecipe> allLikedrecipes = userRecipeserv.allLikedRecipe();
		model.addAttribute("allLikedrecipes", allLikedrecipes);
//		show all recipes
		List<Recipe> allrecipes = recipeservice.allRecipe();
		model.addAttribute("allrecipes", allrecipes);
		
		return "profile.jsp";
	}
//	FIND ONE BY ID
	@GetMapping("/view/{id}")
	public String showOne(@PathVariable("id")Long id,
							Model model, HttpSession session,
							Model model1) {
//			bring in the session Id
			Long userId = (Long)session.getAttribute("user_id");
			User thisUser = userservice.findUser(userId);
			model1.addAttribute("thisUser", thisUser);
			

			Recipe recipe = recipeservice.findRecipe(id);
			model.addAttribute("recipe", recipe);
			
			List<Category> allcategory = categoryservice.allCategory();
			model.addAttribute("allcategory", allcategory);
		
		return "showOne.jsp";
	}
	@GetMapping("/view2/{id}")
	public String showOne2(@PathVariable("id")Long id,
							Model model, HttpSession session,
							Model model1) {
//			bring in the session Id
//			Long userId = (Long)session.getAttribute("user_id");
//			User thisUser = userservice.findUser(userId);
//			model1.addAttribute("thisUser", thisUser);
			

			Recipe recipe = recipeservice.findRecipe(id);
			model.addAttribute("recipe", recipe);
			
			List<Category> allcategory = categoryservice.allCategory();
			model.addAttribute("allcategory", allcategory);
		
		return "showOne1.jsp";
	}
//	category page
	@GetMapping("/category/{id}")
	public String category(@PathVariable("id")Long id,Model model, HttpSession session) {
////		bring in the session Id
		Long userId = (Long)session.getAttribute("user_id");
		User thisUser = userservice.findUser(userId);
		model.addAttribute("thisUser", thisUser);
		Category category = categoryservice.findCategory(id);
		model.addAttribute("category", category);
//		show all recipes
		List<Recipe> allrecipes = recipeservice.allRecipe();
		model.addAttribute("allrecipes", allrecipes);
		
		return "category.jsp";
	}
//	category page
	@GetMapping("/category2/{id}")
	public String category2(@PathVariable("id")Long id,Model model, HttpSession session) {
////		bring in the session Id
//		Long userId = (Long)session.getAttribute("user_id");
//		User thisUser = userservice.findUser(userId);
//		model.addAttribute("thisUser", thisUser);
//		one category
		Category category = categoryservice.findCategory(id);
		model.addAttribute("category", category);
//		show all recipes
		List<Recipe> allrecipes = recipeservice.allRecipe();
		model.addAttribute("allrecipes", allrecipes);
		
		return "category2.jsp";
	}

}

