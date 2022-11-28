<h1 align = "center" weight="bold"> ============== ARQUITETURA MVC ============== </h1>
<br/>
	
<p align = "center">
	<img width = "500" height = "360" src = "https://schuknight.files.wordpress.com/2015/05/mvc-sequence.gif">
</p>
	
### Breve explicação sobre padrão de projeto MVC.

<br/>
	
<h1 align = "center">###### MVC ######</h1>

<br/>

<p>O modelo é uma abreviação pra model, view e controller, onde basicamente: 
'Model' é a parte responsável por representar as tabelas do banco de dados dentro do código. A camada que guarda os dados que entram no sistema atráves do 'V' que por sua vez quer dizer 
'View'. Essa é a camada do sistema onde ocorre interação com o usuário. Pode ser através de interfaces, formulários de login, botões, etc.</p>

<br/>

<p>O Controller é a camada que realiza todas as "regras de negócio da aplicação", ela recebe as requisições da view enviadas pelo usuário, um exemplo claro é quando o usuário clica em um botão de fazer login por exemplo. A requisição do login vai para o controller que faz todas as validações, inclusive se comunicando com o banco de dados.</p>

<br/>
<br/>

<p align = "center"> 
	<img height = "300" src = "https://dkrn4sk0rn31v.cloudfront.net/uploads/2020/06/diagramaMVC.png">
</p>