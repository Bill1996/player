package vvfgaa.player;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;


public class PlayActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    private StandardGSYVideoPlayer detailPlayer;
    private String name;
    private String addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        name = getIntent().getStringExtra("name");
        addr = getIntent().getStringExtra("addr");
        detailPlayer = findViewById(R.id.detail_player);

        setTitle(name);
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getBackButton().setVisibility(View.GONE);

        PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class);
        initVideoBuilderMode();

    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        ImageView imageView = new ImageView(this);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(addr)
                .setCacheWithPlay(true)
                .setVideoTitle(name)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }
}