package com.unicom.test;

import java.util.TreeMap;

import com.tencentcloudapi.dc.v20180410.models.AcceptDirectConnectTunnelRequest;
import com.tencentcloudapi.dc.v20180410.models.BgpPeer;
import com.tencentcloudapi.dc.v20180410.models.CreateDirectConnectTunnelRequest;
import com.tencentcloudapi.dc.v20180410.models.DescribeDirectConnectTunnelsRequest;
import com.tencentcloudapi.dc.v20180410.models.ModifyDirectConnectTunnelAttributeRequest;
import com.tencentcloudapi.dc.v20180410.models.RejectDirectConnectTunnelRequest;
import com.unicom.tencent.api.VpcApi;
import com.unicom.tencent.utils.TreeMapValueBuilder;

public class DcApiTest {
	
	public static final String SECRET_ID = "AKIDgEdw5AFy0NlAgEniXDvU2UpAJNH23BWn";
	public static final String SECRET_KEY= "Rnz0qZFrIYq6B5oOsDtbCczGQUQBk8OS";
	
	public static void main(String[] args) {
		//创建专线网关
		DcApi.createDirectConnectGateway("ap-beijing","vpc-efm85glc","Api创建专线网关-V2");
		//创建专线通道
//		DcApiTest.createDirectConnectTunnel("ap-beijing",createDirectConnectTunnelRequest("ap-beijing"));//
		//接受专线通道申请
//		DcApiTest.acceptDirectConnectTunnel("ap-beijing",acceptDirectConnectTunnelRequest("dcx-14s81p2a"));
		//拒绝专线通道申请
//		DcApiTest.rejectDirectConnectTunnel("ap-beijing",rejectDirectConnectTunnelRequest("dcx-14s81p2a"));
		//查询专线通道列表 ,不传参数返回该账号下的所有专线通道列表
//		DcApiTest.describeDirectConnectTunnels("ap-beijing",describeDirectConnectTunnelsRequest());
		//修改专线通道属性
//		DcApi.modifyDirectConnectTunnelAttribute("ap-beijing", modifyDirectConnectTunnelAttributeRequest());
	}
	
	
	/*
	 * 创建专线通道申请
	 * 参数名称						必选	类型				描述
	 *	Action						是	String			公共参数，本接口取值：CreateDirectConnectTunnel
	 *	Version						是	String			公共参数，本接口取值：2018-04-10
	 *	Region						否	String			公共参数，本接口不需要传递此参数。
	 *	DirectConnectId				是	String			专线 ID，例如：dc-kd7d06of
	 *	DirectConnectTunnelName		是	String			专线通道名称
	 *	DirectConnectOwnerAccount	否	String			物理专线 owner，缺省为当前客户（物理专线 owner）
	 													共享专线时这里需要填写共享专线的开发商账号 ID
	 *	NetworkType					否	String			网络类型，分别为VPC、BMVPC
														VPC：私有网络
														BMVPC：黑石网络
	 *	NetworkRegion				否	String			网络地域
	 *	VpcId						否	String			私有网络统一 ID 或者黑石网络统一 ID
	 *	DirectConnectGatewayId		否	String			专线网关 ID，例如 dcg-d545ddf
	 *	Bandwidth					否	Integer			专线带宽，单位：Mbps
	 													默认是物理专线带宽值
	 *	RouteType					否	String			BGP ：BGP路由
												 		STATIC：静态
												 		默认为 BGP 路由
	 *	BgpPeer						否	BgpPeer			BgpPeer，用户侧bgp信息，包括asn和AuthKey
	 *	RouteFilterPrefixes.N		否	RouteFilterPrefix[]	静态路由，用户IDC的网段地址
	 *	Vlan						否	Integer			vlan，范围：0 ~ 3000
														0：不开启子接口
														默认值是非0
	 *	TencentAddress				否	String	TencentAddress，腾讯侧互联 IP
	 *	CustomerAddress				否	String	CustomerAddress，用户侧互联 IP
	 */
	public static CreateDirectConnectTunnelRequest createDirectConnectTunnelRequest(String reqion){
		CreateDirectConnectTunnelRequest  request = new CreateDirectConnectTunnelRequest();
        request.setDirectConnectId("dc-b7fuvfm5");
        request.setDirectConnectGatewayId("dcg-2kd3fm9y");
        request.setDirectConnectTunnelName("V3版本创建专线通道");
        request.setDirectConnectOwnerAccount("100002654722");
        request.setNetworkType("VPC");
        request.setNetworkRegion(reqion);
        request.setVpcId("vpc-efm85glc");
        request.setBandwidth(10);
        BgpPeer bp = new BgpPeer();
        bp.setAsn(9929);
        bp.setAuthKey("tencent");
        request.setBgpPeer(bp);
        request.setVlan(101);
        request.setTencentAddress("10.10.1.2/30");
        request.setCustomerAddress("10.10.1.1/30");
        return request;
	}
	
