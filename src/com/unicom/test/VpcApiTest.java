package com.unicom.test;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vpc.v20170312.VpcClient;
import com.tencentcloudapi.vpc.v20170312.models.CreateVpcRequest;
import com.tencentcloudapi.vpc.v20170312.models.CreateVpcResponse;
import com.tencentcloudapi.vpc.v20170312.models.DeleteVpcRequest;
import com.tencentcloudapi.vpc.v20170312.models.DeleteVpcResponse;
import com.tencentcloudapi.vpc.v20170312.models.DescribeVpcsRequest;
import com.tencentcloudapi.vpc.v20170312.models.DescribeVpcsResponse;
import com.tencentcloudapi.vpc.v20170312.models.Filter;
import com.tencentcloudapi.vpc.v20170312.models.ModifyVpcAttributeRequest;
import com.tencentcloudapi.vpc.v20170312.models.ModifyVpcAttributeResponse;
import com.unicom.tencent.constants.Constants;


/*
 * VPC相关的V3  SDK版本测试DEMO
 */
public class VpcApiTest {
	public static void main(String[] args) {
//		createVpc("ap-beijing");  //创建VPC
//		deleteVpc("ap-beijing"); //删除VPC
		modifyVpcAttribute("ap-beijing");//修改VPC
//		describeVpcs("ap-beijing");//查询私有网络列表
	}
	
	/*
	 *  功能-创建VPC
	 *  请求参数
     *  参数名称			是否必选	类型			描述
	 *	Action			是		String		公共参数，本接口取值：CreateVpc
	 *	Version			是		String		公共参数，本接口取值：2017-03-12
	 *	VpcName			是		String		vpc名称，最大长度不能超过60个字节。
	 *	CidrBlock		是		String		vpc的cidr，只能为10.0.0.0/16，172.16.0.0/12，192.168.0.0/16这三个内网网段内。
	 *	EnableMulticast	否		String		是否开启组播。true: 开启, false: 不开启。
	 *	DnsServers.N	否		String[]	DNS地址，最多支持4个
	 *	DomainName		否		String		域名
     */
	private static void createVpc(String region){
		try {
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        VpcClient client = new VpcClient(cred, region);
	        
	        CreateVpcRequest cvp = new CreateVpcRequest();
	        //创建参数VPC参数列表
	        cvp.setVpcName("Api创建私有网络-V3");
	        cvp.setCidrBlock("10.22.0.0/16");
	        
        	CreateVpcResponse  cvr = client.CreateVpc(cvp);
        	System.out.println(cvr);
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 功能-删除VPC
     * 请求参数
     * vpcId - 系统分配的私有网络ID，支持升级前的vpcId，也支持升级后的unVpcId。（必填参数）
     */
	private static void deleteVpc(String region){
		try {
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        VpcClient client = new VpcClient(cred, region);
	        
	        
	        DeleteVpcRequest dvp = new DeleteVpcRequest();
	        dvp.setVpcId("vpc-djjglbhm");
	        
        	DeleteVpcResponse  dvr = client.DeleteVpc(dvp);
        	System.out.println(dvr);
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 功能-查询私有网络列表
	 * 请求参数：
	 * 每次请求的实例的上限为100。
	 * 不设置参数---返回默认排序下的前20条VPC信息。
	 * 设置参数---参数不支持同时指定VpcIds和Filters。相当于是实现了过滤功能
	 * setVpcIds---VpcId数组 
	 * Filters ---过滤条件，使用方式如下
	 * &Filters.0.Name=is-default
	 * &Filters.0.Values.0=false
	 * &Filters.1.Name=cidr-block
	 * &Filters.1.Values.0=10.8.0.0
	 * &Filters.1.Values.1=192.168.0.0
	 */
	private static void describeVpcs(String region){
		try {
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        VpcClient client = new VpcClient(cred, region);
	        
	        DescribeVpcsRequest  request = new DescribeVpcsRequest();
	        //当查询2个VPCID,其中一个不存在的时候，API会抛异常，我们后续如果做指定查询操作 需要在异常的回调里面做处理
	        request.setVpcIds(new String[]{"vpc-6rdcqjmu","vpc-efm85gpc"});
//	        request.setFilters(new Filter[5]);
	        
	        DescribeVpcsResponse  response = client.DescribeVpcs(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 功能-修改VPC属性
	 * 参数名称	    	是否必选	       类型	        	  描述
	 * VpcId	    	是	       String			 VPC实例ID。形如：vpc-f49l6u0z。每次请求的实例的上限为100。参数不支持同时指定VpcIds和Filters。
	 * VpcName	    	否	       String			  私有网络名称，可任意命名，但不得超过60个字符。
	 * EnableMulticast	否  		   String			  是否开启组播。true: 开启, false: 关闭。
	 * DnsServers.N		否		   String[]			 DNS地址，最多支持4个，第1个默认为主，其余为备
	 * DomainName		否		   String			  域名
	 */
	private static void modifyVpcAttribute(String region){
		try {
			Credential cred = new Credential(Constants.SECRET_ID,Constants.SECRET_KEY);
	        VpcClient client = new VpcClient(cred, region);
	        
	        ModifyVpcAttributeRequest request = new ModifyVpcAttributeRequest();
	        request.setVpcId("vpc-6rdcqjmu");
	        request.setVpcName("V3测试修改VPC名字");
	        
	        ModifyVpcAttributeResponse  response = client.ModifyVpcAttribute(request);
        	System.out.println(response.toString());
		} catch (TencentCloudSDKException e) {
			e.printStackTrace();
		}
	}
}
