package com.dxz.minademo2;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPServerHandler extends IoHandlerAdapter {
    // ��������ʹ�õ�SLF4J��Ϊ��־���棬����Ϊʲô�ں���˵����
    private final static Logger log = LoggerFactory.getLogger(TCPServerHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        System.out.println("The message received is [" + str + "]");
        if (str.endsWith("quit")) {
            session.close(true);
            return;
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("server session created");
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("server session Opened");
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("server session Closed");
        super.sessionClosed(session);
    }
    
}