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

    public ArrayList biblioteca;
    public Context context;

    public BookAdapter(ArrayList biblioteca, Context context) {
        this.biblioteca = biblioteca;
        this.context = context;
    }


    @Override
    public int getCount() {
        return biblioteca.size();
    }

    @Override
    public ArrayList getItem(int position) {
        return (ArrayList) biblioteca.get(position);
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
        Book book = (Book) biblioteca.get(position);
        TextView titulo = (TextView) view.findViewById(R.id.titleBook);
        titulo.setText(book.getTitulo());
        TextView autor = (TextView) view.findViewById(R.id.authorBook);
        autor.setText(book.getAutor());
        ImageView imagen = (ImageView) view.findViewById(R.id.imageBook);
        Picasso.get().load(BookData.IMAGE).into((ImageView) imagen);
        return view;
    }
}
