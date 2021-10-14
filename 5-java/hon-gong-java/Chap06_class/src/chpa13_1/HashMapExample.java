package chpa13_1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample {
	public static void main(String[] args) {
		
		//  Ű�� strign ���� int
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		// �ʿ� 55��� Ű�� �������� ��ü ����
		map.put("������", 55);
		map.put("ȫ�浿", 90);	// 90 �������� 8738���� �ٲ�
		map.put("����", 42);
		map.put("ȫ�浿", 8738);  // ȫ�浿 �ι����� , Ű�� ������ �ߺ�������� ����
		
		System.out.println("�� Entry��: " + map.size());	// �� Entry��: 3
		
		//                               "ȫ�浿" Ű�� ����� ��value�� �ҷ���
		System.out.println("ȫ�浿 " + map.get("ȫ�浿"));		//ȫ�浿 8738
		System.out.println();

		
		
		// ���� ����Ʈ �̱� , keySet �̿�
		Set<String> keySet = map.keySet();
		// key�� �ݺ��ڸ� ��
		Iterator<String> keyIterator = keySet.iterator();
		
		// ������ Ű�� ���� ������ while�� �ݺ�
		while(keyIterator.hasNext()) {
			String key = keyIterator.next();  // key�� �ϳ� ������
			Integer value = map.get(key);		// key �� ����� int ��ü(value)�� ���� �� ����
			System.out.println("\t" + key + ":" + value);		// "\t" �鿩����
		}
		System.out.println();
		
		
		
		// ��ü ���ֱ�
		map.remove("ȫ�浿");
		System.out.println("�� Entry��: " + map.size());	
		
		
		
		// ����Ʈ �̱� , entrySet �̿�
		// mapEntry �� ����� set �÷����� ����
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		// mapEntry�� ���� �ݺ��� ��
		Iterator <Map.Entry<String, Integer>> entryIterator = entrySet.iterator(); 
		
		while(entryIterator.hasNext()) {
			// �ϳ��� mapEntry��ü�� ��
			Map.Entry<String, Integer> entry = entryIterator.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			System.out.println("\t" + key + ":" + value);
		}
		System.out.println();
		
		// ��ü ��ü ����
		map.clear();
		System.out.println("�� Entry��: " + map.size());	
		
	}
}


// ctrl + shift + O  : �ڵ� ����Ʈ