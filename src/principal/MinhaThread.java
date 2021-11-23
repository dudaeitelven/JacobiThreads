package principal;

public class MinhaThread extends Thread {
	private int ini;
	private int N;
	private int nThreads;
	private int iteracoes;
	private int contador;
	private double erro;
	private float [][]matrizInicial;
	private float []vetorInicial;
	private float []vetorCalculado;
	private float []vetorCalculadoAnt;
	
	public MinhaThread(int ini, int N, int nThreads, float[][] matrizInicial, float[] vetorInicial, float[]vetorCalculado, float[]vetorCalculadoAnt, int iteracoes, double erro, int contador) {
		this.ini = ini;
		this.N = N;
		this.nThreads = nThreads;
		this.iteracoes = iteracoes;
		this.erro = erro;
		this.matrizInicial=matrizInicial;
		this.vetorInicial=vetorInicial;
		this.vetorCalculado = vetorCalculado;
		this.vetorCalculadoAnt = vetorCalculadoAnt;
		this.contador = contador;
	}
	
	@Override
	public void run() {
	    int i, j;
	    float soma = 0;
	    float dp = 0;
	    
	    contador = 0;
	    
	    while (contador < iteracoes) {
		    for(i = ini; i < N; i+=nThreads) {
	    		soma = 0;
	    		for(j=0; j<N; j++) {
	    			if (i != j) {
	    				soma += matrizInicial[i][j] * vetorCalculado[i];	        		
	    			}
	    			else {
	    				dp =  matrizInicial[i][j];
	    			}
	    		}
	    		vetorCalculado[i] = (vetorInicial[i] - soma) / dp;
	    	}
	    	
	    	
			if ((calcularNorma(vetorCalculadoAnt) - calcularNorma(vetorCalculado)) < erro) {
				contador = iteracoes;
			}
			else {
				vetorCalculadoAnt = vetorCalculado;
			}
				
	    	contador++;
	    }
	}
	
	public float calcularNorma(float[] v) {
	    int i;
	    int soma = 0;
	    
	    for(i=0; i<N; i++) {
	    	soma += v[i]*v[i];
	    }
	    
	    Math.sqrt(soma);
	    
	    return soma;
	}	
}