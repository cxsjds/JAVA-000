package nio02.m.ma.ko.io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

//HttpRequestFilter接口
public interface HttpRequestFilter {

    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
    
}
