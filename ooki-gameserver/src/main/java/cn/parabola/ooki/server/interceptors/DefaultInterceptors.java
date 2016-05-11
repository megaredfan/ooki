package cn.parabola.ooki.server.interceptors;
//test
import cn.parabola.ooki.server.servlet.GameServlet;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultInterceptors extends HandlerInterceptorAdapter{
	
	private final static Logger log = Logger.getLogger(DefaultInterceptors.class);
	private Configuration config;
	private String allowIp;
	public DefaultInterceptors(){
		try{
			config = new PropertiesConfiguration("conf.properties");
			allowIp = config.getString("ip");
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ip = request.getHeader("X-Real-IP");
		if(StringUtils.isBlank(allowIp)){
			return true;
		}
		if(GameServlet.getDebug()){
			boolean a = allowIp.equals(ip);
			return a;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
