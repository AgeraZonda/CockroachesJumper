package cockroach;

//class tạo sự kiện bàn phím 

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	private boolean isSpace = false;
	private int isPress = 0;
	private double time = 0;
	Player p;

	public KeyBoard() {
		keyPressedTime = new double[KeyEvent.KEY_LAST];
	}

	private double[] keyPressedTime;

	private double newTime = 0;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (isPress == 0) {
			time = 0;
			newTime = System.currentTimeMillis();
			isPress = 1;
			if (isSpace == false)
				p.setType(1);		// tạo hiệu ứng quả bóng bị nén xuống

		} // newtime chứa thông tin thời gian lúc ấn cách
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressedTime[e.getKeyCode()] = System.currentTimeMillis(); // keypresstime chứa thông tin thời gian lúc thả
																		// nút cách
		isPress = 0; // is press để chứa thông tin người chơi đã ấn cách hay chưa
		time = keyPressedTime[e.getKeyCode()] - newTime; // time chứa thông tin khoảng thời gian giữa ấn và thả cách
		if (time >= 27 * 40)
			time = 27 * 40;
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE && Game.stage == 2 && isSpace == false) { // khi key == code của nút space thì gán
																				// tốc độ Y cho nhân vật
			p.setVelY(-(time / 30));
			isSpace = true;
		}

	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void setIsSpace(boolean isSpace) {
		this.isSpace = isSpace;
	}

	public boolean getIsSpace() {
		return isSpace;
	}

	public int getIspress() {
		return isPress;
	}

	public double getTime() {
		return time;
	}

}