package test;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Scanner;

public class analyse {
    static String keyword[] = { "if", "then", "else", "while", "do" };
    static char operator[] = { '+', '-', '*', '/', '<', '>', '=', '(', ')', ';' };
    static ArrayList<String> result = new ArrayList<>();
    static Boolean iskeyword(String str){
        for(String a :keyword){
            if(str.equals(a)){
                return true;
            }
        }
        return false;
    }
    static Boolean isoperator(char ch){
        for(char a : operator){
            if(ch==a){
                return true;
            }
        }
        return false;
    }
    static void  scan(String str)
    {
        char ch;  //当前分析的字符
        int i = 0; //当前字符位置
        while (i < str.length()) {
            ch = str.charAt(i);
            String buf; //结果串
            buf = "";
            if (ch == ' ') {
                i++;
                continue;
            }
            //如果是算符
            if (isoperator(ch)) {
                result.add("<"+ch+",->");
                i++;
                continue;
            }
            //如果是字母开头
            if (ch >= 'a' && ch <= 'z') {
                while (!isoperator(ch) && ch != ' ') {
                    buf = buf + ch;
                    i++;
                    if (i >= str.length())
                        break;
                    ch = str.charAt(i);
                }
                //关键字
                if (iskeyword(buf))
                    result.add("<"+buf+",->");
                //标识符
                else
                    result.add("<0,"+buf+">");
                continue;
            }
            //数字开头
            if (ch >= '0' && ch <= '9') {
                //0开头
                if (ch == '0') {
                    //下一位为'x' ，十六进制数
                    if (i + 1 < str.length() && str.charAt(i + 1) == 'x') {
                        buf = buf + "0x";
                        i += 2;
                        ch = str.charAt(i);
                        while (i < str.length() && ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f'))) {
                            buf = buf + ch;
                            i++;
                            if (i >= str.length())
                                break;
                            ch = str.charAt(i);
                        }
                        result.add("<3,"+buf+">");
                        continue;
                        //下一位为非0  八进制数
                    }
                    else if (i + 1 < str.length() && (str.charAt(i + 1) > '0' && str.charAt(i + 1) <= '7')) {
                        //buf = "0";
                        i++;
                        ch = str.charAt(i);
                        while (i < str.length() && ch > '0' && ch <= '7') {
                            buf = buf + ch;
                            i++;
                            if (i >= str.length())
                                break;
                            ch = str.charAt(i);
                        }
                        result.add("<2,"+buf+">");
                        continue;
                        //数字0
                    }
                    else {
                        result.add("<1,0>");
                        i++;
                        continue;
                    }
                    //十进制数字
                }
                else {
                    while (i < str.length() && ch >= '0' && ch <= '9') {
                        buf = buf + ch;
                        i++;
                        if (i >= str.length())
                            break;
                        ch = str.charAt(i);
                    }
                    result.add("<1,"+buf+">");
                    continue;
                }
            }

        }
    }
    public static void main(String[] args){
        String str="";
        java.io.File file = new java.io.File("E:\\123.txt");
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                str =str+input.nextLine();
            }
                scan(str);
            System.out.println(result.size());
            for(String a: result){
                System.out.println(a);
            }

        }
        catch (Exception ex){

        }

    }
}
