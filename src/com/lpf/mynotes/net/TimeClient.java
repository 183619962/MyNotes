/**
 * File: TimeClient.java
 * Description: TODO
 * Copyright (c)  2014鏂颁笘绾�氬崱瀹�   All right reserved
 * @author: xb
 * @version: 1.0
 * @Date: 2015骞�6鏈�1鏃�
 */
package com.lpf.mynotes.net;

/**
 * @ClassName: TimeClient 
 * @Description: TODO
 * @author: xb
 * @version: 1.0
 * @Date: 2015骞�6鏈�1鏃�
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ObjectEncoder;

import org.json.JSONObject;

public class TimeClient
{
	
	public void connect(int port, String host, final FutureI fi, final byte[] bt) throws Exception
	{
		// 閰嶇疆瀹㈡埛绔疦IO绾跨▼缁�
		EventLoopGroup group = new NioEventLoopGroup();
		try
		{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>()
			{
				@Override
				protected void initChannel(SocketChannel channel) throws Exception
				{
					System.out.println("client initChannel..");
					channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
					channel.pipeline().addLast(new ObjectEncoder());
					channel.pipeline().addLast("encoder", new LengthFieldPrepender(4, false));

					channel.pipeline().addLast(new TimeClientHandler(fi, bt));
				}
			});
			// 鍙戣捣寮傛杩炴帴鎿嶄綔
			ChannelFuture f = b.connect(host, port).sync();
			
			//f.channel().writeAndFlush(msg)
			// 绛夊緟瀹㈡埛绔摼璺叧闂�
			f.channel().closeFuture().sync();
		}
		finally
		{
			// 浼橀泤閫�鍑猴紝閲婃斁NIO绾跨▼缁�
			group.shutdownGracefully();
		}
	}

	/**
	 * 
	 * @Title: main
	 * @author: xb
	 * @Description: TODO
	 * @param: @param args
	 * @param: @throws Exception
	 * @return: void
	 * @throws:
	 */
	public static void main(String[] args) throws Exception
	{
		int port = 9000;
		if (args != null && args.length > 0)
		{
			try
			{
				port = Integer.parseInt(args[0]);
			}
			catch (Exception e)
			{
			}
		}
		String str = "{\"REQ_CODE\":1000,\"ENCRY_FLAG\":1,\"APP_MODE\":\"U\",\"PROTOCOL_VER\":\"0.1\",\"IS_TEST\":\"0\",\"UUID\":\"68bd544f-a803-4fa9-8705-996a92e21fa1\",\"MAC_NO\":\"F0:25:B7:EC:64:1B\",\"DEV_VER\":\"GT-I9507V5.0.1\",\"USERNAME\":\"13288888888\",\"UID\":\"e2882112-311b-11e5-902b-d43d7ee427da\",\"SID\":\"e2882112-311b-11e5-902b-d43d7ee427da\",\"DATA\":{\"DESKEY\":\"00000000000000000000000000000000\"}}";

		JSONObject json = new JSONObject(str);
		
		//new TimeClient().connect(10004, "192.168.1.5", json.toString().getBytes());
	}
}