package com.turkey.ld32.weapon;

import com.badlogic.gdx.graphics.Texture;
import com.turkey.ld32.entity.Entity;
import com.turkey.ld32.graphics.Draw2D;

public class WeaponSpoon extends Weapon
{

	private int stage = 0;

	private int angle;

	public boolean isActive = false;

	public WeaponSpoon(Entity ent, int damage)
	{
		super(ent, damage);
		super.icon = new Texture("spoon.png");
		super.texture = new Texture("weaponSpoon.png");
		super.owned = true;
	}

	public void render()
	{	
		if(!isActive)
			return;

		stage+=2;
		if(stage >= 16)
		{
			stage=0;
			this.isActive = false;
			for(Entity entity: super.holder.getRoom().getEntites())
			{
				float xDist = entity.getX()-super.holder.getX();
				float xDist2 = (entity.getX()+entity.getWidth())-super.holder.getX();
				if(Math.abs(xDist)>Math.abs(xDist2))
					xDist=xDist2;
				float yDist = entity.getY()-super.holder.getY();
				float yDist2 = (entity.getY()-entity.getHeight())-super.holder.getY();
				if(Math.abs(yDist)>Math.abs(yDist2))
					yDist=yDist2;

				double angle2 = Math.toDegrees(Math.atan2(yDist,xDist));

				double dist = Math.sqrt(Math.pow(yDist, 2) + Math.pow(xDist, 2));
				
				if(Math.abs(angle2 - this.angle) < 25 && dist < 65)
				{
					entity.damage(this.damage);
					break;
				}
			}
		}

		int x = (int) Math.floor((Math.cos(Math.toRadians(angle))*(stage+20)));
		int y =  (int) Math.floor((int) (Math.sin(Math.toRadians(angle))*(stage+20)));

		Draw2D.drawTextured(super.holder.getX()+x, super.holder.getY()+y, texture, angle-90);
	}

	public void onActivate()
	{
		if(stage == 0)
		{
			isActive = true;
			angle = super.holder.yaw;
		}
	}

	public Texture getIcon()
	{
		return this.icon;
	}
}