	/*
	 * 接受专线通道申请请求类
	 * directConnectTunnelRequest - 申请的专线通道ID
	 */
	public static AcceptDirectConnectTunnelRequest acceptDirectConnectTunnelRequest(String directConnectTunnelRequest){
		AcceptDirectConnectTunnelRequest request = new AcceptDirectConnectTunnelRequest();
        request.setDirectConnectTunnelId(directConnectTunnelRequest);
        return request;
	}
	
	/*
	 *  拒绝专线通道申请请求类
	 *  directConnectTunnelRequest - 申请的专线通道ID
	 */
	public static RejectDirectConnectTunnelRequest rejectDirectConnectTunnelRequest(String directConnectTunnelRequest){
		RejectDirectConnectTunnelRequest request = new RejectDirectConnectTunnelRequest();
        request.setDirectConnectTunnelId(directConnectTunnelRequest);
        return request;
	}
	
	/*
	 *  查询专线通道列表请求类
	 *  不传参默认获取账号的专线通道列表,可通过setDirectConnectTunnelIds去查询具体的专线通道
	 *  参数名称						必选	类型					描述
	 *	Action						是	String				公共参数，本接口取值：DescribeDirectConnectTunnels
	 *	Version						是	String				公共参数，本接口取值：2018-04-10
	 *	Region						否	String				公共参数，本接口不需要传递此参数。
	 *	Filters.N					否	Array of Filter		过滤条件:参数不支持同时指定DirectConnectTunnelIds和Filters。
														 	direct-connect-tunnel-name, 专线通道名称。
														 	direct-connect-tunnel-id, 专线通道实例ID，如dcx-abcdefgh。
														 	direct-connect-id, 物理专线实例ID，如，dc-abcdefgh。
	 *	DirectConnectTunnelIds.N	否	Array of String		专线通道 ID数组
	 *	Offset						否	Integer				偏移量，默认为0
	 *	Limit		
	 */
	public static DescribeDirectConnectTunnelsRequest describeDirectConnectTunnelsRequest(){
		DescribeDirectConnectTunnelsRequest request = new DescribeDirectConnectTunnelsRequest();
		//该方法设置过滤条件,可查询满足条件的专线通道
//		request.setDirectConnectTunnelIds(DirectConnectTunnelIds);
		return request;
	}
	
	/*
	 * 修改专线通道属性请求类
	 *  参数名称					必选	类型							描述
	 *	Action					是	String						公共参数，本接口取值：ModifyDirectConnectTunnelAttribute
	 *	Version					是	String						公共参数，本接口取值：2018-04-10
	 *	Region					否	String						公共参数，本接口不需要传递此参数。
	 *	DirectConnectTunnelId	是	String						专线通道ID
	 *	DirectConnectTunnelName	否	String						专线通道名称
	 *	BgpPeer					否	BgpPeer						用户侧BGP，包括Asn，AuthKey
	 *	RouteFilterPrefixes.N	否	Array of RouteFilterPrefix	用户侧网段地址
	 *	TencentAddress			否	String						腾讯侧互联IP
	 *	CustomerAddress			否	String						用户侧互联IP
	 *	Bandwidth				否	Integer						专线通道带宽值，单位为M。
	 */
	public static ModifyDirectConnectTunnelAttributeRequest modifyDirectConnectTunnelAttributeRequest(){
		ModifyDirectConnectTunnelAttributeRequest request = new ModifyDirectConnectTunnelAttributeRequest();
		request.setBandwidth(10);
		request.setDirectConnectTunnelId("dcx-14s81p2a");
		request.setDirectConnectTunnelName("V3修改专线通道名称");
//		request.setBgpPeer("");
//		request.setTencentAddress("");
//		request.setCustomerAddress("");
		return request;
	}
}
