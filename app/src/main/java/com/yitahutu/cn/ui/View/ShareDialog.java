package com.yitahutu.cn.ui.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yitahutu.cn.R;

/**
 * 自定义的分享框
 */
public class ShareDialog extends Dialog {

    private Context context;


    public ShareDialog(Context context) {
        super(context);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.share_pop);
        LinearLayout ll_wxhy = (LinearLayout) findViewById(R.id.share_ll_wxhy);
        ll_wxhy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"改功能暂未开通",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        LinearLayout ll_pyq = (LinearLayout) findViewById(R.id.share_ll_pyq);
        ll_pyq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"改功能暂未开通",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });




    }

//    public void showShareWindow() {
//        View contentView = LayoutInflater.from(context).inflate(R.layout.share_pop, null);
//        ScaleScreenHelperFactory.getInstance().loadView((ViewGroup) contentView);
//        LinearLayout ll_wxhy = (LinearLayout) contentView.findViewById(R.id.share_ll_wxhy);
//        ll_wxhy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
//                sp = new Platform.ShareParams();
//                sp.setShareType(Platform.SHARE_TEXT);
//                sp.setShareType(Platform.SHARE_WEBPAGE);
////					sp.setShareType(Platform.SHARE_IMAGE);
//
//                //        oks.disableSSOWhenAuthorize();
//                // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//                sp.setTitle("久信二手车");
//                // text是分享文本，所有平台都需要这个字段
//                sp.setText("这有一款不错的车，赶紧来看看吧");
//                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//                // url仅在微信（包括好友和朋友圈）中使用
//                sp.setUrl(Conn.SERVICE+Conn.SHAREURL+"?carid="+carid);
//                Log.e("onClick: ",Conn.SERVICE+Conn.SHAREURL+"?carid="+carid);
//
//
//                Platform qzone = ShareSDK.getPlatform(Wechat.NAME);
//// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
//                qzone.setPlatformActionListener(platformActionListener);
//// 执行图文分享
//                qzone.share(sp);
//
//                dismiss();
//
//            }
//        });
//        LinearLayout ll_pyq = (LinearLayout) contentView.findViewById(R.id.share_ll_pyq);
//        ll_pyq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sp = new Platform.ShareParams();
//                sp.setShareType(Platform.SHARE_TEXT);
//                sp.setShareType(Platform.SHARE_WEBPAGE);
////					sp.setShareType(Platform.SHARE_IMAGE);
//
//                //        oks.disableSSOWhenAuthorize();
//                // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//                sp.setTitle("久信二手车");
//
//                // text是分享文本，所有平台都需要这个字段
//                sp.setText("这有一款不错的车，赶紧来看看吧");
//
//                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//                // url仅在微信（包括好友和朋友圈）中使用
//                sp.setUrl(Conn.SERVICE+Conn.SHAREURL+"?carid="+carid);
//
//
//                Platform qzone = ShareSDK.getPlatform(WechatMoments.NAME);
//// 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
//                qzone.setPlatformActionListener(platformActionListener);
//// 执行图文分享
//                qzone.share(sp);
//
//                dismiss();
//
//            }
//        });
//        LinearLayout ll_qq = (LinearLayout) contentView.findViewById(R.id.share_ll_qq);
//        ll_qq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UtilToast.show(context, "该功能尚未开通，敬请期待！");
//            }
//        });
//        RelativeLayout tv_cancel = (RelativeLayout) contentView.findViewById(R.id.share_tv_cancel);
//        tv_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
//
//
//        // 设置SelectPicPopupWindow的View
////        this.setContentView(contentView);
//        // 设置SelectPicPopupWindow弹出窗体的宽
////        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
////        // 设置SelectPicPopupWindow弹出窗体的高
////        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
////        // 设置SelectPicPopupWindow弹出窗体可点击
////        this.setFocusable(true);
////        // 设置SelectPicPopupWindow弹出窗体动画效果
////        this.setAnimationStyle((R.style.popwin_anim_style));
////        // 实例化一个ColorDrawable颜色为半透明
////        ColorDrawable dw = new ColorDrawable(0xb0000000);
////        // 设置SelectPicPopupWindow弹出窗体的背景
////        this.setBackgroundDrawable(dw);
//
//
////		private void qq() {
////			ShareParams sp = new ShareParams();
////			sp.setTitle(shareParams.getTitle());
////			sp.setTitleUrl(shareParams.getUrl()); // 标题的超链接
////			sp.setText(shareParams.getText());
////			sp.setImageUrl(shareParams.getImageUrl());
////			sp.setComment("我对此分享内容的评论");
////			sp.setSite(shareParams.getTitle());
////			sp.setSiteUrl(shareParams.getUrl());
////			Platform qq = ShareSDK.getPlatform(context, "QQ");
////			qq.setPlatformActionListener(platformActionListener);
////			qq.share(sp);
////		}
//
//    }

}