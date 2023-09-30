package ca.sheridancollege.jalani.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.jalani.database.DatabaseAccess;
import jakarta.servlet.http.HttpSession;
import ca.sheridancollege.jalani.beans.*;

@Controller
public class MainController {

	@Autowired @Lazy
	private DatabaseAccess da;

	@GetMapping("/")
	public String indexPage(Model model, @ModelAttribute Book stu)
	{
		model.addAttribute("stu", new Book());
		model.addAttribute("studentList",da.getStudentList()); 
		model.addAttribute("ListOfReviews", da.getStudentList1());
		return "index.html";
	}
	
	@GetMapping("/ViewReviewsById/{id}")
	public String ViewReviews(@PathVariable Long id, Model model, HttpSession session) {
	    Book book = da.getBookById(id);
	    Review review = da.ViewReviewById(id);
	    session.setAttribute("id", id);

	    model.addAttribute("book", book);
	    model.addAttribute("review", review);
	    model.addAttribute("id", id); 

	    List<Review> reviews = da.getStudentList(id);
	    model.addAttribute("reviews", reviews);

	    return "view-book.html";
	}

	
	@GetMapping("/login")
	public String login()
	{
		return "login.html";
	}
	
	@GetMapping("/secure")
	public String secure(Authentication auth, Model model)
	{
		String email = auth.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : auth.getAuthorities()) 
		{
		    roleList.add(ga.getAuthority());
		}
		model.addAttribute("username", email);
		model.addAttribute("roles", roleList);
		return "/user/add-review.html";
		
	}
	@GetMapping("/permissionDenied")
	public String permDenied()
	{
		return "/error/permissionDenied.html";
	}
	
	@GetMapping("/register") 
	public String GetRegister()
	{
		return "register.html";
	}
	@PostMapping("/register")
	public String postRegister(@RequestParam String username,@RequestParam String password)
	{
		da.addUser(username, password);
		Long userId=da.findUserAccount(username).getUserId(); 
		da.addRole(userId,Long.valueOf(1));
		return "redirect:/"; //load a index page
	}
	
	@PostMapping("/AddReview")
	public String AddReview(@RequestParam String review, Model model, HttpSession session) {
	    Long id = (Long) session.getAttribute("id");
	    da.addReviewToDB(id, review);

	    List<Review> updatedReviewList = da.getStudentList(id);

	    model.addAttribute("review", updatedReviewList);

	
	    Book book = da.getBookById(id);
	    model.addAttribute("book", book);

	    return "redirect:/"; 
	}

	@GetMapping("/adminAddbook")
	public String adminAddbook(Authentication auth, Model model)
	{
		String email = auth.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : auth.getAuthorities()) 
		{
		    roleList.add(ga.getAuthority());
		}
		model.addAttribute("username", email);
		model.addAttribute("roles", roleList);
		return "/admin/add-book.html";
		
	}

	@GetMapping("/admin")
	public String secure()
	{
		return "redirect:/";
	}
}
