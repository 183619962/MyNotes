/**
 * File: TimeClientHandler.java
 * Description: TODO
 * Copyright (c)  2014鏂颁笘绾�氬崱瀹�   All right reserved
 * @author: xb
 * @version: 1.0
 * @Date: 2015骞�6鏈�1鏃�
 */
package com.lpf.mynotes.net;

/**
 * @ClassName: TimeClientHandler 
 * @Description: TODO
 * @author: xb
 * @version: 1.0
 * @Date: 2015骞�6鏈�1鏃�
 */
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger
			.getLogger(TimeClientHandler.class.getName());

	private ByteBuf firstMessage;

	private FutureI fi;

	public TimeClientHandler(FutureI fi, byte[] bt) {
		this.fi = fi;
		firstMessage = Unpooled.buffer(bt.length);
		firstMessage.writeBytes(bt);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// 涓庢湇鍔＄寤虹珛杩炴帴鍚�
		System.out.println("client channelActive..");
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("client channelRead..");
		// 鏈嶅姟绔繑鍥炴秷鎭悗
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		final byte[] keyBytes = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10,
				0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD,
				0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36,
				(byte) 0xE2 };
		req = ThreeDES.decryptMode(keyBytes, req);
		String body = new String(req, "UTF-8");
		System.out.println("len=" + req.length + "|Now is :" + body);

		ctx.channel().close();
		ctx.channel().closeFuture().channel().close();
		ctx.close();
		ctx.disconnect();
		ctx = null;

		fi.recall(body);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("client exceptionCaught..");
		// 閲婃斁璧勬簮
		logger.warning("Unexpected exception from downstream:"
				+ cause.getMessage());
		ctx.close();
	}
}