package TADAtendimento;

import model.Atendimento;

public class NodoAtendimentoFila {
    protected Atendimento dado;
    protected NodoAtendimentoFila apos;  
    
    public NodoAtendimentoFila(Atendimento dado){
        this.dado = dado;
        this.apos = null;
    }
    
    //getters e setters 
	public Atendimento getDado() {
		return dado;
	}

	public void setDado(Atendimento dado) {
		this.dado = dado;
	}

	public NodoAtendimentoFila getApos() {
		return apos;
	}

	public void setApos(NodoAtendimentoFila apos) {
		this.apos = apos;
	} 
}
