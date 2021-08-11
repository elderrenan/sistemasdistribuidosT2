# -*- coding: utf-8 -*-

#Esta classe representa um desejo de pacote, onde referencia o local (destino e origem) do pacote o período e a quantidade de vagas no pacote.


class PacoteDesejo:
    def __init__(self, dataIda, dataRetorno, quantPessoas, cidadeOrigem, cidadeDestino, estadoOrigem, estadoDestino):
        self.dataIda = dataIda
        self.dataRetorno = dataRetorno
        self.quantPessoas = quantPessoas
        self.cidadeOrigem = cidadeOrigem
        self.cidadeDestino = cidadeDestino
        self.estadoOrigem = estadoOrigem
        self.estadoDestino = estadoDestino
        

    #Este método retorna esta classe em formato JSON.
 
    
    
    def to_JSON(self):
        return {"dataIda":self.dataIda, "dataRetorno":self.dataRetorno, "quantPessoas":self.quantPessoas, "cidadeOrigem":self.cidadeOrigem, "cidadeDestino":self.cidadeDestino, "estadoOrigem":self.estadoOrigem, "estadoDestino":self.estadoDestino}