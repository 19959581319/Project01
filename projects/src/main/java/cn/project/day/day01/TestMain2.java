package cn.project.day.day01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestMain2 {

	public static void main(String[] args) {
		Map<String, List<String>> map = getMap();
		Set<String> keySet = map.keySet();
		List<String> list = new ArrayList<>();
		for(String set:keySet){
			list.add(set);
		}
		//System.out.println(list.toString());
		//System.out.println(map.toString());
		/*for(int i =0;i<keySet.size()-1;i++){
			List<String> list2 = map.get(list.get(i));
			for(int j = i+1;j<keySet.size();j++){
				List<String> list3 = map.get(list.get(j));
				ArrayList<String> list4 = new ArrayList<>(list3);
				list4.retainAll(list2);
				String s = list.get(i)+"和"+list.get(j)+"的共同好友为:"+list3.toString()*/;
				//System.out.println(s);
				try (
						BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\学习资料\\ziliao\\day01\\comFriends.txt"));	
						){
					for(int i =0;i<keySet.size()-1;i++){
						List<String> list2 = map.get(list.get(i));
						for(int j = i+1;j<keySet.size();j++){
							List<String> list3 = map.get(list.get(j));
							ArrayList<String> list4 = new ArrayList<>(list3);
							list4.retainAll(list2);
							if(list4.size()>0){
							String s = list.get(i)+"和"+list.get(j)+"的共同好友为:"+list4.toString();
					       bw.write(s);
					       bw.newLine();
					       bw.flush();
					        }
					  }
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

	}

	private static Map<String, List<String>> getMap() {
		Map<String, List<String>> map = new HashMap<>();
		
		try (
				BufferedReader br = new BufferedReader(new FileReader("C:\\学习资料\\ziliao\\day01\\friends.txt"));	
				){
			String s;
			while((s=br.readLine())!=null){
				String[] arr = s.split(":");
				String[] arr2 = arr[1].split(",");
				List<String> list = new ArrayList<>();
				for(String str:arr2){
					list.add(str);
				}
				map.put(arr[0], list);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
