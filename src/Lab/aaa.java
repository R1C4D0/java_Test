package Lab;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
class NegativeException extends Exception{
    NegativeException(){
        super();
    }
    NegativeException(String str){
        super(str);
    }
}
public class aaa {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:\\Users\\Ricardo\\Desktop\\P1");
        File[] files = dir.listFiles();

        if (files != null)
        {
            Boolean bl;
            for (File file : files)
            {
                bl = isLegalMagicSquare(file.getAbsolutePath());
                if(bl)
                    System.out.println(file.getAbsolutePath()+": is LegalMagicSquare");
                else
                    System.out.println(file.getAbsolutePath()+": is not LegalMagicSquare");
            }
        }
        //isLegalMagicSquare("C:\\Users\\Ricardo\\Desktop\\P1\\1.txt");
    }
    public static boolean isLegalMagicSquare(String fileName) throws IOException {
        InputStream inStream;
        try {
            inStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(fileName+"File open failed");
            return false;
        }
        Scanner scanner = new Scanner(inStream);
        ArrayList<String[]> list = new ArrayList<>();

        while(scanner.hasNextLine())
            list.add(scanner.nextLine().split("\\t"));
        try{
            int rowLength = 0,colLength =0;
            rowLength = list.get(0).length;
            colLength = list.size();
            if(colLength != rowLength)
            {
                System.out.print("This is not a N x N matrix");
                return false;
            }
            long sum = 0;
            long sumRow = 0;
            //默认初始化为0
            long sumCol[] = new long[colLength];
            for (String i : list.get(0))
            {
                int tmp = Integer.parseInt(i);
                if (tmp < 0)
                {
                    throw new NegativeException(i);
                }
                sum += Integer.parseInt(i);
            }
            //计算每行和每列元素的和
            for (String[] line: list)
            {
                sumRow = 0;
                //临时存放值
                int num;
                for (int i = 0; i < line.length; i++)
                {
                    num = Integer.parseInt(line[i]);
                    sumRow += num;
                    sumCol[i] += num;
                }
                //检测行的和
                if (sum != sumRow)
                {
                    System.out.print("row is not equal");
                    return false;
                }
            }
            //检测每列的和

            for (long i : sumCol)
            {
                if (i != sum)
                {
                    System.out.print("col is not equal");
                    return false;
                }
            }
        }catch (NumberFormatException e){
            System.out.println("Exception thrown  :"+e);
            return false;
        } catch (NegativeException e)
        {
            System.out.println(e);
            return false;
        }
        inStream.close();
        return true;
    }
}
