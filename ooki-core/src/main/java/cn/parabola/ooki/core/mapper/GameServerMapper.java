package cn.parabola.ooki.core.mapper;

import java.util.Map;

public interface GameServerMapper {
	String getConfig(String key);
	int updateConfig(Map<String, Object> args);
	void insertConfig(Map<String, Object> args);
}
