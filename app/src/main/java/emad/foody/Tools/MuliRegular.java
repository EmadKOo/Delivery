package emad.foody.Tools;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MuliRegular extends android.support.v7.widget.AppCompatTextView {
    public MuliRegular(Context context) {
        super(context);
    }

    public MuliRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Typeface.createFromAsset(context.getAssets(), "Muli-Regular.ttf"));


    }
}
