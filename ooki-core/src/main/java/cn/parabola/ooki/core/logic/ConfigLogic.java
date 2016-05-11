package cn.parabola.ooki.core.logic;

import cn.parabola.ooki.core.auto.GameResProtos.GameResource;
import cn.parabola.ooki.core.auto.GameResProtos.GlobalConfig;
import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConfigLogic {
	private final static Logger log = Logger.getLogger(ConfigLogic.class);
	
	private String configVersion;
	private GameResource gameResource;
	private boolean loaded = false;
	private GlobalConfig globalConfig;

	public GlobalConfig getGlobalConfig() {
		return this.globalConfig;
	}
	public boolean isLoaded(){
		return loaded;
	}
	private void check(){
		if(!loaded){
			throw new RuntimeException("ConfigLogic not load data");
		}
	}
	
	public boolean loadConfig(String path){
		try{
			if(!loaded){
				Resource resource = new FileSystemResource(path);
				gameResource = GameResource.parseFrom(resource.getInputStream());
				init();
				loaded = true;
			}
		}catch(IOException e){
			loaded = false;
			throw new RuntimeException("load config error");
		}
		return loaded;
	}


	private void init(){
		globalConfig = gameResource.getGlobalConfig();
		configVersion = gameResource.getVersion();
	}
	public static void main(String[] args) {
		System.out.println(137573171226L % 12);
	}
}

