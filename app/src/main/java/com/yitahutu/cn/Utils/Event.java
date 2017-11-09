package com.yitahutu.cn.Utils;

import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;

import java.util.List;

/**
 * Created by Administrator on 2017\10\17 0017.
 */
public class Event {
    public static class EvaluateEvent{
        public List<EvaluateModel> models;

        public EvaluateEvent(List<EvaluateModel> models) {
            this.models = models;
        }
    }
    public static class LoginEvent{}
    public static class BuyGoodsEvent{}
    public static class UserInfoEvent{}
    public static class RecommendGoodsEvent{}
    public static class GoodsEvent{
        public List<GoodsModel> goodsModels;

        public GoodsEvent(List<GoodsModel> goodsModels) {
            this.goodsModels = goodsModels;
        }
    }
    public static class GoodsByRecommendEvent{
        public List<GoodsModel> goodsModels;

        public GoodsByRecommendEvent(List<GoodsModel> goodsModels) {
            this.goodsModels = goodsModels;
        }
    }
    public static class GoodsByScreenEvent{
        public List<GoodsModel> goodsModels;

        public GoodsByScreenEvent(List<GoodsModel> goodsModels) {
            this.goodsModels = goodsModels;
        }
    }
    public static class RecommendListEvent{}
    public static class PriceChangeEvent{}
    public static class FinanceEvent{
        public List<FinanceModel> financeModels;

        public FinanceEvent(List<FinanceModel> financeModels) {
            this.financeModels = financeModels;
        }
    }
    public static class CartListEvent{
        public int state = -1;
        public List<CartListModel> financeModels;

        public CartListEvent(List<CartListModel> financeModels) {
            this.financeModels = financeModels;
        }
        public CartListEvent(List<CartListModel> financeModels,int state) {
            this.financeModels = financeModels;
            this.state = state;
        }
    }
}
