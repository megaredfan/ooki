package cn.parabola.ooki.robot.core;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.RequestExpectContinue;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpProcessorBuilder;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;

@Service
public class TestGameFactory {

    private static String HTTP_POST = "POST";
    private static String PROTOCOL_URL = "/game.fun";
    
    public MessageBody send(byte[] content,Robot robot) throws Exception{
    	
    	HttpProcessor httpproc = HttpProcessorBuilder.create()
                .add(new RequestContent())
                .add(new RequestTargetHost())
                .add(new RequestConnControl())
                .add(new RequestExpectContinue()).build();

            HttpCoreContext coreContext = HttpCoreContext.create();
            HttpHost host = new HttpHost(robot.getIp(), robot.getPort());
            coreContext.setTargetHost(host);

            DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
           // ConnectionReuseStrategy connStrategy = DefaultConnectionReuseStrategy.INSTANCE;
            
    	 HttpEntity httpEntity = new ByteArrayEntity(content,ContentType.APPLICATION_OCTET_STREAM);
    	 
    	 try {

             if (!conn.isOpen()) {
                 Socket socket = new Socket(host.getHostName(), host.getPort());
                 conn.bind(socket);
             }
             BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(HTTP_POST,PROTOCOL_URL);
             request.setEntity(httpEntity);

             HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
             httpexecutor.preProcess(request, httpproc, coreContext);
             HttpResponse response = httpexecutor.execute(request, conn, coreContext);
             httpexecutor.postProcess(response, httpproc, coreContext);
             HttpEntity responseEntity = response.getEntity();
             if(responseEntity.getContentLength() == 0){
            	 System.out.println("response is null");
            	 return null;
             }
             MessageBody m = MessageBody.parseFrom(EntityUtils.toByteArray(responseEntity));
             System.out.println(m);
             return m;
     } finally {
         conn.close();
     }
    }

    
}
