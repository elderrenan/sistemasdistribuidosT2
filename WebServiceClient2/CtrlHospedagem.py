# -*- coding: utf-8 -*-
from HospedagemDesejo import HospedagemDesejo 
from Util import exec_request, request_to_json, print_list, reservar


#Este m�todo � chamado pelo menu e � respons�vel por controlar uma opera��o de listagem e reserva de uma hospedagem


def opcao_hospedagem():
    url = "http://localhost:8080/Trabalho3WebService/trabalho3/vendaHospedagem"
            
    estado = input('Qual é o estado que deseja se hospedar?')
    cidade = input('E qual é a cidade?')
    periodoInicio = input('Qual é a data inicial da sua reserva? (dd/mm/aaaa)')
    periodoFim = input('E a data final? (dd/mm/aaaa)')
    numQuartoDesejo = input('Qual a quantidade de quartos voc� deseja?')
    
    print("OK, VOU PESQUISAR PARA VOCÊ!")
    
    HD = HospedagemDesejo(estado, cidade, periodoInicio, periodoFim, numQuartoDesejo)
    response = exec_request(url, HD.to_JSON(), "post")
    list_hospedagem = request_to_json(response)

    print_list(list_hospedagem)
    nova_busca = False
    while not(nova_busca):
        opc = input('Para escolher a hospedagem digite o c�digo. Para fazer uma nova busca digite N')
        
        if opc == 'N' or opc == 'n':
            nova_busca = True
        else:
            for lh in list_hospedagem:
                opc2 = int(opc)
                if opc2 == lh.get('codigo'):
                    reservar(opc, url)
                    break