package com.starry.sky.jacksondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    @Bind(R.id.tv_object_tojson)
    TextView mObjectTvTojson;
    @Bind(R.id.tv_array_tojson)
    TextView mArrayTvTojson;
    @Bind(R.id.tv_toObject)
    TextView mTvToObject;
    @Bind(R.id.tv_toList)
    TextView mTvToList;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    private ObjectMapper mMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMapper = JacksonMapper.getMapper();
        //创建ProductBean对象
        ProductBean productBean = createObject();

        //将对象解析成json字符串
        String objectJson = toJson(productBean);
       // String json = toJson2(productBean);
        mObjectTvTojson.setText("toJson==="+objectJson);

        //将数组转化成json字符串
        ArrayList<ProductBean> beanArrayList = new ArrayList<>();
        beanArrayList.add(productBean);
        beanArrayList.add(productBean);
        beanArrayList.add(productBean);
        String arrayJson =toJson(beanArrayList);
        mArrayTvTojson.setText("toJson==="+arrayJson);

        //将json字符串解析为bean
        ProductBean bean = toObjectBean(objectJson);
        mTvToObject.setText("toObjectBean==="+bean.toString());

        //将json字符串解析为List
        ArrayList<ProductBean> list = toArrayBean(arrayJson);
        mTvToList.setText("toArrayBean==="+list.toString());

        //jackson节点解析
        jsonNode(objectJson);

    }

    public void jsonNode(String objectJson) {
        //{"productProperty":[{"v":"红色","k":"颜色","id":1},{"v":"L","k":"尺寸","id":2}],"name":"毛衣","price":130.0,"id":1}
        try {
            JsonNode jsonNode = mMapper.readTree(objectJson);
            JsonNode productProperty = jsonNode.get("productProperty");
            JsonNode name = jsonNode.get("name");
            JsonNode price = jsonNode.get("price");
            JsonNode id = jsonNode.get("id");
            String propertyString = productProperty.toString();

            //如果要转化成对象的话还得用 ObjectMapper的readValue(), productProperty如果用JsonNode的asText()获取的是空
            ArrayList productPropertyBeans = mMapper.readValue(propertyString, new ArrayList<ProductPropertyBean>().getClass());
            Log.d(TAG, "jsonNode:name: "+name.asText());
            Log.d(TAG, "jsonNode:id: "+id.asInt());
            Log.d(TAG, "jsonNode: price:"+price.asDouble());
            Log.d(TAG, "jsonNode: productPropertyBeans"+productPropertyBeans.toString());


        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "jsonNode: "+e.toString());
        }
    }



    public ProductBean createObject(){
        //创建ProductBean对象
        ProductPropertyBean color = new ProductPropertyBean(1,"颜色","红色");
        ProductPropertyBean size = new ProductPropertyBean(2,"尺寸","L");
        ArrayList<ProductPropertyBean> productPropertyBeen = new ArrayList<>();
        productPropertyBeen.add(color);
        productPropertyBeen.add(size);
        ProductBean productBean = new ProductBean(1,"毛衣",130,productPropertyBeen);
        return productBean;
    }
    public String toJson(Object o){
        String s;
        try {
            //将对象解析成json字符串
          s = mMapper.writeValueAsString(o);
        } catch (IOException e) {
            e.printStackTrace();
            s = e.toString();
        }
        Log.i(TAG, "getJson: "+s);
        return s;
    }
    public String toJson2(Object o){
        String s;
        try {
            //将对象解析成json字符串
            StringWriter stringWriter = new StringWriter();
            JsonFactory jsonFactory = mMapper.getJsonFactory();
            JsonGenerator jgen = jsonFactory.createJsonGenerator(stringWriter);
            mMapper.writeValue(jgen,o);
            jgen.flush();
            jgen.close();
            s = stringWriter.toString();
            stringWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            s = e.toString();
        }
        Log.i(TAG, "getJson: "+s);
        return s;
    }
    public ProductBean toObjectBean(String json){
        ProductBean productBean = null;
        try {
          productBean = mMapper.readValue(json, ProductBean.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productBean;
    }
    public ArrayList<ProductBean> toArrayBean(String json){
        ArrayList list = null;
        try {
            list = mMapper.readValue(json, new ArrayList<ProductBean>().getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
