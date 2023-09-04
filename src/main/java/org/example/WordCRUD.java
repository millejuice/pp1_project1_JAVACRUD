package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    /** arrayList 생성자 만들 때 객체화 */
    WordCRUD(Scanner s){
        list = new ArrayList<>();
        this.s = s; //사용자가 입력한 것을 받아온다
    }

    @Override
    public Object add() {
        System.out.print("=>난이도(1,2,3) & 새 단어 입력: ");
        int level = s.nextInt();
        String word = s.nextLine(); //nextLine으로 해야 엔터가 다음 입력버퍼로 안넘어간다

        System.out.print("뜻 입력: ");
        String meaning = s.nextLine();
        //Word.java에서 사용할 객체여서 Word return
        return new Word(0,level,word,meaning);
    }

    public void addWord(){
        Word one = (Word) add(); // add의 return type이 Object이기 때문에 Word로 cast
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다 !!! ");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void seleectOne(int id) {

    }

    public void listAll(){
        System.out.println("--------------------------------");
        for(int i=0; i< list.size();i++){
            System.out.print((i+1)+ " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("---------------------------------");
    }
}
