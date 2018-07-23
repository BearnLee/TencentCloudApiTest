package com.unicom.test;

import java.util.TreeMap;

import com.qcloud.QcloudApiModuleCenter;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.dc.v20180410.DcClient;
import com.tencentcloudapi.dc.v20180410.models.AcceptDirectConnectTunnelRequest;
import com.tencentcloudapi.dc.v20180410.models.AcceptDirectConnectTunnelResponse;
import com.tencentcloudapi.dc.v20180410.models.BgpPeer;
import com.tencentcloudapi.dc.v20180410.models.CreateDirectConnectTunnelRequest;
import com.tencentcloudapi.dc.v20180410.models.CreateDirectConnectTunnelResponse;
import com.tencentcloudapi.dc.v20180410.models.DescribeDirectConnectTunnelsRequest;
import com.tencentcloudapi.dc.v20180410.models.DescribeDirectConnectTunnelsResponse;
import com.tencentcloudapi.dc.v20180410.models.ModifyDirectConnectTunnelAttributeRequest;
import com.tencentcloudapi.dc.v20180410.models.ModifyDirectConnectTunnelAttributeResponse;
import com.tencentcloudapi.dc.v20180410.models.RejectDirectConnectTunnelRequest;
import com.tencentcloudapi.dc.v20180410.models.RejectDirectConnectTunnelResponse;
import com.tencentcloudapi.dc.v20180410.models.RouteFilterPrefix;
import com.unicom.tencent.constants.Constants;
import com.unicom.tencent.utils.TencentApiUtils;
import com.unicom.tencent.utils.TreeMapValueBuilder;

public class DcApi {
	/*
	 * 创建专线网关 V2版本的创建专线网关放在VPC里面的,V3版本已经分离,故这里放在Dc里面
	 */
	public static void createDirectConnectGateway(String region,String vpcId,String directConnectGatewayName){
		TreeMap<String, Object> params = new TreeMapValueBuilder()
				.put("vpcId",vpcId)
				.put("directConnectGatewayName",directConnectGatewayName)
				.build();
		requestVpc("CreateDirectConnectGateway", region, params);
	}
	
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
	
	
	/*
	 * 创建专线通道
	 */
	public static void createDirectConnectTunnel(String region,CreateDirectConnectTunnelRequest request){
		try {
			//Client参数里面的Region没什么用，但是必须传，需要的setNetworkRegion这个参数
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        DcClient client = new DcClient(cred, region);
	        
	        CreateDirectConnectTunnelResponse  response = client.CreateDirectConnectTunnel(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 接受创建专线通道申请
	 */
	
	public static void acceptDirectConnectTunnel(String region,AcceptDirectConnectTunnelRequest request){
		try {
			//Client参数里面的Region没什么用，但是必须传，需要的setNetworkRegion这个参数
			Credential cred = new Credential(Constants.TENCENT_CLOUD_SECRET_ID,Constants.TENCENT_CLOUD_SECRET_KEY);
	        DcClient client = new DcClient(cred, region);
	        
	        AcceptDirectConnectTunnelResponse  response = client.AcceptDirectConnectTunnel(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 拒绝专线通道申请
	 */
	
	public static void rejectDirectConnectTunnel(String region,RejectDirectConnectTunnelRequest request){
		try {
			//Client参数里面的Region没什么用，但是必须传，需要的setNetworkRegion这个参数
			Credential cred = new Credential(Constants.TENCENT_CLOUD_SECRET_ID,Constants.TENCENT_CLOUD_SECRET_KEY);
	        DcClient client = new DcClient(cred, region);
	        
	        RejectDirectConnectTunnelResponse  response = client.RejectDirectConnectTunnel(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  查询专线通道列表
	 */
	
	public static void describeDirectConnectTunnels(String region,DescribeDirectConnectTunnelsRequest request){
		try {
			//Client参数里面的Region没什么用，但是必须传，需要的setNetworkRegion这个参数
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        DcClient client = new DcClient(cred, region);
	        
	        DescribeDirectConnectTunnelsResponse  response = client.DescribeDirectConnectTunnels(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  修改专线通道申请
	 */
	public static void modifyDirectConnectTunnelAttribute(String region,ModifyDirectConnectTunnelAttributeRequest request){
		try {
			//Client参数里面的Region没什么用，但是必须传，需要的setNetworkRegion这个参数
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        DcClient client = new DcClient(cred, region);
	        
	        ModifyDirectConnectTunnelAttributeResponse  response = client.ModifyDirectConnectTunnelAttribute(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
}
