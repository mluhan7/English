package util;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;



public class ReadPdf {

    public static void readPdfToTxt(String pdfPath,String txtfilePath) {

        // 读取pdf所使用的输出流

        PrintWriter writer = null;

        PdfReader reader = null;

        try {

            writer = new PrintWriter(new FileOutputStream(txtfilePath));

            reader = new PdfReader(pdfPath);

            int num = reader.getNumberOfPages();// 获得页数

            System.out.println("Total Page: " + num);

            String content = ""; // 存放读取出的文档内容

            for (int i = 1; i <= num; i++) {

                // 读取第i页的文档内容

                content += PdfTextExtractor.getTextFromPage(reader, i);

            }

            String[] arr = content.split("/n");

            for(int i=0;i<arr.length;i++){

                System.out.println(arr[i]);

            /*String[] childArr = arr[i].split(" ");

            for(int j=0;j<childArr.length;j++){

                System.out.println(childArr[j]);

            }*/

            }



            //System.out.println(content);



            writer.write(content);// 写入文件内容

            writer.flush();

            writer.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }



    /**

     * 读取pdf内容

     * @param pdfPath

     */

    public static String readPdfToTxt(String pdfPath) {

        PdfReader reader = null;

        StringBuffer buff = new StringBuffer();

        try {

            reader = new PdfReader(pdfPath);

            PdfReaderContentParser parser = new PdfReaderContentParser(reader);

            int num = reader.getNumberOfPages();// 获得页数

            TextExtractionStrategy strategy;

            for (int i = 1; i <= num; i++) {

                strategy = parser.processContent(i,

                        new SimpleTextExtractionStrategy());

                buff.append(strategy.getResultantText());

            }

        } catch (IOException e) {

            e.printStackTrace();

        }



        return buff.toString();

    }

}