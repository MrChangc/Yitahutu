package com.yitahutu.cn.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yitahutu.cn.R;
import com.yitahutu.cn.model.AddressModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017\10\27 0027.
 */
public class AddressListAdapter extends BaseAdapter {
    private Context mContext;
    private List<AddressModel> addressModels;

    public AddressListAdapter(Context mContext, List<AddressModel> addressModels) {
        this.mContext = mContext;
        this.addressModels = addressModels;
    }

    @Override
    public int getCount() {
        return addressModels.size();
    }

    @Override
    public Object getItem(int i) {
        return addressModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final AddressModel addressModel = addressModels.get(i);
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_address_list, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.checkBox.setChecked(addressModel.isCheck());
        viewHolder.textUserName.setText(addressModel.getName());
        viewHolder.textUserPhone.setText(addressModel.getPhone());
        viewHolder.textAddress.setText(addressModel.getDetailAddress());
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalViewHolder.checkBox.isChecked()) {
                    for (int j = 0; j < addressModels.size(); j++) {
                        if (j != i) {
                            if (addressModels.get(j).isCheck()) {
                                addressModels.get(j).setCheck(false);
                                addressModels.get(j).save();
                            }
                        }else {
                            addressModels.get(j).setCheck(true);
                            addressModels.get(j).save();
                        }

                    }
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.text_user_name)
        TextView textUserName;
        @BindView(R.id.text_user_phone)
        TextView textUserPhone;
        @BindView(R.id.text_address)
        TextView textAddress;
        @BindView(R.id.checkbox_address)
        CheckBox checkBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
