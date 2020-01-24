package com.retail.test;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.retails.cart.ProductCart;
import com.retails.constants.ItemType;
import com.retails.constants.UserType;
import com.retails.model.Product;
import com.retails.model.User;

import shopping.core.Cart;




/*
 * Test Cases for no discount policy
 */
public class TestDiscounts {
	 private Product groceryItem;
	    private Product otherItem;
	    private User employee;
	    private User affiliate;
	    private User simpleUser;
	    private User simpleUserWith2Years;
	    private List<Product> productList;
	    
	    @Before
	    public void setUp() {
	    	productList=new ArrayList<Product>();
	    	employee =new User(UserType.EMPLOYEE, "james", "989898989", "Delhi",false);
	    	affiliate = new User(UserType.AFFILIATE, "james", "989898989", "Delhi",false);
	    	simpleUser = new User(UserType.SIMPLE, "james", "989898989", "Delhi",false);
	    	simpleUserWith2Years= new User(UserType.SIMPLE, "james", "989898989", "Delhi",true);
	    	groceryItem=new Product("apple", 80, ItemType.GROCERY, 21, 1);
	    	otherItem=new Product("jeans", 1000, ItemType.OTHER, 212, 1);    
	    	
	    }
	    
	    @Test
	    public void test_employeeWithGrocery() {
	    	productList.add(groceryItem);
	        ProductCart cart = new ProductCart(employee, productList);
	        // No discount because of grocery item
	        assertEquals(80, cart.total(), 0.01);
	       
	    }
	    
	    @Test
	    public void test_employeeWithOtherItem() {
	        Cart cart = new Cart(employee, discountPolicy);
	        cart.add(otherItem, 4);
	        /*
	         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
	         *  Total 222 * 4 = 888
	         *  After 30% discount = 621.6
	         *  After 30 dollars off due to price over $600 = 591.6 
	         */
	        assertEquals(591.6, cart.total(), 0.01);
	        
	    }
}
