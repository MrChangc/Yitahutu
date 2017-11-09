package com.yitahutu.cn.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.yitahutu.cn.R;
import com.yitahutu.cn.model.AddressModel;
import com.yitahutu.cn.model.JsonBean;
import com.yitahutu.cn.model.JsonFileReader;
import com.yitahutu.cn.webservice.WebService;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017\10\28 0028.
 */
public class AddressDetailActivity extends BaseActivity {
    @BindView(R.id.edit_user_name)
    EditText editUserName;
    @BindView(R.id.edit_phone_number)
    EditText editPhoneNumber;
    @BindView(R.id.edit_address)
    TextView editAddress;
    @BindView(R.id.edit_address_detail)
    EditText editAddressDetail;
    @BindView(R.id.edit_postal_code)
    EditText editPostalCode;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    private AddressModel addressModel;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    @Override
    void setRightIconListener() {
        if (addressModel == null) {
            addressModel = new AddressModel();
        }
        String phone = editPhoneNumber.getText().toString().trim();
        String userName = editUserName.getText().toString().trim();
        String address = editAddress.getText().toString().trim();
        String addressDetail = editAddressDetail.getText().toString().trim();
        String postalCode = editPostalCode.getText().toString().trim();
        if (userName == null || TextUtils.isEmpty(userName))
            Toast.makeText(mContext, "姓名不能为空!", Toast.LENGTH_SHORT).show();
        else if (phone == null || TextUtils.isEmpty(phone))
            Toast.makeText(mContext, "手机号不能为空!", Toast.LENGTH_SHORT).show();
        else if (address == null || TextUtils.isEmpty(address))
            Toast.makeText(mContext, "请填写区域地址!", Toast.LENGTH_SHORT).show();
        else if (addressDetail == null || TextUtils.isEmpty(addressDetail))
            Toast.makeText(mContext, "请填写详细地址!", Toast.LENGTH_SHORT).show();
        else if (postalCode == null || TextUtils.isEmpty(postalCode))
            Toast.makeText(mContext, "请填写邮政编码!", Toast.LENGTH_SHORT).show();
        else {
            addressModel.setName(userName);
            addressModel.setPhone(phone);
            addressModel.setRegion(address);
            addressModel.setDetailAddress(addressDetail);
            addressModel.setZipCode(Integer.valueOf(postalCode));
            addressModel.save();
            WebService.updateAddress(mContext, addressModel.getId() + "",
                    userName, phone, address, addressDetail, postalCode);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("修改收货地址", R.layout.activity_address_detail);
        ButterKnife.bind(this);
        setRightIconVisibility(false);
        setRightTextVisibility(true);
        setRightText("完成");
        long id = getIntent().getLongExtra("id", -1);
        if (id != -1)
            setData(id);
        initJsonData();
    }

    private void setData(long id) {
        if (id != -1)
            addressModel = AddressModel.findById(AddressModel.class, id);
        if (addressModel != null) {
            editUserName.setText(addressModel.getName());
            editPhoneNumber.setText(addressModel.getPhone());
            editAddress.setText(addressModel.getRegion());
            editAddressDetail.setText(addressModel.getDetailAddress());
            editPostalCode.setText(addressModel.getZipCode() + "");
        }
    }
    private void initJsonData() {   //解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(this, "province_data.json");
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
    private void showPickerView() {
        OptionsPickerView pvOptions=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String text = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                editAddress.setText(text);
            }
        }).isDialog(true).setTitleText("")
                .setDividerColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setContentTextSize(18)
                .setOutSideCancelable(false)
                .build();
          /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
    @OnClick(R.id.ll_address)
    public void setLl_address(){
        showPickerView();
    }
}
