package im.actor.model.droidkit.bser;

import java.io.IOException;

/**
 * Created by ex3ndr on 07.02.15.
 */
public class DataOutput {
    private byte[] data = new byte[16];
    private int offset;

    public DataOutput() {

    }

    private void expand(int size) {
        byte[] nData = new byte[size];
        for (int i = 0; i < offset; i++) {
            nData[i] = data[i];
        }
        data = nData;
    }

    public void writeLong(long v) {
        if (data.length <= offset + 8) {
            expand(offset + 8);
        }

        data[offset++] = (byte) ((v >> 56) & 0xFF);
        data[offset++] = (byte) ((v >> 48) & 0xFF);
        data[offset++] = (byte) ((v >> 40) & 0xFF);
        data[offset++] = (byte) ((v >> 32) & 0xFF);
        data[offset++] = (byte) ((v >> 24) & 0xFF);
        data[offset++] = (byte) ((v >> 16) & 0xFF);
        data[offset++] = (byte) ((v >> 8) & 0xFF);
        data[offset++] = (byte) (v & 0xFF);
    }

    public void writeInt(int v) {
        if (data.length <= offset + 4) {
            expand(offset + 4);
        }

        data[offset++] = (byte) ((v >> 24) & 0xFF);
        data[offset++] = (byte) ((v >> 16) & 0xFF);
        data[offset++] = (byte) ((v >> 8) & 0xFF);
        data[offset++] = (byte) (v & 0xFF);
    }

    public void writeByte(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("Value can't be negative");
        }
        if (v > 255) {
            throw new IllegalArgumentException("Value can't be more than 255");
        }
        if (data.length <= offset + 1) {
            expand(offset + 1);
        }
        data[offset++] = (byte) v;
    }

    public void writeVarInt(long v) {
        while ((v & 0xffffffffffffff80l) != 0l) {
            writeByte((int) ((v & 0x7f) | 0x80));
            v >>>= 7;
        }

        writeByte((int) (v & 0x7f));
    }

    public void writeProtoBytes(byte[] v, int ofs, int len) {
        writeVarInt(len);
        writeBytes(v, ofs, len);
    }

    public void writeBytes(byte[] v, int ofs, int len) {
        if (len > Limits.MAX_BLOCK_SIZE) {
            throw new IllegalArgumentException("Unable to write more than 1 MB");
        }
        if (len < 0) {
            throw new IllegalArgumentException("Length can't be negative");
        }
        if (ofs < 0) {
            throw new IllegalArgumentException("Offset can't be negative");
        }
        if (ofs + len > v.length) {
            throw new IllegalArgumentException("Inconsistent sizes");
        }

        if (data.length < offset + v.length) {
            expand(offset + v.length);
        }
        for (int i = 0; i < len; i++) {
            data[offset++] = v[i + ofs];
        }
    }

    public void writeProtoLongs(long[] values) throws IOException {
        if (values.length > Limits.MAX_PROTO_REPEATED) {
            throw new IllegalArgumentException("Values can't be more than " + Limits.MAX_PROTO_REPEATED);
        }
        writeVarInt(values.length);
        for (long l : values) {
            writeLong(l);
        }
    }

    public void writeProtoString(String value) throws IOException {
        byte[] data = value.getBytes("UTF-8");
        writeProtoBytes(data, 0, data.length);
    }

    public void writeProtoBool(boolean v) throws IOException {
        writeByte(v ? 1 : 0);
    }

    public byte[] toByteArray() {
        byte[] res = new byte[offset];
        for (int i = 0; i < offset; i++) {
            res[i] = data[i];
        }
        return res;
    }

    public void writeASN1Length(int length) {
        if (length > 127) {
            int size = 1;
            int val = length;

            while ((val >>>= 8) != 0) {
                size++;
            }

            writeByte((size | 0x80) & 0xFF);

            for (int i = (size - 1) * 8; i >= 0; i -= 8) {
                writeByte((length >> i) & 0xFF);
            }
        } else {
            writeByte(length & 0xFF);
        }
    }
}
