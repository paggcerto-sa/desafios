# Antecipação de recebíveis

Aos lojistas/prestadores serão permitidos solicitar antecipação de recebíveis das transações aprovadas, no entanto, não é permitido mais de uma solicitação em andamento.
Para realização de uma nova solicitação de antecipação é necessário que a solicitação atual já tenha sido finalizada.

O trâmite de uma solicitação de antecipação progride através das seguintes etapas:

1. Aguardando análise: A solicitação ainda está na fila aguardando análise da equipe financeira;
2. Em análise: A equipe financeira está atualmente analisando as transações solicitadas;
3. Finalizada: Quando a análise da solicitação é encerrada podendo assumir um dos seguintes resultados: aprovada ou reprovada.

Não é permitido incluir em uma nova solicitação de antecipação transações que já foram solicitadas anteriormente.

## Transação

Transações são operações financeiras originadas de vendas com cartão de crédito.
Para cada transação, é cobrado uma taxa fixa de R$ 0,90 (independente do número de parcelas) e para controle dessa movimentação financeira são mantidas as seguintes informações:

- Código identificador numérico;
- Data em que a transação foi efetuada;
- Data do repasse (caso já tenha ocorrido);
- Confirmação da adquirente (aprovada ou recusada);
- Valor da transação;
- Valor do repasse (valor da transação subtraído a taxa fixa);
- Número de parcelas
- Quatro últimos dígitos do cartão (na requisição, o número do cartão deve conter 16 caracteres numéricos, sem espaços)

Toda transação aprovada gera parcelas com vencimento a cada 30 dias, ex: Venda de R$100,00 em 2x, gera duas parcelas de R$50,00, sendo a primeira com vencimento para 30 dias e a segunda para 60 dias. O pagamento da taxa fixa é feito apenas na primeira parcela, então para esse exemplo o lojista receberia na primeira parcela R$49,10 e na segunda R$50,00.

## Solicitação de antecipação

Solicitações de antecipação são documentos emitidos pelo lojista/prestador através do nosso serviço. A antecipação de uma transação tem um custo de 3.8% por parcela (ex: 1x -> 3,8%, 2x -> 3,8 * 2 = 7,6%), sendo automaticamente debitado no seu repasse, já descontado o custo fixo. Dessa forma, ao antecipar, o lojista recebe por todas as parcelas da transação no mesmo dia que a solicitação foi atendida. Para controle dessas solicitações, são mantidas as seguintes informações:

- Identificador numérico;
- Data da solicitação;
- Data da análise (quando iniciou e quando terminou);
- Resultado da análise (aprovado ou reprovado);
- Valor total das transações solicitadas para antecipação (já descontado a taxa fixa);
- Valor total do repasse (descontado a taxa fixa e a taxa da antecipação)
- Lista de transações solicitadas na antecipação.

## Sobre o serviço

A Pagcerto pretende lançar no mercado o serviço de antecipação de recebíveis, e para isso precisamos da sua ajuda.
Construa uma API RESTful para que nossos clientes integrem seus sistemas financeiros com a sua conta da Pagcerto oferecendo os seguintes recursos:

- Realizar pagamento com cartão de crédito (transação);
- Consultar transações disponíveis para antecipar (Os valores já devem estar calculados, visando transparência e possibilitando o planejamento financeiro do nosso cliente);
- Solicitar antecipação de transações informadas;
- Consultar os detalhes da solicitação em andamento (devendo retornar, também, a lista de transações da antecipação);
- Iniciar o atendimento da solicitação de antecipação;
- Aprovar ou reprovar uma solicitação de antecipação;
- Consultar histórico das solicitações realizadas em um determinado período (devendo retornar, também, a lista de transações da antecipação).

## Sobre o desenvolvimento da solução

Para o desenvolvimento do nosso serviço, atente-se para algumas considerações importantes:

1. Nossa API RESTful deverá ser desenvolvida utilizando ASP.NET Core, Entity Framework Core e SQL Server;
2. Deverá ser utilizado o Git para versionamento do código e o repositório deverá ser mantido no GitHub.
