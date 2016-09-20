package com.example.rm40300.democomponentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rm40300.democomponentsandroid.R;
import com.example.rm40300.democomponentsandroid.model.Contato;

import java.util.List;

public class ContatosAdapter extends BaseAdapter {

    private Context context;
    private List<Contato> contatos;

    public ContatosAdapter(Context context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contato_item, parent, false);
        }

        TextView tvNome = (TextView) convertView.findViewById(R.id.tvNome);
        TextView tvTelefone = (TextView) convertView.findViewById(R.id.tvTelefone);

        tvNome.setText(contatos.get(position).getNome());
        tvTelefone.setText(contatos.get(position).getTelefone());

        return convertView;
    }
}
