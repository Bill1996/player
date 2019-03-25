package vvfgaa.player;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new PlayAdapter(getPlaysource()));
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
