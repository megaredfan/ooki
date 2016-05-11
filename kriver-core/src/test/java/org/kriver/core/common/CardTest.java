package org.kriver.core.common;

import org.apache.log4j.Logger;
import org.junit.Test;

public class CardTest {
	private final static Logger logger = Logger.getLogger(CardTest.class);
	
	//@Test
	public void test(){
		String path = CardTest.class.getResource("/").getPath();
		logger.debug("path======"+path);
		String source = path + "image/test.png";
		String target = path + "image/test1.png";
		ImageUtils.scale(source, target, 2, true);
	}
}
