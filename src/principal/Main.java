package principal;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Main {
	private static final int N = 4;
	private static final int N_THREADS = 1;	
	private static final int ITERACOES = 1000;
	private static final double ERRO = 1; //Math.pow (1,-10);

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
	
	public void gerarMatriz(float[][] m) {
        m[0][0] = 10;
		m[0][1] = -1;
		m[0][2] =  2;
		m[0][3] =  0;
		
		m[1][0] = -1;
		m[1][1] = 11;
		m[1][2] = -1;
		m[1][3] =  3;
		
		m[2][0] =  2;
		m[2][1] = -1;
		m[2][2] = 10;
		m[2][3] = -1;
		
		m[3][0] =  0;
		m[3][1] =  3;
		m[3][2] = -1;
		m[3][3] =  8;
	}
	
	public void gerarVetor(float[] v) {
		v[0] =   6;
	    v[1] =  25;
	    v[2] = -11;
	    v[3] =  15;
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
	        	System.out.print(m[i][j] + "\t");
	        }
	        System.out.println("");
	    }
	    System.out.println("");
	}
	
	public void mostrarVetor(float[] v) {
	    int i;
	    
	    for(i=0; i<N; i++) {
        	System.out.print(v[i] + "\t");
	    }
	    System.out.println("\n");
	}
	
	public static void main(String[] args) {
		Main principal = new Main();
		MinhaThread[] t = new MinhaThread[N_THREADS];
		Instant start = Instant.now();
	      
		//Iniciar as variaveis
		principal.gerarMatriz(principal.matrizInicial);
		principal.gerarVetor(principal.vetorInicial);
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
		principal.mostrarVetor(principal.vetorCalculadoAnt);
		
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Duracao: "+ timeElapsed.toMillis() +" millisegundos");
	}
}
