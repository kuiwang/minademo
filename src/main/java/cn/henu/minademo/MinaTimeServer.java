package cn.henu.minademo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {

    private static final int PORT = 9090;//定义监听端口 

    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        //指定编码过滤器 
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

        //指定业务逻辑处理器 
        acceptor.setHandler(new TimeServerHandler());

        //设置端口号 
        acceptor.setDefaultLocalAddress(new InetSocketAddress(PORT));
        acceptor.bind();//启动监听 
        System.out.println("listening at 9090 now!");
    }
}
