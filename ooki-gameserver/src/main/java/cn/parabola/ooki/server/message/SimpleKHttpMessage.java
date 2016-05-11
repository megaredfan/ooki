package cn.parabola.ooki.server.message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import cn.parabola.ooki.core.auto.GameProtos;
import cn.parabola.ooki.core.auto.GameProtos.MessageBody;

public class SimpleKHttpMessage {
	private final static Logger log = Logger.getLogger(SimpleKHttpMessage.class);

	private InputStream inputStream;
	private OutputStream outputStream;
	private int code;//如果code != 1代表序列化失败，数据包不完整或非法
	private int reservedKey;
	private MessageBody messageBody;
	private String ip;
	private int bodyLength;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(MessageBody messageBody) {
		this.messageBody = messageBody;
	}

	public SimpleKHttpMessage(byte[] bs) {
		try {
			if(bs == null || bs.length == 0){
				throw new IllegalArgumentException("bs is null or length <=0");
			}
			this.messageBody = MessageBody.parseFrom(bs);
		} catch (Exception e) {
			log.error("serializable error", e);
		}
	}

	public SimpleKHttpMessage(InputStream inputStream, OutputStream outputStream,
			String ip, int bodyLength) throws IOException {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.ip = ip;
		this.bodyLength = bodyLength;
		
		try {
			
			byte[] buff = new byte[bodyLength];
			int readCount = 0;
			while(readCount < bodyLength){
				readCount += inputStream.read(buff, readCount, bodyLength - readCount); 
			}
			messageBody = GameProtos.MessageBody.parseFrom(buff);
			this.code = 1;
			log.debug(this.toString());
		} catch (Exception e) {
			log.error("sync client inputstream error",
					e);
			this.code = 0;
		}
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "SimpleKHttpMessage [inputStream=" + inputStream
				+ ", outputStream=" + outputStream + ", reservedKey="
				+ reservedKey + ", messageBody=" + messageBody + ", ip=" + ip
				+ ", bodyLength=" + bodyLength + "]";
	}
}
