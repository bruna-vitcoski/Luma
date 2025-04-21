package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.AcoesSelenium;
import java.util.List;
import java.time.Duration;

public class ProdutoPage extends AcoesSelenium {

    private WebDriver driver;

    public ProdutoPage(WebDriver driver) 
    {
        this.driver = driver;
    }

    private static final By botaoAdicionarCarrinho = By.cssSelector("button#product-addtocart-button");
    private static final By mensagemSucesso = By.cssSelector(".message-success.success.message");
    private String tamanhoSelecionado = "";
    private String corSelecionada = "";
    
    public void acessarProduto(String produtoUrl, int esperaSegundos) 
    {
        try 
        {
            driver.get(produtoUrl);
            System.out.println("Produto acessado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao acessar o produto: " + e.getMessage());
        }
    }

    public void selecionarTamanhoCor(int esperaSegundos) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Tamanho
            List<WebElement> tamanhos = driver.findElements(By.xpath("//div[contains(@class,'swatch-attribute size')]//div[@role='option']"));
            if (!tamanhos.isEmpty()) 
            {
                WebElement primeiroTamanho = tamanhos.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(primeiroTamanho));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primeiroTamanho);
                tamanhoSelecionado = primeiroTamanho.getText().trim();
                System.out.println("Tamanho selecionado: " + tamanhoSelecionado);
            }

            // Cor
            List<WebElement> cores = driver.findElements(By.xpath("//div[contains(@class,'swatch-attribute color')]//div[@role='option']"));
            if (!cores.isEmpty()) 
            {
                WebElement primeiraCor = cores.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(primeiraCor));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primeiraCor);
                corSelecionada = primeiraCor.getAttribute("aria-label").trim();
                System.out.println("Cor selecionada: " + corSelecionada);
            }
        } catch (Exception e) 
        {
            System.out.println("❌ Erro ao selecionar tamanho ou cor: " + e.getMessage());
        }
    }

    public void adicionarAoCarrinho(int esperaSegundos) 
    {
        try 
        {
            esperarElementoClicavel(botaoAdicionarCarrinho, esperaSegundos);

            WebElement botao = driver.findElement(botaoAdicionarCarrinho);
            if (botao.isEnabled() && botao.isDisplayed()) 
            {
                rolarAteElemento(botaoAdicionarCarrinho);
                click(driver, botaoAdicionarCarrinho, esperaSegundos);

                esperarElementoVisivel(driver, mensagemSucesso, esperaSegundos);
                String mensagem = driver.findElement(mensagemSucesso).getText();

                // Nome do produto
                String nomeProduto = driver.findElement(By.cssSelector("span.base")).getText();

                System.out.println("✅ Produto adicionado com sucesso!");
                System.out.println("🛒 Produto: " + nomeProduto);
                System.out.println("🎨 Cor: " + (corSelecionada.isEmpty() ? "Não informado" : corSelecionada));
                System.out.println("📏 Tamanho: " + (tamanhoSelecionado.isEmpty() ? "Não informado" : tamanhoSelecionado));
                System.out.println("📢 Mensagem: " + mensagem);
            } else {
                System.out.println("O botão 'Adicionar ao carrinho' não está disponível para clique.");
            }
        } catch (Exception e) 
        {
            System.out.println("Erro ao adicionar ao carrinho: " + e.getMessage());
        }
    }


    // Função para esperar o elemento ser clicável
    private void esperarElementoClicavel(By by, int tempoSegundos) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tempoSegundos));
        wait.until(ExpectedConditions.and(
            ExpectedConditions.visibilityOfElementLocated(by),
            ExpectedConditions.elementToBeClickable(by)
        ));
    }

    // Função para rolar até o elemento
    private void rolarAteElemento(By by) 
    {
        WebElement elemento = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }
}
