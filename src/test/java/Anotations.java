import org.testng.annotations.*;

public class Anotations {

    @BeforeMethod //Pokreće JEDNOM PRE SVAKOG TESTA
    public void beforeMethod(){
        System.out.println("Before Method!");
    }
    @BeforeClass //Pokreće JEDNOM PRE SVIH TESTOVA
    public void beforeClass(){
        System.out.println("Before Class!");
    }
    @AfterMethod //Pokreće JEDNOM POSLE SVAKOG TESTA
    public void afterMethod(){
        System.out.println("After Method!");
    }
    @AfterClass //Pokreće JEDNOM POSLE SVIH TESTOVA
    public void afterClass(){
        System.out.println("After Class!");
    }
    @Test //Ovo je zapravo test!!!
    public void Test1(){
        System.out.println("Test1");
    }
    @Test
    public void Test2(){
        System.out.println("Test2");
    }
    @Test
    public void Test3(){
        System.out.println("Test3");
    }
}
