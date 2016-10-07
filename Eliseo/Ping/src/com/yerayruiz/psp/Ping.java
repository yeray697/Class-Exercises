package com.yerayruiz.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {
	public static void main(String[] args) {
		Process proceso = null;
		BufferedReader in = null;
		try {
			proceso = new ProcessBuilder("ping", args[0]).start();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			in = new BufferedReader(
					new InputStreamReader(proceso.getInputStream(), "UTF-8"));
			for (int i = 0; i < 5; i++) {
				System.out.println(in.readLine());
			}
			if (proceso != null) {
				proceso.destroy();
			}
		} catch (IOException e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}
}
