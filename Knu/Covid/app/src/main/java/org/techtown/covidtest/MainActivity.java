package org.techtown.covidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tvH);

    }

    public void btnClick(View v) {
        switch( v.getId() ){

            case R.id.button:
                // 쓰레드를 생성하여 돌리는 구간
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        data= getData(); // 하단의 getData 메소드를 통해 데이터를 파싱
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }

    String getData(){
        StringBuffer buffer = new StringBuffer();
        String queryUrl="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=FWh87%2FqaLEma7tme7KUMsUs6zp6rbczh1uHDI88B80cXFV29f1uSbPx5tCvgP3eH8jf1vxJ1i0vWZbPXUpGelQ%3D%3D&pageNo=1&numOfRows=10&startCreateDt=20200310&endCreateDt=20200315";

        try {
            URL url= new URL(queryUrl); // 문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); // url 위치로 인풋스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();

            // inputstream 으로부터 xml 입력받기
            xpp.setInput( new InputStreamReader(is, "UTF-8") );
            String tag;
            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작 단계 \n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName(); // 태그 이름 얻어오기

                        if(tag.equals("item")) ;
                        else if(tag.equals("deathCnt")){
                            buffer.append("테스트1 : ");
                            xpp.next();
                            // addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(xpp.getText());
                            buffer.append("\n"); // 줄바꿈 문자 추가
                        }
                        else if(tag.equals("decideCnt")){
                            buffer.append("테스트2 : ");
                            xpp.next();
                            // addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(xpp.getText());
                            buffer.append("\n"); // 줄바꿈 문자 추가
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); // 태그 이름 얻어오기
                        if(tag.equals("item")) buffer.append("\n"); // 첫번째 검색결과종료 후 줄바꿈
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append("파싱 종료 단계 \n");
        return buffer.toString(); // 파싱 다 종료 후 StringBuffer 문자열 객체 반환
    }

}