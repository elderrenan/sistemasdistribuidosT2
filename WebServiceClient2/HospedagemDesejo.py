# -*- coding: utf-8 -*-    

#Esta classe representa um desejo de hospedagem, onde referencia o local da hospedagem o período e a quantidade de quartos


class HospedagemDesejo(object):
    def __init__(self, estado, cidade, periodoInicio, periodoFim, numQuartoDesejo):
        self.estado = estado
        self.cidade = cidade
        self.periodoInicio = periodoInicio
        self.periodoFim = periodoFim
        self.numQuartoDesejo = numQuartoDesejo
        
        
 
    #Este método retorna esta classe em formato JSON.

    
    def to_JSON(self):
        return {"estado":self.estado, "cidade":self.cidade, "periodoInicio":self.periodoInicio, "periodoFim":self.periodoFim, "numQuartoDesejo":self.numQuartoDesejo}
