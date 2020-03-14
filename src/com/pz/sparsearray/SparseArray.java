package com.pz.sparsearray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SparseArray {

	// 持久化数组
	@Test
	public void persistentArray() {
		// 创建一个二位数组11*11
		// 0：表示没有棋子，1表示黑子，2表示蓝子。
		int chessArr[][] = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[6][6] = 6;
		System.out.println("原始数据输出...");// 打印的是红色字体
		for (int[] row : chessArr) {
			for (int data : row) {
				System.err.printf("%d\t", data);
			}
			System.err.println();
		}
		System.out.println("转换为稀疏数组...");
		int sum = 0;// 数组的有效数
		for (int[] row : chessArr) {
			for (int data : row) {
				if (data != 0) {
					sum++;
				}
			}
		}
		System.out.println("棋盘中的有效数据有" + sum + "个");
		int height = 0;
		// 创建稀疏数组
		int sparseArr[][] = new int[sum + 1][3];
		// 为数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		for (int x = 0; x < 11; x++) {
			for (int y = 0; y < 11; y++) {
				if (chessArr[x][y] != 0) {
					height++;
					sparseArr[height][0] = x;
					sparseArr[height][1] = y;
					sparseArr[height][2] = chessArr[x][y];
				}
			}
		}
		// 遍历输出二维数组
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		// 开始持久化，断电处理。
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			// 创建文件保存数据，从API中可以了解到FileOutputStream的构造函数有这个，true代表追加，
			// fos = new FileOutputStream("d.txt", true);
			fos = new FileOutputStream("xishu.txt");
			// 文件的序列化
			oos = new ObjectOutputStream(fos);
			// writeObject 方法用于将对象写入流中。所有对象（包括 String 和数组）都//可以通过 writeObject 写入。
			oos.writeObject(sparseArr);
			System.out.println("持久化写入。。。");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void readPersistentArray() {
		// 开始恢复数据map.data持久化io
		int sparseArr[][] = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream("xishu.txt");
			ois = new ObjectInputStream(fis);
			sparseArr = (int[][]) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("开始恢复数据...");
		for (int i = 0; i < sparseArr.length; i++) {
			System.err.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		for (int[] row : chessArr2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}

	//无持久化处理（完整）
	public static void main(String[] args) {
		// 创建一个二位数组11*11
		// 0：表示没有棋子，1表示黑子，2表示蓝子。
		int chessArr[][] = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[6][6] = 6;
		System.out.println("原始数据输出...");// 打印的是红色字体
		for (int[] row : chessArr) {
			for (int data : row) {
				System.err.printf("%d\t", data);
			}
			System.err.println();
		}
		System.out.println("转换为稀疏数组...");
		int sum = 0;// 数组的有效数
		for (int[] row : chessArr) {
			for (int data : row) {
				if (data != 0) {
					sum++;
				}
			}
		}
		System.out.println("棋盘中的有效数据有" + sum + "个");
		int height = 0;
		// 创建稀疏数组
		int sparseArr[][] = new int[sum + 1][3];
		// 为数组赋值
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		for (int x = 0; x < 11; x++) {
			for (int y = 0; y < 11; y++) {
				if (chessArr[x][y] != 0) {
					height++;
					sparseArr[height][0] = x;
					sparseArr[height][1] = y;
					sparseArr[height][2] = chessArr[x][y];
				}
			}
		}
		// 遍历输出二维数组
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		// 开始恢复数据map.data持久化io
		System.out.println("开始恢复数据...");
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		for (int[] row : chessArr2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}

}
