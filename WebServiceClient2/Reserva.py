# -*- coding: utf-8 -*-

#Esta classe representa uma reserva de hospedagem, passagem aérea ou um pacote. Contem os dados de uma reserva, numero do cartão de crédito, numero de
#parcelas, código de seguraça do cartão, CPF do usuário, numero de reserva que este usuário deseja e o Nome do cliente.


class Reserva():
    def __init__(self, numCartao, numVezes, codSeg, cpf, numReservas, codigo, nomeCliente):
        self.numCartao = numCartao
        self.numVezes = int(numVezes)
        self.codSeg = int(codSeg)
        self.cpf = int(cpf)
        self.numReservas = int(numReservas)
        self.codigo = int(codigo)
        self.nomeCliente = nomeCliente
        
        
    
    #Este método retorna esta classe em formato JSON.
        
    def to_JSON(self):
        return {"numCartao":self.numCartao, "numVezes":self.numVezes, "codSeg":self.codSeg, "cpf":self.cpf, "numReservas":self.numReservas,
                "codigo":self.codigo, "nomeCliente":self.nomeCliente}
