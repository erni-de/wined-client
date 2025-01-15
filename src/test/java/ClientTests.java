/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.google.gson.Gson;
import it.unipi.wined.client.UserActions;
import it.unipi.wined.client.objects.Review;
import it.unipi.wined.client.objects.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author erni
 */
public class ClientTests {
    
    public ClientTests() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test() {
        System.out.println(Long.parseLong("3208963748"));
    }
    
    @Test
    public void testGsonError() {
        Review user = new Review();
        Gson gson = new Gson();
        String json = "{code: 500, message: 'invalid'}";
        System.out.println(json);
        System.out.println(gson.fromJson(json, User.class));
        
    }
}
