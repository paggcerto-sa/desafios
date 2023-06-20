# Analista de Testes

Atualmente na Pagcerto existem duas personas que podem fazer integração com os endpoints, além do login no sistema de gestão. São elas o usuário parceiro e o usuário lojista do parceiro. Um usuário parceiro, ao ser cadastrado e devidamente liberado para acesso aos endpoints e sistema de gestão, pode fazer o cadastro de seus sublojistas (lojista de parceiro). Na hierarquia, ele é responsável pelas movimentações de seus lojistas. O desafio desse teste é realizar o cadastro de um sublojista com as credenciais do parceiro (token), devendo ser validado as regras de negócio descritas na documentação. Para cadastrar um titular (sublojista), você precisa de dois endpoints:

1- Endpoint de autenticação e geração de token de acesso: 

https://desenvolvedor.pagcerto.com.br/v2/account/#operation/autenticar-com-credenciais. 

URL: http://account.homol.pagcerto.com.br/api/v2/{applicationId}/signin

Aplicativo (applicationId): vEDg

Login: analista.teste@email.com

Senha: Analista@2023


2- Endpoint para cadastro de titular (sublojista):
https://desenvolvedor.pagcerto.com.br/v2/account/#tag/conta-titular

URL: http://account.homol.pagcerto.com.br/api/v2/{applicationId}/signup/seller

# Etapa 1: Evidências dos testes

Serão avaliadas suas habilidades junto a softwares de teste de API, como o Postman, por exemplo. Observação: dois sublojistas com o mesmo documento (CPF) não podem ser cadastrados no mesmo parceiro, caso as contas sejam do tipo pessoa física (PF). Se apenas uma das contas for do tipo PF, poderia ser cadastrado uma conta jurídica (PJ) para o mesmo representante (CPF), mas não deveria permitir cadastro com o mesmo CNPJ. 

## Resultado esperado:
- Validar as regras de negócio e simular os erros 422, descritos na documentação. 
- Formular um documento descrevendo os testes realizados e os resultados obtidos, adicionando nesse documento prints de evidências dos testes realizados.

# Etapa 2: Banco de dados

No processo de teste, é importante que o testador tenha habilidades de consulta em banco de dados, para que ele possa validar retornos da API e informações vistas no front-end do sistema. Sendo assim, são apresentadas abaixo três tabelas para que você escreva o script de consulta de determinadas informações.

![DER](https://user-images.githubusercontent.com/3877914/129735429-5e4de68a-fc39-40d1-8879-a33a322b130a.png)

## Consultas solicitadas

- Sabendo que todo parceiro é lojista, mas nem todo lojista é parceiro, e que isso é identificado através da coluna Parceiro na tabela de ContaPagamento, escrever uma consulta SQL que retorne o IdTitular e o Saldo de todos os parceiros ativos da Pagcerto.
- Escrever um script que retorne os valores das seguintes colunas: IdTitular, Id, Valor e DataCadastro (essas três últimas colunas da tabela MovimentacaoConta) das tabelas de ContaPagamento e MovimentacaoConta referente as movimentações no período entre 01 de junho de 2021 e 30 de junho de 2021.
- Sabendo que o detalhe da movimentação pode ser de três tipos: pagamento-conta, transferencia-automatica e taxa-pagamento-conta, escreva um script que retorne o IdTitular, juntamente com o valor e a descrição, quando o tipo do detalhe for "pagamento-conta", também dentro do período da movimentação que abrange de 01 de junho de 2021 e 30 de junho de 2021.

# Sobre o desenvolvimento da solução

Utilize seus conhecimentos e técnicas como analista de testes para formular um documento compreendendo as duas etapas descritas nesse desafio. Os scripts SQL devem ser feitos para SQL SERVER
