package org.kriver.core.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import org.apache.log4j.Logger;

public class FileUtils {
	private final static Logger log = Logger.getLogger(FileUtils.class);
	
	/** 
     * the traditional io way  
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static byte[] toByteArray(String filename) throws IOException{  
          
        File f = new File(filename);  
        if(!f.exists()){  
            throw new FileNotFoundException(filename);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
        BufferedInputStream in = null;  
        try{  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while(-1 != (len = in.read(buffer,0,buf_size))){  
                bos.write(buffer,0,len);  
            }  
            return bos.toByteArray();  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                in.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  
    
    /** 
     * NIO way 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static byte[] toByteArray2(String filename)throws IOException{  
          
        File f = new File(filename);  
        if(!f.exists()){  
            throw new FileNotFoundException(filename);  
        }  
          
        FileChannel channel = null;  
        FileInputStream fs = null;  
        try{  
            fs = new FileInputStream(f);  
            channel = fs.getChannel();  
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());  
            while((channel.read(byteBuffer)) > 0){  
                // do nothing  
//              System.out.println("reading");  
            }  
            return byteBuffer.array();  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                channel.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
            try{  
                fs.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    /** 
     * Mapped File  way 
     * MappedByteBuffer 可以在处理大文件时，提升性能 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static byte[] toByteArrayByLargeFile(String filename)throws IOException{  
          
        FileChannel fc = null;  
        try{  
            fc = new RandomAccessFile(filename,"r").getChannel();  
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();  
            byte[] result = new byte[(int)fc.size()];  
            if (byteBuffer.remaining() > 0) {  
                byteBuffer.get(result, 0, byteBuffer.remaining());  
            }  
            return result;  
        }catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        }finally{  
            try{  
                fc.close();  
            }catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	/**
	 * 保存文件
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
    public static void SaveFileFromInputStream(InputStream stream,String path,String fileName) throws IOException
    {      
    	log.debug("SaveFileFromInputStream,path===="+path);
    	log.debug("SaveFileFromInputStream,fileName===="+fileName);
    	File pathfile = new File(path);
    	if(!pathfile.exists()){
    		if(!pathfile.mkdirs()){
    			throw new RuntimeException("mkdir error,path="+path);
    		}
    	}
    	String fullName = path+fileName;
    	File file = new File(fullName);
    	if(!file.exists()){
    		if(!file.createNewFile()){
    			throw new RuntimeException("create new file error,name=="+fullName);
    		}
    	}

        FileOutputStream fs = new FileOutputStream(fullName);
        byte[] buffer =new byte[1024*1024];
        int byteread = 0; 
        while ((byteread=stream.read(buffer))!=-1)
        {
           fs.write(buffer,0,byteread);
           fs.flush();
        } 
        fs.close();
        stream.close();      
    }      
	
    /**
     * 
     * @param bytes
     * @param path
     * @param fileName
     * @throws IOException
     */
    public static void SaveFileFromInputStream(byte[] bytes,String path,String fileName) throws IOException
    {      
    	log.debug("SaveFileFromInputStream,path===="+path);
    	log.debug("SaveFileFromInputStream,fileName===="+fileName);
    	File pathfile = new File(path);
    	if(!pathfile.exists()){
    		if(!pathfile.mkdirs()){
    			throw new RuntimeException("mkdir error,path="+path);
    		}
    	}
    	String fullName = path+fileName;
    	File file = new File(fullName);
    	if(!file.exists()){
    		if(!file.createNewFile()){
    			throw new RuntimeException("create new file error,name=="+fullName);
    		}
    	}

        FileOutputStream fs = new FileOutputStream(fullName);
        fs.write(bytes);
        fs.flush();
        fs.close();
    } 
    
    public static void main(String[] args) throws IOException {
    	File file = new File("D:\\work\\service\\guess-server\\guess-admin\\target\\guess-admin\\upload\\by_Activity_2013_07_29_16_56_20.xls");
    	file.createNewFile();
	}
}
