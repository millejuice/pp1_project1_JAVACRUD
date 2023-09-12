package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    ArrayList<Word> list;
    Scanner s;
    final String fileName="Dictionary.txt";
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

    public void addItem(){
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

//keywordㅇㅔ 있는 것만 수정하려고 하기 때문에 public void에서 arrayList로 변경
    public ArrayList<Integer> listAll(String keyword){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("--------------------------------");
        for(int i=0; i< list.size();i++){
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1)+ " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("---------------------------------");
        return idlist;
    }

    public void listAll(int level){
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("--------------------------------");
        for(int i=0; i< list.size();i++){
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1)+ " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("---------------------------------");
    }

    public void updateItem() {
        System.out.print("=>수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=>수정할 번호 검색 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=>뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id - 1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");
    }

    public void deleteItem() {
        System.out.print("=>삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=>삭제할 번호 검색 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("=>정말로 삭제하시겠습니까? ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y")){
            list.remove((int)idlist.get(id - 1)); //Integer객체가 들어가있어서 remove를 못함 -> int로 cast
            System.out.println("단어가 삭제되었습니다. ");
        } else{
            System.out.println("취소되었습니다. ");
        }

        Word word = list.get(idlist.get(id - 1));

    }
    
    public void loadFile() {
        try {//file 읽고 불러오기 위해 bufferedReader사용
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int count = 0;
            //Data 한줄씩 읽어서 word 객체 만들어 list에 추가
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]); //data[0]은 int여서 String을 int로 parsing  해줌
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩완료!!!");
        }
     catch (IOException e) {
         e.printStackTrace(); //error 발생하면 디버깅
     }
    }

    public void saveFile() {
        //PrintWriter - 파일 입출력에 좋은것
        try {
            PrintWriter pr = new PrintWriter(new FileWriter("test.txt")); //test.txt파일 열어서 편집
            for(Word one: list){
                pr.write(one.toFileString() + "\n"); //enter를 입력하지 않기 때문에 직접 입력해줌
            }
            pr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.println("=>원하는 레벨은? (1~3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.println("=>내가 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }
}
