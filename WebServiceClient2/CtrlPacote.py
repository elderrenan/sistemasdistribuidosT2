# -*- coding: utf-8 -*-
from PacoteDesejo import PacoteDesejo
from Util import exec_request, request_to_json, print_list, reservar


#Este m�todo � chamado pelo menu e � respons�vel por controlar uma opera��o de listagem e reserva de um pacote

def opcao_pacote():
    url = "http://localhost:8080/Trabalho3WebService/trabalho3/vendaPacote"
    
    estadoOrigem = input('Qual é o estado que deseja partir?')
    cidadeOrigem = input('Qual é a cidade que deseja partir?')
    
    estadoDestino = input('Qual é o estado que deseja ir?')
    cidadeDestino = input('E qual é a cidade que deseja ir?')
    
    dataIda = input('Qual é a data de partida? (dd/mm/aaaa)')
    dataRetorno = input('Qual é a data de retorno? (dd/mm/aaaa)')
    quantPessoas = input('Qual a quantidade de pessoas irão?')
    
    print("OK, VOU PESQUISAR PARA VOCÊ!")
    
    PD = PacoteDesejo(dataIda, dataRetorno, quantPessoas, cidadeOrigem, cidadeDestino, estadoOrigem, estadoDestino)
    response = exec_request(url, PD.to_JSON(), "post")
    list_pacotes = request_to_json(response)

    print_list(list_pacotes)
    nova_busca = False
    while not(nova_busca):
        opc = input('Para escolher o pacote digite o código. Para fazer uma nova busca digite N')
        
        if opc == 'N' or opc == 'n':
            nova_busca = True
        else:
            for lp in list_pacotes:
                opc2 = int(opc)
                if opc2 == lp.get('codigo'):
                    reservar(opc, url)
                    break
        