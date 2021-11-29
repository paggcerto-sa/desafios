# Transação com cartão e antecipação de recebíveis

A Pagcerto pretende lançar no mercado o serviço de antecipação de recebíveis, e para isso precisamos da sua ajuda. Aos lojistas/vendedores serão permitidos solicitar antecipação de recebíveis das transações aprovadas pela Pagcerto. Seu desafio é desenvolver um projeto que represente esse produto, sendo a implementação discorrida em três etapas. A primeira etapa destina-se a transação com cartão, a segunda tem foco no gerenciamento de antecipação e a terceira trata sobre os testes funcionais.

# Etapa 1: Transação

Transações são operações financeiras originadas de vendas com cartão de crédito. Para cada transação, devem ser mantidas as seguintes informações:

- Identificador único numérico da transação (NSU);
- Data em que a transação foi efetuada;
- Data de aprovação (caso aprovada);
- Data de reprovação (caso reprovada);
- Antecipado (flag que marca uma transação como aprovada na solicitação de antecipação);
- Confirmação da adquirente (aprovada ou recusada);
- Valor bruto da transação;
- Valor líquido da transação (valor da transação subtraído a taxa fixa);
- Taxa fixa cobrada;
- Número de parcelas da transação;
- Quatro últimos dígitos do cartão.

Para cada parcela de transação, devem ser mantidas as seguintes informações:

- Identificador único numérico da parcela;
- Chave estrangeira da transação;
- Valor bruto da parcela;
- Valor líquido da parcela;
- Numero da parcela;
- Valor antecipado (Esse campo só deve ser preenchido se a transação for aprovada pela análise do financeiro, na solicitação de antecipação);
- Data prevista de recebimento da parcela;
- Data em que a parcela foi repassada (Esse campo só deve ser preenchido se a transação for aprovada pela análise do financeiro, na solicitação de antecipação).

Toda transação aprovada deve gerar parcelas com vencimento a cada 30 dias, exemplo: Se a transação for de R$100,00 em 2x (duas parcelas), deve ser criado o registro de transação (entidade forte), conforme os critérios acima, e uma lista de parcelas associadas a essa transação (entidade fraca). Nesse exemplo, seriam geradas duas parcelas de R$49,55 cada, sendo esse valor obtido a partir do valor da transação, nesse caso 100 reais, subtraido a taxa fixa, 0,90, e dividido pelo número de parcelas, no exemplo 2x. Sobre a data de recebimento das parcelas, ainda nesse exemplo, a primeira teria seu repasse realizado com 30 dias após a data de realização da venda e a segunda parcela com 60 dias a partir da mesma data de referência.

## Critérios de aceitação

- Cobrar taxa fixa de 0,90 nas transações aprovadas;
- Na requisição de transação (efetuar pagamento), o número do cartão deve conter 16 caracteres numéricos, sem espaços;
- Caso o número do cartão inicie com "5999", deve ter a transação reprovada ao efetuar pagamento, nos demais casos válidos a transação deverá ser aprovada;
- Gerar parcelas apenas em transações aprovadas;
- O vencimento de cada parcela deverá ser obtido através da multiplicação do número da parcela por 30, conforme exemplificado acima;
- O valor líquido da parcela deverá ser obtido a partir do valor bruto da transação subtraído a taxa fixa, dividido pelo número de parcelas (já citado em exemplo).

## Sobre o serviço

Construa uma API RESTful para que nossos clientes integrem seus sistemas financeiros com a sua conta da Pagcerto, oferecendo os seguintes endpoints:

- Efetuar pagamento com cartão de crédito;
- Consultar uma transação e suas parcelas a partir do identificador da transação.

# Etapa 2: Antecipação

Solicitações de antecipação são documentos emitidos pelo lojista/vendedor através do nosso serviço de repasse antecipado de valores. A antecipação de uma transação tem um custo de 3.8% aplicado em cada parcela da transação, se aprovada pela análise do financeiro, sendo automaticamente debitado no seu repasse. Considerando o exemplo da transação citado na fase 1, se cada parcela da transação tem valor líquido de 49,55, o valor antecipado da parcela seria obtido a partir desse valor líquido, debitado a taxa de 3.8%.

Para cada solicitação de antecipação, devem ser mantidas as seguintes informações:

- Identificador único da solicitação;
- Data da solicitação;
- Data de início da análise;
- Data de finalização da análise;
- Resultado da análise (aprovado, aprovado parcialmente ou reprovado);
- Valor solicitado na antecipação (soma do valor líquido das transações solicitadas na antecipação);
- Valor antecipado (soma do valor antecipado de todas as parcelas de transações aprovadas na antecipação);
- Lista de transações solicitadas na antecipação.

## Critérios de aceitação

- Não é permitido incluir em uma nova solicitação de antecipação transações solicitadas anteriormente;
- Para realização de uma nova solicitação de antecipação, é necessário que a solicitação atual já tenha sido finalizada;
- A data de finalização da análise deve ser preenchida quando todas as transações da antecipação forem resolvidas como aprovadas ou reprovadas;
- Aplicar taxa de 3.8% em cada parcela de transação antecipada, considerando o valor líquido da parcela. Esse valor deve ser armazenado no campo "Valor antecipado" da parcela da transação em questão;
- Caso a transação seja aprovada na antecipação, ao finalizar a solicitação, deve ter o campo "Data em que a parcela foi repassada", da entidade "Parcela", preenchida com a data atual.

O trâmite de uma solicitação de antecipação progride através das seguintes etapas:

1. Aguardando análise (pendente): O lojista solicitou antecipação, mas ainda não foi iniciado a análise pela equipe financeira da Pagcerto;
2. Em análise: A equipe financeira iniciou a análise da antecipação, podendo aprovar ou reprovar uma ou mais transações contidas na solicitação;
3. Finalizada: Quando a análise da solicitação é encerrada, a antecipação pode assumir um dos seguintes resultados: aprovada (todas as transações aprovadas), aprovada parcialmente (quando existe ao menos uma transação aprovada e uma transação reprovada na análise) ou reprovada (todas as transações reprovadas).

## Sobre o serviço

Implemente os seguintes endpoints na API:

- Consultar transações disponíveis para solicitar antecipação (não é necessário filtros);
- Solicitar antecipação a partir de uma lista de transações;
- Iniciar o atendimento da antecipação;
- Aprovar ou reprovar uma ou mais transações da antecipação (quando todas as transações forem finalizadas, a antecipação será finalizada);
- Consultar histórico de antecipações com filtro por status (pendente, em análise, finalizada).

# Sobre o desenvolvimento da solução

Para o desenvolvimento do nosso serviço, atente-se para algumas considerações importantes:

1. Nossa API RESTful deverá ser desenvolvida utilizando ASP.NET Core, Entity Framework Core e SQL Server;
2. Todo o código do projeto deve ser escrito em inglês;
3. Deverá ser utilizado o Git do próprio desenvolvedor para versionamento do código e o repositório deverá ser mantido no GitHub.
