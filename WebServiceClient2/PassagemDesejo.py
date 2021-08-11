# -*- coding: utf-8 -*-

#Esta classe representa um desejo de passágens aéreas, onde referencia o local de origem e de destino, a datá de ida e de volta e o numero de passagens.


class PassagemDesejo():

    def __init__(self, estadoOrigem, cidadeOrigem, estadoDestino, cidadeDestino, dataViagem, numPassagens, idaVolta):
        self.estadoOrigem = estadoOrigem
        self.cidadeOrigem = cidadeOrigem
        self.estadoDestino = estadoDestino
        self.cidadeDestino = cidadeDestino
        self.dataViagem = dataViagem
        self.numPassagens = int(numPassagens)
        self.idaVolta = bool(idaVolta)
        
        

    #Este método retorna esta classe em formato JSON.

    
    def to_JSON(self):
        return {'estadoOrigem':self.estadoOrigem, 'cidadeOrigem':self.cidadeOrigem, 'estadoDestino':self.estadoDestino,
                 'cidadeDestino':self.cidadeDestino, 'dataViagem':self.dataViagem, 'numPassagens':self.numPassagens, 'idaVolta':self.idaVolta }
