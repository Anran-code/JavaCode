package com.ranan.interface3;

public class ClassDataInterImplement1 implements ClassDataInter{
    private Student[] students;

    public ClassDataInterImplement1(Student[] students) {
        this.students = students;
    }

    @Override
    public void printAllStudentInfo() {
        for(int i = 0;i < students.length;++i){
            System.out.println(students[i].getName() + ' ' +
                            students[i].getSex() + ' ' +
                            students[i].getScore() + ' '
                    );
        }
    }

    @Override
    public void printAverageScore() {
        double average = 0;
        for(int i = 0;i < students.length;++i){
            average += students[i].getScore();
        }
        average = average / students.length;
        System.out.printf("average score: %.2f\n",average);
    }
}
