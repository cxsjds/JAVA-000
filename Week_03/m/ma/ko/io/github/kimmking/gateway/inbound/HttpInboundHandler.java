package nio02.m.ma.ko.io.github.kimmking.gateway.inbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import nio02.m.ma.ko.io.github.kimmking.gateway.filter.HttpRequestFilterHandler;
import nio02.m.ma.ko.io.github.kimmking.gateway.outbound.httpclient4.HttpOutboundHandler;
import nio02.m.ma.ko.io.github.kimmking.gateway.router.HttpEndpointRouterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *使用HttpEndpointRouterHandler实现路由
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    private HttpOutboundHandler handler;
    private HttpRequestFilterHandler filterHandler;
    private HttpEndpointRouterHandler routerHandler;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.filterHandler = new HttpRequestFilterHandler();
        this.routerHandler = new HttpEndpointRouterHandler();
        handler = new HttpOutboundHandler(this.proxyServer);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            // 使用过滤器来给header里面添加一些信息
            filterHandler.filter(fullRequest, ctx);
            // 实现简单的路由
            String routeTo = routerHandler.route(Arrays.asList(fullRequest.uri().split("/")));
            fullRequest.setUri(routeTo);
            handler.handle(fullRequest, ctx);
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
