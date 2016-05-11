package org.kriver.core.common;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

public class PoiUtils {
	public final static String CMD_END = "$end";
	
	public static int getInt(HSSFCell cell){
		return (int)Double.parseDouble(cell.toString());
	}
	public static int getInt(HSSFCell cell,int defaultValue){
		try{
			return (int)Double.parseDouble(cell.toString());
		}catch(Exception e){
			return defaultValue;
		}
	}
	public static Long getLong(HSSFCell cell){
		try{
			return (long)Double.parseDouble(cell.toString());
		}catch(Exception e){
			return null;
		}
	}
	public static double getDouble(HSSFCell cell){
		return Double.parseDouble(cell.toString());
	}
	public static double getDouble(HSSFCell cell,double defaultValue){
		try{
			return Double.parseDouble(cell.toString());
		}catch(Exception e){
			return defaultValue;
		}
	}
	public static String getString(HSSFCell cell){
		String str = cell.toString();
		if(StringUtils.isNotBlank(str) && StringUtils.endsWith(str, ".0")){
			str = StringUtils.remove(str, ".0");
		}
		return str;
	}
	public static String getString(HSSFCell cell,String defaultValue){
		return cell == null ? defaultValue : PoiUtils.getString(cell);
	}
	public static void writeCell(Cell cell,Object value){
		cell.setCellValue(value.toString());
	}
	public static CellStyle createStyleCell(Workbook wb){
		CellStyle style = wb.createCellStyle();
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		
		style.setRightBorderColor(IndexedColors.BLACK.getIndex());
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}
}
