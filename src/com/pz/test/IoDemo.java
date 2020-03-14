package com.pz.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IoDemo {
	
	@org.junit.jupiter.api.Test
	public void test05(){
		// 创建一个文件
		File file = new File("hello.txt");
		// 文件输出流
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(file);
			String str = "你好";
			// 缓存流
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			fos.write(str.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@org.junit.Test
	public void test06(){ // 文件的序列化
		// 创建一个map集合
		Map<Integer, String> maps = new HashMap<Integer, String>();
		// 向集合中添加数据
		maps.put(1, "a");
		maps.put(2, "b");
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try {
			// 创建文件保存数据，从API中可以了解到FileOutputStream的构造函数有这个，true代表追加，
			//fos = new FileOutputStream("d.txt", true);
			fos = new FileOutputStream("hello.txt");
			// 文件的序列化
			oos = new ObjectOutputStream(fos);
			// writeObject 方法用于将对象写入流中。所有对象（包括 String 和数组）都//可以通过 writeObject 写入。
			oos.writeObject(maps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(oos!=null) {
					oos.close();
				}
				if(fos!=null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@org.junit.Test
	public void tesr07(){
		Map maps=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try {
			fis = new FileInputStream("hello.txt");
			ois = new ObjectInputStream(fis);
			maps = (HashMap) ois.readObject();
			// readObject 方法用于从流读取对象。应该使用 Java 的安全强制转换来获取所需的类型。
			// 在 Java 中，字符串和数组都是对象，所以在序列化期间将其视为对象。读取时，需要将其强制转换为期望的类型。
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ois!=null) {
					ois.close();
				}
				if(fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// map集合的遍历，有三种方式，此为其中一种
		Set<Map.Entry<Integer, String>> sets = maps.entrySet();
		for (Map.Entry<Integer, String> s : sets) {
			System.out.println("key=" + s.getKey() + "value=" + s.getValue());
		}
		// readObject 方法用于从流中读取对象。应该使用 Java 的安全强制转换来获取所需的类型。
		// 在 Java 中，字符串和数组都是对象，所以在序列化期间将其视为对象。读取时，需要将其强制转换为期望的类型。
		// readObject 方法用于从流中读取对象。在 Java
		// 中，字符串数组和集合都是对象，所以在序列化期间将其视为对象。读取时，需要将其强制转换为期望的类型。
	}

}
