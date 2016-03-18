package https;
 
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;
 
import javax.net.ssl.SSLEngine;
 
public class HttpDemoServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = ch.pipeline();
 
        if (HttpDemoServer.isSSL) {
            SSLEngine engine = SecureChatSslContextFactory.getServerContext().createSSLEngine();
            engine.setNeedClientAuth(true); //ssl˫����֤
            engine.setUseClientMode(false);
            engine.setWantClientAuth(true);
            engine.setEnabledProtocols(new String[]{"SSLv3"});
            pipeline.addLast("ssl", new SslHandler(engine));
        }
 
        /**
         * http-request������
         * http�������˶�request����
         */
        pipeline.addLast("decoder", new HttpRequestDecoder());
        /**
         * http-response������
         * http�������˶�response����
         */
        pipeline.addLast("encoder", new HttpResponseEncoder());
 
        pipeline.addLast("aggregator", new HttpObjectAggregator(1048576));
        /**
         * ѹ��
         * Compresses an HttpMessage and an HttpContent in gzip or deflate encoding
         * while respecting the "Accept-Encoding" header.
         * If there is no matching encoding, no compression is done.
         */
        pipeline.addLast("deflater", new HttpContentCompressor());
 
        pipeline.addLast("handler", new HttpDemoServerHandler());
    }
}