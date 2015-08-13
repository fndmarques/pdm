package br.edu.ufabc.android.jogo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity {

	public final long[] minutos = { 0 };
	public final long[] segundos = { 0 };
	public final long[] novo = { 1000 };

	public final int[] doces = new int[] { R.drawable.docinho_1,
			R.drawable.docinho_2, R.drawable.docinho_3, R.drawable.docinho_4,
			R.drawable.docinho_5 };
	public int[] matrizDoce = new int[25];

	public int flag = 25;
	public int cont = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		for (int i = 0; i < matrizDoce.length; i++) {
			matrizDoce[i] = doces[(int) (Math.random() * 5)];
		}

		atualizaGV(matrizDoce);

		GridView gv = (GridView) findViewById(R.id.gridView1);

		gv.setOnItemClickListener(new GridView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int temp = 0;
				if (cont <= 0) {
					flag = position;
				}
				cont++;
				try {
					if (flag == 0) {
						if (flag == position - 1 || flag == position - 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);

							Toast.makeText(getBaseContext(), "Posicao 0",
									Toast.LENGTH_SHORT).show();
						}
					} else if (flag == 20) {
						if (flag == position - 1 || flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 24) {
						if (flag == position + 1 || flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 4) {
						if (flag == position + 1 || flag == position - 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 1 || flag == 2 || flag == 3) {
						if (flag == position + 1 || flag == position - 5
								|| flag == position - 1) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 5 || flag == 10 || flag == 15) {
						if (flag == position - 1 || flag == position - 5
								|| flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 9 || flag == 14 || flag == 19) {
						if (flag == position + 1 || flag == position - 5
								|| flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;
							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else if (flag == 21 || flag == 22 || flag == 23) {
						if (flag == position + 1 || flag == position + 5
								|| flag == position - 1) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					} else {
						if (flag == position + 1 || flag == position + 5
								|| flag == position - 1 || flag == position - 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
						}
					}

				} catch (Exception e) {

				}
				if (cont == 2) {
					cont = 0;
				}

			}
		});

		CountDownTimer wavetimer = new myTimer(novo[0] + 3000, 1000).start();
	}

	public void verificaMatchH(int i) {
		if (matrizDoce[i] == matrizDoce[i + 1]
				&& matrizDoce[i + 1] == matrizDoce[i + 2]) {
			matrizDoce[i] = doces[(int) (Math.random() * 5)];
			matrizDoce[i + 1] = doces[(int) (Math.random() * 5)];
			matrizDoce[i + 2] = doces[(int) (Math.random() * 5)];

			atualizaGV(matrizDoce);
		}

		if (i == 25) {
			return;
		}

		verificaMatchH(i + 1);
	}

	public void atualizaGV(int[] matrizDoce) {
		GridView gv = (GridView) findViewById(R.id.gridView1);
		gv.setAdapter(new Adaptador(this, matrizDoce));
	}

	public void atualizaTexto(long min, long seg) {
		TextView txtTempo = (TextView) findViewById(R.id.tempo);
		String txtMin, txtSeg;
		if (min < 10) {
			txtMin = "0" + Long.toString(min);
		} else {
			txtMin = Long.toString(min);
		}

		if (seg < 10) {
			txtSeg = "0" + Long.toString(seg);
		} else {
			txtSeg = Long.toString(seg);
		}

		txtTempo.setText(txtMin + ":" + txtSeg);
	}

	public class myTimer extends CountDownTimer {

		private long millisActual;

		public myTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			millisActual = millisInFuture - 3000;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			Log.d("onTick", "Novo = " + novo[0]);
			novo[0] = millisUntilFinished;
			minutos[0] = millisUntilFinished / 60000;
			segundos[0] = ((millisUntilFinished % 60000) - (millisUntilFinished % 1000)) / 1000;
			atualizaTexto(minutos[0], segundos[0]);
			Log.d("onTick", "Entrou");
		}

		@Override
		public void onFinish() {
			atualizaTexto(minutos[0], segundos[0]);
			// finish();
		}
	}

}
