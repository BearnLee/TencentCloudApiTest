package com.unicom.tencent.utils;

import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Morphling;
import com.unicom.tencent.constants.Constants;

/*
 * V1.0版本 TencentApiUtils
 */
public class TencentApiUtils {
	/*
	 * 0-VPC业务 ,1-CVM业务 ,2-DC业务
	 */
	private static final int BUSSINESS_TYPE_VPC = 0;
	private static final int BUSSINESS_TYPE_CVM = 1;
	private static final int BUSSINESS_TYPE_DC = 2;

	/*
	 * bussiness 业务类型 config 公有参数
	 */
	
	public static QcloudApiModuleCenter createDcModule(TreeMap<String, Object> config) {
		Morphling morphling = createBaseMorphling(BUSSINESS_TYPE_DC);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(morphling, config);
		return module;
	}

	public static QcloudApiModuleCenter createCvmModule(TreeMap<String, Object> config) {
		Morphling morphling = createBaseMorphling(BUSSINESS_TYPE_CVM);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(morphling, config);
		return module;
	}

	public static QcloudApiModuleCenter createVpcModule(TreeMap<String, Object> config) {
		Morphling morphling = createBaseMorphling(BUSSINESS_TYPE_VPC);
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(morphling, config);
		return module;
	}

	/*
	 * 根据type类型创建对应的Morphing对象,V3版本不需要
	 */
	private static Morphling createBaseMorphling(int type) {
		switch (type) {
		case BUSSINESS_TYPE_VPC:
			return new Morphling("vpc");
		case BUSSINESS_TYPE_CVM:
			return new Morphling("cvm");
		case BUSSINESS_TYPE_DC:
			return new Morphling("dc");
		default:
			return  new Morphling("cvm");
		}
	}
	
	/*
	 * 创建公共参数，有的为SDK自动集成,目前需要传入region
	 * 暂定SecretId 和 SecretKey为本程序自有
	 */
	public static TreeMap<String, Object> buildPublicVpcParams(String region){
		return buildPublicVpcParams(region,null);
	} 
	
	public static TreeMap<String, Object> buildPublicVpcParams(String region,String signatureType){
		TreeMap<String, Object> map = new TreeMapValueBuilder()
				.put("SecretId",Constants.SECRET_ID)
				.put("SecretKey",Constants.SECRET_KEY)
				.put("DefaultRegion", region)
				.put("Version", "2017-03-12")
				.put("SignatureMethod", signatureType)
				.build();
		return map;
	}

}
