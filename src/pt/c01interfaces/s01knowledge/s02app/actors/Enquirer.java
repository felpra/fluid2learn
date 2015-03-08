package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
		
        IBaseConhecimento bc = new BaseConhecimento();
        String[] bichos = bc.listaNomes(); // puxa base de dados de animais
        String[] jafeita = new String[42]; // perguntas ja feitas
        String[] resp = new String[42];   // respostas ja dadas

        int i=0, j=0, k=0;
        boolean ja=false;
        obj = bc.recuperaObjeto(bichos[i]);
        IDeclaracao decl = obj.primeira();
 
        while (decl != null){
			ja=false; // supoe se inicialmente que a primeira pergunta n foi feita
		
       
			
			String pergunta = decl.getPropriedade();
			String respostaEsperada = decl.getValor();
			j=0; // inicializa contador das ja feitas para cada animal
			if(k!=0) //se for a 1 iteracao, nao precisa verificar se ja ha perguntas feitas
while(jafeita[j]!=null)          { //verifica se ja ha perguntas ate limite do vetor
                
					if (pergunta.equalsIgnoreCase(jafeita[j]))  {
						ja = true; //se ja foi feita ja = true
			break;
					                                            }
					else{	
						j++;// senao incrementa j para gravar a pergunta na posicao					
					    }
				
				                  }
			
				
                if(!ja){ //se nao foi feita aciona o responder para obter resposta
				String resposta = responder.ask(pergunta);
				resp[j] = resposta; // grava resposta
				jafeita[j] = pergunta; // grava pergunta nas já feitas
				if (resposta.equalsIgnoreCase(respostaEsperada))
					decl = obj.proxima(); //se for a esperada passa para proxima
				else{ // se nao for a esperada vai para o proximo animal
					i++;
					obj = bc.recuperaObjeto(bichos[i]);
							decl = obj.primeira();
				}
                }
                else{ // se ja feita, compara o resultado ja existente na variavel "jafeita"
                
                	if (resp[j].equalsIgnoreCase(respostaEsperada))
    					decl = obj.proxima();
    				else{
    					i++;
    					obj = bc.recuperaObjeto(bichos[i]);
    							decl = obj.primeira();
    							
    				}
                }
			
                
			
            
                k++;
        }
boolean acertei = responder.finalAnswer(bichos[i]);
		
		if (acertei)
			System.out.println("Oba! Acertei!");
		else
			System.out.println("fuem! fuem! fuem!");
	    	
	    }
}
