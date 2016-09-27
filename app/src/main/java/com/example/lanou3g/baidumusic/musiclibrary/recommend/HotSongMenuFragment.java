package com.example.lanou3g.baidumusic.musiclibrary.recommend;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lanou3g.baidumusic.DividerItemDecoration;
import com.example.lanou3g.baidumusic.R;
import com.example.lanou3g.baidumusic.main.BaseFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by dllo on 16/9/27.
 */
public class HotSongMenuFragment extends BaseFragment{
    private HotSongMenuBean hotSongMenuBean;
    private RecyclerView rv;
    private TextView tv_title;
    private ImageView iv_more;
    private ImageView img;
    private TextView tv_tag;
    private TextView tv_listennum;
    private TextView tv_collect;
    private TextView tv_comment;
    private TextView tv_share;
    private TextView tv_songlist;
    private TextView tv_download;
    private TextView tv_playAll;
    private DisplayImageOptions options;
    private TextView tv_desc;

    public void setHotSongMenuBean(HotSongMenuBean hotSongMenuBean) {
        this.hotSongMenuBean = hotSongMenuBean;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_hotsong_menu;
    }

    @Override
    protected void initView() {
        ImageView iv_back = bindView(R.id.iv_back_hot_songmenu);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_slide_in_right, R.anim.fragment_slide_out_left);
                transaction.remove(HotSongMenuFragment.this);
                transaction.commit();
            }
        });

        rv = bindView(R.id.rv_hot_songmenu);
        LinearLayout ll_collec =bindView(R.id.ll_collect_hot_songmenu);
        LinearLayout ll_comment =bindView(R.id.ll_comment_hot_songmenu);
        LinearLayout ll_share =bindView(R.id.ll_share_hot_songmenu);
        tv_title = bindView(R.id.tv_title_hot_songmenu);
        iv_more = bindView(R.id.iv_more_hot_songmenu);
        img = bindView(R.id.iv_hot_songmenu);
        tv_tag = bindView(R.id.tv_tag_hot_songmenu);
        tv_listennum = bindView(R.id.tv_listennum_hot_songmenu);
        tv_collect = bindView(R.id.tv_collect_count);
        tv_comment = bindView(R.id.tv_Comment_count);
        tv_share = bindView(R.id.tv_share_count);
        tv_songlist = bindView(R.id.tv_songlist_count_hot_songmenu);
        tv_download = bindView(R.id.tv_download_hot_songmenu);
        tv_playAll = bindView(R.id.tv_songlist_play_hot_songmenu);
        tv_desc = bindView(R.id.tv_dec_hot_songmenu);

        options = new DisplayImageOptions
               .Builder()
               .showImageForEmptyUri(R.mipmap.default_artist)
               .showImageOnLoading(R.mipmap.default_artist)
               .cacheInMemory(true)
               .cacheOnDisk(true)
               .considerExifParams(true)
               .build();
    }

    @Override
    protected void initData() {
        tv_title.setText(hotSongMenuBean.getTitle());
        ViewGroup.LayoutParams params = img.getLayoutParams();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int h = metrics.heightPixels;
        params.width = h / 4;
        params.height = h / 4;
        img.setLayoutParams(params);
        ImageLoader.getInstance().displayImage(hotSongMenuBean.getPic_300(), img, options);
        tv_tag.setText(hotSongMenuBean.getTag());
        tv_listennum.setText(hotSongMenuBean.getListenum());
        if (hotSongMenuBean.getCollectnum().length() > 0){
            tv_collect.setText(hotSongMenuBean.getCollectnum());
        } else {
            tv_collect.setText("收藏");
        }
        tv_comment.setText("评论");
        tv_share.setText("分享");
        tv_songlist.setText("/" + hotSongMenuBean.getContent().size() + "首歌");
        tv_desc.setText(hotSongMenuBean.getDesc());
        HotSongMenuAdapter adapter = new HotSongMenuAdapter(context);
        adapter.setContentBeen(hotSongMenuBean.getContent());
        rv.setAdapter(adapter);
        LinearLayoutManager manager1 = new LinearLayoutManager(context);
        rv.setLayoutManager(manager1);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST);
        rv.addItemDecoration(dividerItemDecoration);
    }

}
