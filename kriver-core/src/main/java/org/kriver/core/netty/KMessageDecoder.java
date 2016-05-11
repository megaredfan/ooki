package org.kriver.core.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.util.List;

public class KMessageDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
        if (in.readableBytes() < KMessage.HEAD_SIZE) {
            return;
        }

        in.markReaderIndex();

        int messageType = in.readShort();
        int messageCode = in.readShort();
        int magicNumber = in.readInt();
        if (!KMessage.validMagicNumber(messageType, magicNumber)) {
            in.resetReaderIndex();
            throw new CorruptedFrameException(
                    "Invalid magic number: " + magicNumber);
        }
        
        // Wait until the whole data is available.
       
        int dataLength = in.readInt() - KMessage.HEAD_SIZE;
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        // Convert the received data into a new BigInteger.
        byte[] decoded = new byte[dataLength];
        in.readBytes(decoded);
        KMessage m = new KMessage(messageType,messageCode,decoded);
        out.add(m);
	}

}
