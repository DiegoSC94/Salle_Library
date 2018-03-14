package rubendiego.salle_library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Diego on 10/03/2018.
 */

public class Book implements Parcelable {

    public String titulo ;
    public String description;
    public String autor;
    public String imagen;

    public Book(String titulo, String description, String autor, String imagen) {

        this.titulo = titulo;
        this.description = description;
        this.autor = autor;
        this.imagen = imagen;
    }

    protected Book(Parcel in) {
        titulo = in.readString();
        description = in.readString();
        autor = in.readString();
        imagen = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(autor);
        parcel.writeString(description);
        parcel.writeString(imagen);
    }
}
