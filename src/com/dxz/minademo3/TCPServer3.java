package com.dxz.minademo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.dxz.minademo2.TCPServerHandler;

public class TCPServer3 {

    public static void main(String[] args) throws IOException {

        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        // 编写过滤器

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new CmccSipcCodecFactory(Charset.forName("UTF-8"))));
        

        // 设置handler
        acceptor.getFilterChain().addLast("myIoFilter", new ReferenceCountingFilter(new MyIoFilter()));

        // 设置handler
        acceptor.setHandler(new TCPServerHandler());
        
        // 绑定端口
        acceptor.bind(new InetSocketAddress(9124));

        System.out.println(acceptor.getSessionConfig());
    }
}