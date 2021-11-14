package org.techtown.finalpro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class air extends AppCompatActivity {

    TextView airView;
    EditText airLon;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.air);
        setTitle("금일 대기 중금속 확인");
        airView = (TextView)findViewById(R.id.airView);
        airLon = (EditText)findViewById(R.id.editLon);
    }

    // 버튼을 클릭 했을 경우
    @SuppressLint("NonConstantResourceId")
    public void btnClick(View v){
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String lon = airLon.getText().toString();
                        if(data.trim().equals("")){
                            showToast("해당지역의 데이터가 없거나\n지역을 입력하지 않았습니다.");
                        } else {
                            String time = time();
//                            String location = latLon(lon);
                            data = getXmlData(time);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                airView.setText(data);
                                showToast("해당 지역의 금일 대기 중금속 정보를 갖고왔습니다.");
                            }
                        });
                    }
                }).start();
                break;
        }
    }

//    //대기 환경 코드
//    public String latLon (String city) {
//        String gps = null;
//        if(city.equals("수도") || city.equals("수도권")) {
//            gps = "1";
//        } else if (city.equals("백령") || city.equals("백령도")) {
//            gps = "2";
//        } else if (city.equals("호남") || city.equals("호남권")) {
//            gps = "3";
//        } else if (city.equals("중부") || city.equals("중부권")) {
//            gps = "4";
//        } else if (city.equals("제주") || city.equals("제주권")) {
//            gps = "5";
//        } else if (city.equals("영남") || city.equals("영남권")) {
//            gps = "6";
//        } else if (city.equals("경기") || city.equals("경기권")) {
//            gps = "7";
//        } else if (city.equals("충청") || city.equals("충청권")) {
//            gps = "8";
//        } else if (city.equals("전북") || city.equals("전북권")) {
//            gps = "9";
//        } else if (city.equals("강원") || city.equals("강원권")) {
//            gps = "10";
//        } else { //지역이 없을경우
//            gps = "";
//        }
//        return gps;
//    }

    //금일 or 전일 연, 월, 일, 시간 정보 불러오기
    public String time() {
        //현재 시간 불러오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        //년-월-일
        SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyyMMdd");

        String getDay;
        getDay = simpleDateFormatDay.format(date);
        return getDay;
    }

    //코로나 확진자 데이터를 api에서 파싱해서 가져옴
    String getXmlData(String getDay){ //연월일 데이터 가져옴
        StringBuffer buffer=new StringBuffer();
        String query="FWh87%2FqaLEma7tme7KUMsUs6zp6rbczh1uHDI88B80cXFV29f1uSbPx5tCvgP3eH8jf1vxJ1i0vWZbPXUpGelQ%3D%3D";
        String queryUrl="http://apis.data.go.kr/1480523/MetalMeasuringResultService/MetalService?numOfRows=1&pageNo=1&resultType=xml&stationcode=1&date=2021114&timecode=RH02&itemcode=90303&serviceKey=" + query;

        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기
                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("value")){ //위치
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("incDec")){
                            buffer.append(" 확진자 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(" 명");
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....

    //토스트 메세지
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

