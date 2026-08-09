package com.devempire.fushaconverter;

import android.app.Activity;
import android.os.Bundle;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private EditText input, output;
    private final Map<String,String> dict = new LinkedHashMap<>();

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        input=findViewById(R.id.input);
        output=findViewById(R.id.output);

        String[][] pairs = {
            {"شو","ماذا"},{"ليش","لماذا"},{"وين","أين"},{"ايمت","متى"},{"متى","متى"},
            {"كيفك","كيف حالك"},{"شلونك","كيف حالك"},{"شخبارك","كيف حالك"},
            {"شو عم تعمل","ماذا تفعل"},{"شو تسوي","ماذا تفعل"},{"شنو تسوي","ماذا تفعل"},
            {"عم","أقوم بـ"},{"مو","ليس"},{"مو عم","لا أقوم بـ"},{"ما عم","لا أقوم بـ"},
            {"ما في","لا يوجد"},{"مافي","لا يوجد"},{"في","يوجد"},{"هلق","الآن"},
            {"هسا","الآن"},{"هسه","الآن"},{"بكرا","غدًا"},{"امبارح","أمس"},
            {"هلأ","الآن"},{"هاد","هذا"},{"هاذ","هذا"},{"هاي","هذه"},{"هذي","هذه"},
            {"هداك","ذلك"},{"هديك","تلك"},{"كتير","كثيرًا"},{"وايد","كثيرًا"},
            {"مرة","جدًا"},{"بدي","أريد"},{"بديك","تريد"},{"بدك","تريد"},
            {"بقدر","أستطيع"},{"مقدر","لا أستطيع"},{"ما بقدر","لا أستطيع"},
            {"رح","سوف"},{"راح","سوف"},{"لسا","ما زال"},{"لسه","ما زال"},
            {"خلص","انتهى"},{"تمام","حسنًا"},{"اي","نعم"},{"إي","نعم"},
            {"لازم","يجب"},{"عشان","لكي"},{"لأنو","لأنه"},{"لانو","لأنه"},
            {"بس","لكن"},{"معي","لديّ"},{"عندي","لديّ"},{"منو","من هو"},
            {"مين","من"},{"شو اسمه","ما اسمه"},{"وينك","أين أنت"},
            {"تعال","تعال"},{"روح","اذهب"},{"اعطيني","أعطني"},{"عطيني","أعطني"}
        };
        for(String[] p:pairs) dict.put(p[0],p[1]);

        findViewById(R.id.convert).setOnClickListener(v -> output.setText(convert(input.getText().toString())));
        findViewById(R.id.copy).setOnClickListener(v -> {
            ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(ClipData.newPlainText("فصحى", output.getText().toString()));
            Toast.makeText(this,"تم نسخ النص",Toast.LENGTH_SHORT).show();
        });
    }

    private String convert(String s) {
        String r=s;
        for(Map.Entry<String,String> e:dict.entrySet())
            r=r.replaceAll("(?<!\\S)"+java.util.regex.Pattern.quote(e.getKey())+"(?!\\S)", e.getValue());
        r=r.replaceAll("\\s+"," ").trim();
        return r;
    }
}
