package org.example;

import java.util.Scanner;


public class WordManager {
    WordCRUD wordCRUD; //member 생성자 만들어주고
    Scanner scanner = new Scanner(System.in);
    public WordManager() {
        wordCRUD = new WordCRUD(scanner); //scanner가 있어서 scanner parameter 추가
    }


    public int selectMenu(){
        System.out.print("*** 영단어 마스터 ***\n" +
                "********************\n" +
                "1. 모든 단어 보기\n" +
                "2. 수준별 단어 보기\n" +
                "3. 단어 검색\n" +
                "4. 단어 추가\n" +
                "5. 단어 수정\n" +
                "6. 단어 삭제\n" +
                "7. 파일 저장\n" +
                "0. 나가기\n" +
                "********************\n" +
                "=> 원하는 메뉴는? ");
        return scanner.nextInt();
    }

    public void start(){
        while(true){
            int menu = selectMenu();
            if(menu == 0)
                break;
            else if (menu == 1) {
                wordCRUD.listAll();
            } else if (menu == 4) {
               wordCRUD.addWord(); //WordCRUD에 있기 때문에 생성자 만들어 줘야 한다
            }
        }
    }
}