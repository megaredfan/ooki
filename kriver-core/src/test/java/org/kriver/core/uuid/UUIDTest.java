package org.kriver.core.uuid;

import org.junit.Test;

public class UUIDTest {
	
	//@Test
	public void test(){
		UUID64 uuid = UUID64.buildDefaultUUID(1, 1, 1, 0);
		long oid1 = uuid.getNextUUID();
		
		System.out.println(oid1);
		System.out.println(uuid.getOid(oid1));
		
		UUID64 u2 = UUID64.buildDefaultUUID(1, 1, 1, uuid.getOid(oid1));
		long oid2 = u2.getNextUUID();
		System.out.println(oid2);
		System.out.println(uuid.getOid(oid2));
		
		UUID64 u3 = UUID64.buildDefaultUUID(1, 1, 1, uuid.getOid(oid2));
		System.out.println(u3.getNextUUID());
		
		
	}

}
