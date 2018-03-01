package com.xuke.androidone.view.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.xuke.androidone.R;
import com.xuke.androidone.contract.home.HomeContenContract;
import com.xuke.androidone.model.bean.one.OneBean;
import com.xuke.androidone.presenter.home.HomeMainPresenter;
import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.fragment.BaseMVPCompatFragment;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xuke on 2018/2/27.
 * 首页内容
 */
public class HomeContentFragment extends BaseMVPCompatFragment<HomeContenContract.HomeContentPresenter, HomeContenContract.IHomeContentModel> implements HomeContenContract.IHomeContentView {

    @BindView(R.id.img_hp_img_url)
    ImageView imgHpImgUrl;
    @BindView(R.id.tv_hp_author)
    TextView tvHpAuthor;
    @BindView(R.id.tv_hp_content)
    TextView tvHpContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_praisenum)
    ImageView imgPraisenum;
    @BindView(R.id.img_share)
    ImageView imgShare;

    private static String INDEX = "index";
    private OneBean.DataBean.ShareListBean.QqBean qq;
    private OneBean.DataBean.ShareListBean.WxBean wx;

    public static HomeContentFragment getInstance(String index) {
        HomeContentFragment fragment = new HomeContentFragment();
        Bundle args = new Bundle();
        args.putString(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_content;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        String index = getArguments().getString(INDEX);
        mPresenter.loadContent(index);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return HomeMainPresenter.newInstance();
    }

    @Override
    public void updateContent(OneBean oneBean) {
        OneBean.DataBean data = oneBean.getData();

        Glide.with(this)
                .load(data.getHp_img_url())
                .placeholder(R.drawable.img_default)//图片加载出来前，显示的图片
                .error(R.drawable.img_default)//图片加载失败后，显示的图片
                .into(imgHpImgUrl);
        tvHpAuthor.setText(data.getHp_author());
        tvHpContent.setText(data.getHp_content());
        tvTitle.setText(data.getHp_title());

        OneBean.DataBean.ShareListBean share_list = data.getShare_list();
        qq = share_list.getQq();
        wx = share_list.getWx();

    }

    @Override
    public void showNetworkError() {

    }

    /**
     * 分享
     */
    public void share(String url, String content) {
        ShareAction shareAction = new ShareAction(mActivity);
        shareAction.setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE);

        UMImage thumb = new UMImage(mActivity, R.mipmap.ic_launcher);
        UMWeb web = new UMWeb(url);
        web.setTitle("one by one");
        web.setThumb(thumb);
        web.setDescription(content);

        shareAction.withMedia(web);
        shareAction.setCallback(umShareListener);
        shareAction.open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            ToastUtils.showToast(share_media + "分享开始");
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showToast(platform + " 分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast(platform + " 分享失败啦");
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast(platform + " 分享取消了");
        }
    };

    @OnClick(R.id.img_share)
    public void onViewClicked() {
        String url = qq.getLink();
        String content = tvHpContent.getText().toString().trim();
        share(url, content);
    }
}
