package com.unicom.tencent.api;

/*
 * VPC Api相关类逻辑处理
 */

import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.unicom.tencent.constants.Constants;
import com.unicom.tencent.utils.TencentApiUtils;
import com.unicom.tencent.utils.TreeMapValueBuilder;


public class VpcApi {
	/*
	 * action 请求API的接口名字
	 * region 腾讯大区
	 * params 私有参数列表
	 */
	public static void requestVpc(String action,String region,TreeMap<String, Object> params){
		requestVpc(action,region,null,params);
	}
	
	public static void requestVpc(String action,String region,String signatureType,TreeMap<String, Object> params){
		TreeMap<String, Object> config = TencentApiUtils.buildPublicVpcParams(region);
		QcloudApiModuleCenter module = TencentApiUtils.createVpcModule(config);
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
	
}
