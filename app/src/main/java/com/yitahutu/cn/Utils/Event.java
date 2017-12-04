package com.yitahutu.cn.Utils;

import com.yitahutu.cn.model.CartListModel;
import com.yitahutu.cn.model.ConsumeModel;
import com.yitahutu.cn.model.EvaluateModel;
import com.yitahutu.cn.model.ExpressModel;
import com.yitahutu.cn.model.FinanceDetailModel;
import com.yitahutu.cn.model.FinanceModel;
import com.yitahutu.cn.model.GoodsModel;
import com.yitahutu.cn.model.RefundRecordModel;

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
    public static class AddGoodsToCart{}
    public static class UserInfoEvent{}
    public static class RecommendGoodsEvent{
        public List<GoodsModel> goodsModels;

        public RecommendGoodsEvent(List<GoodsModel> goodsModels) {
            this.goodsModels = goodsModels;
        }
    }
    public static class FinanceDetailEvent{
        public List<FinanceDetailModel> financeModels;

        public FinanceDetailEvent(List<FinanceDetailModel> financeModels) {
            this.financeModels = financeModels;
        }
    }
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
    public static class GoodsBySearch {
        public List<GoodsModel> goodsModels;

        public GoodsBySearch(List<GoodsModel> goodsModels) {
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
    public static class TotalModelEvent{}
    public static class FinanceEvent{
        public List<FinanceModel> financeModels;

        public FinanceEvent(List<FinanceModel> finRefundRecordModelanceModels) {
            this.financeModels = finRefundRecordModelanceModels;
        }
    }
    public static class RefundRecordModelEvent{
        public List<RefundRecordModel> financeModels;

        public RefundRecordModelEvent(List<RefundRecordModel> financeModels) {
            this.financeModels = financeModels;
        }
    }
    public static class ConsumeModelEvent{
        public List<ConsumeModel> financeModels;

        public ConsumeModelEvent(List<ConsumeModel> financeModels) {
            this.financeModels = financeModels;
        }
    }
    public static class ExpressModelEvent{
        public List<ExpressModel.ExpressState> financeModels;

        public ExpressModelEvent(List<ExpressModel.ExpressState> financeModels) {
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
