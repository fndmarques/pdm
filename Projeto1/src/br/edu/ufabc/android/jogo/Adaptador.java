package br.edu.ufabc.android.jogo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class Adaptador extends BaseAdapter {
	
	private Context ctx;
	private int[] doces;
	
	public Adaptador(Context ctx, int[]doces){
		this.ctx = ctx;
		this.doces = doces;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return doces.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return doces[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ImageView iv = new ImageView(ctx);
		iv.setImageResource(doces[position]);
		iv.setAdjustViewBounds(true);
		return iv;
	}
}
