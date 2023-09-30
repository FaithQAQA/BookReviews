package ca.sheridancollege.jalani.database;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.jalani.beans.*;

@Repository
public class DatabaseAccess {
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public DatabaseAccess(NamedParameterJdbcTemplate jdbc)
	{
		this.jdbc=jdbc;
	}
	
	
	
	
	
	public List<Book> getStudentList()
	{
		MapSqlParameterSource namedParameters=new MapSqlParameterSource();
		String s1="SELECT * FROM  books;";
		ArrayList<Book> studentlst=(ArrayList<Book>)jdbc.query(s1, namedParameters, new BeanPropertyRowMapper<Book>(Book.class));
		System.out.println("test "+ studentlst.toString());
		return studentlst;
	}
	
	public Review ViewReviewById(Long id) {
	    String sqlQuery = "SELECT id, bookId, text FROM reviews WHERE id = :id;";

	    MapSqlParameterSource namedParameter = new MapSqlParameterSource();
	    namedParameter.addValue("id", id);
System.out.println(id +" View Review by id");
	    BeanPropertyRowMapper<Review> mapper = new BeanPropertyRowMapper<>(Review.class);
	    Review review = null;
	    try {
	        review = jdbc.queryForObject(sqlQuery, namedParameter, mapper);
	    } catch (EmptyResultDataAccessException e) {
	        System.out.println("Review not found for id=" + id);
	    }
	    return review;
	}

	public Book getBookById(Long id)
	{
		MapSqlParameterSource namedParameter=new MapSqlParameterSource();
		String s4="SELECT * FROM books WHERE id=:id;";
		namedParameter.addValue("id", id);
		BeanPropertyRowMapper<Book> mapper=new BeanPropertyRowMapper<>(Book.class);
		Book student=null;
		System.out.println(id +" get book by id");

		try
		{
			student=jdbc.queryForObject(s4, namedParameter, mapper);
		}
		catch(EmptyResultDataAccessException e)
		{
			System.out.println("student not found for id="+id);
		}
		return student;		
	}
	
	
	
	
	
	
	public User findUserAccount(String email)
	{
		MapSqlParameterSource parameters=new MapSqlParameterSource();
		String query="SELECT * FROM users WHERE email=:email;";
		parameters.addValue("email",email);
		try
		{
			return jdbc.queryForObject(query, parameters,new BeanPropertyRowMapper<User>(User.class));
		}
		catch(EmptyResultDataAccessException ex)
		{
			return null;
		}
	}
	public List<String> getRolesById(Long userId)
	{
		MapSqlParameterSource parameters=new MapSqlParameterSource();
		String query="SELECT roles.rolename FROM user_role,roles WHERE user_role.roleid=roles.roleid AND userid=:userId";
		parameters.addValue("userId", userId);
		return jdbc.queryForList (query,parameters ,String.class);
    }
	
	
	
	public void addUser(String email,String password)
	{
		MapSqlParameterSource parameters=new MapSqlParameterSource();
		String q1="INSERT INTO users(email,encryptedpassword,enabled) VALUES(:email,:encryptedpassword,true);";
		parameters.addValue("email",email);
		parameters.addValue("encryptedpassword",passwordEncoder.encode(password));
		jdbc.update(q1, parameters);
	}
	public void addRole(long userId,long roleId)
	{
		MapSqlParameterSource parameters=new MapSqlParameterSource();
		String q2="INSERT INTO user_role(userId,roleId) VALUES(:user,:role);";
		parameters.addValue("user",userId);
		parameters.addValue("role",roleId);
		jdbc.update(q2, parameters);
	}

	
	public void addReviewToDB(Long bookId, String reviewText) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String q2 = "INSERT INTO reviews (bookId, text) VALUES (:bookId, :text);";
	    parameters.addValue("bookId", bookId);
	    parameters.addValue("text", reviewText);
	    jdbc.update(q2, parameters);
	}

	
	public List<Review> getStudentList(Long id) {
	    String sqlQuery = "SELECT * FROM reviews WHERE bookId = :bookId";
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("bookId", id);

	    List<Review> bookList = jdbc.query(sqlQuery, namedParameters, new BeanPropertyRowMapper<>(Review.class));

	    System.out.println("this list is for book review " + id + " " + bookList);
	    return bookList;
	}

	public List<Review> getStudentList1()
	{
		MapSqlParameterSource namedParameters=new MapSqlParameterSource();
		String s1="SELECT * FROM  reviews;";
		ArrayList<Review> studentlst=(ArrayList<Review>)jdbc.query(s1, namedParameters, new BeanPropertyRowMapper<Review>(Review.class));
		return studentlst;
	}
	
	public void addBook(String title,String author)
	{
		MapSqlParameterSource parameters=new MapSqlParameterSource();
		String q1="INSERT INTO books(title,author) VALUES(:title,:author);";
		parameters.addValue("title",title);
		parameters.addValue("author",author);
		jdbc.update(q1, parameters);
	}
}
