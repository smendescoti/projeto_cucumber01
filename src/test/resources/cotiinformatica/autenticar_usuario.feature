#language: pt

Funcionalidade: Autenticar usuário
	como um usuário já cadastrado no sistema
	eu quero acessar minha conta
	para que eu possa utilizar os recursos da aplicação
	
Cenário: Autenticação de usuário com sucesso
	Dado Acessar a página de autenticação
	E Informar o email de acesso "anamaria@gmail.com"
	E Informar a senha de acesso "@Teste123"
	Quando Solicitar o acesso à conta do usuário
	Então Sistema autentica o usuário com sucesso
	E Redireciona o usuário para a sua área restrita
	
Cenário: Acesso negado
	Dado Acessar a página de autenticação
	E Informar o email de acesso "teste@gmail.com"
	E Informar a senha de acesso "@Teste123"
	Quando Solicitar o acesso à conta do usuário
	Então Sistema informa que o acesso é negado
	
Cenário: Validação de campos obrigatórios
	Dado Acessar a página de autenticação
	E Manter os campos email e senha vazios
	Quando Solicitar o acesso à conta do usuário
	Então Sistema informa que email e senha são obrigatórios
