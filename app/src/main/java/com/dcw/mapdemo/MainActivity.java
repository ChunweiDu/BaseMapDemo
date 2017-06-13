package com.dcw.mapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import static android.R.attr.paddingBottom;
import static android.R.attr.paddingLeft;
import static android.R.attr.paddingRight;
import static android.R.attr.paddingTop;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.map);
        //设置百度地图图标显示位置
        mMapView.setLogoPosition(LogoPosition.logoPostionleftBottom);
        int mapLevel = mMapView.getMapLevel();
        Log.d(TAG, "onCreate: mapLevel:" + mapLevel);
        init();
    }

    private void init() {
        BaiduMap baiduMap = mMapView.getMap();
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。
        // 使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        //        baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        //开启交通图
        baiduMap.setTrafficEnabled(true);
        //地图Logo不允许遮挡,参数paddingLeft、paddingTop、paddingRight、paddingBottom
        // 参数表示距离屏幕边框的左、上、右、下边距的距离，单位为屏幕坐标的像素密度
        baiduMap.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_launcher);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)//设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
        //将marker添加到地图上
        Marker marker = (Marker) (baiduMap.addOverlay(option));
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option);


        //调用BaiduMap对象的setOnMarkerDragListener方法设置marker拖拽的监听
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
            }

            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
            }

            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
