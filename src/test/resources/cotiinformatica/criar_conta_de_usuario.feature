#language: pt

Funcionalidade: Criar conta de usuário
	como um usuário do sistema
	eu quero criar uma conta de acesso
	para que eu possa acessar a aplicação
	
Cenário: Criação de conta de usuário com sucesso
	Dado Acessar a página de criação de conta de usuário
	E Informar o nome do usuário "Ana Maria"
	E Informar o email do usuário "anamaria@gmail.com"
	E Informar a senha do usuário "@Teste123"
	E Confirmar a senha do usuário "@Teste123"
	Quando Solicitar a realização do cadastro
	Então Sistema informa que o usuário foi cadastrado com sucesso

Cenário: Validação de email já cadastrado
	Dado Acessar a página de criação de conta de usuário
	E Informar o nome do usuário "Ana Maria"
	E Informar um email já cadastrado para outro usuário "anamaria@gmail.com"
	E Informar a senha do usuário "@Teste123"
	E Confirmar a senha do usuário "@Teste123"
	Quando Solicitar a realização do cadastro
	Então Sistema informa que o email já foi cadastrado para outro usuário
	
Cenário: Validação de campos obrigatórios
	Dado Acessar a página de criação de conta de usuário
	E Manter os campos vazios
	Quando Solicitar a realização do cadastro
	Então Sistema informa que todos os campos são obrigatórios 