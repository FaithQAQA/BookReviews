package ca.sheridancollege.jalani.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.jalani.database.DatabaseAccess;



@Controller
public class BookController 
{
	

	@Autowired @Lazy
	private DatabaseAccess da;
	
	@PostMapping("/AddBook")
	public String AddBook(@RequestParam String title, String author) 
	{
	 da.addBook(title, author);
	    return "redirect:/"; // Load the index page
	}

}
