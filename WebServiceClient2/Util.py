# -*- coding: utf-8 -*-
import json

import requests

from Reserva import Reserva

#Este arquivo tem métodos comuns as outras classes



#Este método converte a resposta recebioda pelo metodo exec_request para uma lista de JSONs


def request_to_json(response):
    if(response.status_code == 200):
        response_content_byte = response.content
        response_content_str = response_content_byte.decode('unicode_escape')
        response_content_json = json.loads(response_content_str)
        
        return response_content_json
    else:
        print("TENHO PROBLEMAS ", response.status_code)
        


#Este método imprime uma lista de JSONs na tela.


def print_list(lista):
    print("====================================================")
    for i in lista:
        for chave, valor in i.items():
            print(chave, " - ", valor)
        print("====================================================")
    print()
    
    

#Este método efetua a reserva no servidor web de hospedagens, pacotes e passagens aéreas com apenas ida.

def reservar(cod, url):
    print("Reserva!")   
    numCartao = input("Numero do seu cartão de crédito")
    numVezes = input("Numero de parcelas")
    codSeg = input("código de segurança do cartão")
    cpf = input("CPF do proprietário do cartão")
    numReservas = input("Numero de reservas ")
    codigo = cod
    nomeCliente = input("Nome do pproprietário do cart�o")
    reserva = Reserva(numCartao, numVezes, codSeg, cpf, numReservas, codigo, nomeCliente)
    
    resposta = exec_request(url + "/reserva", reserva.to_JSON(), "post")

    if(resposta.status_code == 200):
        print("RESERVA EFETUADA COM SUCESSO")
        return True
    else:
        print("TENHO PROBLEMAS ", resposta.status_code)
        return False
    
    

#Este método efetua a reserva de passagens aéreas no WebService apenas quando é solicitado a ida e a volta.


def reservarIV(codI, codV, url):
    print("Reserva!")   
    numCartao = input("Numero do seu cartão de crédito")
    numVezes = input("Numero de parcelas")
    codSeg = input("código de segurança do cartão")
    cpf = input("CPF do propriet�rio do cartão")
    numReservas = input("Numero de reservas ")
    codigoI = codI
    codigoV = codV
    nomeCliente = input("Nome do pproprietário do cart�o")
    reservaI = Reserva(numCartao, numVezes, codSeg, cpf, numReservas, codigoI, nomeCliente)
    reservaV = Reserva(numCartao, numVezes, codSeg, cpf, numReservas, codigoV, nomeCliente)
    
    respostaI = exec_request(url + "/reserva", reservaI.to_JSON(), "post")
    respostaV = exec_request(url + "/reserva", reservaV.to_JSON(), "post")


    if(respostaI.status_code == 200 & respostaV.status_code == 200):
        print("RESERVA EFETUADA COM SUCESSO")
        return True
    else:
        print("TENHO PROBLEMAS IDA -", respostaI.status_code, " VOLTA -", respostaV.status_code)
        return False
    
    

#Este método faz a conecção com o WebService informado na url de acordo com o comando http. Enviando o JSON informado e recebendo uma resposta do 
#servidor. 


def exec_request(url, json, http_comand):
    if http_comand == "post":
        resposta = requests.post(url=url, json=json)
    if http_comand == "get":
        resposta = requests.get(url=url, json=json)
    if http_comand == "put":
        resposta = requests.put(url=url, json=json)
    if(resposta.status_code == 200):
        return resposta
    else:
        print("TENHO PROBLEMAS ", resposta.status_code)
