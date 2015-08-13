package br.edu.ufabc.android.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void clickBtnGo(View view){
		new Solicitacao().execute("http://ip.jsontest.com/");		
	}
	
	public void teste(){
		TextView txtStatus = (TextView)findViewById(R.id.txtStatus);
		txtStatus.setText("Ok!!");
	}
	
	// AsyncTask recebe 3 tipos de parâmetros em sua declaracao
	// p1 - Utilizado para ser passado ao método doInBackground - efetiva execucao da tarefa
	// p2 - Utilizado para monitorar o progresso da atividade (on ProgressUpdate)
	// p3 - Utilizado para concluir a tarefa (é o retorno do doInBackground e parâmetro para
	//      o método onPostExecute
	private class Solicitacao extends AsyncTask<String, Void, String>{
		
		
		
		protected void onPreExecute(){
			TextView txtProgress = (TextView)findViewById(R.id.txtProgress);
			txtProgress.setText("Pre");	
		}
		// este parâmetro é do tipo exatamente igual ao 3o tipo de dado da declaracao
		protected void onPostExecute(String param){
			TextView txtProgress = (TextView)findViewById(R.id.txtProgress);
			TextView txtResult   = (TextView)findViewById(R.id.txtResultado);
			TextView txtStatus   = (TextView)findViewById(R.id.txtStatus);
			
			// atualizando informacoes
			txtProgress.setText(txtProgress.getText() + " - Post");
			txtStatus.setText("Concluido...");
			
			// coloco na textView o resultado obtido no doInBackground
			txtResult.setText(param);
			
			MainActivity.this.teste();
		}
		// o retorno desse tipo de dado é o mesmo do parâmetro
		// do onPostExceute
		// e ainda recebe uma lista de parâmetros do primeiro tipo
		protected String doInBackground(String... param1){
			String conteudo = "";
			String linha;
			
			try{
				// crio minha URL com o primeiro parametro passado para o método
				URL url = new URL(param1[0]);
				
				// crio a conexao http a partir da url
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				
				// realizo a conexao
				connection.connect();
				
				int response = connection.getResponseCode();
				if (response == HttpURLConnection.HTTP_OK){
					// crio um leitor a partir da conexao obtida
					InputStreamReader isr = new InputStreamReader(connection.getInputStream());
					// crio um objeto para ler linha a linha meu resultado
					BufferedReader br = new BufferedReader(isr);
					// faço a leitura da pagina
					while ((linha = br.readLine()) != null){
						conteudo += linha;
					}
					br.close();
					isr.close();
				}
			}
			catch(Exception ex){
				Log.d("HTTPCLIENT","Erro ao conectar - "+ex.getMessage());
			}
			return conteudo;
		}
		
	}

}
