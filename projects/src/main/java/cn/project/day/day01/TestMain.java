package cn.project.day.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestMain {
	//涛哥:杨哥,远哥,帅帅,叮叮,星哥,小鹏鹏,娜姐
	public static void main(String[] args) {
		ComboPooledDataSource source = new ComboPooledDataSource();
		QueryRunner run = new QueryRunner(source);
		String sql = "insert into uidcount values(?,?)";
		
		Map<String, Integer> map = getMap();
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<>();
		for(Entry<String, Integer> en:entrySet){
			list.add(en);
			
		}
		Collections.sort(list, new Comparator<Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				
				return o2.getValue()-o1.getValue();
			}
			
		});
		//System.out.println(list.toString());
		try (
				BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\学习资料\\ziliao\\day01\\save.txt"));
				){
			for(Entry<String, Integer> entry:list){
				bw.write(entry.getKey()+":count="+ entry.getValue());
				bw.newLine();
				bw.flush();
				run.update(sql, entry.getKey(),entry.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static Map<String, Integer> getMap() {
		Map<String, Integer> map = new HashMap<>();
		try (
				BufferedReader br = new BufferedReader(new FileReader("C:\\学习资料\\ziliao\\day01\\friends.txt"));	
				){
			String str;
			while((str=br.readLine())!=null){
				String[] arr = str.split(":");
				String[] arr2 = arr[1].split(",");
				map.put(arr[0], arr2.length);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
