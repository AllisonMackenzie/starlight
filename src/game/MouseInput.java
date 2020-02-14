package game;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import framework.GameObject;

@SuppressWarnings("unused")
public class MouseInput extends MouseAdapter
{
	private Handler handler;
	private int mouseX = 0;
	private int mouseY = 0; 
	private boolean mouseDragged = false;
	//private Camera cam;

	
	public MouseInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		int button = e.getButton();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);			
		}
		
		e.consume();
	}
	
	public void mouseDragged(MouseEvent e)
	{
		mouseDragged = true;
		mouseX = e.getX();
		mouseY = e.getY();
		
		e.consume();
	}
	public void mouseEntered(MouseEvent e)
	{
		e.consume();
	}
	
	public void mouseExited(MouseEvent e)
	{
		e.consume();
	}
	
	public void mouseMoved(MouseEvent e)
	{
		mouseDragged = false;
		mouseX = e.getX();
		mouseY = e.getY();
		
		e.consume();
	}
	public void mousePressed(MouseEvent e)
	{
		e.consume();
	}
	public void mouseReleased(MouseEvent e)
	{
		e.consume();
	}
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		e.consume();
	}
	
	public Point getMousePos(MouseEvent e)
	{
		
		return e.getPoint();
	}
	
	public int getXPos()
	{
		
		return mouseX;
	}
	
	public int getYPos()
	{
		return mouseY;
	}
}
