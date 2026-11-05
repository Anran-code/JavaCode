package com.ranan.innerclass;

public class People {
    private int heartBeat = 100;

    public class Heart {
        private int heartBeat = 200;

        public void show(){
            int heartBeat = 300;
            // 100
            System.out.println(People.this.heartBeat);
            // 200
            System.out.println(this.heartBeat);
            // 300
            System.out.println(heartBeat);
        }
    }
}
