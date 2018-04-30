package rubendiego.salle_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookFavAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> libros;

    public BookFavAdapter(Context context, ArrayList<Book> libros) {
        this.context = context;
        this.libros = libros;
    }

    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Object getItem(int position) {
        return libros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BookFavAdapter.ViewHolder viewHolder;

        try {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_book, parent, false);

                viewHolder = new BookFavAdapter.ViewHolder();

                viewHolder.titulo = convertView.findViewById(R.id.titleBook);
                viewHolder.autor = convertView.findViewById(R.id.authorBook);
                viewHolder.imagen = convertView.findViewById(R.id.imageBook);

                Book book = libros.get(position);

                viewHolder.titulo.setText(book.getTitulo());
                viewHolder.autor.setText(book.getAutor());
                convertView.setTag(viewHolder);
                Picasso.get().load(book.getImagen()).into(viewHolder.imagen);

            }
        } catch (Exception e) {

        }

/*

        if (convertView == null) {

            /*
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.activity_favorite_book, null);
            *//*
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_book, parent, false);
        }

        TextView titulo = convertView.findViewById(R.id.titleBook);
        TextView autor = convertView.findViewById(R.id.authorBook);
        //ImageView imagen = convertView.findViewById(R.id.imageBook);

        titulo.setText(libros.get(position).getTitulo());
        autor.setText(libros.get(position).getAutor());
        //Picasso.get().load(libros.get(position).getImagen()).into(imagen);

        */
        return convertView;
    }

    private static class ViewHolder {

        public TextView titulo;
        public TextView autor;
        public ImageView imagen;
    }
}
