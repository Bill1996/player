package vvfgaa.player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity {
    private boolean showDanmaku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new PlayAdapter(getPlaysource()));

        StandardGSYVideoPlayer standardGSYVideoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        //暂定，不明确
        VideoView videoView = (VideoView) findViewById(R.id.detail_player);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.danmu;
        videoView.setVideoPath(uri);
        videoView.start();
        danmakuView = (DanmakuView)findViewById(R.id.danmaku_view);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = danmakuContext.create();
        danmakuView.prepare(parser,danmakuContext);

        final LinearLayout operationLayout = (LinearLayout)findViewById(R.id.operation_layout);
        Button send = (Button) findViewById(R.id.send);
        final EditText editText = (EditText)findViewById(R.id.edit_text);
        danmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operationLayout.getVisibility() == View.GONE){
                    operationLayout.setVisibility(View.VISIBLE);
                }else{
                    operationLayout.setVisibility(View.GONE);
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if(!TextUtils.isEmpty(content)){
                    danmakuView.addDanmaku(content , true , Color.GREEN);/////
                    editText.setText("");
                }
            }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener(){

            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if(visibility == View.SYSTEM_UI_FLAG_VISIBLE){
                    onWindowFocusChanged(true);
                }
            }
        });
    }
    private void addDanmaku(String content,boolean withBorder){
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if(withBorder){
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }
    private void generateSomeDanmaku(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku){
                    int time = new Random().nextInt(300);
                    String content = "" + time +time;
                    addDanmaku(content,false);
                    try{
                        Thread.sleep(time);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private int  sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale +0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()){
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if(danmakuView != null){
            danmakuView.release();
            danmakuView = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(danmakuView != null && danmakuView.isPrepared()){
            danmakuView.pause();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && Build.VERSION.SDK_INT >= 19){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }


    private List<Playsource> getPlaysource(){
        List<Playsource> playsources=new ArrayList<>();
        playsources.add(new Playsource("CCTV-1","http://223.110.241.139:6610/gitv/live1/G_CCTV-1-HQ/G_CCTV-1-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-2","http://223.110.241.139:6610/gitv/live1/G_CCTV-2-HD/G_CCTV-2-HD.m3u8"));
        playsources.add(new Playsource("CCTV-3","http://223.110.241.139:6610/gitv/live1/G_CCTV-3-HQ/G_CCTV-3-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-4","http://223.110.241.139:6610/gitv/live1/G_CCTV-4-HQ/G_CCTV-4-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-5","http://223.110.241.139:6610/gitv/live1/G_CCTV-5-HQ/G_CCTV-5-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-5+","http://223.110.241.139:6610/gitv/live1/G_CCTV-5PLUS-HQ/G_CCTV-5PLUS-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-6","http://223.110.241.139:6610/gitv/live1/G_CCTV-6-HQ/G_CCTV-6-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-7","http://223.110.241.139:6610/gitv/live1/G_CCTV-7-HQ/G_CCTV-7-HQ.m3u8"));
        playsources.add(new Playsource("CCTV-8","http://223.110.241.139:6610/gitv/live1/G_CCTV-8-HQ/G_CCTV-8-HQ.m3u8"));
        playsources.add(new Playsource("江苏卫视","http://223.110.241.139:6610/gitv/live1/G_JIANGSU-CQ/G_JIANGSU-CQ.m3u8"));
        return playsources;
    }
}
