import Part3.CountWord;
import Part4.Sort;
import Part5.ExceedWords;
import Part9.AutoTranslate;
import util.ReadDoc;
import util.ReadFileUtil;
import util.ReadPdf;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.List;

public class UI extends JFrame{
    File file = null;
    String article = "";
    String[] split = null;
    String[] lema = null;
    String result = null;
    String myText = null;
    Map<String, Integer> Countmap = new HashMap<String, Integer>();
    String vocabulary = "cet4";
    JPanel p1, p2, p3;
    String[] str = {"统计", "词频升序", "词频降序", "字母升序", "字母降序", "选择超纲词", "变化形式", "提取单词", "总词汇数", "相同词汇数"};
    JTextField singleWord, path;
    JButton wordSum, look, clear;
    JTextArea text;
    JScrollPane jsp;
    JList js;
    JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenuItem item1;
    JMenuItem item2;
    JMenu item4;
    JMenu item5;
    JMenuItem item6;
    JMenuItem item7;
    JMenuItem item8;
    JMenuItem item9;
    JMenuItem item10;
    JMenuItem item11;
    JMenuItem item12;
    JMenuItem item13;
    JMenuItem item14;
    JMenuItem item15;
    JMenuItem bg1, bg2, bg3, bg4, bg5;
    JPopupMenu popupMenu = null;
    JPopupMenu selectWord = null;
    JPopupMenu getWord = null;

    //打开一个文件
    public void openFile() {
        FileDialog fileDialog = new FileDialog(this, "打开", FileDialog.LOAD);
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null)
            file = new File(fileDialog.getDirectory() + fileDialog.getFile());

