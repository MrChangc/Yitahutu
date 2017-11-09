package com.yitahutu.cn.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;

import com.yitahutu.cn.R;
import com.yitahutu.cn.Utils.Event;
import com.yitahutu.cn.Utils.PreferUtil;
import com.yitahutu.cn.model.EvaluateAll;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.ui.activity.GoodsDetailActivity;
import com.yitahutu.cn.ui.adapter.EvaluateAdapter;
import com.yitahutu.cn.webservice.WebService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class EvaluateFragment extends Fragment {
    @BindView(R.id.button_evaluate_all)
    RadioButton buttonEvaluateAll;
    @BindView(R.id.button_evaluate_good)
    RadioButton buttonEvaluateGood;
    @BindView(R.id.button_evaluate_normal)
    RadioButton buttonEvaluateNormal;
    @BindView(R.id.button_evaluate_feedback)
    RadioButton buttonEvaluateFeedback;
    @BindView(R.id.list_evaluate)
    ListView listEvaluate;
    Unbinder unbinder;
    private View rootView;
    private List<EvaluateModel> models = new ArrayList<>();
    private EvaluateAdapter evaluateAdapter ;
    private long id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoodsDetailActivity detailActivity = (GoodsDetailActivity) getActivity();
        id = detailActivity.getId();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_evaluate, null);
        unbinder = ButterKnife.bind(this, rootView);
        initAdapter();
        initList();
        WebService.getEvaluateList(getActivity(),id+"");
        return rootView;
    }

    private void initAdapter() {
        List<EvaluateModel> evaluateModels = EvaluateModel.listAll(EvaluateModel.class);
        models.addAll(evaluateModels);
        evaluateAdapter = new EvaluateAdapter(getActivity(),models);
        listEvaluate.setAdapter(evaluateAdapter);
    }

    private void initList() {
        List<EvaluateAll> evaluateAlls = EvaluateAll.listAll(EvaluateAll.class);
        List<EvaluateModel> evaluateModels = EvaluateModel.listAll(EvaluateModel.class);
        models.clear();
        models.addAll(evaluateModels);
        if (evaluateAlls.size()>0){
            EvaluateAll evaluateAll = evaluateAlls.get(0);
            buttonEvaluateAll.setText("全部("+evaluateAll.getCount()+")");
            buttonEvaluateGood.setText("好评("+evaluateAll.getGoodNum()+")");
            buttonEvaluateNormal.setText("中评("+evaluateAll.getMedNum()+")");
            buttonEvaluateFeedback.setText("差评("+evaluateAll.getBadNum()+")");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.button_evaluate_all)
    public void setButtonEvaluateAll(){

    }
    @OnClick(R.id.button_evaluate_good)
    public void setButtonEvaluateGood(){

    }
    @OnClick(R.id.button_evaluate_normal)
    public void setButtonEvaluateNormal(){

    }
    @OnClick(R.id.button_evaluate_feedback)
    public void setButtonEvaluateFeedback(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(Event.EvaluateEvent event){
        models.clear();
        models.addAll(event.models);
        evaluateAdapter.notifyDataSetChanged();
    }
}
