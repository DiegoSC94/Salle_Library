package rubendiego.salle_library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Diego on 10/03/2018.
 */

public class Book implements Parcelable {

    public String id;
    public String titulo ;
    public String description;
    public String autor;
    public int imagen;

    public Book(String id, String titulo, String description, String autor, int imagen) {
        this.id = id;
        this.titulo = titulo;
        this.description = description;
        this.autor = autor;
        this.imagen = imagen;
    }

    protected Book(Parcel in) {
        id = in.readString();
        titulo = in.readString();
        description = in.readString();
        autor = in.readString();
        imagen = in.readInt();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
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
        parcel.writeInt(imagen);
    }
}
