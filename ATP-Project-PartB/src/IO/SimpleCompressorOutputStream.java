package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {
    private final OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out){
        this.out = out;

    }

    public void write(int b){

    }

    public void write(byte[] bytes) throws IOException {
        ArrayList<Byte> byteArrayList = new ArrayList<Byte>();
        int index = 0;
        if (bytes.length == 0) {
            return;
        }
        while (index < 12) {
            byteArrayList.add(bytes[index]);
            index++;
        }
        byte currentByte = bytes[12];
        int count = 1;
        for (int i = 13; i < bytes.length; i++) {
            if (bytes[i] == currentByte) {
                count++;
            } else {
                currentByte = bytes[i];
                byteArrayList.add((byte) count);
                count = 1;
            }
        }
        try {
            for (int i = 0; i < byteArrayList.size(); i++) {
                int b = byteArrayList.get(i);
                while (b > 255) {
                    out.write(255);
                    out.write(0);
                    b = b - 255;
                }
                out.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws IOException {
        out.close();
    }

}
