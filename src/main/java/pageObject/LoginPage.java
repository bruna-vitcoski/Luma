package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utils.AcoesSelenium;

public class LoginPage extends AcoesSelenium {

    private WebDriver driver;

    public LoginPage(WebDriver driver) 
    {
        this.driver = driver;
    }

    public static final By campoEmail = By.id("email");
    public static final By campoSenha = By.id("pass");
    public static final By botaoLogin = By.id("send2");
    public static final By nomeUsuario = By.xpath("//span[contains(text(), 'Welcome')]");

    public void realizarLogin(String email, String senha, int esperaSegundos) 
    {
        enviarDados(driver, campoEmail, email, esperaSegundos);
        enviarDados(driver, campoSenha, senha, esperaSegundos);
        click(driver, botaoLogin, esperaSegundos);
        System.out.println("Login realizado com sucesso!");
    }

    public boolean verificarLogin(int esperaSegundos) 
    {
        try {
            esperarElementoVisivel(driver, nomeUsuario, esperaSegundos);
            String nome = driver.findElement(nomeUsuario).getText();
            System.out.println("Usuário logado: " + nome);
            return true;
        } 
        catch (Exception e) 
        {
            System.out.println("Erro ao verificar o login: " + e.getMessage());
            return false;
        }
    }
}
