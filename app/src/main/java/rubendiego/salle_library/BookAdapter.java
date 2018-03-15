package rubendiego.salle_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Diego on 10/03/2018.
 */

public class BookAdapter extends BaseAdapter{

    public Book[] biblioteca;
    public Context context;

    public BookAdapter(Book[] biblioteca, Context context) {
        this.biblioteca = biblioteca;
        this.context = context;
    }

    private static class ViewHolder {

        public TextView titulo;
        public TextView autor;
        public ImageView imagen;
    }

    @Override
    public int getCount() {
        return biblioteca.length;
    }

    @Override
    public Object getItem(int position) {
        return biblioteca[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_book, viewGroup, false);

            viewHolder = new ViewHolder();

            viewHolder.titulo = view.findViewById(R.id.titleBook);
            viewHolder.autor = view.findViewById(R.id.authorBook);
            viewHolder.imagen = view.findViewById(R.id.imageBook);

            Book book = (Book) biblioteca[position];

            viewHolder.titulo.setText(book.getTitulo());
            viewHolder.autor.setText(book.getAutor());
            view.setTag(viewHolder);
            Picasso.get().load(book.getImagen()).into(viewHolder.imagen);

        }
        return view;
    }
}
