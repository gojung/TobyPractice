package com.toby.practice.example.Chapter3_Template;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    //그냥 쌩으로 더하는 친구
    public Integer calcSum_proto(String filepath) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            Integer result = 0;
            String line = null;

            while ((line = br.readLine()) != null) {
                result += Integer.valueOf(line);
            }

            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /*File Template*/
    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReader(br);   //call callback object
            return ret;
        } catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null){
                try { br.close(); }
                catch (IOException e){ System.out.println(e.getMessage()); }
            }
        }
    }

    public Integer calcSum_file(String filepath) throws IOException{
        BufferedReaderCallback sumCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        String line = null;
                        Integer result = 0;
                        while ((line = br.readLine()) != null) {
                            result += Integer.valueOf(line);
                        }
                        return result;
                    }
                };
        return fileReadTemplate(filepath, sumCallback);
    }

    public Integer calcMul_file(String filepath) throws IOException{
        BufferedReaderCallback sumCallback =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        String line = null;
                        Integer result = 1;
                        while ((line = br.readLine()) != null) {
                            result *= Integer.valueOf(line);    //calcSum과의 유일한 차이점
                        }
                        return result;
                    }
                };
        return fileReadTemplate(filepath, sumCallback);
    }

    /*Line Template*/
    //template 2
    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;
            while ((line = br.readLine()) != null){
                res = callback.doSomethingWithLine(line, res);  //LineCallback에 저장된 값을 가져다가 다음 라인에서 씀
            }
            return res;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            throw e;
        }
        finally {
            if (br != null){
                try { br.close(); }
                catch (IOException e){ System.out.println(e.getMessage()); }
            }
        }
    }

    public Integer calcSum_line(String filepath) throws IOException{
        LineCallback<Integer> sumCallback =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value + Integer.valueOf(line);
                    }
                };
        return lineReadTemplate(filepath, sumCallback, 0);
    }

    public Integer calcMul_line(String filepath) throws IOException{
        LineCallback<Integer> mulCallback =
                new LineCallback<Integer>() {
                    @Override
                    public Integer doSomethingWithLine(String line, Integer value) {
                        return value * Integer.valueOf(line);
                    }
                };
        return lineReadTemplate(filepath, mulCallback, 1);
    }

    public String concatenate(String filepath) throws IOException{
        LineCallback<String> concatenateCallback =
                new LineCallback<String>() {
                    @Override
                    public String doSomethingWithLine(String line, String value) {
                        return value + line;
                    }
                };
        return lineReadTemplate(filepath, concatenateCallback, "");
    }


}
