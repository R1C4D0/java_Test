package test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class aaa {
    public static void main(String[] args) throws IOException {
        isLegalMagicSquare("C:\\Users\\Ricardo\\Desktop\\P1\\1.txt");
    }
    public static boolean isLegalMagicSquare(String fileName) throws IOException {
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("文件打开失败");
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(inStream);
        ArrayList<String[]> list = new ArrayList<>();

        while(scanner.hasNextLine())
            list.add(scanner.nextLine().split("\\t"));
        {
            int rowLength = 0,colLength =0;
            rowLength = list.get(0).length;
            colLength = list.size();
            if(colLength != rowLength)
            {
                System.out.println("This is not a ");
                return false;
            }
            long sum = 0;
            long sumRow = 0;
            //默认初始化为0
            long sumCol[] = new long[colLength];
            for (String i : list.get(0))
            {
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
                    System.out.println("行值不等");
                    return false;
                }
            }
            //检测每列的和

            for (long i : sumCol)
            {
                if (i != sum)
                {
                    System.out.println("列值不等");
                    return false;
                }
            }
        }
        System.out.println("This is LegalMagicSquare");
        inStream.close();
        return true;
    }
}

