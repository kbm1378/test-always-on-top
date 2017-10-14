package com.example.sunpy.alwaysontop;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


/**
 * Created by sunpy on 2017-10-14.
 */

public class TopService extends Service{
    private View m_View;
    private WindowManager.LayoutParams m_Params;
    private WindowManager m_WindowManager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test", "서비스 onCreate");

        //top_view.xml 레이아웃을 생성하여 뷰 출력
        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_View = mInflater.inflate(R.layout.top_view, null);
        m_View.setOnTouchListener(onTouchListener);

        m_Params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        //WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY
        //이건 항상 최상위에 있도록 해주는 타입입니다
        //반대로 아래에서 사용하게될 WindowManager.LayoutParams.TYPE_PHONE은 터치 이벤트도 받을수 있습니다

        //WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
        //이건 뷰를 제외한 나머지 부분의 터치를 가능하게 해준다...라고 알고있습니다


        m_WindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        m_WindowManager.addView(m_View, m_Params);




    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (m_View != null) {
            // 생성 된 레이아웃 제거.
            m_WindowManager.removeView(m_View);
            m_WindowManager = null;
        }
    }


    // 터치 이벤트.
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;

                case MotionEvent.ACTION_MOVE:
                    break;

                case MotionEvent.ACTION_UP:
                    break;
            }

            return false;
        }
    };

    // 터치 동작 확인을 위해 버튼 터치 시 메인 액티비티 호출.
    public void onBtnTest(View v) {
        Log.d("test", "클릭 탑뷰 버튼");
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
