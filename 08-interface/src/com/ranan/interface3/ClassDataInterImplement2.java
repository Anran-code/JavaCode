package com.ranan.interface3;

public class ClassDataInterImplement2 implements ClassDataInter{
    private Student[] students;

    public ClassDataInterImplement2(Student[] students) {
        this.students = students;
    }

    @Override
    public void printAllStudentInfo() {
        int man_num = 0;
        int woman_num = 0;
        for(int i = 0;i < students.length;++i){
            System.out.println(students[i].getName() + ' ' +
                    students[i].getSex() + ' ' +
                    students[i].getScore() + ' '
            );
            if(students[i].getSex() == '男') man_num++;
            else woman_num++;

        }
        System.out.println("男生人数：" + man_num);
        System.out.println("女生人数：" + woman_num);
    }

    @Override
    public void printAverageScore() {
        double average = 0;
        double max = students[0].getScore();
        double min = max;
        for(int i = 0;i < students.length;++i){
            average += students[i].getScore();
            min = Math.min(students[i].getScore(), min);
            max = Math.max(students[i].getScore(), max);
        }
        average -= min;
        average -= max;
        average = average / (students.length - 2);
        System.out.printf("average score: %.2f\n",average);
    }
}
