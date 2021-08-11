# -*- coding: utf-8 -*-
from PassagemDesejo import PassagemDesejo
from Util import exec_request, request_to_json, print_list, reservar, reservarIV

#Este método é chamado pelo menu e é responsável por controlar uma operação de listagem e reserva de uma passagem aérea


def opcao_passagem():
    url = "http://localhost:8080/Trabalho3WebService/trabalho3/vendaPassagem"
            
    estadoOrigem = input('Estado de origem?')
    cidadeOrigem = input('Cidadede origem?')
    estadoDestino = input('Estado de destino?')
    cidadeDestino = input('Cidade de destino?')
    dataViagem = input('Qual é a data da viagem (dd/mm/aaaa)')
    numPassagens = input('Numero de passagens')
    temp = input('Ida e volta (S - sim | N - não) ?')
    if temp == 's' or temp == 'S':
        idaVolta = True
    else:
        idaVolta = False
    
    print("OK, VOU PESQUISAR PARA VOC�!")
    
    PD = PassagemDesejo(estadoOrigem, cidadeOrigem, estadoDestino, cidadeDestino, dataViagem, numPassagens, idaVolta)
    
    response = exec_request(url, PD.to_JSON(), "post")
    list_passagens = request_to_json(response)
    
    print_list(list_passagens)
    nova_busca = False
    while not(nova_busca):
        if idaVolta:
            opcI = input("CÓDIGO DA OPÇÃO DE IDA. Para fazer uma nova busca digite N")
            opcV = input("CÓDIGO DA OPÇÃO DE VOLTA. Para fazer uma nova busca digite N")
            if opcI == 'N' or opcI == 'n' or opcV == 'N' or opcV == 'n' :
                nova_busca = True
            else:
                cont = 0
                for lp in list_passagens:
                    opcI2 = int(opcI)
                    if opcI2 == lp.get('codigo'):
                        cont = cont + 1
                        break
                for lp in list_passagens:
                    opcV2 = int(opcV)
                    if opcV2 == lp.get('codigo'):
                        cont = cont + 1
                        break
                if cont == 2:
                    reservarIV(opcI2, opcV2, url)
                    break 
        else:
            opc = input('CÓDIGO DA OPÇÃO. Para fazer uma nova busca digite N')
            if opc == 'N' or opc == 'n':
                nova_busca = True
            else:
                for lp in list_passagens:
                    opc2 = int(opc)
                    if opc2 == lp.get('codigo'):
                        reservar(opc2, url)
                        break
                    else:
                        print("CóDIGO INVALIDO")
