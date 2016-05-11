package org.kriver.core.common;

import org.junit.Assert;
import org.junit.Test;

public class SecretManagerTest {
	SecretManager sm = new SecretManager("qwertyuiopasdfgh");
	
	//@Test
	public void test(){
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try{
					String source = "lichuan";
					String enStr = sm.encode(source);
					System.out.println(enStr);
					String deStr = sm.decode(enStr);
					System.out.println(deStr);
					Assert.assertEquals(source,deStr);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		};
		long s = System.currentTimeMillis();
		for(int i = 0;i<1;i++){
			new Thread(run).start();
		}
		System.out.println(System.currentTimeMillis()- s);
	}

}
