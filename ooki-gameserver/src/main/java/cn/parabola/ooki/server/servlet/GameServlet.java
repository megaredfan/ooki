package cn.parabola.ooki.server.servlet;

import cn.parabola.ooki.core.auto.GameProtos.MessageBody;
import cn.parabola.ooki.server.logic.HttpMessageManager;
import cn.parabola.ooki.server.message.KHttpMessageContext;
import cn.parabola.ooki.server.message.SimpleKHttpMessage;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GameServlet {
	
//	@Resource(name = "logManager")
//	private LogManager logManager;
	private final static Logger log = Logger.getLogger(GameServlet.class);
	private static boolean debug = true;
	
	public static boolean getDebug(){
		return debug;
	}
	
	@RequestMapping("/debug.fun")
	public void setDebug(HttpServletRequest request, HttpServletResponse response){
		String debugStr = request.getParameter("debug");
		debug = Integer.parseInt(debugStr) == 0 ?false :true;
	}
	
	@PostConstruct
	public void init(){
		try{
			debug =  new PropertiesConfiguration("conf.properties").getBoolean("debug");
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}
	@Resource(name = "httpMessageManager")
	private HttpMessageManager httpMessageManager;
	
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/game.fun")
	public void initGame(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int bodyLength = request.getContentLength();
		if(bodyLength == -1){
			response.sendRedirect("http://www.baidu.com");
			return;
		}
		String ip = request.getHeader("X-Real-IP");
		SimpleKHttpMessage message = new SimpleKHttpMessage(request.getInputStream(),response.getOutputStream(),ip,bodyLength);
		if(message.getCode() != 1){
			log.error("syn error close this request");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getOutputStream().flush();;
			return;
		}
		KHttpMessageContext context = new KHttpMessageContext(message);

		HttpMessageManager.setKHttpMessageContext(context);
		try{
			MessageBody mb = context.getSimpleKHttpMessage().getMessageBody();
			long startTime = System.currentTimeMillis();
			httpMessageManager.handler(mb);
			log.info("end handl message type : " + mb.getMessageType() +" use time: "+ (System.currentTimeMillis()-startTime));
		}catch(Exception e){
			log.error(e.getMessage(), e);
//				MessageContent.Builder builder = HttpMessageManager.getMessageContentBuilder();
//				builder.setMessageType(mc.getMessageType());
//				builder.setMessageCode(MessageCode.Err_Not_Known);
//				httpMessageManager.send(builder);
		}
	}
	
	@RequestMapping("/config.fun")
	public void reloadConfig(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	}
	
	public static void main(String[] args) {
		System.out.println(137573173209L % 12);
	}
}
