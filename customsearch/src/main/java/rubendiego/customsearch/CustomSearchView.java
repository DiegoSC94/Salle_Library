package rubendiego.customsearch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * TODO: document your custom view class.
 */
public class CustomSearchView extends LinearLayout implements View.OnClickListener{
    private EditText editText;
    private ImageButton buscar, cancel;


    public CustomSearchView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_custom_search_view, this);

        editText = (EditText) findViewById(R.id.campoBusqueda);
        buscar = (ImageButton) findViewById(R.id.buscar);
        cancel = (ImageButton) findViewById(R.id.cancel);


        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomSearchView, defStyle, 0);

        CharSequence s = a.getString(R.styleable.CustomSearchView_hintText);
        if (s != null) {
            setHintText(s.toString());
        }

        int textSize = a.getDimensionPixelOffset(R.styleable.CustomSearchView_textSize, 16);
        if (textSize > 0){
            setTextSize(textSize);
        }

        a.recycle();

        buscar.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    public void setHintText (String text){
        editText.setHint(text);
        invalidate();
        requestLayout();
    }

    public void setTextSize(int size){
        editText.setTextSize(size);
        invalidate();
        requestLayout();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel){
            editText.setText("");
        }
        else if (v.getId() == R.id.buscar){

        }
    }
}