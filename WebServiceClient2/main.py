# -*- coding: utf-8 -*-
import CtrlHospedagem
import CtrlPacote
import CtrlPassagem


class main():
    
#Main do programa, é responsável por apresentar o menu inicial e chamar os métodos de acordo com a opção escolhida pelo usuário.
    
    
    while True:
        print("O que deseja buscar?")
        print("1 - HOSPEDAGENS  ")
        print("2 - PASSAGENS  ")
        print("3 - PACOTES  ")
        cod = input("Entre com a opção")
        
        if cod == "1":
            CtrlHospedagem.opcao_hospedagem()
        if cod == "2":
            CtrlPassagem.opcao_passagem()
        if cod == "3":
            CtrlPacote.opcao_pacote()   
            