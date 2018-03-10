package rubendiego.salle_library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.adapter_book, viewGroup, false);
        }
        Book book = biblioteca[position];
        TextView titulo = (TextView) view.findViewById(R.id.titleBook);
        titulo.setText(book.getTitulo());
        TextView autor = (TextView) view.findViewById(R.id.authorBook);
        autor.setText(book.getAutor());
        ImageView imagen = (ImageView) view.findViewById(R.id.imageBook);
        imagen.set(book.getImagen());
        return view;
    }
}
