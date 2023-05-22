package cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helpers.ChromeHelper;
import helpers.ScreenshotHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class CriarContaDeUsuarioTestSteps {

	WebDriver driver;
	
	@Dado("Acessar a página de criação de conta de usuário")
	public void acessar_a_pagina_de_criacao_de_conta_de_usuario() {
		driver = ChromeHelper.create("http://appcontascoti-001-site1.dtempurl.com/Account/Register");
	}

	@E("Informar o nome do usuário {string}")
	public void informar_o_nome_do_usuario(String nome) {
		driver.findElement(By.xpath("//*[@id=\"Nome\"]")).sendKeys(nome);
	}

	@E("Informar o email do usuário {string}")
	public void informar_o_email_do_usuario(String email) {
		String emailIni = email.substring(0, email.indexOf('@'));
		String emailFim = email.substring(email.indexOf('@'));
		email = emailIni + new Random().nextInt() + emailFim;
		
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
	}

	@E("Informar a senha do usuário {string}")
	public void informar_a_senha_do_usuario(String senha) {
		driver.findElement(By.xpath("//*[@id=\"Senha\"]")).sendKeys(senha);
	}

	@E("Confirmar a senha do usuário {string}")
	public void confirmar_a_senha_do_usuario(String senha) {
		driver.findElement(By.xpath("//*[@id=\"SenhaConfirmacao\"]")).sendKeys(senha);
	}

	@Quando("Solicitar a realização do cadastro")
	public void solicitar_a_realizacao_do_cadastro() {
		driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[5]/input")).click();
	}

	@Então("Sistema informa que o usuário foi cadastrado com sucesso")
	public void sistema_informa_que_o_usuario_foi_cadastrado_com_sucesso() {
		ScreenshotHelper.create(driver, "Criar conta de usuário - Criação de conta de usuário com sucesso.png");
		String mensagem = driver.findElement(By.xpath("/html/body/div[1]")).getText();
		assertEquals("Sucesso! Parabéns, sua conta foi cadastrada com sucesso.", mensagem);
		driver.close();
	}

	@E("Informar um email já cadastrado para outro usuário {string}")
	public void informar_um_email_ja_cadastrado_para_outro_usuario(String email) {
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
	}

	@Então("Sistema informa que o email já foi cadastrado para outro usuário")
	public void sistema_informa_que_o_email_ja_foi_cadastrado_para_outro_usuario() {
		ScreenshotHelper.create(driver, "Criar conta de usuário - Validação de email já cadastrado.png");
		String mensagem = driver.findElement(By.xpath("/html/body/div[1]")).getText();
		assertEquals("Alerta! O email informado já está cadastrado no sistema, tente outro.", mensagem);
		driver.close();
	}

	@E("Manter os campos vazios")
	public void manter_os_campos_vazios() {
		driver.findElement(By.xpath("//*[@id=\"Nome\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"Senha\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"SenhaConfirmacao\"]")).clear();
	}

	@Então("Sistema informa que todos os campos são obrigatórios")
	public void sistema_informa_que_todos_os_campos_sao_obrigatorios() {
		ScreenshotHelper.create(driver, "Criar conta de usuário - Validação de campos obrigatórios.png");
		String mensagem = driver.findElement(By.xpath("/html/body/div[1]")).getText();
		assertEquals("Alerta! Ocorreram erros de validação no preenchimento do formulário.", mensagem);
		driver.close();
	}
}
