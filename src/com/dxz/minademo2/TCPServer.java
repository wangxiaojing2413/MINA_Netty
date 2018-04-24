package com.dxz.minademo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LogLevel;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        LoggingFilter lf = new LoggingFilter();    
        lf.setSessionOpenedLogLevel(LogLevel.ERROR);    
        acceptor.getFilterChain().addLast("logger", lf);
        

        // ��д������
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(), 
                        LineDelimiter.WINDOWS.getValue()))
                );
        
        //����handler
        acceptor.setHandler(new TCPServerHandler());
        
        //�󶨶˿�
        acceptor.bind(new InetSocketAddress(9124));
    }
}