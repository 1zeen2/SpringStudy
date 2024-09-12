package com.sist.chat;



import java.util.*;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;

@ServerEndpoint("/chat/chat-ws")
public class ChatServer {
	// 사용자 저장 공간
	// synchronizedList : 동기화 => 접속 시 마다 Session 생성, 각자의 번호를 부여.
	private static List<Session> users = Collections.synchronizedList(new ArrayList<Session>());
	// 1. 접속 시에 처리
	@OnOpen
	public void onOpen(Session session) {
		// 접속자 저장 => 각자의 session 번호 부여
		users.add(session);
		System.out.println("클라이언트 접속자 : " + session.getId());
	}
	
	// 2. 메시지 전송 시 처리
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.println(session.getId() + "님의 메시지 : " + message);
		for (Session s : users) {
			s.getBasicRemote().sendText(message);
		}
	}
	
	// 3. 퇴장 시 처리
	@OnClose
	public void onClose(Session session) {
		// 퇴장한 유저의 session 번호 삭제.
		users.remove(session);
		System.out.println("클라이언트 퇴장 : " + session.getId());
	}
	

}
