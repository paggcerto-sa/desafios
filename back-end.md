# Antecipação de recebíveis

Aos lojistas/prestadores serão permitidos solicitar antecipação de recebíveis das transações aprovadas, no entanto, não é permitido mais de uma solicitação em andamento.
Para realização de uma nova solicitação de antecipação é necessário que a solicitação atual já tenha sido finalizada.

O trâmite de uma solicitação de antecipação progride através das seguintes etapas:

1. Aguardando análise: A solicitação ainda está na fila aguardando análise da equipe financeira;
2. Em análise: A equipe financeira está atualmente analisando as transações solicitadas;
3. Finalizada: Quando a análise da solicitação é encerrada podendo assumir um dos seguintes resultados: aprovada ou reprovada.

Não é permitido incluir em uma nova solicitação de antecipação transações que já foram solicitadas anteriormente.

## Transação

Transações são operações financeiras originadas de uma venda e realizadas através de cartão de crédito utilizando o nosso pinpad.
Para cada transação são cobrados R$ 0,90 (independente do número de parcelas) e para controle dessa movimentação financeira são mantidas as seguintes informações:

- Data em que a transação foi efetuada;
- Data do repasse (caso já tenha ocorrido);
- Confirmação da adquirente (aprovada ou recusada);
- Valor da transação;
- Valor do repasse;
- Número de parcelas.

## Solicitação de antecipação

Solicitações de antecipação são documentos emitidos pelo lojista/prestador através do nosso serviço. A antecipação de uma transação tem um custo de 3.8% por parcela (ex: 1x -> 3,8%, 2x -> 3,8 * 2 = 7,6%), sendo automaticamente debitado no seu repasse. Para controle dessas solicitações são mantidas as seguintes informações:

- Data da solicitação;
- Data da análise (quando iniciou e quando terminou);
- Resultado da análise (aprovado ou reprovado);
- Valor total das transações solicitadas para antecipação;
- Valor total do repasse.

## Sobre o serviço

A Paggcerto pretende lançar no mercado o serviço de antecipação de recebíveis e para isso precisamos da sua ajuda.
Construa uma API RESTful para que nossos clientes integrem seus sistemas financeiros com a sua conta da Paggcerto oferecendo os seguitens recursos:

- Consultar transações disponíveis para antecipar. Os valores já devem estar calculados visando transparência e possibilitando planejamento financeiro do nosso cliente;
- Solicitar antecipação de transações selecionadas;
- Consultar os detalhes da solicitação em andamento;
- Consultar histórico das solicitações realizadas em um determinado período.

## Sobre o desenvolvimento da solução

Para o desenvolvimento do nosso serviço atente-se para algumas considerações importantes:

1. Nossa API RESTful deverá ser desenvolvida utilizando .NET Core e SQL Server;
2. Deverá ser utilizado o Git para versionamento do código e o repositório deverá ser mantido no GitHub.
