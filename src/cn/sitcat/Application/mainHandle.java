/**
 * 主方法实现类
 * @author hiseico
 * @version 0.1 Bate
 */
package cn.sitcat.Application;

import cn.sitcat.factory.ServiceFacory;
import cn.sitcat.PO.Stu_grades;
import cn.sitcat.PO.Stu_info;

import java.util.*;

public class mainHandle {
    Scanner in = new Scanner(System.in);
    static int cls = 0;

    /**
     * 主菜单显示
     */
    public void showMenu() {
        if (cls != 0) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
        System.out.println("===============================\n" +
                "\t【学生信息管理系统】\n\n" +
                "\t1.查询学生信息\n" +
                "\t2.查询全部学生信息\n" +
                "\t3.添加学生信息\n" +
                "\t4.修改学生信息\n" +
                "\t5.删除学生信息\n\n" +
                "\t6.查询学生成绩\n" +
                "\t7.添加学生成绩\n" +
                "\t8.修改学生成绩\n" +
                "\t9.删除学生成绩\n" +
                "\t10.查看学生信息及成绩\n" +
                "\t11.成绩总分排行榜\n" +
                "\t12.成绩平均分排行榜\n" +

                "\t0.退出\n" +
                "===============================\n");
        cls++;
    }

    /**
     * 根据学号查询单个学生信息
     */
    public void showStuInfo() {
        System.out.println("请输入您要查询的学生信息的学号：");
        in = new Scanner(System.in);
        String input = in.next();
        try {
            Stu_info info = new Stu_info();
            info = ServiceFacory.getIStu_infoServiceInstance().get(input);
            if (info.getStu_id() != null) {
                System.out.println("学号：" + info.getStu_id() + "\n" +
                        "姓名：" + info.getStu_name() + "\n" +
                        "性别：" + info.getSex() + "\n" +
                        "年龄：" + info.getAge() + "\n" +
                        "所在班级：" + info.getStu_class());
                System.out.println();
                System.out.println("是否继续查询(y/n)");
                in = new Scanner(System.in);
                input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.showStuInfo();
                }
            } else {
                System.out.println("不存在的学号：" + input);
                System.out.println();
                System.out.println("是否继续查询(y/n)");
                in = new Scanner(System.in);
                input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.showStuInfo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出所有学生信息
     */
    public void showAllStuInfo() {
        System.out.println("学号\t\t姓名\t\t年龄\t\t性别\t\t所在班级");
        try {
            List<Stu_info> list = ServiceFacory.getIStu_infoServiceInstance().list();
            Iterator<Stu_info> iter = list.iterator();
            if (list.size() == 0) {
                System.out.println("学生信息为空！");
            }
            while (iter.hasNext()) {
                Stu_info info = null;
                in = new Scanner(System.in);
                info = iter.next();
                System.out.println(info.getStu_id() + "\t" + info.getStu_name() + "\t\t" + info.getAge() + "\t\t" + info.getSex() + "\t\t" + info.getStu_class());
            }
            System.out.println("\n按任意键返回主菜单....");
            in = new Scanner(System.in);
            in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加学生信息到数据库
     */
    public void addStuInfo() {
        try {
            Stu_info info = new Stu_info();
            System.out.println("学生信息添加顺序为：学号、姓名、年龄、性别、所在班级");
            System.out.print("学号：");
            in = new Scanner(System.in);
            String id = in.next();
            while (!(id.matches("[0-9a-zA-Z]+"))) {
                System.out.println("请按照格式进行输入：A009 ");
                in = new Scanner(System.in);
                id = in.next();
            }
            info.setStu_id(id);

            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id", id);
            if (count > 0) {
                System.out.println("您输入的学号已存在！请重新输入");
                this.addStuInfo();
            }

            System.out.print("姓名：");
            in = new Scanner(System.in);
            String name = in.next();
            int i = 0;
        while (!(name.matches("[\u4e00-\u9fa5]{0,}[a-zA-Z]{0,}"))) {

                System.out.println("您输入的姓名不符合,请重新输入，字母、中文！");
                in = new Scanner(System.in);
                name = in.next();
            }
            info.setStu_name(name);


            System.out.print("年龄：");
            in = new Scanner(System.in);
            String strAge = in.next();
            boolean ageStatu=strAge.matches("[0-9]{1,}");
            while (!ageStatu){
                System.out.println("年龄请用阿拉伯数字表示！");
                in = new Scanner(System.in);
                strAge = in.next();
                ageStatu=strAge.matches("[0-9]{1,}");
            }
            int age = Integer.parseInt(strAge);
            while (age <= 0 || age > 500) {
                System.out.println("您输入的年龄范围有误，,请重新输入！ 范围为：1-499");
                in = new Scanner(System.in);
                age = in.nextInt();
            }
            info.setAge(age);


            System.out.print("性别：");
            in = new Scanner(System.in);
            String sex = in.next();
            while (!(sex.matches("[男女]{1}"))) {
                System.out.println("性别只能输入 男/女！请重新输入");
                in = new Scanner(System.in);
                sex = in.next();
            }

            info.setSex(sex);

            System.out.print("所在班级：");
            in = new Scanner(System.in);
            String stuClass = in.next();
            while (!(stuClass.matches("[一二三四五六]{1}年级[一二三四五六七八九十]+班"))) {
                System.out.println("所在班级输入规则：“三年级六班”，请按照格式输入");
                in = new Scanner(System.in);
                stuClass = in.next();
            }
            info.setStu_class(stuClass);

            boolean statu = ServiceFacory.getIStu_infoServiceInstance().insert(info);
            if (statu) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
            System.out.println();
            System.out.println("是否继续添加(y/n)");
            in = new Scanner(System.in);
            String input = in.next();
            if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                this.addStuInfo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号更新学生信息
     */
    public void updateStuInfo() {
        try {
            System.out.println("请输入要修改信息的学号：");
            in = new Scanner(System.in);
            String id = in.next();
            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id", id);
            if (count == 0) {
                System.out.println("该学号不存在！");
                System.out.println();
                System.out.println("是否继续修改(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.updateStuInfo();
                }
            } else {
                System.out.println("该学生的原信息如下：");
                Stu_info info = new Stu_info();
                info = ServiceFacory.getIStu_infoServiceInstance().get(id);
                if (info.getStu_id() != null) {
                    System.out.println("学号：" + info.getStu_id() + "\n" +
                            "姓名：" + info.getStu_name() + "\n" +
                            "性别：" + info.getSex() + "\n" +
                            "年龄：" + info.getAge() + "\n" +
                            "所在班级：" + info.getStu_class());
                    System.out.println();
                    System.out.println("学生信息内容修改顺序为：学号、姓名、年龄、性别、所在班级");
                    info.setStu_id(id);

                    System.out.print("姓名：");
                    in = new Scanner(System.in);
                    String name = in.next();
                while (!(name.matches("[\u4e00-\u9fa5]{0,}[a-zA-Z]{0,}"))) {

                        System.out.println("您输入的姓名不符合,请重新输入，字母、中文！");
                        in = new Scanner(System.in);
                        name = in.next();
                    }
                    info.setStu_name(name);


                    System.out.print("年龄：");
                    in = new Scanner(System.in);
                    String strAge = in.next();
                    boolean ageStatu=strAge.matches("[0-9]{1,}");
                    while (!ageStatu){
                        System.out.println("年龄请用阿拉伯数字表示！");
                        in = new Scanner(System.in);
                        strAge = in.next();
                        ageStatu=strAge.matches("[0-9]{1,}");
                    }
                    int age = Integer.parseInt(strAge);
                    while (age <= 0 || age > 500) {
                        System.out.println("您输入的年龄范围有误，,请重新输入！ 范围为：1-499");
                        in = new Scanner(System.in);
                        age = in.nextInt();
                    }
                    info.setAge(age);


                    System.out.print("性别：");
                    in = new Scanner(System.in);
                    String sex = in.next();
                    while (!(sex.matches("[男女]{1}"))) {
                        System.out.println("性别只能输入 男/女！请重新输入");
                        in = new Scanner(System.in);
                        sex = in.next();
                    }

                    info.setSex(sex);

                    System.out.print("所在班级：");
                    in = new Scanner(System.in);
                    String stuClass = in.next();
                    while (!(stuClass.matches("[一二三四五六]{1}年级[一二三四五六七八九十]+班"))) {
                        System.out.println("所在班级输入规则：“三年级六班”，请按照格式输入");
                        in = new Scanner(System.in);
                        stuClass = in.next();
                    }
                    info.setStu_class(stuClass);


                    boolean statu = ServiceFacory.getIStu_infoServiceInstance().update(info);
                    if (statu) {
                        System.out.println("更新成功！");
                    } else {
                        System.out.println("更新失败！");
                    }
                    System.out.println();
                    System.out.println("是否继续更新其他学生信息(y/n)");
                    in = new Scanner(System.in);
                    String input = in.next();
                    if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                        this.updateStuInfo();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号移出学生信息
     */
    public void removeStuInfo() {
        System.out.println("请输入要删除的学生信息的学号");
        in = new Scanner(System.in);
        String id = in.next();
        try {
            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id", id);
            if (count == 0) {
                System.out.println("该学号不存在！");
                System.out.println();
                System.out.println("是否重新输入(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.removeStuInfo();
                }
            } else {
                System.out.println("该学生的信息如下：");
                Stu_info info = new Stu_info();
                info = ServiceFacory.getIStu_infoServiceInstance().get(id);
                if (info.getStu_id() != null) {
                    System.out.println("学号：" + info.getStu_id() + "\n" +
                            "姓名：" + info.getStu_name() + "\n" +
                            "性别：" + info.getSex() + "\n" +
                            "年龄：" + info.getAge() + "\n" +
                            "所在班级：" + info.getStu_class());
                }
                System.out.println();
                System.out.println("确认删除(y/n)");
                in = new Scanner(System.in);
                String input = in.nextLine();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    Set<String> set = new TreeSet<>();
                    set.add(id);
                    boolean statu = ServiceFacory.getIStu_infoServiceInstance().delete(set);
                    System.out.println(statu);
                    if (statu) {
                        System.out.println("删除成功!");
                        System.out.println("是否继续删除....(y/n)");
                        in = new Scanner(System.in);
                        input = in.next();
                        if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                            this.removeStuInfo();
                        }
                    } else {
                        System.out.println("删除失败");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//成绩操作方法====================

    /**
     * 根据学号查询学生成绩
     */
    public void showStuScore() {
        System.out.println("请输入您要查询成绩的学号：");

        in = new Scanner(System.in);
        String input = in.next();
        while (!(input.matches("[0-9a-zA-Z]+"))) {
            System.out.println("请按照格式进行输入：A009 ");
            in = new Scanner(System.in);
            input = in.next();
        }
        try {
            //判断该学号存在与学生表和数据表
            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id",input);

            //查询成绩
            Stu_grades grades = null;
            grades = ServiceFacory.getIStu_gradesServiceInstance().get(input);
            if (grades.getStu_id() != null) {
                System.out.println("学号：" + grades.getStu_id() + "\n" +
                        "语文：" + grades.getChinese() + "\n" +
                        "数学：" + grades.getMath() + "\n" +
                        "英语：" + grades.getEnglish() + "\n" +
                        "平均分：" + grades.getAvg() + "\n" +
                        "总分：" + grades.getCount());

                System.out.println();
                System.out.println("是否继续查询(y/n)");
                in = new Scanner(System.in);
                input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.showStuScore();
                }
            }else if (count>0&&grades.getStu_id() == null){
                System.out.println("学号为："+input+"的学生信息存在，但是未录入成绩！\n");
                System.out.println("是否继续查询(y/n)");
                in = new Scanner(System.in);
                input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.showStuScore();
                }
            }
            else {
                System.out.println("不存在的学号：" + input);
                System.out.println();
                System.out.println("是否继续查询(y/n)");
                in = new Scanner(System.in);
                input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.showStuScore();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号添加学生成绩
     */
    public void addStuScore() {
        try {
            //判断学号是否存在于学生信息表中
            Stu_grades grades = new Stu_grades();
            System.out.println("学生信息添加顺序为：学号、姓名、年龄、性别、所在班级");
            System.out.println("学号：");
            in = new Scanner(System.in);

            String id = in.next();
            while (!(id.matches("[0-9a-zA-Z]+"))) {
                System.out.println("请按照格式进行输入：A009 ");
                in = new Scanner(System.in);
                id = in.next();
            }
            grades.setStu_id(id);
            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id", id);
            if (count != 1) {
                System.out.println("您输入的学号不存在！请重新输入\n");
                System.out.println("是否继续添加(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.addStuScore();
                } else {
                    return;
                }
            }

            //判断该学号是否已存在成绩
            count = ServiceFacory.getIStu_gradesServiceInstance().listCount("stu_id", id);
            if (count > 0) {
                System.out.println("该学生成绩已存在！无需再次添加！\n");
                System.out.println("是否继续添加(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.addStuScore();
                } else {
                    return;
                }
            }

            //开始添加成绩
            System.out.print("语文：");
            double chinese=0.0;
            in = new Scanner(System.in);
            String strChinese=in.next();
            boolean strStatu=strChinese.matches("[0-9]{1,}");
            while (!strStatu) {
                System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                System.out.print("语文：");
                in = new Scanner(System.in);
                strChinese = in.next();
                strStatu = strChinese.matches("[0-9]{1,}");
                if(strStatu){
                    chinese= Double.parseDouble(strChinese);
                    if (chinese > 100 || chinese < 0) {
                        System.out.println("您输入的语文成绩有误！成绩范围为0~100。请重新输入！\n");
                        System.out.print("语文：");
                        in = new Scanner(System.in);
                        strChinese = in.next();
                        strStatu = strChinese.matches("[0-9]{1,}");
                    }
                }
            }
            chinese= Double.parseDouble(strChinese);
            grades.setChinese(chinese);

            System.out.print("数学：");
            double Math=0.0;
            in = new Scanner(System.in);
            String strMath=in.next();
            strStatu=strMath.matches("[0-9]{1,}");
            while (!strStatu) {
                System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                System.out.print("数学：");
                in = new Scanner(System.in);
                strMath = in.next();
                strStatu = strMath.matches("[0-9]{1,}");
                if(strStatu){
                    Math= Double.parseDouble(strMath);
                    if (Math > 100 || Math < 0) {
                        System.out.println("您输入的数学成绩有误！成绩范围为0~100。请重新输入！\n");
                        System.out.print("数学：");
                        in = new Scanner(System.in);
                        strMath = in.next();
                        strStatu = strMath.matches("[0-9]{1,}");
                    }
                }
            }
            Math= Double.parseDouble(strMath);
            grades.setMath(Math);

            System.out.print("英语：");
            double English=0.0;
            in = new Scanner(System.in);
            String strEnglish=in.next();
            strStatu=strEnglish.matches("[0-9]{1,}");
            while (!strStatu) {
                System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                System.out.print("英语：");
                in = new Scanner(System.in);
                strEnglish = in.next();
                strStatu = strEnglish.matches("[0-9]{1,}");
                if(strStatu){
                    English= Double.parseDouble(strEnglish);
                    if (English > 100 || English < 0) {
                        System.out.println("您输入的英语成绩有误！成绩范围为0~100。请重新输入！\n");
                        System.out.print("英语：");
                        in = new Scanner(System.in);
                        strEnglish = in.next();
                        strStatu = strEnglish.matches("[0-9]{1,}");
                    }
                }
            }
            English= Double.parseDouble(strEnglish);
            grades.setEnglish(English);

            //返回是否成功
            boolean statu = ServiceFacory.getIStu_gradesServiceInstance().insert(grades);
            if (statu) {
                System.out.println("添加成功！");
            } else {
                System.out.println("添加失败！");
            }
            System.out.println();
            System.out.println("是否继续添加(y/n)");
            in = new Scanner(System.in);
            String input = in.next();
            if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                this.addStuScore();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据学号修改成绩
    public void updateStuScore() {
        try {
            //判断该学号是否存在于成绩表
            System.out.println("请输入要修改成绩的学号：");
            in = new Scanner(System.in);
            String id = in.next();
            while (!(id.matches("[0-9a-zA-Z]+"))) {
                System.out.println("请按照格式进行输入：A009 ");
                in = new Scanner(System.in);
                id = in.next();
            }
            int count = ServiceFacory.getIStu_gradesServiceInstance().listCount("stu_id", id);
            if (count == 0) {
                System.out.println("该学号不存在！");
                System.out.println();
                System.out.println("是否继续修改(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.updateStuScore();
                }
            } else {
                //显示原成绩
                System.out.println("该学生的原成绩如下：");
                Stu_grades grades = new Stu_grades();
                grades = ServiceFacory.getIStu_gradesServiceInstance().get(id);
                if (grades.getStu_id() != null) {
                    System.out.println("学号：" + grades.getStu_id() + "\n" +
                            "语文：" + grades.getChinese() + "\n" +
                            "数学：" + grades.getMath() + "\n" +
                            "英语：" + grades.getEnglish() + "\n" +
                            "平均分：" + grades.getAvg() + "\n" +
                            "总分：" + grades.getCount());
                    System.out.println();


                    //开始修改学生成绩
                    System.out.println("学生信息内容修改顺序为：语文、数学、英语");
                    grades.setStu_id(id);

                    System.out.print("语文：");
                    double chinese=0.0;
                    in = new Scanner(System.in);
                    String strChinese=in.next();
                    boolean strStatu=strChinese.matches("[0-9]{1,}");
                    while (!strStatu) {
                        System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                        System.out.print("语文：");
                        in = new Scanner(System.in);
                        strChinese = in.next();
                        strStatu = strChinese.matches("[0-9]{1,}");
                        if(strStatu){
                            chinese= Double.parseDouble(strChinese);
                            if (chinese > 100 || chinese < 0) {
                                System.out.println("您输入的语文成绩有误！成绩范围为0~100。请重新输入！\n");
                                System.out.print("语文：");
                                in = new Scanner(System.in);
                                strChinese = in.next();
                                strStatu = strChinese.matches("[0-9]{1,}");
                            }
                        }
                    }
                    chinese= Double.parseDouble(strChinese);
                    grades.setChinese(chinese);

                    System.out.print("数学：");
                    double Math=0.0;
                    in = new Scanner(System.in);
                    String strMath=in.next();
                    strStatu=strMath.matches("[0-9]{1,}");
                    while (!strStatu) {
                        System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                        System.out.print("数学：");
                        in = new Scanner(System.in);
                        strMath = in.next();
                        strStatu = strMath.matches("[0-9]{1,}");
                        if(strStatu){
                            Math= Double.parseDouble(strMath);
                            if (Math > 100 || Math < 0) {
                                System.out.println("您输入的数学成绩有误！成绩范围为0~100。请重新输入！\n");
                                System.out.print("数学：");
                                in = new Scanner(System.in);
                                strMath = in.next();
                                strStatu = strMath.matches("[0-9]{1,}");
                            }
                        }
                    }
                    Math= Double.parseDouble(strMath);
                    grades.setMath(Math);

                    System.out.print("英语：");
                    double English=0.0;
                    in = new Scanner(System.in);
                    String strEnglish=in.next();
                    strStatu=strEnglish.matches("[0-9]{1,}");
                    while (!strStatu) {
                        System.out.println("成绩请用阿拉伯数字表示！成绩范围为0~100。请重新输入！\n");
                        System.out.print("英语：");
                        in = new Scanner(System.in);
                        strEnglish = in.next();
                        strStatu = strEnglish.matches("[0-9]{1,}");
                        if(strStatu){
                            English= Double.parseDouble(strEnglish);
                            if (English > 100 || English < 0) {
                                System.out.println("您输入的英语成绩有误！成绩范围为0~100。请重新输入！\n");
                                System.out.print("英语：");
                                in = new Scanner(System.in);
                                strEnglish = in.next();
                                strStatu = strEnglish.matches("[0-9]{1,}");
                            }
                        }
                    }
                    English= Double.parseDouble(strEnglish);
                    grades.setEnglish(English);


                    boolean statu = ServiceFacory.getIStu_gradesServiceInstance().update(grades);
                    if (statu) {
                        System.out.println("更新成功！");
                    } else {
                        System.out.println("更新失败！");
                    }
                    System.out.println();
                    System.out.println("是否继续更新其他学生信息(y/n)");
                    in = new Scanner(System.in);
                    String input = in.next();
                    if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                        this.updateStuInfo();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据学号删除学生成绩
     */
    public void removeStuScore() {
        System.out.println("请输入要删除的成绩对应的序号");
        String id = in.next();
        while (!(id.matches("[0-9a-zA-Z]+"))) {
            System.out.println("请按照格式进行输入：A009 ");
            in = new Scanner(System.in);
            id = in.next();
        }
        try {
            int count = ServiceFacory.getIStu_infoServiceInstance().listCount("stu_id", id);
            if (count == 0) {
                System.out.println("该学号不存在！");
                System.out.println();
                System.out.println("是否重新输入(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    this.removeStuInfo();
                }
            } else {
                System.out.println("该学生的成绩如下：");
                Stu_grades grades = new Stu_grades();
                grades = ServiceFacory.getIStu_gradesServiceInstance().get(id);
                if (grades.getStu_id() != null) {
                    System.out.println("学号：" + grades.getStu_id() + "\n" +
                            "语文：：" + grades.getChinese() + "\n" +
                            "数学：" + grades.getMath() + "\n" +
                            "英语：" + grades.getEnglish() + "\n" +
                            "平均分：" + grades.getAvg() + "\n" +
                            "总分：" + grades.getCount());
                }
                System.out.println();
                System.out.println("确认删除(y/n)");
                in = new Scanner(System.in);
                String input = in.next();
                if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                    //原设计的数据层操作是传入多个学号，可同时删除多个，所以使用集合保存。但是此时实现的为删除单个学号成绩
                    Set<String> set = new TreeSet<>();
                    set.add(id);
                    boolean statu = ServiceFacory.getIStu_gradesServiceInstance().delete(set);
                    if (statu) {
                        System.out.println("删除成功!");
                        System.out.println("是否继续删除其他学生成绩....(y/n)");
                        in = new Scanner(System.in);
                        input = in.next();
                        if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                            this.removeStuInfo();
                        }
                    } else {
                        System.out.println("删除失败");
                        System.out.println("是否继续删除其他学生成绩....(y/n)");
                        in = new Scanner(System.in);
                        input = in.next();
                        if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                            this.removeStuInfo();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看学生信息及成绩
     */
    public void showStuDetails() {
        System.out.println("请输入您查询的学生具体信息的学号：");
        String id = in.next();
        while (!(id.matches("[0-9a-zA-Z]+"))) {
            System.out.println("请按照格式进行输入：A009 ");
            in = new Scanner(System.in);
            id = in.next();
        }
        System.out.println("学号\t\t姓名\t\t年龄\t\t性别\t\t所在班级\t\t语文\t\t数学\t\t英语\t\t平均分\t\t总分");
        try {
            List<Object> list = ServiceFacory.getIStu_infoServiceInstance().findStuDetails(id);
            Iterator<Object> iter = list.iterator();
            while (iter.hasNext()) {
                Stu_info info = null;
                Stu_grades grades = null;
                info = (Stu_info) iter.next();
                grades = (Stu_grades) iter.next();
                System.out.println(info.getStu_id() + "\t" + info.getStu_name() + "\t" + info.getAge() + "\t\t" + info.getSex() + "\t\t" + info.getStu_class() + "\t" + grades.getChinese() + "\t" + grades.getMath() + "\t" + grades.getEnglish() + "\t" + grades.getAvg() + "\t\t" + grades.getCount());
            }
            System.out.println();
            System.out.println("是否继续查询其他学生信息及成绩(y/n)");
            in = new Scanner(System.in);
            String input = in.next();
            if ("y".equals(input) || "yes".equals(input) || "Y".equals(input) || "YES".equals(input) || "Yes".equals(input)) {
                this.showStuDetails();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //成绩总分排名
    public void showTotalRank() {
        System.out.println("————————————————总分排名————————————————\n");
        System.out.println("名次\t\t学号\t\t姓名\t\t年龄\t\t性别\t\t所在班级\t\t语文\t\t数学\t\t英语\t\t平均分\t\t总分");
        try {
            //取出对应信息
            List<Object> list = ServiceFacory.getIStu_gradesServiceInstance().TotalRank();
            Iterator<Object> iter = list.iterator();
            int top = 1;
            //输出对应信息
            while (iter.hasNext()) {
                Stu_info info = null;
                Stu_grades grades = null;
                info = (Stu_info) iter.next();
                grades = (Stu_grades) iter.next();
                System.out.println("第" + top + "名" + "\t" + info.getStu_id() + "\t" + info.getStu_name() + "\t\t" + info.getAge() + "\t\t" + info.getSex() + "\t\t" + info.getStu_class() + "\t" + grades.getChinese() + "\t" + grades.getMath() + "\t" + grades.getEnglish() + "\t" + grades.getAvg() + "\t\t" + grades.getCount());
                top++;
            }
            System.out.println("\n按任意键返回主菜单....");
            in = new Scanner(System.in);
            in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 成绩平均分排名
     */
    public void showAvgRank() {
        System.out.println("————————————————平均分排名————————————————\n");
        System.out.println("名次\t\t学号\t\t姓名\t\t年龄\t\t性别\t\t所在班级\t\t语文\t\t数学\t\t英语\t\t总分\t\t平均分");
        try {
            //取出对应信息
            List<Object> list = ServiceFacory.getIStu_gradesServiceInstance().AvgRank();
            Iterator<Object> iter = list.iterator();
            int top = 1;
            //输出对应信息
            while (iter.hasNext()) {
                Stu_info info = null;
                Stu_grades grades = null;
                info = (Stu_info) iter.next();
                grades = (Stu_grades) iter.next();
                System.out.println("第" + top + "名" + "\t" + info.getStu_id() + "\t" + info.getStu_name() + "\t\t" + info.getAge() + "\t\t" + info.getSex() + "\t\t" + info.getStu_class() + "\t" + grades.getChinese() + "\t" + grades.getMath() + "\t" + grades.getEnglish() + "\t\t" + grades.getCount() + "\t" + grades.getAvg());
                top++;
            }
            System.out.println("\n按任意键返回主菜单....");
            in = new Scanner(System.in);
            in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}