        System.out.println(file.getAbsolutePath());
        path.setText(file.getAbsolutePath());
        try {
            if (file != null) {
                /*FileInputStream in = new FileInputStream(file);
                InputStreamReader ipr = new InputStreamReader(in,"GBK");
                BufferedReader bf = new BufferedReader(ipr);
                String str="";
                while ((str=bf.readLine())!=null){
                    text.append(str+"\n");
                    //text.setText(text.getText()+str+"\n");
                }*/
                if (file.getAbsolutePath().lastIndexOf(".doc") > 0) {
                    text.setText(ReadDoc.readWord(file.getAbsolutePath()));
                    article = ReadDoc.readWord(file.getAbsolutePath());
                }
                if (file.getAbsolutePath().lastIndexOf(".txt") > 0) {
                    text.setText(ReadFileUtil.readFile(file.getAbsolutePath()));
                    article = ReadFileUtil.readFile(file.getAbsolutePath());
                }
                if (file.getAbsolutePath().lastIndexOf(".pdf") > 0) {
                    text.setText(ReadPdf.readPdfToTxt(file.getAbsolutePath()));
                    article = ReadPdf.readPdfToTxt(file.getAbsolutePath());
                }
                List<String> list = NPL.split(article);

                split = NPL.split(article).toArray(new String[split.length]);
                lema = NPL.getlema(article).toArray(new String[split.length]);

            }
        } catch (FileNotFoundException e) {
            System.out.println("打开文件失败");
        } catch (IOException e) {
            System.out.println("打开文件失败");
        }
    };

    //另存文件为
    public void saveFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.showSaveDialog(jfc);
        file = jfc.getSelectedFile();
        if (file != null) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        OutputStreamWriter out = null;
        if (file != null) {
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            out = new OutputStreamWriter(fos, "GBK");

            out.write(text.getText());
            out.flush();
            out.close();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    };
    // 清空路径
    public void clearPath(){
        path.getText();
        path.setText("");
        text.setText("");
    };
    public static void main(String args[]) {
        UI demo = new UI();
    }

    static int time1=0;
    static int count=0;
    String path1="";

    public UI() {
        init();
        this.setTitle("英文单词词频统计");
        this.setSize(1000,800);
        this.setVisible(true);
    }
    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(250, 20);
        text = new JTextArea();
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(600,40));
        p1.setLayout(new FlowLayout(1, 5, 5));
        p2 = new JPanel();
        p3 = new JPanel();
        wordSum = new JButton("统计此单词数目");
        look = new JButton("查看路径文档内容");
        clear = new JButton("清空");
        singleWord = new JTextField();
        singleWord.setPreferredSize(new Dimension(100, 30));
        path = new JTextField();
        path.setPreferredSize(new Dimension(500, 30));
        js = new JList(str);
        text = new JTextArea(42, 80);
        text.setLineWrap(true);
        text.setBackground(Color.PINK);//为了看出效果，设置了颜色
        jsp = new JScrollPane(text);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//始终显示垂直滚动条
        //jsp.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //添加各个组件
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.WEST);
        this.add(p3, BorderLayout.CENTER);
        p1.add(singleWord);
        p1.add(wordSum);
        p1.add(path);
        p1.add(clear);
        p1.add(look);
        p2.add(js);
        p3.add(jsp);
        //设置布局
        //创建菜单
        JMenuBar jmb = new JMenuBar();
        //不能设定位置，会自动放在最上部
        this.setJMenuBar(jmb);
        //添加菜单
        menu1 = new JMenu("File");
        menu2 = new JMenu("Edit");
        menu3 = new JMenu("Help");
        item1 = new JMenuItem("Open");
        item2 = new JMenuItem("Save");
        item4 = new JMenu("Color");
        item5 = new JMenu("Font&Size");
        //item6 = new JMenuItem("Size");
        item7 = new JMenuItem("About");
        bg1 = new JMenuItem("蓝色");
        bg2 = new JMenuItem("白色");
        bg3 = new JMenuItem("绿色");
        bg4 = new JMenuItem("粉色");
        bg5 = new JMenuItem("紫色");
        item8 = new JMenuItem("宋体&14px");
        item9 = new JMenuItem("宋体&16px");
        item10 = new JMenuItem("隶书&14px");
        item11 = new JMenuItem("隶书&16px");
        item12 = new JMenuItem("微软雅黑&14px");
        item13 = new JMenuItem("微软雅黑&16px");
        item14 = new JMenuItem("方正舒体&14px");
        item15 = new JMenuItem("方正舒体&16px");
        //添加菜单项至菜单
        menu1.add(item1);
        menu1.add(item2);
        menu2.add(item4);
        menu2.add(item5);
        menu3.add(item7);

        item4.add(bg1);
        item4.add(bg2);
        item4.add(bg3);
        item4.add(bg4);
        item4.add(bg5);
        item5.add(item8);
        item5.add(item9);
        item5.add(item10);
        item5.add(item11);
        item5.add(item12);
        item5.add(item13);
        item5.add(item14);
        item5.add(item15);
        //将菜单加入至菜单条
        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);

        //弹出框
        popupMenu = new JPopupMenu();
        selectWord = new JPopupMenu();
        getWord = new JPopupMenu();
        JMenuItem first = new JMenuItem("选择第一个文件");
        JMenuItem second = new JMenuItem("选择第二个文件");
        JMenuItem four = new JMenuItem("选择四级词汇");
        JMenuItem six = new JMenuItem("选择六级词汇");
        JMenuItem anyLength = new JMenuItem("提取某个长度的词");
        JMenuItem headWord = new JMenuItem("提取词头含有指定字符串的词");
        JMenuItem tailWord = new JMenuItem("提取词尾含有指定字符串的词");
        popupMenu.add(first); //添加菜单项Open
        popupMenu.add(second);
        selectWord.add(four);
        selectWord.add(six);
        getWord.add(anyLength);
        getWord.add(headWord);
        getWord.add(tailWord);
        this.setTitle("英文单词词频统计");
        this.setSize(1000, 800);
        //this.setLayout(null);
        this.setLocation(50, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        //添加监听事件
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("Open".equals(str)) {
                    System.out.println("Open正在被点击");
                    openFile();
                }
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("Save".equals(str)) {
                    System.out.println("Save正在被点击");
                    saveFile();
                }
            }
        });
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("About".equals(str)) {
                    System.out.println("About正在被点击");
                    //读取web文件
                    URL url = null;
                    URLConnection conn = null;
                    try {
                        url = new URL("https://www.cnblogs.com/yz2019-4-19/p/11144118.html");
                        conn = url.openConnection();

                        System.out.println(url.getContent());
                        System.out.println(url.getAuthority());
                        System.out.println(conn.getContent());
                        System.out.println(conn.getContentType());
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    try {
                        Runtime.getRuntime().exec("rundll32  url.dll,FileProtocolHandler " + "https://www.cnblogs.com/yz2019-4-19/p/11144118.html");
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }
                }
            }
        });
        bg1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("蓝色".equals(str)) {
                    text.setBackground(Color.decode("#c4e1ff"));
                    js.setBackground(Color.decode("#e0e0e0"));
                }
            }
        });
        bg2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("白色".equals(str)) {
                    text.setBackground(Color.white);
                    js.setBackground(Color.white);
                }
            }
        });
        bg3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("绿色".equals(str)) {
                    text.setBackground(Color.decode("#bbffbb"));
                    js.setBackground(Color.decode("#d6d6ad"));
                }
            }
        });
        bg4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("粉色".equals(str)) {
                    text.setBackground(Color.pink);
                    js.setBackground(Color.decode("#ffeedd"));
                }
            }
        });
        bg5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("紫色".equals(str)) {
                    text.setBackground(Color.decode("#d2a2cc"));
                    js.setBackground(Color.decode("#ffb5b5"));
                }
            }
        });
        item8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("宋体&14px".equals(str)) {
                    js.setFont(new Font("宋体", Font.BOLD, 14));
                    clear.setFont(new Font("宋体", Font.BOLD, 14));
                    wordSum.setFont(new Font("宋体", Font.BOLD, 14));
                    text.setFont(new Font("宋体", Font.BOLD, 14));
                    menu1.setFont(new Font("宋体", Font.BOLD, 14));
                    menu2.setFont(new Font("宋体", Font.BOLD, 14));
                    menu3.setFont(new Font("宋体", Font.BOLD, 14));
                    item1.setFont(new Font("宋体", Font.BOLD, 14));
                    item2.setFont(new Font("宋体", Font.BOLD, 14));
                    item4.setFont(new Font("宋体", Font.BOLD, 14));
                    item5.setFont(new Font("宋体", Font.BOLD, 14));
                    item7.setFont(new Font("宋体", Font.BOLD, 14));
                    item8.setFont(new Font("宋体", Font.BOLD, 14));
                    item9.setFont(new Font("宋体", Font.BOLD, 14));
                    item10.setFont(new Font("宋体", Font.BOLD, 14));
                    item11.setFont(new Font("宋体", Font.BOLD, 14));
                    item12.setFont(new Font("宋体", Font.BOLD, 14));
                    item13.setFont(new Font("宋体", Font.BOLD, 14));
                    item14.setFont(new Font("宋体", Font.BOLD, 14));
                    item15.setFont(new Font("宋体", Font.BOLD, 14));
                }
            }
        });
        item9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("宋体&16px".equals(str)) {
                    js.setFont(new Font("宋体", Font.BOLD, 16));
                    clear.setFont(new Font("宋体", Font.BOLD, 16));
                    wordSum.setFont(new Font("宋体", Font.BOLD, 16));
                    text.setFont(new Font("宋体", Font.BOLD, 16));
                    menu1.setFont(new Font("宋体", Font.BOLD, 16));
                    menu2.setFont(new Font("宋体", Font.BOLD, 16));
                    menu3.setFont(new Font("宋体", Font.BOLD, 16));
                    item1.setFont(new Font("宋体", Font.BOLD, 16));
                    item2.setFont(new Font("宋体", Font.BOLD, 16));
                    item4.setFont(new Font("宋体", Font.BOLD, 16));
                    item5.setFont(new Font("宋体", Font.BOLD, 16));
                    item7.setFont(new Font("宋体", Font.BOLD, 16));
                    item8.setFont(new Font("宋体", Font.BOLD, 16));
                    item9.setFont(new Font("宋体", Font.BOLD, 16));
                    item10.setFont(new Font("宋体", Font.BOLD, 16));
                    item11.setFont(new Font("宋体", Font.BOLD, 16));
                    item12.setFont(new Font("宋体", Font.BOLD, 16));
                    item13.setFont(new Font("宋体", Font.BOLD, 16));
                    item14.setFont(new Font("宋体", Font.BOLD, 16));
                    item15.setFont(new Font("宋体", Font.BOLD, 16));
                }
            }
        });
        item10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("隶书&14px".equals(str)) {
                    js.setFont(new Font("隶书", Font.BOLD, 14));
                    clear.setFont(new Font("隶书", Font.BOLD, 14));
                    wordSum.setFont(new Font("隶书", Font.BOLD, 14));
                    text.setFont(new Font("隶书", Font.BOLD, 14));
                    menu1.setFont(new Font("隶书", Font.BOLD, 14));
                    menu2.setFont(new Font("隶书", Font.BOLD, 14));
                    menu3.setFont(new Font("隶书", Font.BOLD, 14));
                    item1.setFont(new Font("隶书", Font.BOLD, 14));
                    item2.setFont(new Font("隶书", Font.BOLD, 14));
                    item4.setFont(new Font("隶书", Font.BOLD, 14));
                    item5.setFont(new Font("隶书", Font.BOLD, 14));
                    item7.setFont(new Font("隶书", Font.BOLD, 14));
                    item8.setFont(new Font("隶书", Font.BOLD, 14));
                    item9.setFont(new Font("隶书", Font.BOLD, 14));
                    item10.setFont(new Font("隶书", Font.BOLD, 14));
                    item11.setFont(new Font("隶书", Font.BOLD, 14));
                    item12.setFont(new Font("隶书", Font.BOLD, 14));
                    item13.setFont(new Font("隶书", Font.BOLD, 14));
                    item14.setFont(new Font("隶书", Font.BOLD, 14));
                    item15.setFont(new Font("隶书", Font.BOLD, 14));
                }
            }
        });
        item11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("隶书&16px".equals(str)) {
                    js.setFont(new Font("隶书", Font.BOLD, 16));
                    clear.setFont(new Font("隶书", Font.BOLD, 16));
                    wordSum.setFont(new Font("隶书", Font.BOLD, 16));
                    text.setFont(new Font("隶书", Font.BOLD, 16));
                    menu1.setFont(new Font("隶书", Font.BOLD, 16));
                    menu2.setFont(new Font("隶书", Font.BOLD, 16));
                    menu3.setFont(new Font("隶书", Font.BOLD, 16));
                    item1.setFont(new Font("隶书", Font.BOLD, 16));
                    item2.setFont(new Font("隶书", Font.BOLD, 16));
                    item4.setFont(new Font("隶书", Font.BOLD, 16));
                    item5.setFont(new Font("隶书", Font.BOLD, 16));
                    item7.setFont(new Font("隶书", Font.BOLD, 16));
                    item8.setFont(new Font("隶书", Font.BOLD, 16));
                    item9.setFont(new Font("隶书", Font.BOLD, 16));
                    item10.setFont(new Font("隶书", Font.BOLD, 16));
                    item11.setFont(new Font("隶书", Font.BOLD, 16));
                    item12.setFont(new Font("隶书", Font.BOLD, 16));
                    item13.setFont(new Font("隶书", Font.BOLD, 16));
                    item14.setFont(new Font("隶书", Font.BOLD, 16));
                    item15.setFont(new Font("隶书", Font.BOLD, 16));
                }
            }
        });
        item12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("微软雅黑&14px".equals(str)) {
                    js.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    clear.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    wordSum.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    text.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    menu1.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    menu2.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    menu3.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item1.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item2.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item4.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item5.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item7.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item8.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item9.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item10.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item11.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item12.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item13.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item14.setFont(new Font("微软雅黑", Font.BOLD, 14));
                    item15.setFont(new Font("微软雅黑", Font.BOLD, 14));
                }
            }
        });
        item13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("微软雅黑&16px".equals(str)) {
                    js.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    clear.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    wordSum.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    text.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    menu1.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    menu2.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    menu3.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item1.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item2.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item4.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item5.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item7.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item8.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item9.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item10.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item11.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item12.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item13.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item14.setFont(new Font("微软雅黑", Font.BOLD, 16));
                    item15.setFont(new Font("微软雅黑", Font.BOLD, 16));
                }
            }
        });
        item14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("方正舒体&14px".equals(str)) {
                    js.setFont(new Font("方正舒体", Font.BOLD, 14));
                    clear.setFont(new Font("方正舒体", Font.BOLD, 14));
                    wordSum.setFont(new Font("方正舒体", Font.BOLD, 14));
                    text.setFont(new Font("方正舒体", Font.BOLD, 14));
                    menu1.setFont(new Font("方正舒体", Font.BOLD, 14));
                    menu2.setFont(new Font("方正舒体", Font.BOLD, 14));
                    menu3.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item1.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item2.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item4.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item5.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item7.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item8.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item9.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item10.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item11.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item12.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item13.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item14.setFont(new Font("方正舒体", Font.BOLD, 14));
                    item15.setFont(new Font("方正舒体", Font.BOLD, 14));
                }
            }
        });
        item15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("方正舒体&16px".equals(str)) {
                    js.setFont(new Font("方正舒体", Font.BOLD, 16));
                    clear.setFont(new Font("方正舒体", Font.BOLD, 16));
                    wordSum.setFont(new Font("方正舒体", Font.BOLD, 16));
                    text.setFont(new Font("方正舒体", Font.BOLD, 16));
                    menu1.setFont(new Font("方正舒体", Font.BOLD, 16));
                    menu2.setFont(new Font("方正舒体", Font.BOLD, 16));
                    menu3.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item1.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item2.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item4.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item5.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item7.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item8.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item9.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item10.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item11.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item12.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item13.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item14.setFont(new Font("方正舒体", Font.BOLD, 16));
                    item15.setFont(new Font("方正舒体", Font.BOLD, 16));
                }
            }
        });
        first.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("选择第一个文件".equals(str)) {
                    openFile();
                }
            }
        });
        second.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("选择第二个文件".equals(str)) {
                    openFile();
                }
            }
        });
        wordSum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if("统计此单词数目".equals(str)){
                    System.out.println("这里是统计此单词数目");
                    try {
                        text.setText("此单词数目为："+CountWord.FindWord(singleWord.getText(),file.getAbsolutePath()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if("清空".equals(str)){
                    clearPath();
                    System.out.println("点击了清空");
                }
            }
        });
        look.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if("查看路径文档内容".equals(str)){
                    singleWord.setText("");
                    System.out.println("查看路径文档内容");
                    text.setText("查看路径文档内容");
                    String currentPath = path.getText();
                    text.setText(currentPath);
                    if(currentPath.lastIndexOf(".txt")!=-1){
                        //读txt文档
                        text.setText(article);
                    }
                    if(currentPath.lastIndexOf(".doc")!=-1){
                        //读doc文档
                        text.setText(article);
                    }
                    if(currentPath.lastIndexOf(".pdf")!=-1){
                        //读pdf文档
                        text.setText(article);
                    }

                }
            }
        });
        four.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("选择四级词汇".equals(str)) {
                    vocabulary = "cet4";
                    text.setText(ExceedWords.SortEceedWords(Countmap, vocabulary));
                    System.out.println("这里是四级词汇");
                }
            }
        });
        six.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("选择六级词汇".equals(str)) {
                    vocabulary = "cet6";
                    ExceedWords.SortEceedWords(Countmap, vocabulary);
                    text.setText(ExceedWords.SortEceedWords(Countmap, vocabulary));
                    System.out.println("这里是六级词汇");
                }
            }
        });
        anyLength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("提取某个长度的词".equals(str)) {
                    text.setText("");
                    text.setText("提取某个长度的词");
                    System.out.println("提取某个长度的词");
                    String str1 = JOptionPane.showInputDialog("输入所要提取单词长度");
                    int length = Integer.valueOf(str1);


                    if (str1 == null || str1.equals("")) {
                        System.out.println("点击了取消");
                        return;
                    } else {
                        //提取单词长度
                        HashMap<String, Integer> hashMap = Part7.Length(split, length);
                        text.setText("");
                        Set keys = hashMap.keySet();
                        for (Object key : keys) {
                            text.append(key + ":" + hashMap.get(key) + "\n");
                            System.out.println(key + ":" + hashMap.get(key) + "\n");
                        }
                    }
                }
            }
        });
        headWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("提取词头含有指定字符串的词".equals(str)) {
                    text.setText("");
                    System.out.println("提取词头含有指定字符串的词");
                    text.setText("提取词头含有指定字符串的词");
                    String str2 = JOptionPane.showInputDialog("输入所要提取单词的词头");
                    if (str2 == null || str2.equals("")) {
                        System.out.println("点击了取消");
                        return;
                    } else {
                        //提取词头含有指定字符串的词
                        HashMap<String, Integer> hashMap = Part7.Head(split, str2);
                        text.setText("");
                        Set keys = hashMap.keySet();
                        for (Object key : keys) {
                            text.append(key + ":" + hashMap.get(key) + "\n");
                            System.out.println(key + ":" + hashMap.get(key) + "\n");
                        }
                    }
                }
            }
        });
        tailWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = e.getActionCommand();
                if ("提取词尾含有指定字符串的词".equals(str)) {
                    text.setText("");
                    System.out.println("提取词尾含有指定字符串的词");
                    text.setText("提取词尾含有指定字符串的词");
                    String str3 = JOptionPane.showInputDialog("输入所要提取单词的词尾");
                    if (str3 == null || str3.equals("")) {
                        System.out.println("点击了取消");
                        return;
                    } else {
                        //提取词尾含有指定字符串的词
                        HashMap<String, Integer> hashMap = Part7.Tail(split, str3);
                        text.setText("");
                        Set keys = hashMap.keySet();
                        for (Object key : keys) {
                            text.append(key + ":" + hashMap.get(key) + "\n");
                            System.out.println(key + ":" + hashMap.get(key) + "\n");
                        }
                    }
                }
            }
        });
        text.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                myText = text.getSelectedText();
                System.out.println(myText);
                result = AutoTranslate.Translation(myText);
                if(myText!=null){
                    JDialog dialog=new JDialog();
                    dialog.setLayout(new FlowLayout());
                    dialog.setSize(400,200);
                    dialog.setLocation(300,300);
                    dialog.setTitle("翻译结果");
                    JTextArea reaultText = new JTextArea(4,30);
                    JScrollPane resultpanel = new JScrollPane(reaultText);
                    JButton jButton=new JButton("确定");
                    //dialog.add(reaultText);
                    dialog.add(jButton);
                    dialog.add(resultpanel);
                    dialog.setVisible(true);

                    ActionListener ok = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.dispose();
                            reaultText.setText("");
                        }
                    };
                    reaultText.setText(result);
                    //reaultText.setText(myText);
                    jButton.addActionListener(ok);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        js.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //js.setSelectedIndex(js.locationToIndex(e.getPoint())); //获取鼠标点击的项
                //js.setSelectedIndex(11);
                //ShowPopup(e);
                int index = js.getSelectedIndex() + 1;
                if (index == 1) {
                    text.setText("");
                    //text.setText("这里是统计");
                    try {
                        Countmap = CountWord.countword(file.getAbsolutePath());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "文档字数" + Countmap.get("Total"), "英文单词词频统计", JOptionPane.PLAIN_MESSAGE);
                    text.setText(Sort.UpSortMap(Countmap));

                }
                if (index == 2) {
                    text.setText("");
                    text.setText("这里是词频升序");
                    text.setText(Sort.UpSortMap(Countmap));

                }
                if (index == 3) {
                    text.setText("");
                    text.setText("这里是词频降序");
                    text.setText(Sort.DownSortMap(Countmap));

                }
                if (index == 4) {
                    text.setText("");
                    text.setText("这里是字母升序");
                    text.setText(Sort.CharUpSortMap(Countmap));
                }
                if (index == 5) {
                    text.setText("");
                    text.setText("这里是字母降序");
                    text.setText(Sort.CharDownSortMap(Countmap));

                }
                if (index == 6) {
                    text.setText("");
                    text.setText("这里是超纲词");
                    selectWord.show(e.getComponent(), e.getX(), e.getY());
                    text.setText(ExceedWords.SortEceedWords(Countmap, vocabulary));

                }
                if (index == 7) {
                    try {
                        System.out.println("here");
                        text.setText("");
                        List<List<String>> list = Part6.Distinguish(split);
                        text.append("复数:" + list.get(0).size() + "\n");
                        text.append("过去式" + list.get(0).size() + "\n");
                        text.append("过去分词" + list.get(0).size() + "\n");
                        text.append("现在分词" + list.get(0).size() + "\n\n");

                        text.append("复数:\n");
                        for (int i = 0; i < list.get(0).size(); i++) text.append(list.get(0).get(i) + "\n");

                        text.append("\n过去式:\n");
                        for (int i = 0; i < list.get(1).size(); i++) text.append(list.get(1).get(i) + "\n");

                        text.append("\n过去分词:\n");
                        for (int i = 0; i < list.get(2).size(); i++) text.append(list.get(2).get(i) + "\n");

                        text.append("\n现在分词:\n");
                        for (int i = 0; i < list.get(3).size(); i++) text.append(list.get(3).get(i) + "\n");

                        System.out.println("here");

                    } catch (Exception e1) {

                    }

                }
                if (index == 8) {
                    text.setText("");
                    text.setText("这里是提取单词");
                    getWord.show(e.getComponent(), e.getX(), e.getY());
                }
                if (index == 9) {
                    text.setText("");
                    text.setText("总词汇数：" + split.length);
                }
                if (index == 10) {
                    JDialog dialog = new JDialog();
                    dialog.setLayout(new FlowLayout());
                    dialog.setSize(400, 150);
                    dialog.setLocation(300, 300);
                    dialog.setTitle("选择文档");

                    JButton jButton1 = new JButton("选择文档1");
                    JButton jButton2 = new JButton("选择文档2");
                    JButton jButton3 = new JButton("确定");
                    JTextArea jTextArea1 = new JTextArea(1, 25);
                    JTextArea jTextArea2 = new JTextArea(1, 25);

                    ActionListener fileselect1 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            FileDialog fileDialog = new FileDialog(dialog, "打开", FileDialog.LOAD);
                            fileDialog.setVisible(true);
                            if (fileDialog.getFile() != null)
                                jTextArea1.setText(fileDialog.getDirectory() + fileDialog.getFile());


                        }
                    };

                    ActionListener fileselect2 = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            FileDialog fileDialog = new FileDialog(dialog, "打开", FileDialog.LOAD);
                            fileDialog.setVisible(true);
                            if (fileDialog.getFile() != null)
                                jTextArea2.setText(fileDialog.getDirectory() + fileDialog.getFile());
                        }
                    };

                    ActionListener close = new ActionListener() {
                        public void actionPerformed(ActionEvent event) {
                            try {
                                dialog.dispose();
                                text.setText("");
                                String[] list = FileRead.Readsplit(jTextArea1.getText());
                                String[] list2 = FileRead.Readsplit(jTextArea2.getText());
                                List<String> stringList = Part8.Compare(list, list2);
                                text.append("文档1单词数：" + stringList.get(0) + "\n");
                                text.append("文档2单词数：" + stringList.get(1) + "\n");
                                text.append("\n相同词汇：\n");
                                for (int i = 2; i < stringList.size(); i++) {
                                    text.append(stringList.get(i) + "\n");
                                }
                            } catch (Exception e1) {

                            }
                            //关闭

                        }
                    };

                    jButton1.addActionListener(fileselect1);
                    jButton2.addActionListener(fileselect2);
                    jButton3.addActionListener(close);

                    dialog.add(jTextArea1);
                    dialog.add(jButton1);
                    dialog.add(jTextArea2);
                    dialog.add(jButton2);
                    dialog.add(jButton3);
                    dialog.setVisible(true);
                }
            }

            //e.getButton()==e.BUTTON1||e.getButton()==e.BUTTON3
            @Override
            public void mouseReleased(MouseEvent e) {
                int i = js.getSelectedIndex() + 1;
                if (i == 10) {
                    ShowPopup(e);
                }
                if (i == 6) {
                    ShowSelectPopup(e);
                }
                if (i == 8) {
                    ShowGetPopup(e);
                }

            }

            /*@Override
            public void mouseEntered(MouseEvent e){
                int k = js.getSelectedIndex()+1;
                if(k==8) {
                    js.setToolTipText("点击右键选择具体操作");
                }
            }*/
            public void ShowPopup(MouseEvent e) {
                if (e.isPopupTrigger() && js.getSelectedIndex() != -1) {

                    //获取选择项的值
                    Object selected = js.getModel().getElementAt(js.getSelectedIndex());
                    System.out.println(selected);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void ShowSelectPopup(MouseEvent e) {
                if (e.isPopupTrigger() && js.getSelectedIndex() != -1) {

                    //获取选择项的值
                    Object selected = js.getModel().getElementAt(js.getSelectedIndex());
                    System.out.println(selected);
                    selectWord.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void ShowGetPopup(MouseEvent e) {
                if (e.isPopupTrigger() && js.getSelectedIndex() != -1) {

                    //获取选择项的值
                    Object selected = js.getModel().getElementAt(js.getSelectedIndex());
                    System.out.println(selected);
                    getWord.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}