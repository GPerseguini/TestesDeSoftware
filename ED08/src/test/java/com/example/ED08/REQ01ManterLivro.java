package com.example.ED08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class REQ01ManterLivro {
    private WebDriver driver;
    private Map<String, Object> vars;

    @BeforeEach
    public void setUp() {
        System.out.println("ok");
        driver = new SafariDriver();
        //driver.get("https://ts-scel-web.herokuapp.com/login");
        driver.navigate().to("https://ts-scel-web.herokuapp.com/login");
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }


    @Test
    public void ct01cadastrarlivrocomsucesso() {

        // dado que o livro não esta cadastrado
        espera();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.linkText("Livros")).click();
        espera();
        // quando o usuario cadastrar um livro
        driver.findElement(By.id("isbn")).click();
        driver.findElement(By.id("isbn")).sendKeys("2222");
        driver.findElement(By.id("autor")).click();
        driver.findElement(By.id("autor")).sendKeys("J. K. Rowling");
        driver.findElement(By.id("titulo")).click();
        driver.findElement(By.id("titulo")).sendKeys("Harry Potter");
        driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
        // entao apresenta as informacoes do livro
        assertTrue(driver.getPageSource().contains("Harry Potter"));
        assertTrue(driver.getPageSource().contains("J. K. Rowling"));
        assertEquals(("Lista de livros"), driver.findElement(By.className("panel-title")).getText());
        assertEquals("https://ts-scel-web.herokuapp.com/sig/livros", driver.getCurrentUrl());

        // *********************************************************************************
        // teardown - exclusao do registro
        // *********************************************************************************
        driver.findElement(By.linkText("Excluir")).click();
        espera();
    }

    @Test public void ct02atualizalivrocomsucesso() {

        // dado que o livro não esta cadastrado
        espera();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.linkText("Livros")).click();
        driver.findElement(By.id("isbn")).click();
        driver.findElement(By.id("isbn")).sendKeys("2223");
        driver.findElement(By.id("autor")).sendKeys("Ricardo Satosho");
        driver.findElement(By.id("titulo")).sendKeys("Lógica de Programação");
        driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
        espera();
        //**********************************************************************************
// quando o usuario altera o nome do autor do livro
        //**********************************************************************************
        driver.findElement(By.linkText("Editar")).click();
        driver.findElement(By.id("autor")).sendKeys("Ricardo Satoshi");
        driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
        assertTrue(driver.getPageSource().contains("Satoshi"));
        assertFalse(driver.getPageSource().contains("Satosho"));

        // *********************************************************************************
        // teardown - exclusao do registro
        // *********************************************************************************
       // driver.findElement(By.linkText("Excluir")).click();
        espera();
    }

    @Test
    public void ct03excluirlivrocomsucesso() {

        // dado que o livro não esta cadastrado
        espera();
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.cssSelector("button")).click();
        driver.findElement(By.linkText("Livros")).click();
        espera();
        // quando o usuario cadastrar um livro
        driver.findElement(By.id("isbn")).click();
        driver.findElement(By.id("isbn")).sendKeys("2222");
        driver.findElement(By.id("autor")).click();
        driver.findElement(By.id("autor")).sendKeys("J. K. Rowling");
        driver.findElement(By.id("titulo")).click();
        driver.findElement(By.id("titulo")).sendKeys("Harry Potter");
        driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
        // entao apresenta as informacoes do aluno
        assertTrue(driver.getPageSource().contains("Harry Potter"));
        assertEquals(("Lista de livros"), driver.findElement(By.className("panel-title")).getText());
        assertTrue(driver.getPageSource().contains("J. K. Rowling"));
        // *********************************************************************************
        // teardown - exclusao do registro
        // *********************************************************************************
        driver.findElement(By.linkText("Excluir")).click();
        espera();
        assertFalse(driver.getPageSource().contains("J. K. Rowling"));
        assertTrue(driver.getPageSource().contains("Harry Potter"));

    }

    public void espera() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
