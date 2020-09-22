# Antecipação de recebíveis

Aos lojistas/vendedores serão permitidos solicitar antecipação de recebíveis das transações aprovadas pela Pagcerto, no entanto, não é permitido mais de uma solicitação em andamento.
Para realização de uma nova solicitação de antecipação, é necessário que a solicitação atual já tenha sido finalizada (atenção para esse critério de aceitação).

O trâmite de uma solicitação de antecipação progride através das seguintes etapas:

1. Aguardando análise: O lojista solicitou antecipação, mas ainda não foi iniciado a análise pela equipe financeira da Pagcerto;
2. Em análise: A equipe financeira iniciou a análise da antecipação, podendo aprovar ou reprovar uma ou mais transações contidas na solicitação;
3. Finalizada: Quando a análise da solicitação é encerrada, a antecipação pode assumir um dos seguintes resultados: aprovada (todas as transações aprovadas), aprovada parcialmente (quando existe ao menos uma transação aprovada e uma transação reprovada na análise) ou reprovada (todas as transações reprovadas).

## Importante: 
Não é permitido incluir em uma nova solicitação de antecipação transações que já foram solicitadas anteriormente.

## Transação

Transações são operações financeiras originadas de vendas com cartão de crédito.
Para cada transação, é cobrado uma taxa fixa de R$ 0,90 (independente do número de parcelas) e para controle dessa movimentação financeira são mantidas as seguintes informações:

- Identificador único numérico da transação (NSU);
- Data em que a transação foi efetuada;
- Data de aprovação (caso aprovada);
- Data de reprovação (caso reprovada);
- Antecipada (flag que marca a transação como já antecipada. Essa flag é marcada no momento que a transação for solicitada para antecipação);
- Confirmação da adquirente (aprovada ou recusada). Para testar uma reprovação pela adquirente, considere o número de cartão que inicie com "5999";
- Valor bruto da transação;
- Valor líquido da transação (valor da transação subtraído a taxa fixa);
- Taxa fixa cobrada;
- Número de parcelas da transação;
- Quatro últimos dígitos do cartão (na requisição, o número do cartão deve conter 16 caracteres numéricos, sem espaços).

## Dica: 
Uma vez que a transação foi marcada como antecipada, não ficará mais disponível para novas solicitações de antecipação. 

Toda transação aprovada deve gerar parcelas com vencimento a cada 30 dias, exemplo: Se a transação for de R$100,00 em 2x (duas parcelas), deve ser criado o registro de transação (entidade forte), conforme os critérios acima, e uma lista de parcelas associadas a essa transação (entidade fraca). Nesse exemplo, seriam geradas duas parcelas de R$49,55 cada, sendo esse valor obtido a partir do valor da transação, nesse caso 100 reais, subtraido a taxa fixa, 0,90, e dividido pelo número de parcelas, no exemplo 2x. Sobre a data de recebimento das parcelas, ainda nesse exemplo, a primeira teria seu repasse realizado com 30 dias após a data de realização da venda e a segunda parcela com 60 dias a partir da mesma data de referência.

Para cada parcela de transação, devem ser mantidas as seguintes informações:

- Identificador único numérico da parcela;
- Chave estrangeira da transação;
- Valor bruto da parcela;
- Valor líquido da parcela;
- Valor antecipado (Esse campo só deve ser preenchido se a transação for aprovada pela análise do financeiro);
- Data prevista de recebimento da parcela;
- Data em que a parcela foi repassada (Esse campo só deve ser preenchido se a transação for aprovada pela análise do financeiro);

## Solicitação de antecipação

Solicitações de antecipação são documentos emitidos pelo lojista/vendedor através do nosso serviço de repasse antecipado de valores. A antecipação de uma transação tem um custo de 3.8% aplicado em cada parcela da transação aprovada pela análise do financeiro, sendo automaticamente debitado no seu repasse. Considerando o exemplo da transação já citada, como cada parcela da transação tem valor líquido de 49,55, o valor antecipado da parcela seria obtido a partir do seu valor líquido debitado os 3.8%.

Para cada solicitação de antecipação, devem ser mantidas as seguintes informações:

- Identificador único da solicitação;
- Data da solicitação;
- Data de início da análise;
- Data de finalização da análise (preenchida quando todas as transações forem resolvidas como aprovadas ou reprovadas pela análise)
- Resultado da análise (aprovado, aprovado parcialmente ou reprovado);
- Valor total líquido das transações solicitadas para antecipação;
- Valor total da antecipação (considerando apenas as transações aprovadas, descontado a taxa de antecipação)
- Lista de transações solicitadas na antecipação (Relacionamento de uma antecipação para uma ou mais transações).

## Importante: 
Uma transação com análise aprovada ou reprovada não pode ser modificada, ou seja, não deve permitir alteração no status (aprovada/reprovada).

## Sobre o serviço

A Pagcerto pretende lançar no mercado o serviço de antecipação de recebíveis, e para isso precisamos da sua ajuda.
Construa uma API RESTful para que nossos clientes integrem seus sistemas financeiros com a sua conta da Pagcerto oferecendo os seguintes endpoints:

- Realizar pagamento com cartão de crédito (transação);
- Consultar uma transação e suas parcelas a partir da identificador da transação;
- Consultar transações disponíveis para solicitar antecipação;
- Solicitar antecipação a partir de uma lista de transações;
- Consultar uma solicitação de antecipação e suas transações relacionadas a partir do identificador da antecipação;
- Iniciar o atendimento da antecipação;
- Aprovar ou reprovar uma ou mais transações da antecipação (quando todas as transações forem finalizadas, a antecipação será finalizada);
- Consultar histórico de antecipações com filtro por status (pendente, em análise, finalizada).

## Sobre o desenvolvimento da solução

Para o desenvolvimento do nosso serviço, atente-se para algumas considerações importantes:

1. Nossa API RESTful deverá ser desenvolvida utilizando ASP.NET Core, Entity Framework Core e SQL Server;
2. Todo o código do projeto deve ser escrito em inglês;
2. Deverá ser utilizado o Git para versionamento do código e o repositório deverá ser mantido no GitHub.
