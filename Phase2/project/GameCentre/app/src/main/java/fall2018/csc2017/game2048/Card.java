package fall2018.csc2017.game2048;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

//卡片类（小方格）
public class Card extends FrameLayout {

    private int num = 0;//卡片上的数
    private TextView lable;

    public Card(Context context) {
        super(context);
        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(lable, lp);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    //设置卡片上的数
    public void setNum(int num) {

        //设置数的大小
        this.num = num;
        if (num > 0) {
            lable.setText(num + "");
        } else {
            lable.setText("");
        }

        //根据数值大小改变卡片的背景颜色
        switch (num) {
            case 0:
                lable.setBackgroundColor(0xffccc0b2);
                break;

            case 2:
                lable.setBackgroundColor(0xffffe4e1);
                lable.setTextColor(0xffcd853f);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;

            case 4:
                lable.setBackgroundColor(0xffff8c00);
                lable.setTextColor(0xfffffaf0);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;

            case 8:
                lable.setBackgroundColor(0xfffa8072);
                lable.setTextColor(0xffffe4b5);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;

            case 16:
                lable.setBackgroundColor(0xff66cdaa);
                lable.setTextColor(0xffe0ffff);
                lable.setTextSize(50);
                break;

            case 32:
                lable.setBackgroundColor(0xffff6347);
                lable.setTextColor(0xffffe4c4);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;

            case 64:
                lable.setBackgroundColor(0xffcd5c5c);
                lable.setTextColor(0xffff8c00);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 128:
                lable.setBackgroundColor(0xff8b4513);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 256:
                lable.setBackgroundColor(0xff1e90ff);
                lable.setTextColor(0xfffa8072);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 512:
                lable.setBackgroundColor(0xff191970);
                lable.setTextColor(0xff008b8b);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 1024:
                lable.setBackgroundColor(0xffffff00);
                lable.setTextColor(0xff99cc00);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 2048:
                lable.setBackgroundColor(0xffcd5c5c);
                lable.setTextColor(0xff8b4513);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 4096:
                lable.setBackgroundColor(0xffff9933);
                lable.setTextColor(0xff663300);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            case 8192:
                lable.setBackgroundColor(0xff99ccff);
                lable.setTextColor(0xff003366);
                lable.setTextSize(50);
                lable.setTypeface(Typeface.DEFAULT_BOLD);
                break;
            default:
                lable.setBackgroundColor(0xffedc22d);
                break;
        }

    }

    //判断两个卡片上的数值是否相等
    public boolean equals(Card card) {
        return getNum() == card.getNum();
    }

}
