package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.raven.main.BeginWindow;

import javazoom.jl.player.Player;

public class PlayMusic implements Runnable{
	private String filename;
	public PlayMusic() {
		
	}
	public PlayMusic(String filename) {
		this.filename = filename;
	}
	@Override
	public void run() {
		
		BeginWindow.bofang = true;
		
		try {
			InputStream in = GameRoomUtil.class.getResourceAsStream(filename);
			
			Player play = new Player(in);
			play.play();
			BeginWindow.bofang = false;
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
