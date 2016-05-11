package cn.parabola.ooki.core.util;

import org.junit.Test;

public class PositionTest {
	
	
	@Test
	public void tests(){
		Integer[][] map = {{0,0},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},
						{1,0},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},
						{2,0},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},
						{3,0},{3,2},{3,3},{3,4},{3,5},{3,6},{3,7},
						{4,0},{4,2},{4,3},{4,4},{4,5},{4,6},{4,7},
						{5,0},{5,2},{5,3},{5,4},{5,5},{5,6},{5,7}};
		//2234,2223,2133,2333
		//int32 int32
		for(Integer[] array : map){
			System.out.println(String.format("x=%s,y=%s,value=%s",array[0],array[1],101 * array[0] + array[1]));
		}
		
	}
	
}
