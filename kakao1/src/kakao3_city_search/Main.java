package kakao3_city_search;

import java.util.HashMap;
import java.util.Map;

public class Main {
/*
 입력 형식
캐시 크기(cacheSize)와 도시이름 배열(cities)을 입력받는다.
cacheSize는 정수이며, 범위는 0 ≦ cacheSize ≦ 30 이다.
cities는 도시 이름으로 이뤄진 문자열 배열로, 최대 도시 수는 100,000개이다.
각 도시 이름은 공백, 숫자, 특수문자 등이 없는 영문자로 구성되며, 대소문자 구분을 하지 않는다.(중요 NewYork와 newyork는 같다) 도시 이름은 최대 20자로 이루어져 있다.
출력 형식
입력된 도시이름 배열을 순서대로 처리할 때, "총 실행시간"을 출력한다.
조건
캐시 교체 알고리즘은 LRU(Least Recently Used)를 사용한다.
cache hit일 경우 실행시간은 1이다.
cache miss일 경우 실행시간은 5이다.

캐시크기(cacheSize)	도시이름(cities)	실행시간
	
 */
	public static void main(String[] args) {
		LRUCache(3,	new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});	
		LRUCache(3,	new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});	
		LRUCache(2,	new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});	
		LRUCache(5,	new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});	
		LRUCache(2,	new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});	
		LRUCache(0,	new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"});		
	}
	
	public static void LRUCache(int cacheSize, String[] cities) {
		int runningTime = 0;
		Map<String, Integer> map = new HashMap<String, Integer>();
		//도시 배열 처리
		for(int i =0; i<cities.length;i++) {
			String target = cities[i]; //도시값을 꺼내옴
			//1. cache에 도시가 있는지 찾는다.
			//HashMap에 containsKey 메소드에 키값을 넘겨주게 되면 해당 키값이 HashMap에 있을 경우 true, 없으면 false줌
			if(map.containsKey(target.toUpperCase())) {//대소문자를 구분하지 않기 때문에 대문자로 꺼냄
				//1.1 값이 있으면 cache hit 1
				runningTime++;
				//여기도 캐시배열의 순서를 바꿔주는 로직이 필요함, 이때는 찾은 경우라 캐시의 값을 없애면 안되고 순서를 바꿔줘야함 선택정렬 필요//(생략됨)
			}else {
				//1.2 값이 없으면 cache miss 5
				runningTime+=5;
				//1.2.1 cacheSize보다 입력된 도시가 적으면 put
				if(cacheSize>map.size()) {
					map.put(target.toUpperCase(), map.size()+1);
				}
				//1.2.2 cacheSize보다 입력된 도시가 같거나 많으면 remove & put
				else {//맨 앞에꺼 하나 없애고 새로 map에 넣음
				//향상된 포문 사용
				String delKey="";
				//Map의 값을 전체 출력하기 위해서는 entrySet(), keySet()을 사용하는데 entrySet()은 key와 value가 다 필요
				//keySet() 메서드는 key의 값만 필요
				 for(String key: map.keySet()) {//keySet()은 key를 다 가져옴
					 if(map.get(key)==1) {//get(key)로 value값을 받음
						 //1.2.2.1제거 //젤 오래된 캐시값이 찾은 값인 경우
						 delKey=key;
					 }else {
						 //1.2.2.2 각순위-1
						 map.put(key, map.get(key)-1);//map.get(key)가 제네릭으로 숫자이기 때문에 캐스팅 필요없음
					 }
				 }
				 //1.2.2.3 remove & put
				 map.remove(delKey);
				 map.put(target.toUpperCase(), map.size()+1);
			}
		}
		}
		System.out.println("Running Time :: "+runningTime);
	}
}
