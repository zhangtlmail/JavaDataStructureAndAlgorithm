package com.pz.sparsearray;

public class SparseArray {

	public static void main(String[] args) {
		// 创建一个二位数组11*11
		// 0：表示没有棋子，1表示黑子，2表示蓝子。
		int chessArr[][] = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[6][6] = 6;
		System.out.println("原始数据输出...");//打印的是红色字体
		for (int[] row : chessArr) {
			for (int data : row) {
				System.err.printf("%d\t", data);
			}
			System.err.println();
		}
		System.out.println("转换为稀疏数组...");
		int sum = 0;// 数组的高度
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
		//为数组赋值
		sparseArr[0][0]=11;
		sparseArr[0][1]=11;
		sparseArr[0][2]=sum;
		for (int x = 0; x < 11; x++) {
			for (int y = 0; y < 11; y++) {
				if(chessArr[x][y]!=0) {
					height++;
					sparseArr[height][0] = x;
					sparseArr[height][1] = y;
					sparseArr[height][2] = chessArr[x][y];
				}
			}
		}
		//遍历输出二维数组
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}
		//开始恢复数据map.data持久化io
		System.out.println("开始恢复数据...");
		int chessArr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];
		for (int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
		}
		for (int[] row : chessArr2) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
	}

}
