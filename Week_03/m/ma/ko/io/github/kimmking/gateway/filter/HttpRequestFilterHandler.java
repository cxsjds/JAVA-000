  
package nio02.m.ma.ko.io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class HttpRequestFilterHandler implements HttpRequestFilter{
 public final static String NIO_KEY = "NIO";
 
 //Override
 public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx){
  if(null != fullRequest && null != fullRequest.headers()){
    HttpHeaders httpHeaders = fullRequest.headers();
    httpHeaders.set(NIO_KEY, "VIP 111");
  }
 }
   
}
