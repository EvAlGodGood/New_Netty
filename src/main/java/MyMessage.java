import java.io.Serializable;

public class MyMessage implements Serializable {//суть в том что сюда падает кусочек файла т.е. перед отправкой файл
    //читается кусками и пишется из файлика на другом конце
    byte[] buf;
    int count = 0;
    int countR = 0;

    public int getCountR() {
        return countR;
    }

    public void setCountR(int countR) {
        this.countR = countR;
    }

    public void setCount(int count) { //номер кусочка (возможно понадобиться пусть будет)
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public byte[] getBuf() { //кусочек файла
        return buf;
    }

    public void setBuf(byte[] buf) {
        this.buf = buf;
    }


}