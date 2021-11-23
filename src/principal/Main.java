package principal;
import java.util.Random;

public class Main {
	private static final int N = 5;
	private static final int N_THREADS = 4;
	private static final int N_MAX = 10;	
	private static final int ITERACOES = 1000;
	private static final double ERRO = Math.pow (1,-10);

	private float [][]matrizInicial;
	private float []vetorInicial;
	private float []vetorCalculado;
	private float []vetorCalculadoAnt;
	private int contador;
	
	public Main() {
		matrizInicial = new float[N][N];
		vetorInicial = new float[N];
		vetorCalculado = new float[N];
		vetorCalculadoAnt = new float[N];
		contador = 0;
	}
	
	public void gerarMatrizAleatoria(float[][] m) {
	    int n;
	    int i, j;
	    Random gerador = new Random();
	    
	    for(i=0; i<N; i++) {
	        for(j=0; j<N; j++) {
	            n = gerador.nextInt(N_MAX) + 1 ;
	            m[i][j] = n;
	        }
	    }
	}
	
	public void gerarVetorAleatorio(float[] v) {
	    int n;
	    int i;
	    Random gerador = new Random();
	    
	    for(i=0; i<N; i++) {
	    	n = gerador.nextInt(N_MAX) + 1 ;
            v[i] = n;
	    }
	}
	
	public void gerarVetorZerado(float[] v) {
	    int i;
	    
	    for(i=0; i<N; i++) {
            v[i] = 0;
	    }
	}
	
	public void mostrarMatriz(float[][] m) {
	    int i, j;
	    
	    for(i=0; i<N; i++) {
	        for(j=0; j<N; j++) {
	        	System.out.print(m[i][j] + " ");
	        }
	        System.out.println("");
	    }
	    System.out.println("");
	}
	
	public void mostrarVetor(float[] v) {
	    int i;
	    
	    for(i=0; i<N; i++) {
        	System.out.print(v[i] + " ");
	    }
	    System.out.println("\n");
	}
	
	public static void main(String[] args) {
		Main principal = new Main();
		MinhaThread[] t = new MinhaThread[N_THREADS];
		
		//Iniciar as variaveis
		principal.gerarMatrizAleatoria(principal.matrizInicial);
		principal.gerarVetorAleatorio(principal.vetorInicial);
		principal.gerarVetorZerado(principal.vetorCalculado);
		principal.gerarVetorZerado(principal.vetorCalculadoAnt);
		
		System.out.println("Matriz:");
		principal.mostrarMatriz(principal.matrizInicial);
		
		System.out.println("Vetor inicial:");
		principal.mostrarVetor(principal.vetorInicial);
		
		for (int i=0; i<N_THREADS; i++) {
			t[i] = new MinhaThread(i, N, N_THREADS, principal.matrizInicial, principal.vetorInicial, principal.vetorCalculado, principal.vetorCalculadoAnt, ITERACOES, ERRO, principal.contador);
			t[i].start();
		}
		
		try {
			for (int i=0; i<N_THREADS; i++) {
				t[i].join();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}	
		
		System.out.println("Vetor calculado:");
		principal.mostrarVetor(principal.vetorCalculado);
	}
}
