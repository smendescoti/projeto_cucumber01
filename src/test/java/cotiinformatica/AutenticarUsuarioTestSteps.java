package cotiinformatica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import helpers.ChromeHelper;
import helpers.ScreenshotHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AutenticarUsuarioTestSteps {

	WebDriver driver;
	
	@Dado("Acessar a página de autenticação")
	public void acessar_a_pagina_de_autenticacao() {
		driver = ChromeHelper.create("http://appcontascoti-001-site1.dtempurl.com/");
	}

	@E("Informar o email de acesso {string}")
	public void informar_o_email_de_acesso(String email) {
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).sendKeys(email);
	}

	@E("Informar a senha de acesso {string}")
	public void informar_a_senha_de_acesso(String senha) {
		driver.findElement(By.xpath("//*[@id=\"Senha\"]")).sendKeys(senha);
	}

	@Quando("Solicitar o acesso à conta do usuário")
	public void solicitar_o_acesso_a_conta_do_usuario() {
		driver.findElement(By.xpath("/html/body/div/div/div/div/form/div[3]/input")).click();
	}

	@Então("Sistema autentica o usuário com sucesso")
	public void sistema_autentica_o_usuario_com_sucesso() {
		ScreenshotHelper.create(driver, "Autenticar usuário - Autenticação de usuário com sucesso.png");
		assertEquals("http://appcontascoti-001-site1.dtempurl.com/Home/Index", driver.getCurrentUrl());
	}

	@E("Redireciona o usuário para a sua área restrita")
	public void redireciona_o_usuario_para_a_sua_area_restrita() {
		String titulo = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/h5")).getText();
		assertTrue(titulo.contains("Resumo de Contas"));		
		driver.close();
	}

	@Então("Sistema informa que o acesso é negado")
	public void sistema_informa_que_o_acesso_e_negado() {
		ScreenshotHelper.create(driver, "Autenticar usuário - Acesso negado.png");
		String mensagem = driver.findElement(By.xpath("/html/body/div[1]")).getText();
		assertEquals("Alerta! Acesso negado, usuário não encontrado.", mensagem);
		driver.close();
	}

	@E("Manter os campos email e senha vazios")
	public void manter_os_campos_email_e_senha_vazios() {
		driver.findElement(By.xpath("//*[@id=\"Email\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"Senha\"]")).clear();
	}

	@Então("Sistema informa que email e senha são obrigatórios")
	public void sistema_informa_que_email_e_senha_sao_obrigatorios() {
		ScreenshotHelper.create(driver, "Autenticar usuário - Validação de campos obrigatórios.png");
		String mensagem = driver.findElement(By.xpath("/html/body/div[1]")).getText();
		assertEquals("Alerta! Ocorreram erros de validação no preenchimento do formulário.", mensagem);
		driver.close();
	}
}
