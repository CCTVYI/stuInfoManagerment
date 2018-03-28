/**
 * 主程序
 * @author hiseico
 * @version 0.1 Bate
 */

package cn.sitcat.Application;

import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception{
/**
 *                 
 "【学生信息管理系统】" 
 "1.查询学生信息" 
 "2.查询全部学生信息" 
 "3.添加学生信息" 
 "4.修改学生信息" 
 "5.删除学生信息" 
 "6.查询学生成绩" 
 "7.添加学生成绩" 
 "8.修改学生成绩" 
 "9.删除学生成绩" 
 "10.查看学生信息及成绩" 
 "11.成绩总分排行榜" 
 "12.成绩平均分排行榜"
 "0.退出" 
 */

        int choose;
        mainHandle handle = new mainHandle();
        do {
            handle.showMenu();
            Scanner in = new Scanner(System.in);
            String strChoose = in.next();
            boolean strStatu= strChoose.matches("[0-9]{1,}");
            while (!strStatu){
                System.out.println("请按照菜单选项进行输入阿拉伯数字进行选择！");
                strChoose = in.next();
                strStatu= strChoose.matches("[0-9]{1,}");
                while(!strStatu){
                    System.out.println("请按照菜单选项进行输入阿拉伯数字进行选择！");
                    strChoose = in.next();
                    strStatu=strChoose.matches("[0,1,2,3,4,5,6,7,8,9,10,11,12]{1}");
                }
                strStatu= strChoose.matches("[0-9]{1,}");
            }

            choose= Integer.parseInt(strChoose);
            switch (choose) {
                case 1:
                    handle.showStuInfo();
                    break;
                case 2:
                    handle.showAllStuInfo();
                    break;
                case 3:
                    handle.addStuInfo();
                    break;
                case 4:
                    handle.updateStuInfo();
                    break;
                case 5:
                    handle.removeStuInfo();
                    break;
                case 6:
                    handle.showStuScore();
                    break;
                case 7:
                    handle.addStuScore();
                    break;
                case 8:
                    handle.updateStuScore();
                    break;
                case 9:
                    handle.removeStuScore();
                    break;
                case 10:
                    handle.showStuDetails();
                    break;
                case 11:
                    handle.showTotalRank();
                    break;
                case 12:
                    handle.showAvgRank();
                    break;
            }
        } while (choose != 0);
    }
}
