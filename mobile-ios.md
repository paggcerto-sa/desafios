# Carteira de Moeda Estrangeira

A **‘MoneyAju SA’**, que é uma empresa que trabalha com compra e venda de moedas estrangeiras, necessita a criação de um aplicativo iOS em Swift para que os seus clientes possam comprar e vender moedas além de consultar o seu saldo detalhado por moeda selecionada.

Sua tarefa será **criar o aplicativo da ’MoneyAju’**. A API a ser utilizada será a **‘exchangeratesapi.io’**, uma API 100% RESTful. A listagem de moedas disponíveis para compra e o seu valor de venda serão fornecidos através dessa API. No momento do cadastro do usuário ele receberá 10.000,00BRL (Dez mil reais) fictícios para utilizar no aplicativo.

## Features

- Cadastro e login (Dados podem ser salvos localmente ou através do firebase).
- Consulta de saldo discriminado por moeda escolhida (Dados podem ser salvos localmente ou através do firebase).
- Consulta de saldo total em BRL (convertendo e somando o valor de todas as moedas).
- Listagem de moedas disponíveis para compra.
- Histórico de valor de uma moeda com filtro por data.
- Compra de moeda estrangeira pagando em BRL.
- Venda de moeda estrangeira recebendo em BRL.

## Recomendações
- Utilizar o CocoaPods para gerenciamento de dependências.
- Utilizar o AlamoFire para requsições HTTP.
- Utilizar o ObjectMapper para tratar objetos JSON.

## Bônus

- Utilizar o padrão de arquitetura de software **VIPER**.

## Informações Adicionais

A API e sua documentação pode ser acessada neste endereço: https://exchangeratesapi.io/

Lembrando que todas as requisições devem ter como moeda base o REAL(BRL).

## Dúvidas

Em caso de dúvidas abra uma *issue* que assim que possível iremos responder e entrar em contato.
