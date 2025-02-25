/*
 * 1:1 server

 * 
 * server에서키보드 입력받은 데이타를 client에게 전송
 * 
 * pg 통신 순서
 * client 접속한다
 * server ---- 접속 메시지 -----> client
 * server <------client msg -------- client
 * server -----------server msg -------->client
 * 종료메시지 ("quit")받으면 종료 --대소문자 구분 하지 않는다.
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class Client {

	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader input = null;
		BufferedWriter output = null;
		int port = 5000;
		Scanner scan = new Scanner(System.in);
		try {
					
		//1. 소켓을 만들어서 서버에 접속한다.
		String host = "localhost";
		socket = new Socket(host,port);
		System.out.println("wellcome IP Server");
		//2. 서버와 통신
		//2.1 통신을 위한 input/outputStream 생성
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		while(true) {
		//2.2(2.4) server ---- 접속메시지 ----> client
		String readMsg = scan.nextLine();
		System.out.println("readMsg : "+readMsg);
		
		//2.5 입력받은 데이타가 quit이면 종료

		//2.3 server <------client msg -------- client
		//2.3.1 키보드로 데이타 입력받기
		String sendMsg = scan.nextLine();
		System.out.println("sendMsg : "+sendMsg);
		
		//2.3.2 입력받은 데이타 전송하기
		output.write(sendMsg+"\n");
		output.flush();
		//2.3.3 보내는 메시지가 quit이면 종료
		if(sendMsg.equalsIgnoreCase("quit")) {
			break;
		}
		
	}
		
		//3 접속 종료
		socket.close();
		System.out.println("bye");
		}catch(IOException e) {
			e.getMessage();
		}finally {
			try {
				socket.close();
			}catch(IOException e) {
				e.getStackTrace();
			}
		}
	}

}
