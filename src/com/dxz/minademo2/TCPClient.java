package com.dxz.minademo2;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class TCPClient {

    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(), 
                        LineDelimiter.WINDOWS.getValue())));
        
        connector.setHandler(new TCPClientHandler("��ã�\r\n ��Һã�"));    
        
        connector.connect(new InetSocketAddress("localhost", 9124));
        
        System.out.println("�����첽ִ�У�");
        //connector.dispose();

    }

}