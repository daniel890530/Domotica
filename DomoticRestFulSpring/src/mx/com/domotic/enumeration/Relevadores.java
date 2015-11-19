package mx.com.domotic.enumeration;

public enum Relevadores {
	RELEVADOR1("r1",1),
	RELEVADOR2("r2",2),
	RELEVADOR3("r3",3),
	RELEVADOR4("r4",4),
	RELEVADOR5("r5",5),
	RELEVADOR6("r6",6),
	RELEVADOR7("r7",7),	
	RELEVADOR8("r8",8),
	RELEVADOR9("r9",9),
	RELEVADOR10("r10",10);
	
	private String nombreRelevador;
	private int numeroRelevador;
	
	Relevadores(String nombreRelevador, int numeroRelevador)
	{
		this.nombreRelevador	=	nombreRelevador;
		this.numeroRelevador	=	numeroRelevador;
	}

	public String getNombreRelevador() {
		return nombreRelevador;
	}

	public void setNombreRelevador(String nombreRelevador) {
		this.nombreRelevador = nombreRelevador;
	}

	public int getNumeroRelevador() {
		return numeroRelevador;
	}

	public void setNumeroRelevador(int numeroRelevador) {
		this.numeroRelevador = numeroRelevador;
	}
}
