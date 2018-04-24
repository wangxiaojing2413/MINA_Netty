package com.dxz.minademo3;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CmccSipcEncoder extends ProtocolEncoderAdapter {
	private final Charset charset;

	public CmccSipcEncoder(Charset charset) {
		this.charset = charset;
	}

	@Override
	/**
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		SmsObject sms = (SmsObject) message;
		CharsetEncoder ce = charset.newEncoder();
		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
		String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";
		String sender = sms.getSender();
		String receiver = sms.getReceiver();
		String smsContent = sms.getMessage();
		buffer.putString(statusLine + '\n', ce);
		buffer.putString("S: " + sender + '\n', ce);
		buffer.putString("R: " + receiver + '\n', ce);
		buffer.putString("L: " + (smsContent.getBytes(charset).length) + "\n", ce);
		buffer.putString(smsContent, ce);
		buffer.flip();
		out.write(buffer);
	}
	**/

	public void encode(IoSession session, Object message,    
    		ProtocolEncoderOutput out) throws Exception {    
    		//SmsObject sms = (SmsObject) message;    
    		CharsetEncoder ce = charset.newEncoder();    
    		String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";    
    		String sender = "15801012253";    
    		String receiver = "15866332698";    
    		String smsContent = "ÄãºÃ£¡Hello World!";    
    		IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);    
    		buffer.putString(statusLine + '\n', ce);    
    		buffer.putString("S: " + sender + '\n', ce);    
    		buffer.putString("R: " + receiver + '\n', ce);    
    		buffer.flip();    
    		out.write(buffer);    
    		IoBuffer buffer2 = IoBuffer.allocate(100).setAutoExpand(true);    
    		buffer2.putString("L: " + (smsContent.getBytes(charset).length)    
    		+ "\n",ce);    
    		buffer2.putString(smsContent, ce);    
    		buffer2.putString(statusLine + '\n', ce);    
    		buffer2.flip();    
    		out.write(buffer2);    
    		IoBuffer buffer3 = IoBuffer.allocate(100).setAutoExpand(true);    
    		buffer3.putString("S: " + sender + '\n', ce);    
    		buffer3.putString("R: " + receiver + '\n', ce);    
    		buffer3.putString("L: " + (smsContent.getBytes(charset).length)    
    		+ "\n",ce);    
    		buffer3.putString(smsContent, ce);    
    		buffer3.putString(statusLine + '\n', ce);    
    		buffer3.flip();    
    		out.write(buffer3);    
    		}

	private final AttributeKey CONTEXT = new AttributeKey(getClass(), "context");

	public Context getContext(IoSession session) {
		Context ctx = (Context) session.getAttribute(CONTEXT);
		if (ctx == null) {
			ctx = new Context();
			session.setAttribute(CONTEXT, ctx);
		}
		return ctx;
	}

	private class Context {
		// ×´Ì¬±äÁ¿
	}
}