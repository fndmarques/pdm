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

	public CountDownTimer wavetimer;

	public final long[] minutos = { 0 };
	public final long[] segundos = { 0 };
	public final long[] novo = { 0 };
	public int pontuacao = 0;

	public final int[] doces = new int[] { R.drawable.docinho_1,
			R.drawable.docinho_2, R.drawable.docinho_3, R.drawable.docinho_4,
			R.drawable.docinho_5, R.drawable.docinho_6 };
	public int[] matrizDoce = new int[25];

	public int flag = 25;
	public int cont = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent intent = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);

		for (int i = 0; i < matrizDoce.length; i++) {
			matrizDoce[i] = doces[(int) (Math.random() * 6)];
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
							verificaMatchV(0);

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
							verificaMatchV(0);
						}
					} else if (flag == 24) {
						if (flag == position + 1 || flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else if (flag == 4) {
						if (flag == position + 1 || flag == position - 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else if (flag == 1 || flag == 2 || flag == 3) {
						if (flag == position + 1 || flag == position - 5
								|| flag == position - 1) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else if (flag == 5 || flag == 10 || flag == 15) {
						if (flag == position - 1 || flag == position - 5
								|| flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else if (flag == 9 || flag == 14 || flag == 19) {
						if (flag == position + 1 || flag == position - 5
								|| flag == position + 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;
							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else if (flag == 21 || flag == 22 || flag == 23) {
						if (flag == position + 1 || flag == position + 5
								|| flag == position - 1) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					} else {
						if (flag == position + 1 || flag == position + 5
								|| flag == position - 1 || flag == position - 5) {
							temp = matrizDoce[position];
							matrizDoce[position] = matrizDoce[flag];
							matrizDoce[flag] = temp;

							atualizaGV(matrizDoce);
							verificaMatchH(0);
							verificaMatchV(0);
						}
					}

				} catch (Exception e) {

				}
				if (cont == 2) {
					cont = 0;
				}

			}
		});

		wavetimer = new myTimer(novo[0] + 120000, 1000).start();

	}

	public void verificaMatchH(int i) {

		if (i == 0 || i == 1 || i == 2 || i == 5 || i == 6 || i == 7 || i == 10
				|| i == 11 || i == 12 || i == 15 || i == 16 || i == 17
				|| i == 20 || i == 21 || i == 22) {
			if (matrizDoce[i] == matrizDoce[i + 1]
					&& matrizDoce[i + 1] == matrizDoce[i + 2]) {
				matrizDoce[i] = doces[(int) (Math.random() * 6)];
				matrizDoce[i + 1] = doces[(int) (Math.random() * 6)];
				matrizDoce[i + 2] = doces[(int) (Math.random() * 6)];



				pontuacao += 1;
				atualizaPontuacao();

				if ((matrizDoce[i + 2] == matrizDoce[i + 3])
						&& (i == 0 || i == 1 || i == 5 || i == 6 || i == 10
								|| i == 11 || i == 15 || i == 16 || i == 20 || i == 21)) {
					matrizDoce[i] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 1] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 2] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 3] = doces[(int) (Math.random() * 6)];



					pontuacao += 5;
					atualizaPontuacao();

					if ((matrizDoce[i + 3] == matrizDoce[i + 4])
							&& (i == 0 || i == 5 || i == 10 || i == 15 || i == 20)) {

						matrizDoce[i] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 1] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 2] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 3] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 4] = doces[(int) (Math.random() * 6)];



						pontuacao += 15;
						atualizaPontuacao();

					}
				}
			}
			atualizaPontuacao();

			wavetimer.cancel();

			wavetimer = new myTimer(novo[0], 1000).start();

			atualizaGV(matrizDoce);


		}

		if (i == 23) {
			return;
		}

		verificaMatchH(i + 1);
	}

	public void verificaMatchV(int i) {
		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6
				|| i == 7 || i == 8 || i == 9 || i == 10 || i == 11 || i == 12
				|| i == 13 || i == 14) {
			if (matrizDoce[i] == matrizDoce[i + 5]
					&& matrizDoce[i + 5] == matrizDoce[i + 10]) {
				matrizDoce[i] = doces[(int) (Math.random() * 6)];
				matrizDoce[i + 5] = doces[(int) (Math.random() * 6)];
				matrizDoce[i + 10] = doces[(int) (Math.random() * 6)];



				pontuacao += 1;
				atualizaPontuacao();

				if ((matrizDoce[i + 10] == matrizDoce[i + 15])
						&& (i == 0 || i == 1 || i == 2 || i == 3 || i == 4
								|| i == 5 || i == 6 || i == 7 || i == 8 || i == 9)) {
					matrizDoce[i] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 5] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 10] = doces[(int) (Math.random() * 6)];
					matrizDoce[i + 15] = doces[(int) (Math.random() * 6)];



					pontuacao += 5;
					atualizaPontuacao();

					if ((matrizDoce[i + 15] == matrizDoce[i + 20])
							&& (i == 0 || i == 1 || i == 2 || i == 3 || i == 4)) {

						matrizDoce[i] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 5] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 10] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 15] = doces[(int) (Math.random() * 6)];
						matrizDoce[i + 20] = doces[(int) (Math.random() * 6)];



						pontuacao += 15;
						atualizaPontuacao();

					}
				}
			}

			wavetimer.cancel();

			wavetimer = new myTimer(novo[0], 1000).start();

			atualizaGV(matrizDoce);


		}

		if (i == 15) {
			return;
		}

		verificaMatchV(i + 1);
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

	public void atualizaPontuacao() {
		TextView txtPonto = (TextView) findViewById(R.id.textView1);

		txtPonto.setText(Integer.toString(pontuacao));
	}

	public class myTimer extends CountDownTimer {

		private long millisActual;

		public myTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			millisActual = millisInFuture - 3000;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			novo[0] = millisUntilFinished;
			minutos[0] = millisUntilFinished / 60000;
			segundos[0] = ((millisUntilFinished % 60000) - (millisUntilFinished % 1000)) / 1000;
			atualizaTexto(minutos[0], segundos[0]);
		}

		@Override
		public void onFinish() {
			atualizaTexto(minutos[0], segundos[0]);
			// finish();
		}
	}

}
