package com.unicom.tencent.api;

import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.unicom.tencent.utils.TencentApiUtils;

public class DcApi {
	/*
	 * action 请求API的接口名字
	 * region 腾讯大区
	 * params 私有参数列表
	 */
	public static void requestDc(String action,String region,TreeMap<String, Object> params){
		requestDc(action,region,null,params);
	}
	
	public static void requestDc(String action,String region,String signatureType,TreeMap<String, Object> params){
		TreeMap<String, Object> config = TencentApiUtils.buildPublicVpcParams(region);
		QcloudApiModuleCenter module = TencentApiUtils.createDcModule(config);
        System.out.println(module.generateUrl(action, params));
        String result = null;
        try {
            // call 方法正式向指定的接口名发送请求，并把请求参数 params 传入，返回即是接口的请求结果。
            result = module.call(action, params);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error..." + e.getMessage());
        }
	}
	
	
	//创建专线网关
	public static void createDirectConnectGateway(){
		
	}
}
