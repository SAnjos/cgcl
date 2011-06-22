package analise.sintatica;

import analise.lexica.AnaliseLexica;
import analise.sintatica.producoes.RegrasProducaoModule;
import analise.sintatica.producoes.RegrasProducaoProgram;
import coretypes.IndiceNumerico;
import coretypes.Token;
import coretypes.TokenList;

public class AnaliseSintatica {
	
	private TokenList pilhaDeTokens;
	//private DicionarioDeRegrasProducao listaDeProducoes;
	private AnaliseLexica analiseLexica;
	

	public AnaliseSintatica(AnaliseLexica analiseLexica){
		this.pilhaDeTokens = new TokenList();
		this.limpaPilhaDeTokens();

		//this.listaDeProducoes = ProducoesListBuilder.producoesGCL();
		this.analiseLexica = analiseLexica;
	}
	
	private void limpaPilhaDeTokens(){
		this.pilhaDeTokens.clear();
	}
	
	private boolean empilhaToken(){
		
		try {
			Token token = this.analiseLexica.getNextToken();
			this.pilhaDeTokens.addLast(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	private boolean hasTokenParaProcessar(){
		return this.empilhaToken();
	}
	
	private boolean validaSintaxeEGeraASA() {
			
		IndiceNumerico i = new IndiceNumerico();
		

		//if ( RegrasProducaoProgram.getInstancia().isValida(pilhaDeTokens, i) ) return true;
		if ( RegrasProducaoModule.getInstancia().isValida(pilhaDeTokens, i) ) return true;				 
				
		return false;		
	}
	

	public Token desempilhaToken(){
		return this.pilhaDeTokens.removeLast();
	}

	public int empilhaToken(Token token){
		this.pilhaDeTokens.addLast(token);
		return this.pilhaDeTokens.size();
	}


	
	public boolean valida(){
		try{
			
			while ( hasTokenParaProcessar() ){
				
				if ( this.validaSintaxeEGeraASA() ){
					this.limpaPilhaDeTokens();
				}			
				
			}
			return true;	
		}catch(Exception e){
			return false;
		}
	  			
	}

}
