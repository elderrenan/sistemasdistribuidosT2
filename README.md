# sistemasdistribuidosT2
Trabalho 2 da disciplina de Sistemas Distribuídos do semestre 2021/01 do Bacharelado em Sistemas de Informação da UTFPR.

Vamos continuar com a aplicação de caronas e os mesmos métodos solicitados na aplicação 1.
Porém, cliente e servidor precisam estar em linguagens de programação diferentes.
O mais fácil é alterar a linguagem do cliente.
Mas, vocês podem alterar a linguagem do cliente e do servidor, caso não queiram mais usar Java ou Python.

Para prover a comunicação entre os processos deve-se utilizar Web Services (SOAP ou REST) e não mais Java RMI ou Pyro.

A assinatura digital não precisa ser implementada.

Para o envio das notificações de eventos, vocês vão utilizar o SSE (Server-Sent Events).

Resumindo:
- Duas linguagens de programação diferente;
- Uso de SOAP ou REST para comunicação entre clientes e servidor;
- Uso do SSE para envio das notificações do servidor para os clientes.

Avaliação (valor 3,0):
- Consulta de caronas (valor 0,5);
- Registro de interesse em eventos (valor 1,0);
- Cancelamento de registro de interesse (valor 0,5);
- Envio de notificações com uso do SSE do servidor para o cliente (valor 1,0).

Obs.: como não precisamos mais enviar a chave pública, pois não precisamos mais assinar as mensagens, se vocês preferirem podem remover o método de cadastro de usuário.
Nesse caso, informações para contato podem ser enviadas no registro de interesse.
Porém, não há problema se quiserem manter a assinatura e o cadastro

