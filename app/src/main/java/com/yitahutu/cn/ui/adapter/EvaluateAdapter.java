package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.TimeUtils;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.webservice.WebService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class EvaluateAdapter extends BaseAdapter {
    private Context mContext;
    private List<EvaluateModel> models;
    public EvaluateAdapter(Context mContext, List<EvaluateModel> models) {
        this.mContext = mContext;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EvaluateModel model = models.get(i);
        ViewHolder viewHolder = null;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_evaluate, null);
            viewHolder = new ViewHolder(view,model,mContext);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (model!=null){
            viewHolder.textTime.setText(TimeUtils.getTime3(model.getCreateTime()));
            viewHolder.textBrowseCount.setText("浏览"+model.getPageView()+"次");
            viewHolder.textEvaluate.setText(model.getComment());
        }
        return view;
    }

    static class ViewHolder {
        private EvaluateModel goodsModel;
        private Context mContext;
        @BindView(R.id.text_evaluate)
        TextView textEvaluate;
        @BindView(R.id.text_time)
        TextView textTime;
        @BindView(R.id.text_browse_count)
        TextView textBrowseCount;
        @BindView(R.id.ll_goods_comment)
        LinearLayout llGoodsComment;
        @BindView(R.id.ll_goods_best_up)
        LinearLayout llGoodsBestUp;

        ViewHolder(View view,EvaluateModel goodsModel,Context mContext) {
            ButterKnife.bind(this, view);
            this.goodsModel = goodsModel;
            this.mContext = mContext;
        }
        @OnClick(R.id.ll_goods_best_up)
        public void setLlGoodsBestUp(){
            WebService.getGoodsBestUp(mContext,goodsModel.getId()+"");
        }
    }

}
