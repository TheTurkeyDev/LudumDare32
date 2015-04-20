package com.turkey.ld32.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.entity.Entity;

public class Weapon
{
	protected int damage;
	protected Entity holder;
	
	protected Texture icon;
	protected Texture texture;
	
	public boolean owned = false;
	
	public Weapon(Entity ent, int damage)
	{
		this.holder = ent;
		this.damage = damage;
	}
	
	public void render()
	{
		
	}
	
	public void onActivate()
	{
		
	}
	
	public Texture getIcon()
	{
		return icon;
	}
}
