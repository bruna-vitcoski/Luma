package test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utils.GerenciamentoDeDrivers;
import pageObject.LoginPage;
import pageObject.ProdutoPage;


public class TestMagentoLogin {

    private WebDriver driver;
    LoginPage loginPage;
    ProdutoPage produtoPage;

    String email = "seu e-mail";
    String senha = "sua senha";
    
    String produtoUrl = "https://magento.softwaretestingboard.com/women/bottoms-women/shorts-women.html";

    @BeforeEach
    public void inicio() 
    {
        driver = GerenciamentoDeDrivers.abrirBrowser("chrome");
        loginPage = new LoginPage(driver);
        produtoPage = new ProdutoPage(driver);
    }

    @Test
    public void testeLoginEAcessoProduto() {
        System.out.println("Iniciando Teste Magento");

        driver.get("https://magento.softwaretestingboard.com/customer/account/login");
        loginPage.realizarLogin(email, senha, 20);

        boolean logado = loginPage.verificarLogin(20);
        Assertions.assertTrue(logado, "Usuário não logado com sucesso.");

        driver.get(produtoUrl);

        var produtos = driver.findElements(By.cssSelector(".product-item-info a.product.photo.product-item-photo"));

        int quantidadeParaTestar = Math.min(produtos.size(), 3);
        for (int i = 0; i < quantidadeParaTestar; i++) 
        {
            WebDriver newTab = driver;
            try 
            {
                WebElement produto = produtos.get(i);
                String link = produto.getAttribute("href");
                newTab.get(link); 

                System.out.println("Acessando produto " + (i + 1));
                produtoPage.selecionarTamanhoCor(20);
                produtoPage.adicionarAoCarrinho(20);

                // Volta para a lista de produtos
                newTab.get(produtoUrl);
                produtos = driver.findElements(By.cssSelector(".product-item-info a.product.photo.product-item-photo"));
            } catch (Exception e) 
            {
                System.out.println("Erro ao acessar ou testar produto " + (i + 1) + ": " + e.getMessage());
            }
        }
    }
    
    @AfterEach
    public void fim() 
    {
        System.out.println("Fim do teste");
    }
}
